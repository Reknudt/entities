package org.pavlov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.pavlov.model.Department;
import org.pavlov.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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


@EnableMethodSecurity(jsr250Enabled = true)
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/departments")
@Tag(
        name = "Взаимодействие с отделом",
        description = "Методы добавления, обновления и др")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Добавление отдела",
            description = "Сохранение сущности в бд")
    public void createDepartment(@RequestBody @Valid Department departmentRequest) {

        departmentService.createDepartment(departmentRequest);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @RolesAllowed("admin")                              //
    @Operation(
            summary = "Обновление отдела",
            description = "Обновление сущности в бд")
    public void updateDepartment(@PathVariable Long id,
                              @RequestBody @Valid Department departmentRequest) {

        departmentService.updateDepartment(id, departmentRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    @Operation(
            summary = "Получение отдела по ID",
            description = "Для получения отправьте ID")
    public Department getDepartmentByID(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех отделов")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Удаление отдела по ID",
            description = "Для удаления отправьте ID")
    public void deleteDepartmentByID(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}