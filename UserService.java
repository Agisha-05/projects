package com.vmx.shophub.models.user;

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
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utility utility;

    public ResponseEntity<HashMap> getUserList() {
        ResponseEntity<HashMap> response = null;
        HashMap<String, List> userMap = new HashMap<>();
        try {
            List<User> userList = userRepository.findAll();
            userMap.put("userlist", userList);
            response = new ResponseEntity<>(userMap, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("UserService | getUserList | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> addUser(User user) {
        ResponseEntity<ApiResponse> response = null;
        try {

            if (user.getUserId() != null && userRepository.existsById(user.getUserId())) {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CONFLICT, "User already exists");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
            } else {
                userRepository.save(user);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED, "User Created Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("UserService | addUser | Exception occurred: {}", e.getMessage(), e);
            throw e;
        }
        return response;
    }




    public ResponseEntity<ApiResponse> updateUser(User user) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<User> optionalUser = userRepository.findById(user.getUserId());
            if (optionalUser.isPresent()) {
                userRepository.save(user);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "User Updated Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "User not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("UserService | updateUser | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteUser(Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                userRepository.deleteById(id);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "User Deleted Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "User not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("UserService | deleteUser | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<HashMap> getUserById(Integer id) {
        ResponseEntity<HashMap> response = null;
        HashMap<String, Object> userMap = new HashMap<>();
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                userMap.put("user", optionalUser.get());
                response = new ResponseEntity<>(userMap, HttpStatus.OK);
            } else {
                userMap.put("message", "User not found");
                response = new ResponseEntity<>(userMap, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("UserService | getUserById | Exception occured ", e);
            throw e;
        }
        return response;
    }
}
