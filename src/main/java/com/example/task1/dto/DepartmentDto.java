package com.example.task1.dto;

import com.example.task1.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    private String name;
    private Company company;

}
