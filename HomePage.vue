<template>
  <div class="container text-center mt-5">
    <h2 class="text-danger mb-4">WELCOME TO VMX SHOP HUB</h2>

    <div class="card shadow p-5 mx-auto" style="max-width: 400px;">
     

      <router-link to="/login" class="btn btn-dark btn-lg w-100 mb-3">
      Login
      </router-link>

      <router-link to="/register" class="btn btn-success btn-lg w-100">
      Register
      </router-link>

    </div>
  </div>
</template>

<script>
export default {
  name: "HomePage",
};
</script>

<style scoped>
.container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>
