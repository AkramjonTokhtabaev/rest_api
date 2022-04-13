package com.example.task1.service;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.DepartmentDto;
import com.example.task1.dto.EmployeeDto;
import com.example.task1.entity.Address;
import com.example.task1.entity.Department;
import com.example.task1.entity.Employee;
import com.example.task1.repository.DepartmentRepository;
import com.example.task1.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getOne(Integer id) {
        return employeeRepository.findById(id);
    }


    public ApiResponse addEmployee(EmployeeDto employeeDto) {

        Address address = new Address();
        address.setStreet(employeeDto.getStreet());
        address.setHomeNumber(employeeDto.getHomeNumber());

        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setAddress(address);
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        return new ApiResponse("added", true);
    }

    public ApiResponse editEmployee(Integer id, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Address address = new Address();
            address.setStreet(employeeDto.getStreet());
            address.setHomeNumber(employeeDto.getHomeNumber());

            Employee employee = new Employee();
            employee.setName(employeeDto.getName());
            employee.setDepartment(employeeDto.getDepartment());
            employee.setAddress(address);
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            return new ApiResponse("edited",true);
        }
        return new ApiResponse("error",false);
    }

    public ApiResponse deleteEmployee(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            employeeRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }


}
