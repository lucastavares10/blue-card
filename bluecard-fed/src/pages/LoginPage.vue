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
      auth.user?.role === "ADMIN" ? "/admin/dashboard" : "/user/dashboard"
    );
  } catch {
    error.value = "Credenciais inválidas";
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 10vh auto;
  padding: 2rem;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

input {
  padding: 0.75rem 1rem;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.2s, box-shadow 0.2s;
}

input:focus {
  border-color: #42b983; /* verde Vue */
  box-shadow: 0 0 0 3px rgba(66, 185, 131, 0.2);
  outline: none;
}

button {
  padding: 0.75rem 1rem;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: 600;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

button:hover {
  background-color: #36976f;
}

button:disabled {
  background-color: #a0cdbc;
  cursor: not-allowed;
}

p {
  color: #e63946; /* vermelho suave para erros */
  font-size: 0.95rem;
  text-align: center;
}
</style>
