package com.vmx.shophub.models.employee;

import com.vmx.shophub.models.employee.Employee;
import com.vmx.shophub.models.employee.EmployeeRepository;
import com.vmx.shophub.utils.ApiResponse;
import com.vmx.shophub.utils.Utility;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Utility utility;

    public ResponseEntity<HashMap> getEmployeeList() {
        ResponseEntity<HashMap> response = null;
        HashMap<String, List> employeeMap = new HashMap<>();
        try {
            List<Employee> employeeList = employeeRepository.findAll();
            employeeMap.put("employeelist", employeeList);
            response = new ResponseEntity<>(employeeMap, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("EmployeeService | getEmployeeList | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> addEmployee(Employee employee) {
        ResponseEntity<ApiResponse> response = null;
        try {
            if (employee.getEmployeeId() != null && employeeRepository.existsById(employee.getEmployeeId())) {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CONFLICT, "Employee already exists");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
            } else {
                employeeRepository.save(employee);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED, "Employee Created Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("EmployeeService | addEmployee | Exception occurred: {}", e.getMessage(), e);
            throw e;
        }
        return response;
    }



    public ResponseEntity<ApiResponse> updateEmployee(Employee employee) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getEmployeeId());
            if (optionalEmployee.isPresent()) {
                employeeRepository.save(employee);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Employee Updated Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Employee not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("EmployeeService | updateEmployee | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteEmployee(Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                employeeRepository.deleteById(id);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Employee Deleted Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Employee not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("EmployeeService | deleteEmployee | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<HashMap> getEmployeeById(Integer id) {
        ResponseEntity<HashMap> response = null;
        HashMap<String, Object> employeeMap = new HashMap<>();
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                employeeMap.put("employee", optionalEmployee.get());
                response = new ResponseEntity<>(employeeMap, HttpStatus.OK);
            } else {
                employeeMap.put("message", "Employee not found");
                response = new ResponseEntity<>(employeeMap, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("EmployeeService | getEmployeeById | Exception occured ", e);
            throw e;
        }
        return response;
    }
}
