import api from "@/services/api";
import { defineStore } from "pinia";
import * as jwtDecode from "jwt-decode";

interface User {
  id: string;
  name: string;
  email: string;
  role: string;
}

interface AuthState {
  user: User | null;
  token: string | null;
}

interface JwtPayload {
  sub: string;
  id: string;
  name: string;
  email: string;
  role: string;
  iat: number;
  exp: number;
}

export const useAuthStore = defineStore("auth", {
  state: (): AuthState => ({
    user: null,
    token: localStorage.getItem("token"),
  }),

  actions: {
    async fetchUserFromToken() {
      if (!this.token) return;

      const decoded = jwtDecode.jwtDecode<JwtPayload>(this.token);

      this.user = {
        id: decoded.id,
        name: decoded.name,
        email: decoded.email,
        role: decoded.role,
      };
    },

    async login(email: string, password: string) {
      const response = await api.post("/auth/login", { email, password });
      this.token = response.data.token;

      if (this.token) localStorage.setItem("token", this.token);

      await this.fetchUserFromToken();
    },

    init() {
      if (this.token) {
        this.fetchUserFromToken();
      }
    },

    logout() {
      this.user = null;
      this.token = null;
      localStorage.removeItem("token");
    },

    isAuthenticated() {
      return !!this.token;
    },
  },
});
