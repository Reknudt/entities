package org.pavlov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pavlov.dto.request.DepartmentRequest;
import org.pavlov.dto.response.DepartmentResponse;
import org.pavlov.service.DepartmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
@Tag(
        name = "Взаимодействие с отделом",
        description = "Методы добавления, обновления и др")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @Operation(
            summary = "Добавление отдела",
            description = "Сохранение сущности в бд")
    public void createDepartment(@RequestParam DepartmentRequest departmentRequest) {

        departmentService.createDepartment(departmentRequest);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление отдела",
            description = "Обновление сущности в бд")
    public void updateDepartment(@PathVariable Long id,
                              @RequestParam DepartmentRequest departmentRequest) {

        departmentService.updateDepartment(id, departmentRequest);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение отдела по ID",
            description = "Для получения отправьте ID")
    public Optional<DepartmentResponse> getDepartmentByID(@PathVariable Long id) {

        return departmentService.getDepartment(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех отделов")
    public List<DepartmentResponse> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление отдела по ID",
            description = "Для удаления отправьте ID")
    public void deleteDepartmentByID(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }
}