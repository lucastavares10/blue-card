<template>
  <div class="layout">
    <aside class="sidebar">
      <h2>Web RioCard</h2>
      <nav>
        <router-link to="/dashboard">Dashboard</router-link>
        <router-link v-if="isAdmin" to="/admin/users">Usuários</router-link>
        <router-link to="/cards">Cartões</router-link>
        <router-link to="/recharges">Recargas</router-link>
      </nav>
    </aside>

    <div class="main">
      <header>
        <div>Olá, {{ user?.name }}</div>
        <button @click="logout">Sair</button>
      </header>
      <main>
        <router-view />
      </main>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useAuthStore } from "@/store/auth";
import { computed } from "vue";
import { useRouter } from "vue-router";

const auth = useAuthStore();
const router = useRouter();

const user = computed(() => auth.user);
const isAdmin = computed(() => user.value?.role === "ADMIN");

function logout() {
  auth.logout();
  router.push("/");
}
</script>

<style scoped>
.layout {
  display: flex;
  height: 100vh;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.sidebar {
  width: 220px;
  background-color: #42b983;
  color: white;
  padding: 2rem 1rem;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.sidebar h2 {
  margin: 0 0 2rem 0;
  font-weight: 700;
}

.sidebar nav {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.sidebar a {
  color: white;
  text-decoration: none;
  font-weight: 600;
  padding: 0.5rem 1rem;
  border-radius: 6px;
  transition: background-color 0.2s;
}

.sidebar a.router-link-exact-active,
.sidebar a:hover {
  background-color: #36976f;
}

.main {
  flex: 1;
  display: flex;
  flex-direction: column;
}

header {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  background: #f9f9f9;
  padding: 1rem 2rem;
  border-bottom: 1px solid #ddd;
  gap: 1rem;
}

header button {
  cursor: pointer;
  background-color: #e63946;
  border: none;
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 6px;
  font-weight: 600;
  transition: background-color 0.2s;
}

header button:hover {
  background-color: #ba2e3a;
}

main {
  flex: 1;
  padding: 2rem;
  background-color: #f5f5f5;
  overflow-y: auto;
}
</style>
