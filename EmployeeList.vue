<template>
  <div>
    <h5>Employee List</h5>

    <table class="table table-hover table-striped table-bordered align-middle">
      <thead class="table-primary text-center">
        <tr>
          <th>#</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
          <th>Gender</th>
          <th>DOB</th>
          <th>Salary</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="(emp, index) in employees"
          :key="emp.employeeId"
          :class="emp.employeeId === selectedEmployeeId ? 'table-info' : ''"
        >
          <td class="text-center">{{ index + 1 }}</td>
          <td>{{ emp.firstName }}</td>
          <td>{{ emp.lastName }}</td>
          <td>{{ emp.email }}</td>
          <td>{{ emp.gender }}</td>
          <td>{{ emp.dob }}</td>
          <td>{{ emp.salary }}</td>
          <td>{{ emp.employeeStatus?.statusName || 'N/A' }}</td>

          <td class="text-center">
            <button
              class="btn btn-sm btn-outline-primary me-2"
              @click="$emit('select-employee', emp)"
            >
              <i class="bi bi-pencil-square"></i> Edit
            </button>

            <button
              class="btn btn-sm btn-outline-danger"
              @click="deleteEmployee(emp.employeeId)"
            >
              <i class="bi bi-trash"></i> Delete
            </button>
          </td>
        </tr>

        <tr v-if="employees.length === 0">
          <td colspan="9" class="text-center text-muted">
            No employees found.
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EmployeeList",
  props: {
    employees: Array,
    selectedEmployeeId: Number
  },
  methods: {
    async deleteEmployee(employeeId) {
      if (!confirm("Are you sure you want to delete this employee?")) return;

      try {
        const response = await axios.delete(
          `http://localhost:8080/v1/employee/delete/${employeeId}`
        );

        if (response.status === 200) {
          alert("Employee deleted successfully!");
          this.$emit("employee-deleted"); // ✅ Tell parent to refresh list
        } else {
          throw new Error("Unexpected response");
        }
      } catch (err) {
        console.error("Error deleting employee:", err);
        alert("Failed to delete employee!");
      }
    }
  }
};
</script>

<style scoped>
.table th,
.table td {
  vertical-align: middle;
}
button i {
  margin-right: 4px;
}
</style>
