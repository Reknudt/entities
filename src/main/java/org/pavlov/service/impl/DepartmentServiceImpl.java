package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.dto.request.DepartmentRequest;
import org.pavlov.dto.response.DepartmentResponse;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.mapper.DepartmentMapper;
import org.pavlov.model.Department;
import org.pavlov.repository.DepartmentRepository;
import org.pavlov.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public void createDepartment(DepartmentRequest departmentRequest) {
        Department department = departmentMapper.createRequestToEntity(departmentRequest);

        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Department department = findByIdOrThrow(id);

        department = departmentMapper.updateDepartmentFromRequest(departmentRequest, department);

        departmentRepository.save(department);
    }
    
    @Override
    public Optional<DepartmentResponse> getDepartment(Long id) {
        Department department = findByIdOrThrow(id);

        return Optional.ofNullable(departmentMapper.entityToResponse(department));
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
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