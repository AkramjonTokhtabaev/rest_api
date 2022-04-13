package com.example.task1.controller;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.CompanyDto;
import com.example.task1.entity.Company;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.CompanyRepository;
import com.example.task1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor

public class CompanyController {

    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;
    final CompanyService companyService;


    @GetMapping
    public HttpEntity<?> getAll() {
        List<Company> all = companyService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Company> optional = companyService.getOne(id);
        return ResponseEntity.status(!optional.isPresent() ?
                HttpStatus.OK :
                HttpStatus.NOT_FOUND).body(optional.get());
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody CompanyDto companyDto) {
        ApiResponse response = companyService.addCompany(companyDto);
        return ResponseEntity.status(response.isSuccess() ?
                        HttpStatus.CREATED : HttpStatus.CONFLICT).
                body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCompany(@PathVariable Integer id) {
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
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


