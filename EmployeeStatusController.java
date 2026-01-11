package com.vmx.shophub.controller;

import com.vmx.shophub.models.employeestatus.EmployeeStatusService;
import com.vmx.shophub.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/v1/employeestatus")
public class EmployeeStatusController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeStatusController.class);

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> getEmployeeStatusList() {
        ResponseEntity<HashMap> response = null;
        try {
            response = employeeStatusService.getEmployeeStatusList();
        } catch (Exception e) {
            logger.error("EmployeeStatusController | getEmployeeStatusList | Exception occurred ", e);
            response = utility.getErrorMessage(e);
        }
        return response;
    }
}
