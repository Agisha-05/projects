<template>
  <h4>Employee Page</h4>

  <div class="container">
    <div class="row">

      <!-- Left Column: Add Employee + Employee List -->
      <div class="col-md-8 col-lg-9">
        <AddEmployee @employee-saved="reloadList" />
        <EmployeeList
          :employees="employees"
          :selectedEmployeeId="selectedEmployee?.employeeId"
          @select-employee="selectEmployee"
          @employee-deleted="reloadList"
        />
      </div> 

      <!-- Right Column: Edit Employee -->
      <div class="col-md-4 col-lg-3">
        <h5>Edit Employee</h5>
        <hr/>
        <EditEmployee
          v-if="selectedEmployee"
          :employeeData="selectedEmployee"
          @employee-updated="reloadList"
          @cancel-edit="selectedEmployee = null"
        />
        <p v-else class="text-muted">Select an employee to edit.</p>
      </div>

    </div> <!-- close row -->
  </div> <!-- close container -->
</template>


<script>
import AddEmployee from "../components/AddEmployee.vue";
import EmployeeList from "../components/EmployeeList.vue";
import EditEmployee from "../components/EditEmployee.vue";
import axios from "axios";

export default {
  name: "EmployeePage",
  components: { AddEmployee, EmployeeList, EditEmployee },
  data() {
    return {
      employees: [],
      selectedEmployee: null
    };
  },
  mounted() {
    this.fetchEmployees();
  },
  methods: {
    async fetchEmployees() {
      try {
        const res = await axios.get("http://localhost:8080/v1/employee/list");
        this.employees = res.data.employeelist || [];
      } catch (err) {
        console.error("Error fetching employees:", err);
      }
    },

    reloadList() {
      this.fetchEmployees();
      this.selectedEmployee = null; // Reset edit form after add/update
    },

    selectEmployee(emp) {
      // Deep clone to avoid direct binding issues
      this.selectedEmployee = JSON.parse(JSON.stringify(emp));
      if (!this.selectedEmployee.employeeStatus) {
        this.selectedEmployee.employeeStatus = { id: "" };
      }
    },

    // 🔹 Handle delete instantly for better UX
    async handleDelete(id) {
      const confirmed = window.confirm("Are you sure you want to delete this employee?");
      if (!confirmed) return;

      // 🔸 Instantly update the UI for faster response
      this.employees = this.employees.filter(emp => emp.employeeId !== id);

      try {
        await axios.delete(`http://localhost:8080/v1/employee/delete/${id}`);
        console.log("Employee deleted successfully");
      } catch (err) {
        console.error("Error deleting employee:", err);
        alert("Failed to delete employee!");
        // Optionally re-fetch to restore in case of error
        this.fetchEmployees();
      }

      // Reset selection if deleted employee was being edited
      if (this.selectedEmployee?.employeeId === id) {
        this.selectedEmployee = null;
      }
    }
  }
};
</script>
