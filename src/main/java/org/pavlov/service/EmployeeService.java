package org.pavlov.service;

import org.pavlov.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void createEmployee(Employee employeeRequest);

    void updateEmployee(Long id, Employee employeeRequest);

    Optional<Employee> getEmployee(Long id);

    List<Employee> getAllEmployees();

    List<Employee> getAllByBoss(Long id);

    List<Employee> getAllByBossAlt(Long bossId);

    void deleteEmployee(Long id);
}
