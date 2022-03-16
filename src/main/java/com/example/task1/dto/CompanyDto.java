package com.example.task1.dto;

import com.example.task1.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {

    @NotNull(message = "name should not be empty")
    private String name;
    @NotNull(message = "name should not be empty")
    private String directorName;
    @NotNull(message = "street should not be empty")
    private String steet;
    @NotNull(message = "homeNumber should not be empty")
    private String homeNumber;

}
