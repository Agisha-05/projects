package com.vmx.shophub.controller;

import com.vmx.shophub.models.employee.Employee;
import com.vmx.shophub.models.employee.EmployeeService;
import com.vmx.shophub.utils.ApiResponse;
import com.vmx.shophub.utils.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@Component
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> listEmployees() {
        ResponseEntity<HashMap> response = null;
        try {
            response = employeeService.getEmployeeList();
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getEmployeeList"), e);


            throw e;
        }
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HashMap> getEmployeeById(@PathVariable Integer id) {
        ResponseEntity<HashMap> response = null;
        try {
            response = employeeService.getEmployeeById(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getEmployeeById"), e);
            throw e;
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addEmployee(@RequestBody Employee employee) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = employeeService.addEmployee(employee);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "addEmployee"), e);
            throw e;
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "updateEmployee"), e);
            throw e;
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = employeeService.deleteEmployee(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "deleteEmployee"), e);

            throw e;
        }
        return response;
    }
}
