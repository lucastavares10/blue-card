<template>
  <div class="user-container">
    <h1><i class="fa-solid fa-users"></i> Gerenciar Usuários</h1>

    <BaseButton variant="primary" icon="fa-solid fa-plus" @click="goToCreate">
      Novo Usuário
    </BaseButton>

    <BaseTable :columns="columns" :data="users">
      <template #actions="{ row }">
        <BaseButton
          variant="secondary"
          icon="fa-solid fa-pen"
          @click="editUser(row.id)"
        />
        <BaseButton
          variant="danger"
          icon="fa-solid fa-trash"
          @click="deleteUser(row.id)"
        />
      </template>
    </BaseTable>

    <p v-if="!users.length" class="empty-msg">Nenhum usuário cadastrado.</p>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import api from "@/services/api";
import { useToast } from "vue-toastification";
import BaseTable from "@/components/common/BaseTable.vue";
import BaseButton from "@/components/common/BaseButton.vue";

interface User {
  id: number;
  name: string;
  email: string;
  role: string;
}

const users = ref<User[]>([]);
const router = useRouter();
const toast = useToast();

const columns = [
  { label: "Nome", key: "name" },
  { label: "Email", key: "email" },
  { label: "Perfil", key: "role" },
];

async function fetchUsers() {
  try {
    const { data } = await api.get("/user");
    users.value = data.users;
  } catch {
    toast.error("Erro ao carregar usuários");
  }
}

function goToCreate() {
  router.push("/admin/users/create");
}

function editUser(id: number) {
  router.push(`/admin/users/${id}/edit`);
}

async function deleteUser(id: number) {
  if (confirm("Tem certeza que deseja excluir este usuário?")) {
    try {
      await api.delete(`/user/${id}`);
      toast.success("Usuário excluído com sucesso");
      fetchUsers();
    } catch {
      toast.error("Erro ao excluir usuário");
    }
  }
}

onMounted(fetchUsers);
</script>

<style scoped>
@import "@/styles/form.css";
@import "@/styles/table.css";
</style>
