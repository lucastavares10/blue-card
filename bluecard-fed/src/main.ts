import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { pinia } from "./store";
import { useAuthStore } from "./store/auth";
import Toast, { PluginOptions } from "vue-toastification";
import "vue-toastification/dist/index.css";
import "@/styles/base.css";
import "@/styles/layout.css";

const app = createApp(App);

app.use(router);
app.use(pinia);

const options: PluginOptions = {};

app.use(Toast, options);

const authStore = useAuthStore();
authStore.init();

app.mount("#app");
