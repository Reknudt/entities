package org.pavlov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.pavlov.model.Project;
import org.pavlov.service.ProjectService;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/projects")
@Tag(
        name = "Взаимодействие с проектом",
        description = "Методы добавления, обновления и др")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Добавление проекта",
            description = "Сохранение сущности в бд")
    public void createProject(@RequestBody @Valid Project projectRequest) {

        projectService.createProject(projectRequest);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление проекта",
            description = "Обновление сущности в бд")
    public void updateProject(@PathVariable Long id,
                              @RequestBody @Valid Project projectRequest) {

        projectService.updateProject(id, projectRequest);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение проекта по ID",
            description = "Для получения отправьте ID")
    public Optional<Project> getByProjectID(@PathVariable Long id) {

        return projectService.getProject(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех проектов")
    public List<Project> getAllProjects() {

        return projectService.getAllProjects();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Удаление проекта по ID",
            description = "Для удаления отправьте ID")
    public void deleteProjectByID(@PathVariable Long id) {

        projectService.deleteProject(id);
    }
}