<template>
  <table class="base-table">
    <thead>
      <tr>
        <th v-for="(col, index) in columns" :key="index">
          {{ col.label }}
        </th>
        <th v-if="$slots.actions">Ações</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="row in data" :key="row.id">
        <td v-for="(col, index) in columns" :key="index">
          {{ row[col.key] }}
        </td>
        <td v-if="$slots.actions">
          <slot name="actions" :row="row"></slot>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup lang="ts">
import { defineProps } from "vue";

interface Column {
  label: string;
  key: string;
}

defineProps<{
  columns: Column[];
  data: any[];
}>();
</script>

<style scoped>
.base-table {
  width: 100%;
  border-collapse: collapse;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  background: #fff;
}

.base-table th,
.base-table td {
  padding: 12px 16px;
  border-bottom: 1px solid #eaeaea;
  text-align: left;
}

.base-table th {
  background-color: #f7f7f7;
  color: #444;
}

.base-table tr:hover {
  background-color: #f9f9f9;
}
</style>
