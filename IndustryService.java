package com.vmx.shophub.models.industry;

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
public class IndustryService {

    private static final Logger logger = LoggerFactory.getLogger(IndustryService.class);

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private Utility utility;

    public ResponseEntity<HashMap> getIndustryList() {
        ResponseEntity<HashMap> response = null;
        HashMap<String, List> industryMap = new HashMap<>();
        try {
            List<Industry> industryList = industryRepository.findAll();
            industryMap.put("industrylist", industryList);
            response = new ResponseEntity<>(industryMap, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IndustryService | getIndustryList | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> addIndustry(Industry industry) {
        ResponseEntity<ApiResponse> response = null;
        try {
            if (industry.getIndustryId() != null && industryRepository.existsById(industry.getIndustryId())) {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CONFLICT, "Industry already exists");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
            } else {
                industryRepository.save(industry);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED, "Industry Created Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("IndustryService | addIndustry | Exception occurred: {}", e.getMessage(), e);
            throw e;
        }
        return response;
    }




    public ResponseEntity<ApiResponse> updateIndustry(Industry industry) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<Industry> optionalIndustry = industryRepository.findById(industry.getIndustryId());
            if (optionalIndustry.isPresent()) {
                industryRepository.save(industry);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Industry Updated Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Industry not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("IndustryService | updateIndustry | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteIndustry(Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<Industry> optionalIndustry = industryRepository.findById(id);
            if (optionalIndustry.isPresent()) {
                industryRepository.deleteById(id);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Industry Deleted Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Industry not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("IndustryService | deleteIndustry | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<HashMap> getIndustryById(Integer id) {
        ResponseEntity<HashMap> response = null;
        HashMap<String, Object> industryMap = new HashMap<>();
        try {
            Optional<Industry> optionalIndustry = industryRepository.findById(id);
            if (optionalIndustry.isPresent()) {
                industryMap.put("industry", optionalIndustry.get());
                response = new ResponseEntity<>(industryMap, HttpStatus.OK);
            } else {
                industryMap.put("message", "Industry not found");
                response = new ResponseEntity<>(industryMap, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("IndustryService | getIndustryById | Exception occured ", e);
            throw e;
        }
        return response;
    }
}
