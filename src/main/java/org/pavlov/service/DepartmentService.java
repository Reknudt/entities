package org.pavlov.service;

import org.pavlov.dto.request.DepartmentRequest;
import org.pavlov.dto.response.DepartmentResponse;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    void createDepartment(DepartmentRequest departmentRequest);

    void updateDepartment(Long id, DepartmentRequest departmentRequest);

    Optional<DepartmentResponse> getDepartment(Long id);

    List<DepartmentResponse> getAllDepartments();

    void deleteDepartment(Long id);
}
