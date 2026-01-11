<template>
  <div class="container mt-5">
    <h3 class="text-center text-primary mb-4">User Login</h3>
    <div class="card p-4 shadow mx-auto" style="max-width: 400px;">

      <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" v-model="loginUsername" class="form-control" />
      </div>

      <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" v-model="loginPassword" class="form-control" />
      </div>

      <button class="btn btn-primary w-100" @click="loginUser">Login</button>

      <div v-if="message" class="alert mt-3" :class="alertClass">{{ message }}</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginPage",
  data() {
    return {
      loginUsername: "",
      loginPassword: "",
      message: "",
      alertClass: ""
    };
  },
  methods: {
    async loginUser() {
      if (!this.loginUsername || !this.loginPassword) {
        this.message = "Please fill all fields!";
        this.alertClass = "alert-danger";
        return;
      }

      try {
        // Fetch all users from backend
        const response = await axios.get("http://localhost:8080/v1/user/list");
        const users = response.data.userlist;

        // Find user with matching username & password
        const user = users.find(
          u => u.username === this.loginUsername && u.password === this.loginPassword
        );

        if (user) {
          localStorage.setItem("user", JSON.stringify(user)); // store logged-in user
          this.message = "Login successful!";
          this.alertClass = "alert-success";
          setTimeout(() => this.$router.push("/employee"), 500); // redirect
        } else {
          this.message = "Invalid credentials!";
          this.alertClass = "alert-danger";
        }
      } catch (error) {
        console.error(error);
        this.message = "Login failed! Try again later.";
        this.alertClass = "alert-danger";
      }
    }
  }
};
</script>
