package org.pavlov.service;

import org.pavlov.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createTask(Task taskRequest);

    void updateTask(Long id, Task taskRequest);

    Task getTask(Long id);

    List<Task> getAllTasks();

    void deleteTask(Long id);

    void addEmployee(Long id, Long employeeId);

    void removeEmployee(Long id, Long employeeId);
}