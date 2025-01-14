package org.pavlov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.pavlov.model.Employee;
import org.pavlov.model.Task;
import org.pavlov.response.TaskResponse;
import org.pavlov.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
@Tag(
        name = "Взаимодействие с сотрудниками",
        description = "Методы добавления, обновления и др")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Добавление сотрудника",
            description = "Сохранение сущности в бд")
    public void createEmployee(@RequestBody @Valid Employee employeeRequest) {
        employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('user')")
    @Operation(
            summary = "Обновление сотрудника",
            description = "Обновление сущности в бд")
    public void updateEmployee(@PathVariable Long id,
                               @RequestBody @Valid Employee employeeRequest) {
        employeeService.updateEmployee(id, employeeRequest);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение сотрудника по ID",
            description = "Для получения отправьте ID")
    public Employee getByEmployeeID(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("tasks/{id}")
    @Operation(
            summary = "Получение задания по ID сотрудника",
            description = "Для получения отправьте ID")
    public Optional<List<Task>> getEmployeeTasksByID(@PathVariable Long id) {
        return employeeService.getEmployeeTasks(id);
    }

    @GetMapping("taskResponses/{id}")
    @PreAuthorize("hasAuthority('user') and hasAuthority('editor')")
    @Operation(
            summary = "Получение только задания по ID сотрудника",
            description = "Для получения отправьте ID")
    public Optional<List<TaskResponse>> getEmployeeTaskResponsesByID(@PathVariable Long id) {
        return employeeService.getEmployeeTaskResponses(id);
    }

    @GetMapping("bossId/{id}")
    @Operation(
            summary = "Получение сотрудника по boss ID",
            description = "Для получения отправьте boss ID")
    public List<Employee> getByBossID(@PathVariable Long id) {

        return employeeService.getAllByBoss(id);
    }

    @GetMapping("bossIdAlt/{id}")
    @Operation(
            summary = "Получение сотрудника по boss ID recursive",
            description = "Для получения отправьте boss ID")
    public List<Long> getByBossIDAlt(@PathVariable Long id) {

        return employeeService.getAllByBossAlt(id);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('user') and hasAuthority('visitor')")
    @Operation(
            summary = "Получение всех сотрудников")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Удаление сотрудника по ID",
            description = "Для удаления отправьте ID")
    public void deleteEmployeeByID(@PathVariable Long id) {

        employeeService.deleteEmployee(id);
    }
}