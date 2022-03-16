package com.example.task1.dto;

import com.example.task1.entity.Address;
import com.example.task1.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {

    private String name;

    private String phoneNumber;

    private Department department;

    private String street;

    private String homeNumber;
}
