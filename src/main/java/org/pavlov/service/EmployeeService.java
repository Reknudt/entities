package org.pavlov.service;

import org.pavlov.model.Employee;
import org.pavlov.model.Task;
import org.pavlov.response.TaskResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void createEmployee(Employee employeeRequest);

    void updateEmployee(Long id, Employee employeeRequest);

    Employee getEmployee(Long id);

    List<Employee> getAllEmployees();

    List<Employee> getAllByBoss(Long id);

    List<Long> getAllByBossAlt(Long bossId);

    void deleteEmployee(Long id);

    Optional<List<Task>> getEmployeeTasks(Long id);

    Optional<List<TaskResponse>> getEmployeeTaskResponses(Long id);
}