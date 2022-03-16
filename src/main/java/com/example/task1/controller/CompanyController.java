package com.example.task1.controller;

import com.example.task1.dto.CompanyDto;
import com.example.task1.entity.Company;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.CompanyRepository;
import com.example.task1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor

public class CompanyController {

    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;
    final CompanyService companyService;


    @GetMapping
    public HttpEntity<?> getAllCompanies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Company> companies = companyService.getCompanies(page, size);
        return ResponseEntity.ok(companies);

    }


    @GetMapping("/{id}")
    public HttpEntity<?> getCompany(@PathVariable Integer id) {
        Company company = companyService.getCompany(id);
        return ResponseEntity.status(company != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(company);
    }

    @PostMapping
    public HttpEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
        Company savedCompany = companyService.addCompany(companyDto);
        return ResponseEntity.status(201).body(savedCompany);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAddress(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        Company editedCompany = companyService.editAddress(id, companyDto);
        return ResponseEntity.status(editedCompany != null ? 202 : 409).body(editedCompany);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCompany(@PathVariable Integer id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}







//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;


