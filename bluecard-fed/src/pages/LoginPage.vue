<template>
  <form @submit.prevent="submit">
    <input v-model="email" type="email" placeholder="Email" required />
    <input v-model="password" type="password" placeholder="Senha" required />
    <button type="submit" :disabled="loading">Entrar</button>
    <p v-if="error">{{ error }}</p>
  </form>
</template>

<script lang="ts" setup>
import { useAuthStore } from "@/store/auth";
import { ref } from "vue";
import { useRouter } from "vue-router";

const auth = useAuthStore();
const router = useRouter();

const email = ref("freirelts@gmail.com");
const password = ref("lucas@bluecard");
const loading = ref(false);
const error = ref("");

async function submit() {
  loading.value = true;
  error.value = "";
  try {
    await auth.login(email.value, password.value);

    router.push(
      auth.user?.role === "ADMIN" ? "/admin/dashboard" : "/client/dashboard"
    );
  } catch {
    error.value = "Credenciais inválidas";
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
@import "@/styles/login.css";
</style>
