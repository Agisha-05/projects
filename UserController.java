package com.vmx.shophub.controller;

import com.vmx.shophub.models.user.User;
import com.vmx.shophub.models.user.UserService;
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
@RequestMapping("/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Utility utility;

    @GetMapping("/list")
    public ResponseEntity<HashMap> listUser() {
        ResponseEntity<HashMap> response = null;
        try {
            response = userService.getUserList();
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getUserList"), e);
            throw e;
        }
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HashMap> getUserById(@PathVariable Integer id) {
        ResponseEntity<HashMap> response = null;
        try {
            response = userService.getUserById(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "getUserById"), e);
            throw e;
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = userService.addUser(user);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "addUser"), e);
            throw e;
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = userService.updateUser(user);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "updateUser"), e);
            throw e;
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = userService.deleteUser(id);
        } catch (Exception e) {
            logger.error(utility.getErrorMessage(this.getClass().getName(), "deleteUser"), e);
            throw e;
        }
        return response;
    }
}
