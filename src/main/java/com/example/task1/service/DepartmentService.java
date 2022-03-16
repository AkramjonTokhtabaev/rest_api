package com.example.task1.service;

import com.example.task1.dto.CompanyDto;
import com.example.task1.entity.Address;
import com.example.task1.entity.Company;
import com.example.task1.entity.Department;
import com.example.task1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    final DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);

    }

    public Company addCompany(CompanyDto companyDto) {

        Address address = new Address();
        address.setStreet(companyDto.getSteet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address);
        Company save = companyRepository.save(company);
        return save;
    }

    public Company editAddress(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company editingCompany = optionalCompany.get();

            Address address = new Address();
            address.setStreet(companyDto.getSteet());
            address.setHomeNumber(companyDto.getHomeNumber());

            editingCompany.setName(companyDto.getName());
            editingCompany.setDirectorName(companyDto.getDirectorName());
            editingCompany.setAddress(address);

            return companyRepository.save(editingCompany);
        }
        return null;
    }

    public boolean deleteCompany(Integer id) {

        try {
            companyRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
