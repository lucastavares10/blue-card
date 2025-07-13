<template>
  <div>
    <h1>Bem-vindo ao seu painel, {{ user?.name }}</h1>
    <p>
      Aqui você pode consultar seus cartões, solicitar recargas e ver histórico.
    </p>
    <!-- Pode colocar cards, gráficos e listagens aqui -->
  </div>
</template>

<script lang="ts" setup>
import { computed } from "vue";
import { useAuthStore } from "@/store/auth";

const auth = useAuthStore();
const user = computed(() => auth.user);
</script>

<style scoped>
h1 {
  margin-bottom: 1rem;
  color: #333;
}
</style>
