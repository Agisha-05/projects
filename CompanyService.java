package com.vmx.shophub.models.company;
import com.vmx.shophub.models.company.Company;
import com.vmx.shophub.models.company.CompanyRepository;
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
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private Utility utility;

    public ResponseEntity<HashMap> getCompanyList() {
        ResponseEntity<HashMap> response = null;
        HashMap<String, List> companyMap = new HashMap<>();
        try {
            List<Company> companyList = companyRepository.findAll();
            companyMap.put("companylist", companyList);
            response = new ResponseEntity<>(companyMap, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("CompanyService | getCompanyList | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> addCompany(Company company) {
        ResponseEntity<ApiResponse> response = null;
        try {
            if (company.getCompanyId() != null && companyRepository.existsById(company.getCompanyId())) {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CONFLICT, "Company already exists");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
            } else {
                companyRepository.save(company);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED, "Company Created Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("CompanyService | addCompany | Exception occurred: {}", e.getMessage(), e);
            throw e;
        }
        return response;
    }



    public ResponseEntity<ApiResponse> updateCompany(Company company) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<Company> optionalCompany = companyRepository.findById(company.getCompanyId());
            if (optionalCompany.isPresent()) {
                companyRepository.save(company);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Company Updated Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Company not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("CompanyService | updateCompany | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteCompany(Integer id) {
        ResponseEntity<ApiResponse> response = null;
        try {
            Optional<Company> optionalCompany = companyRepository.findById(id);
            if (optionalCompany.isPresent()) {
                companyRepository.deleteById(id);
                ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Company Deleted Successfully");
                response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Company not found");
                response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("CompanyService | deleteCompany | Exception occured ", e);
            throw e;
        }
        return response;
    }

    public ResponseEntity<HashMap> getCompanyById(Integer id) {
        ResponseEntity<HashMap> response = null;
        HashMap<String, Object> companyMap = new HashMap<>();
        try {
            Optional<Company> optionalCompany = companyRepository.findById(id);
            if (optionalCompany.isPresent()) {
                companyMap.put("company", optionalCompany.get());
                response = new ResponseEntity<>(companyMap, HttpStatus.OK);
            } else {
                companyMap.put("message", "Company not found");
                response = new ResponseEntity<>(companyMap, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("CompanyService | getCompanyById | Exception occured ", e);

            throw e;
        }
        return response;
    }
}
