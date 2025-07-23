<template>
  <div class="cards-container">
    <div class="header">
      <h1>
        <i class="fa-solid fa-credit-card"></i>
        Cartões
      </h1>

      <BaseButton
        v-if="isClient"
        variant="primary"
        icon="fa-solid fa-plus"
        type="button"
        @click="requestNewCard"
      >
        Solicitar Novo Cartão
      </BaseButton>
    </div>

    <table v-if="cards.length > 0">
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
          <td>{{ formatCardNumber(card.cardNumber) }}</td>
          <td>R$ {{ card.balance.toFixed(2) }}</td>
          <td>
            <span :class="['badge', badgeClass(card.status)]">
              {{ badgeText(card.status) }}
            </span>
          </td>
          <td v-if="isAdmin">
            <BaseButton
              v-if="card.status === 'ACTIVE'"
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

    <p v-else class="empty-msg">Nenhum cartão encontrado.</p>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import api from "@/services/api";
import { useAuthStore } from "@/store/auth";
import { useToast } from "vue-toastification";
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
const isAdmin = computed(() => auth.user?.role === "ADMIN");
const isClient = computed(() => auth.user?.role === "CLIENT");

const badgeClass = (status: string) => {
  switch (status) {
    case "ACTIVE":
      return "badge-active";
    case "BLOCKED":
      return "badge-blocked";
    default:
      return "";
  }
};

const badgeText = (status: string) => {
  switch (status) {
    case "ACTIVE":
      return "Ativo";
    case "BLOCKED":
      return "Bloqueado";
    default:
      return "";
  }
};

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

function formatCardNumber(cardNumber: string) {
  if (!cardNumber) return "";
  return cardNumber.replace(/(\d{4})(?=\d)/g, "$1 ").trim();
}

async function requestNewCard() {
  try {
    await api.post(`/card-request`, {
      ownerId: auth.user?.id,
    });
    toast.success("Solicitação de novo cartão realizada com sucesso");
    fetchCards();
  } catch {
    toast.error("Erro ao solicitar novo cartão");
  }
}

onMounted(fetchCards);
</script>

<style scoped>
@import "@/styles/form.css";
@import "@/styles/table.css";
</style>
