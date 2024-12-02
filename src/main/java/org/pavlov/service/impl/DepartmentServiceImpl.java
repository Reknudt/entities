package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Department;
import org.pavlov.repository.DepartmentRepository;
import org.pavlov.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public void createDepartment(Department departmentRequest) {

        departmentRepository.save(departmentRequest);
    }

    @Override
    public void updateDepartment(Long id, Department departmentRequest) {
        Department department = findByIdOrThrow(id);

        department.setBoss_id(departmentRequest.getBoss_id());
        department.setEmployees(departmentRequest.getEmployees());
        department.setName(departmentRequest.getName());
        department.setProjects(departmentRequest.getProjects());

        departmentRepository.save(department);
    }
    
    @Override
    public Optional<Department> getDepartment(Long id) {
        Department department = findByIdOrThrow(id);

        return Optional.ofNullable(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .toList();
    }

    @Override
    public void deleteDepartment(Long id) {

        departmentRepository.deleteById(id);
    }

    private Department findByIdOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resource not found"));
    }
}