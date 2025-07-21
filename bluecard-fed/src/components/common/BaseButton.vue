<template>
  <button
    :type="type"
    :class="['base-btn', `base-btn--${variant}`]"
    @click="$emit('click')"
  >
    <i v-if="icon" :class="icon"></i>
    <slot />
  </button>
</template>

<script setup lang="ts">
import { defineProps } from "vue";

defineProps<{
  variant?: "primary" | "secondary" | "danger";
  icon?: string;
  type?: "button" | "submit" | "reset";
}>();
</script>

<style scoped>
.base-btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
  display: inline-flex;
  align-items: center;
  gap: 0.4rem;
  transition: background-color 0.2s ease;
}

.base-btn--primary {
  background-color: #42b983;
  color: white;
}

.base-btn--secondary {
  background-color: #2196f3;
  color: white;
}

.base-btn--danger {
  background-color: #e63946;
  color: white;
}

.base-btn:hover {
  opacity: 0.9;
}
</style>
