package com.vmx.shophub.controller;

import com.vmx.shophub.models.company.Company;
import com.vmx.shophub.models.company.CompanyService;
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
@RequestMapping("/v1/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> listCompany() {
        ResponseEntity<HashMap> response = null;
        try {
            response = companyService.getCompanyList();
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getCompanyList"), e);

            throw e;
        }
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HashMap> getCompanyById(@PathVariable Integer id) {
        ResponseEntity<HashMap> response = null;
        try {
            response = companyService.getCompanyById(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getCompanyById"), e);
            throw e;
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCompany(@RequestBody Company company) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = companyService.addCompany(company);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "addCompany"), e);
            throw e;
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCompany(@RequestBody Company company) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = companyService.updateCompany(company);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "updateCompany"), e);
            throw e;
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = companyService.deleteCompany(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "deleteCompany"), e);
            throw e;
        }
        return response;
    }
}
