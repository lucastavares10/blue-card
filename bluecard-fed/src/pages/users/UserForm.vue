<template>
  <div class="form-container">
    <h1>
      <i :class="isEdit ? 'fa-solid fa-pen' : 'fa-solid fa-plus'"></i>
      {{ isEdit ? "Editar Usuário" : "Novo Usuário" }}
    </h1>

    <form @submit.prevent="saveUser">
      <div class="form-group">
        <label>Nome</label>
        <input v-model="form.name" required />
      </div>
      <div class="form-group">
        <label>Email</label>
        <input v-model="form.email" required type="email" />
      </div>
      <div class="form-group">
        <label>Perfil</label>
        <select v-model="form.role">
          <option value="CLIENT">Cliente</option>
          <option value="ADMIN">Administrador</option>
        </select>
      </div>
      <div class="form-group" v-if="!isEdit">
        <label>Senha</label>
        <input v-model="form.password" required type="password" />
      </div>
      <div class="actions">
        <BaseButton type="submit" variant="primary" icon="fa-solid fa-save">
          {{ isEdit ? "Atualizar" : "Salvar" }}
        </BaseButton>

        <BaseButton
          type="button"
          variant="secondary"
          icon="fa-solid fa-arrow-left"
          @click="goBack"
        >
          Cancelar
        </BaseButton>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import api from "@/services/api";
import { useToast } from "vue-toastification";
import BaseButton from "@/components/common/BaseButton.vue";

interface UserForm {
  name: string;
  email: string;
  role: string;
  password?: string;
}

const form = ref<UserForm>({
  name: "Lucas Tavares Freire",
  email: "freirelts@gmail.com",
  role: "CLIENT",
  password: "lucas@bluecard",
});
const route = useRoute();
const router = useRouter();
const toast = useToast();
const isEdit = computed(() => !!route.params.id);

async function fetchUser() {
  if (isEdit.value) {
    try {
      const { data } = await api.get(`/user/${route.params.id}`);
      form.value = data;
    } catch (exception) {
      toast.error("Erro ao carregar usuário");
    }
  }
}

async function saveUser() {
  try {
    if (isEdit.value) {
      await api.patch(`/user/${route.params.id}`, form.value);
      toast.success("Usuário atualizado com sucesso");
    } else {
      await api.post("/user", form.value);
      toast.success("Usuário criado com sucesso");
    }
    router.push("/admin/users");
  } catch (error: any) {
    console.log(error.response.data);

    toast.error("Erro ao salvar usuário");
  }
}

function goBack() {
  router.push("/admin/users");
}

onMounted(fetchUser);
</script>

<style scoped>
@import "@/styles/form.css";
</style>
