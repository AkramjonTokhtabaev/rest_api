package com.example.task1.service;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.CompanyDto;
import com.example.task1.entity.Address;
import com.example.task1.entity.Company;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CompanyService {

    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> getOne(Integer id) {
        return companyRepository.findById(id);
    }


    public ApiResponse addCompany(CompanyDto companyDto) {

        Address address = new Address();
        address.setStreet(companyDto.getSteet());
        address.setHomeNumber(companyDto.getHomeNumber());

        Company company = new Company();
        company.setName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address);
        Company save = companyRepository.save(company);
        return new ApiResponse("added", true);
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company editingCompany = optionalCompany.get();

            Address address = new Address();
            address.setStreet(companyDto.getSteet());
            address.setHomeNumber(companyDto.getHomeNumber());

            editingCompany.setName(companyDto.getName());
            editingCompany.setDirectorName(companyDto.getDirectorName());
            editingCompany.setAddress(address);
            companyRepository.save(editingCompany);
            return new ApiResponse("edited",true);
        }
        return new ApiResponse("error",false);
    }

    public ApiResponse deleteCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            companyRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }
}
