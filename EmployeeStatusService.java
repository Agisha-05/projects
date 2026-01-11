package com.vmx.shophub.models.employeestatus;

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

@Service
@Transactional
public class EmployeeStatusService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeStatusService.class);

    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;

    @Autowired
    private Utility utility;

    public ResponseEntity<HashMap> getEmployeeStatusList() {
        ResponseEntity<HashMap> response = null;
        HashMap<String, List> statusMap = new HashMap<>();
        try {
            List<EmployeeStatus> statusList = employeeStatusRepository.findAll();
            statusMap.put("employeestatuslist", statusList);
            response = new ResponseEntity<>(statusMap, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("EmployeeStatusService | getEmployeeStatusList | Exception occurred ", e);
            throw e;
        }
        return response;
    }
}
