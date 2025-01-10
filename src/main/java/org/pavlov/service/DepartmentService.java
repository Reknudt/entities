package org.pavlov.service;

import org.pavlov.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    void createDepartment(Department departmentRequest);

    void updateDepartment(Long id, Department departmentRequest);

    Department getDepartment(Long id);

    List<Department> getAllDepartments();

    void deleteDepartment(Long id);
}
