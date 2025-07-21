<template>
  <div class="layout">
    <aside class="sidebar">
      <h2>Web BlueCard</h2>
      <nav>
        <router-link :to="dashboardLink">Dashboard</router-link>
        <router-link v-if="isAdmin" to="/admin/users">Usuários</router-link>
        <router-link to="/admin/cards">Cartões</router-link>
        <router-link to="/admin/recharges">Recargas</router-link>
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
const dashboardLink = computed(() => {
  return isAdmin.value ? "/admin/dashboard" : "/client/dashboard";
});

function logout() {
  auth.logout();
  router.push("/");
}
</script>
