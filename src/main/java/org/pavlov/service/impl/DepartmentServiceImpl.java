package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Department;
import org.pavlov.repository.DepartmentRepository;
import org.pavlov.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public void createDepartment(Department departmentRequest) {

        departmentRepository.save(departmentRequest);
    }

    @Transactional
    @Override
    public void updateDepartment(Long id, Department departmentRequest) {
        Department department = findByIdOrThrow(id);

        department.setBossId(departmentRequest.getBossId());
        department.setEmployees(departmentRequest.getEmployees());
        department.setName(departmentRequest.getName());
        department.setProjects(departmentRequest.getProjects());

        departmentRepository.save(department);
    }

    @Transactional
    @Override
    public Department getDepartment(Long id) {
        return findByIdOrThrow(id);
    }

    @Transactional
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
                        () -> new ResourceNotFoundException("Department "+ id + " not found"));
    }
}