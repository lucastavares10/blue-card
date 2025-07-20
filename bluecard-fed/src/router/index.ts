import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { useAuthStore } from "@/store/auth";
import LoginPage from "@/pages/LoginPage.vue";
import BaseLayout from "@/layouts/BaseLayout.vue";
import UserDashboard from "@/pages/user/UserDashboard.vue";
import AdminDashboard from "@/pages/admin/AdminDashboard.vue";

const routes: Array<RouteRecordRaw> = [
  { path: "/", component: LoginPage },
  {
    path: "/user",
    component: BaseLayout,
    meta: { requiresAuth: true, role: "USER" },
    children: [
      {
        path: "dashboard",
        component: UserDashboard,
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
        component: () => import("@/pages/admin/AdminDashboard.vue"),
      },
      {
        path: "users",
        component: () => import("@/pages/admin/users/UserList.vue"),
      },
      {
        path: "users/create",
        component: () => import("@/pages/admin/users/UserForm.vue"),
      },
      {
        path: "users/:id/edit",
        component: () => import("@/pages/admin/users/UserForm.vue"),
        props: true,
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
