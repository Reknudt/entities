package org.pavlov.service;

import org.pavlov.dto.request.EmployeeRequest;
import org.pavlov.dto.response.EmployeeResponse;
import org.pavlov.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void createEmployee(Employee employeeRequest);

    void updateEmployee(Long id, EmployeeRequest employeeRequest);

    Optional<EmployeeResponse> getEmployee(Long id);

    List<EmployeeResponse> getAllEmployees();

    void deleteEmployee(Long id);
}
