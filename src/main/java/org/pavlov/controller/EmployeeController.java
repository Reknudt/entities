package org.pavlov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.pavlov.dto.request.EmployeeRequest;
import org.pavlov.dto.response.EmployeeResponse;
import org.pavlov.model.Employee;
import org.pavlov.service.EmployeeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Operation(
            summary = "Добавление сотрудника",
            description = "Сохранение сущности в бд")
    public void createEmployee(@RequestBody @Valid Employee employeeRequest) {

        //EmployeeRequest employeeRequest = new EmployeeRequest(name, boss_id, department_id);

        //System.out.println("request " + employeeRequest);
        employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление сотрудника",
            description = "Обновление сущности в бд")
    public void updateEmployee(@PathVariable Long id,
                               @RequestParam EmployeeRequest employeeRequest) {

        employeeService.updateEmployee(id, employeeRequest);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение сотрудника по ID",
            description = "Для получения отправьте ID")
    public Optional<EmployeeResponse> getByEmployeeID(@PathVariable Long id) {

        return employeeService.getEmployee(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех сотрудников")
    public List<EmployeeResponse> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление сотрудника по ID",
            description = "Для удаления отправьте ID")
    public void deleteEmployeeByID(@PathVariable Long id) {

        employeeService.deleteEmployee(id);
    }
}