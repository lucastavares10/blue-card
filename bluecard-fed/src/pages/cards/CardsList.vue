<template>
  <div class="cards-container">
    <div class="header">
      <h1>
        <i class="fa-solid fa-credit-card"></i>
        Cartões
      </h1>

      <BaseButton
        v-if="isAdmin"
        variant="primary"
        icon="fa-solid fa-plus"
        @click="goToNewCard"
      >
        Solicitar Novo Cartão
      </BaseButton>
    </div>

    <table>
      <thead>
        <tr>
          <th>Número</th>
          <th>Saldo</th>
          <th>Status</th>
          <th v-if="isAdmin">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="card in cards" :key="card.id">
          <td>{{ card.cardNumber }}</td>
          <td>R$ {{ card.balance.toFixed(2) }}</td>
          <td>
            <span
              :class="[
                'badge',
                card.status === 'BLOCKED' ? 'badge-blocked' : 'badge-active',
              ]"
            >
              {{ card.status === "BLOCKED" ? "Bloqueado" : "Ativo" }}
            </span>
          </td>
          <td v-if="isAdmin">
            <BaseButton
              v-if="card.status === 'BLOCKED'"
              variant="danger"
              icon="fa-solid fa-ban"
              @click="blockCard(card.id)"
            >
              Bloquear
            </BaseButton>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import api from "@/services/api";
import { useAuthStore } from "@/store/auth";
import { useToast } from "vue-toastification";
import { useRouter } from "vue-router";
import BaseButton from "@/components/common/BaseButton.vue";

interface Card {
  id: number;
  cardNumber: string;
  balance: number;
  status: "ACTIVE" | "BLOCKED";
  ownerId: number;
}

const cards = ref<Card[]>([]);
const auth = useAuthStore();
const toast = useToast();
const router = useRouter();
const isAdmin = computed(() => auth.user?.role === "ADMIN");

async function fetchCards() {
  try {
    const { data } = await api.get(`/card/${auth.user?.id}`);
    cards.value = data.cards;
  } catch (error) {
    toast.error("Erro ao carregar cartões");
  }
}

async function blockCard(id: number) {
  try {
    await api.post(`/card/${id}/block`);
    toast.success("Cartão bloqueado com sucesso");
    fetchCards();
  } catch {
    toast.error("Erro ao bloquear cartão");
  }
}

function goToNewCard() {
  return;
}

onMounted(fetchCards);
</script>

<style scoped>
@import "@/styles/form.css";
@import "@/styles/table.css";
</style>
