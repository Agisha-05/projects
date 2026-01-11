package com.vmx.shophub.controller;

import com.vmx.shophub.models.attendance.StudentAttendance;
import com.vmx.shophub.models.attendance.StudentAttendanceService;
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
@RequestMapping("/v1/attendance")
public class StudentAttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(StudentAttendanceController.class);

    @Autowired
    private StudentAttendanceService attendanceService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> getAllAttendance() {
        try {
            return attendanceService.getAttendanceList();
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getAttendanceList"), e);
            throw e;
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HashMap> getAttendance(@PathVariable Integer id) {
        try {
            return attendanceService.getAttendanceById(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getAttendanceById"), e);
            throw e;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAttendance(@RequestBody StudentAttendance attendance) {
        try {
            return attendanceService.addAttendance(attendance);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "addAttendance"), e);
            throw e;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateAttendance(@RequestBody StudentAttendance attendance) {
        try {
            return attendanceService.updateAttendance(attendance);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "updateAttendance"), e);
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAttendance(@PathVariable Integer id) {
        try {
            return attendanceService.deleteAttendance(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "deleteAttendance"), e);
            throw e;
        }
    }
}
