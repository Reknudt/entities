package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Employee;
import org.pavlov.model.Task;
import org.pavlov.repository.TaskRepository;
import org.pavlov.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeServiceImpl employeeService;

    @Transactional
    @Override
    public void createTask(Task taskRequest) {
        taskRepository.save(taskRequest);
    }

    @Transactional
    @Override
    public void updateTask(Long id, Task taskRequest) {
        Task task = findByIdOrThrow(id);

        task.setName(taskRequest.getName());
        task.setEmployees(taskRequest.getEmployees());

        taskRepository.save(task);
    }

    @Override
    public Task getTask(Long id) {
        return findByIdOrThrow(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream()
                .toList();
    }

    @Override
    public void deleteTask(Long id) {
        Task task = findByIdOrThrow(id);
        taskRepository.delete(task);
    }

    @Transactional
    @Override
    public void addEmployee(Long id, Long employeeId) {
        Task task = findByIdOrThrow(id);
        Employee employee = employeeService.findByIdOrThrow(employeeId);

        List<Employee> employees = task.getEmployees();
        employees.add(employee);
        task.setEmployees(employees);
        taskRepository.save(task);
    }

    @Override
    public void removeEmployee(Long id, Long employeeId) {
        Task task = findByIdOrThrow(id);
        Employee employee = employeeService.findByIdOrThrow(employeeId);

        List<Employee> employees = task.getEmployees();
        employees.remove(employee);
        task.setEmployees(employees);
        taskRepository.save(task);
    }

    private Task findByIdOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Task "+ id + " not found"));
    }
}