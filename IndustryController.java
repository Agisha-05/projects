package com.vmx.shophub.controller;

import com.vmx.shophub.models.industry.Industry;
import com.vmx.shophub.models.industry.IndustryService;
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
@RequestMapping("/v1/industry")
public class IndustryController {

    private static final Logger logger = LoggerFactory.getLogger(IndustryController.class);

    @Autowired
    private IndustryService industryService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> listIndustry() {
        ResponseEntity<HashMap> response = null;
        try {
            response = industryService.getIndustryList();
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getIndustryList"), e);
            throw e;
        }
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HashMap> getIndustryById(@PathVariable Integer id) {
        ResponseEntity<HashMap> response = null;
        try {
            response = industryService.getIndustryById(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getIndustryById"), e);
            throw e;
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addIndustry(@RequestBody Industry industry) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = industryService.addIndustry(industry);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "addIndustry"), e);
            throw e;
        }
        return response;
    }

    @PutMapping ("/update")
    public ResponseEntity<ApiResponse> updateIndustry(@RequestBody Industry industry) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = industryService.updateIndustry(industry);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "updateIndustry"), e);
            throw e;
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteIndustry(@PathVariable Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = industryService.deleteIndustry(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "deleteIndustry"), e);
            throw e;
        }
        return response;
    }
}
