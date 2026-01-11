<template>
  <div>
    <!-- Employee Form -->
    <div class="mb-3">
      <label class="form-label">First Name</label>
      <input type="text" v-model="employee.firstName" class="form-control" />
    </div>

    <div class="mb-3">
      <label class="form-label">Last Name</label>
      <input type="text" v-model="employee.lastName" class="form-control" />
    </div>

    <div class="mb-3 position-relative">
      <label class="form-label">Email</label>
      <input
        type="email"
        v-model="employee.email"
        class="form-control"
        pattern="[a-zA-Z0-9._%+-]+@gmail\.com$"
        title="Only Gmail addresses are allowed (e.g. example@gmail.com)"
        required
      />
      <small v-if="!isValidGmail" class="text-danger">Please enter a valid Gmail address.</small>
    </div>

    <div class="mb-3 position-relative">
      <label class="form-label">Gender</label>
      <div class="input-group">
        <select v-model="employee.gender" class="form-select">
          <option value="" disabled>Select</option>
          <option>Male</option>
          <option>Female</option>
          <option>Other</option>
        </select>
        
      </div>
    </div>

    <div class="mb-3">
      <label class="form-label">Date of Birth</label>
      <input type="date" v-model="employee.dob" class="form-control" />
    </div>

    <div class="mb-3">
      <label class="form-label">Salary</label>
      <input type="number" v-model="employee.salary" class="form-control" />
    </div>

    <!-- Employee Status Dropdown -->
    <div class="mb-3 position-relative">
      <label class="form-label">Employee Status</label>
      <div class="input-group">
        <select v-model="employee.employeeStatus.id" class="form-select">
          <option value="" disabled>Select Status</option>
          <option v-for="status in employeeStatuses" :key="status.id" :value="status.id">
            {{ status.statusName }}
          </option>
        </select>
        
      </div>
    </div>

    <!-- Buttons -->
    <div class="d-flex justify-content-end">
      <button class="btn btn-secondary me-2" @click="cancelEdit">Cancel</button>
      <button class="btn btn-primary" @click="updateEmployee" :disabled="!isValidGmail">Update</button>
    </div>

    <!-- Message -->
    <div v-if="message" class="mt-3 alert" :class="messageType">{{ message }}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EditEmployee",
  props: {
    employeeData: Object
  },
  data() {
    return {
      employee: {},
      employeeStatuses: [],
      message: "",
      messageType: ""
    };
  },
  computed: {
    isValidGmail() {
      return /^[a-zA-Z0-9._%+-]+@gmail\.com$/.test(this.employee.email || "");
    }
  },
  watch: {
    employeeData: {
      immediate: true,
      handler(newVal) {
        if (newVal) {
          this.employee = {
            ...newVal,
            employeeStatus: newVal.employeeStatus
              ? { ...newVal.employeeStatus }
              : { id: "", statusName: "" }
          };
        }
      }
    }
  },
  mounted() {
    this.fetchEmployeeStatuses();
  },
  methods: {
    async fetchEmployeeStatuses() {
      try {
        const res = await axios.get("http://localhost:8080/v1/employeestatus/list");
        this.employeeStatuses = res.data.employeestatuslist || [];
      } catch (err) {
        console.error("Error fetching employee statuses:", err);
      }
    },

    async updateEmployee() {
      if (!this.employee.firstName || !this.employee.lastName || !this.employee.email) {
        this.message = "Please fill in all required fields!";
        this.messageType = "alert-warning";
        return;
      }
      if (!this.isValidGmail) {
        this.message = "Only Gmail addresses are allowed!";
        this.messageType = "alert-warning";
        return;
      }

      try {
        await axios.put("http://localhost:8080/v1/employee/update", this.employee);
        this.message = "Employee updated successfully!";
        this.messageType = "alert-success";
        this.$emit("employee-updated");
      } catch (err) {
        console.error(err);
        this.message = "Failed to update employee!";
        this.messageType = "alert-danger";
      }
    },

    cancelEdit() {
      this.employee = {};
      this.message = "";
      this.messageType = "";
      this.$emit("cancel-edit");
    }
  }
};
</script>

<style scoped>
.alert {
  padding: 8px 12px;
}

.input-group-text {
  background-color: #f8f9fa;
  border-left: none;
}

select.form-select {
  cursor: pointer;
}
</style>
