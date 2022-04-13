package com.example.task1.controller;


import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.DepartmentDto;
import com.example.task1.dto.EmployeeDto;
import com.example.task1.entity.Department;
import com.example.task1.entity.Employee;
import com.example.task1.repository.EmployeeRepository;
import com.example.task1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor

public class EmployeeController {


    final EmployeeRepository employeeRepository;
    final EmployeeService employeeService;

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Employee> all = employeeService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Employee> optional = employeeService.getOne(id);
        return ResponseEntity.status(!optional.isPresent() ?
                HttpStatus.OK :
                HttpStatus.NOT_FOUND).body(optional.get());
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody EmployeeDto employeeDto) {
        ApiResponse response = employeeService.addEmployee(employeeDto);
        return ResponseEntity.status(response.isSuccess() ?
                        HttpStatus.CREATED : HttpStatus.CONFLICT).
                body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        ApiResponse apiResponse = employeeService.editEmployee(id, employeeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteEmployee(@PathVariable Integer id) {
        ApiResponse apiResponse = employeeService.deleteEmployee(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
