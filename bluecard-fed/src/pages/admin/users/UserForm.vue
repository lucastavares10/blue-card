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
        <button type="submit" class="btn btn-save">
          <i class="fa-solid fa-save"></i> {{ isEdit ? "Atualizar" : "Salvar" }}
        </button>
        <button type="button" class="btn btn-cancel" @click="goBack">
          <i class="fa-solid fa-arrow-left"></i> Cancelar
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import api from "@/services/api";
import { useToast } from "vue-toastification";

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
      await api.put(`/user/${route.params.id}`, form.value);
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
.form-container {
  background: #fff;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  max-width: 450px;
  margin: 0 auto;
}

h1 {
  font-size: 1.6rem;
  margin-bottom: 1.5rem;
  color: #333;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
}

label {
  font-weight: 600;
  margin-bottom: 0.4rem;
  color: #444;
}

input,
select {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 6px;
  transition: border-color 0.3s;
}

input:focus,
select:focus {
  border-color: #42b983;
  outline: none;
}

.actions {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
  transition: background 0.3s;
}

.btn-save {
  background-color: #42b983;
  color: white;
}

.btn-cancel {
  background-color: #e63946;
  color: white;
}

.btn:hover {
  opacity: 0.9;
}
</style>
