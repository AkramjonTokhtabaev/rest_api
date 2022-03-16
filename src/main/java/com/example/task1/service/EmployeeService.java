package com.example.task1.service;

import com.example.task1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    final DepartmentRepository departmentRepository;


}
