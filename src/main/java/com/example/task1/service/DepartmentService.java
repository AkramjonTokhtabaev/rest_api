package com.example.task1.service;
import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.DepartmentDto;
import com.example.task1.entity.Department;
import com.example.task1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    final DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getOne(Integer id) {
        return departmentRepository.findById(id);
    }


    public ApiResponse addDepartment(DepartmentDto departmentDto) {

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(departmentDto.getCompany());
        departmentRepository.save(department);
        return new ApiResponse("added", true);
    }

    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();
            department.setName(departmentDto.getName());
            department.setCompany(departmentDto.getCompany());
            return new ApiResponse("edited",true);
        }
        return new ApiResponse("error",false);
    }

    public ApiResponse deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            departmentRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }
}
