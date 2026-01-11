<template>
  <div class="container mt-5">
    <h3 class="text-center text-primary mb-4">User Registration</h3>
    <div class="card p-4 shadow mx-auto" style="max-width: 400px;">

      <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" v-model="user.username" class="form-control" />
      </div>

      <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" v-model="user.password" class="form-control" />
      </div>

      <div class="mb-3">
        <label class="form-label">Role</label>
        <select v-model="user.role" class="form-select">
          <option value="Employee">Employee</option>
          <option value="Manager">Manager</option>
          <option value="Admin">Admin</option>
        </select>
      </div>

      <button class="btn btn-success w-100" @click="registerUser">Register</button>

      <div v-if="message" class="alert mt-3" :class="alertClass">{{ message }}</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RegisterPage",
  data() {
    return {
      user: { username: "", password: "", role: "Employee" }, // default role
      message: "",
      alertClass: ""
    };
  },
  methods: {
    async registerUser() {
      if (!this.user.username || !this.user.password || !this.user.role) {
        this.message = "Please fill all fields!";
        this.alertClass = "alert-danger";
        return;
      }

      try {
        const response = await axios.post("http://localhost:8080/v1/user/add", this.user, {
          headers: { "Content-Type": "application/json" }
        });

        if (response.status === 201) {
          this.message = "Registration successful!";
          this.alertClass = "alert-success";
          setTimeout(() => this.$router.push("/login"), 1000);
        }
      } catch (error) {
        console.error(error);
        this.message =
          error.response?.data?.message || "Registration failed! User may already exist.";
        this.alertClass = "alert-danger";
      }
    }
  }
};
</script>
