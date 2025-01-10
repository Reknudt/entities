package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Employee;
import org.pavlov.model.Task;
import org.pavlov.repository.EmployeeRepository;
import org.pavlov.response.TaskResponse;
import org.pavlov.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public void createEmployee(Employee employeeRequest) {
        employeeRepository.save(employeeRequest);
    }

    @Transactional
    @Override
    public void updateEmployee(Long id, Employee employeeRequest) {
        Employee employee = findByIdOrThrow(id);

        employee.setName(employeeRequest.getName());
        employee.setBossId(employeeRequest.getBossId());
        employee.setDepartmentId(employeeRequest.getDepartmentId());

        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee getEmployee(Long id) {
        return findByIdOrThrow(id);
    }

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .toList();
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<List<TaskResponse>> getEmployeeTaskResponses(Long id) {
        Employee employee = findByIdOrThrow(id);
        List<Task> tasks = employee.getTasks();
        List<TaskResponse> taskResponses = new ArrayList<>();

        for (Task task: tasks) {
            taskResponses.add(new TaskResponse(task));
        }

        return Optional.of(taskResponses);
    }

    @Override
    public Optional<List<Task>> getEmployeeTasks(Long id) {
        Employee employee = findByIdOrThrow(id);
        List<Task> tasks = employee.getTasks();

        return Optional.ofNullable(tasks);
    }


    @Transactional
    @Override
    public List<Long> getAllByBossAlt(Long bossId) {
        return employeeRepository.findByBossIdRecursive(bossId)
                .stream()
                .toList();
    }

    @Transactional
    @Override
    public List<Employee> getAllByBoss(Long bossId) {
        List<Employee> bossesEmployee = employeeRepository
                .findByBossId(bossId)
                .stream()
                .toList();

        List<Employee> newList = new ArrayList<>(bossesEmployee);

        for (int i = 1; i < newList.size(); i++) {
            Long newBossId = newList.get(i).getId();

            List<Employee> newBossesEmployee = employeeRepository
                    .findByBossId(newBossId)
                    .stream()
                    .toList();

            newList.addAll(newBossesEmployee);
        }
        return newList;
    }


    public Employee findByIdOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee " + id + " not found"));
    }
}