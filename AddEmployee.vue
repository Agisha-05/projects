<template>
  <div class="row">
    
      <div class="accordion mb-3" id="accordionExample">
        <div class="accordion-item shadow-sm">
          <h2 class="accordion-header">
            <button
              class="accordion-button collapsed fw-bold"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#collapseOne"
              aria-expanded="false"
              aria-controls="collapseOne"
            >
              ➕ Add Employee
            </button>
          </h2>

          <div
            id="collapseOne"
            class="accordion-collapse collapse"
            data-bs-parent="#accordionExample"
          >
            <div class="accordion-body bg-light rounded">
              <!-- Employee Form -->
              <div class="mb-3">
                <label class="form-label fw-semibold">First Name</label>
                <input
                  type="text"
                  v-model="employee.firstName"
                  class="form-control"
                  placeholder="Enter first name"
                />
              </div>

              <div class="mb-3">
                <label class="form-label fw-semibold">Last Name</label>
                <input
                  type="text"
                  v-model="employee.lastName"
                  class="form-control"
                  placeholder="Enter last name"
                />
              </div>

              <div class="mb-3">
                <label class="form-label fw-semibold">Email</label>
                <input
                  type="email"
                  v-model="employee.email"
                  class="form-control"
                  required
                  placeholder="example@gmail.com"
                  @blur="validateEmail"
                />
                <small v-if="emailError" class="text-danger">{{ emailError }}</small>
              </div>

              <div class="mb-3 position-relative">
                <label class="form-label fw-semibold">Gender</label>
                <div class="input-group">
                  <select v-model="employee.gender" class="form-select">
                    <option value="" disabled>Select Gender</option>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                  </select>
                  
                </div>
              </div>

              <div class="mb-3">
                <label class="form-label fw-semibold">Date of Birth</label>
                <input type="date" v-model="employee.dob" class="form-control" />
              </div>

              <div class="mb-3">
                <label class="form-label fw-semibold">Salary</label>
                <input
                  type="number"
                  v-model="employee.salary"
                  class="form-control"
                  placeholder="Enter salary"
                />
              </div>

              <div class="mb-3 position-relative">
                <label class="form-label fw-semibold">Employee Status</label>
                <div class="input-group">
                  <select v-model="employee.employeeStatus.id" class="form-select">
                    <option value="" disabled>Select Status</option>
                    <option
                      v-for="status in employeeStatuses"
                      :key="status.id"
                      :value="status.id"
                    >
                      {{ status.statusName }}
                    </option>
                  </select>
                  
                </div>
              </div>

              <!-- Buttons -->
              <div class="d-flex gap-2 mt-4">
                <button class="btn btn-primary" @click="saveEmployee">
                  <i class="bi bi-save"></i> Save
                </button>
                <button class="btn btn-secondary" @click="resetForm">
                  <i class="bi bi-x-circle"></i> Cancel
                </button>
              </div>

              <!-- Message -->
              <div v-if="message" class="mt-3 alert" :class="messageType" role="alert">
                {{ message }}
              </div>
            </div>
          </div>
        </div>
      </div>
    
  </div>
</template>

<script>
import axios from "axios";
import "bootstrap-icons/font/bootstrap-icons.css";

export default {
  name: "AddEmployee",
  data() {
    return {
      employee: {
        firstName: "",
        lastName: "",
        email: "",
        gender: "",
        dob: "",
        salary: "",
        employeeStatus: { id: "" },
      },
      employeeStatuses: [],
      message: "",
      messageType: "",
      emailError: "",
    };
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

    validateEmail() {
      const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
      if (!gmailRegex.test(this.employee.email)) {
        this.emailError = "Only Gmail addresses are allowed!";
      } else {
        this.emailError = "";
      }
    },

    async saveEmployee() {
      // Validation
      if (!this.employee.firstName || !this.employee.email || !this.employee.employeeStatus.id) {
        this.message = "Please fill all required fields!";
        this.messageType = "alert-warning";
        return;
      }

      // Prevent submission if invalid email
      if (this.emailError) {
        this.message = "Invalid email format!";
        this.messageType = "alert-danger";
        return;
      }

      try {
        const response = await axios.post("http://localhost:8080/v1/employee/add", this.employee);

        // Only show success if actual data returned
        if (response.status === 200 || response.status === 201) {
          this.$emit("employee-saved");
          this.resetForm();
          this.message = "✅ Employee added successfully!";
          this.messageType = "alert-success";

          // Collapse accordion smoothly
          const collapse = document.getElementById("collapseOne");
          if (collapse && window.bootstrap) {
            const bsCollapse =
              window.bootstrap.Collapse.getInstance(collapse) ||
              new window.bootstrap.Collapse(collapse);
            bsCollapse.hide();
          }
        } else {
          throw new Error(`Unexpected status: ${response.status}`);
        }
      } catch (err) {
        console.error("Error saving employee:", err.response?.data || err);
        this.message = "❌ Failed to save employee!";
        this.messageType = "alert-danger";
      }
    },

    resetForm() {
      this.employee = {
        firstName: "",
        lastName: "",
        email: "",
        gender: "",
        dob: "",
        salary: "",
        employeeStatus: { id: "" },
      };
      this.message = "";
      this.messageType = "";
      this.emailError = "";
    },
  },
};
</script>

<style scoped>
.alert {
  padding: 8px 12px;
}
.input-group-text {
  border-left: none;
}
.form-select:focus {
  box-shadow: none;
}
</style>
