package com.example.task1.controller;


import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.CompanyDto;
import com.example.task1.dto.DepartmentDto;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor

public class DepartmentController {

    final DepartmentService departmentService;
    final DepartmentRepository departmentRepository;



    @GetMapping
    public HttpEntity<?> getAll() {
        List<Department> all = departmentService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Department> optional = departmentService.getOne(id);
        return ResponseEntity.status(!optional.isPresent() ?
                HttpStatus.OK :
                HttpStatus.NOT_FOUND).body(optional.get());
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody DepartmentDto departmentDto) {
        ApiResponse response = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(response.isSuccess() ?
                        HttpStatus.CREATED : HttpStatus.CONFLICT).
                body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDepartment(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }



}
