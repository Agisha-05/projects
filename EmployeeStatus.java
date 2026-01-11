package com.vmx.shophub.models.employeestatus;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_status")
public class EmployeeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    // Example values you can insert:
    // Probation, Active, Resigned, Terminated, On Leave

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
