import { createRouter, createWebHistory } from "vue-router";
import HomePage from "../views/HomePage.vue";    
import EmployeePage from "../views/EmployeePage.vue";
import IndustryPage from "../views/IndustryPage.vue";
import RegisterPage from "../views/RegisterPage.vue";
import LoginPage from "../views/LoginPage.vue";

const routes = [
  { path: "/", component: HomePage },
  { path: "/employee", component: EmployeePage },
  { path: "/industry", component: IndustryPage },
  { path: "/register", component: RegisterPage },
  { path: "/login", component: LoginPage },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
