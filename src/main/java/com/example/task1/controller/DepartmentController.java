package com.example.task1.controller;


import com.example.task1.dto.CompanyDto;
import com.example.task1.entity.Company;
import com.example.task1.entity.Department;
import com.example.task1.repository.DepartmentRepository;
import com.example.task1.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor

public class DepartmentController {

    final DepartmentService departmentService;
    final DepartmentRepository departmentRepository;


    @GetMapping
    public HttpEntity<?> getAllDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);

    }


    @GetMapping
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
