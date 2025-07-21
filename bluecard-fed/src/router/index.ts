import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { useAuthStore } from "@/store/auth";
import LoginPage from "@/pages/LoginPage.vue";
import BaseLayout from "@/layouts/BaseLayout.vue";

const routes: Array<RouteRecordRaw> = [
  { path: "/", component: LoginPage },
  {
    path: "/client",
    component: BaseLayout,
    meta: { requiresAuth: true, role: "CLIENT" },
    children: [
      {
        path: "dashboard",
        component: () => import("@/pages/dashboards/ClientDashboard.vue"),
      },
    ],
  },
  {
    path: "/admin",
    component: BaseLayout,
    meta: { requiresAuth: true, role: "ADMIN" },
    children: [
      {
        path: "dashboard",
        component: () => import("@/pages/dashboards/AdminDashboard.vue"),
      },
      {
        path: "users",
        component: () => import("@/pages/users/UserList.vue"),
      },
      {
        path: "users/create",
        component: () => import("@/pages/users/UserForm.vue"),
      },
      {
        path: "users/:id/edit",
        component: () => import("@/pages/users/UserForm.vue"),
        props: true,
      },
      {
        path: "cards",
        component: () => import("@/pages/cards/CardsList.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const auth = useAuthStore();

  if (to.meta.requiresAuth && !auth.isAuthenticated()) {
    return next("/");
  }

  if (to.meta.role && auth.user?.role && auth.user.role !== to.meta.role) {
    return next("/");
  }

  next();
});

export default router;
