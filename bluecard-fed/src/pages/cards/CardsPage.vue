<template>
  <div class="cards-page">
    <div class="tabs-header">
      <button
        :class="['tab-button', activeTab === 'cards' ? 'active' : '']"
        @click="activeTab = 'cards'"
      >
        <i class="fa-solid fa-credit-card"></i> Cartões
      </button>
      <button
        :class="['tab-button', activeTab === 'requests' ? 'active' : '']"
        @click="activeTab = 'requests'"
      >
        <i class="fa-solid fa-clipboard-list"></i> Solicitações
      </button>
    </div>

    <div v-if="activeTab === 'cards'">
      <CardsList />
    </div>

    <div v-else-if="activeTab === 'requests'">
      <CardRequests />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import CardsList from "./CardsList.vue";
import CardRequests from "./CardRequests.vue";

const activeTab = ref<"cards" | "requests">("cards");
</script>

<style scoped>
.cards-page {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.tabs-header {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}

.tab-button {
  padding: 0.5rem 1rem;
  border: none;
  cursor: pointer;
  background-color: #f1f1f1;
  border-radius: 6px;
  font-weight: bold;
  transition: all 0.2s;
}

.tab-button.active {
  background-color: #007bff;
  color: white;
}
</style>
