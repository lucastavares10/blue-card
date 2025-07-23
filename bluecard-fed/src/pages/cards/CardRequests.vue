<template>
  <div class="cards-container">
    <div class="header">
      <h1>
        <i class="fa-solid fa-clipboard-list"></i>
        Solicitações de Cartões
      </h1>
    </div>

    <table v-if="requests.length > 0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Motivo</th>
          <th>Status</th>
          <th>Data</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="request in requests" :key="request.id">
          <td>{{ request.id }}</td>
          <td>{{ request.reason }}</td>
          <td>
            <span :class="['badge', badgeClass(request.status)]">
              {{ badgeText(request.status) }}
            </span>
          </td>
          <td>{{ formatDate(request.createdAt) }}</td>
        </tr>
      </tbody>
    </table>

    <p v-else class="empty-msg">Nenhuma solicitação encontrada.</p>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import api from "@/services/api";
import { useAuthStore } from "@/store/auth";
import { useToast } from "vue-toastification";

interface CardRequest {
  id: number;
  reason: string;
  status: "PENDING" | "APPROVED" | "REJECTED";
  createdAt: string;
}

const requests = ref<CardRequest[]>([]);
const auth = useAuthStore();
const toast = useToast();

const badgeClass = (status: string) => {
  switch (status) {
    case "PENDING":
      return "badge-pending";
    case "APPROVED":
      return "badge-active";
    case "REJECTED":
      return "badge-blocked";
    default:
      return "";
  }
};

const badgeText = (status: string) => {
  switch (status) {
    case "PENDING":
      return "Pendente";
    case "APPROVED":
      return "Aprovada";
    case "REJECTED":
      return "Rejeitada";
    default:
      return "";
  }
};

async function fetchRequests() {
  try {
    const { data } = await api.get(`/card-request/${auth.user?.id}`);
    requests.value = data.requestCards;
  } catch {
    toast.error("Erro ao carregar solicitações");
  }
}

function formatDate(date: string) {
  return new Date(date).toLocaleString("pt-BR");
}

onMounted(fetchRequests);
</script>

<style scoped>
@import "@/styles/form.css";
@import "@/styles/table.css";
</style>
