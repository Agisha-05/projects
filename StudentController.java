package com.vmx.shophub.controller;

import com.vmx.shophub.models.student.Student;
import com.vmx.shophub.models.student.StudentService;
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
@RequestMapping("/v1/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> getStudents() {
        try {
            return studentService.getStudentList();
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getStudentList"), e);
            throw e;
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HashMap> getStudentById(@PathVariable Integer id) {
        try {
            return studentService.getStudentById(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getStudentById"), e);
            throw e;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody Student student) {
        try {
            return studentService.addStudent(student);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "addStudent"), e);
            throw e;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateStudent(@RequestBody Student student) {
        try {
            return studentService.updateStudent(student);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "updateStudent"), e);
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id) {
        try {
            return studentService.deleteStudent(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "deleteStudent"), e);
            throw e;
        }
    }
}
