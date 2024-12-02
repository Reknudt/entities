package org.pavlov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pavlov.dto.request.ProjectRequest;
import org.pavlov.dto.response.ProjectResponse;
import org.pavlov.service.ProjectService;
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
@RequestMapping("/api/v1/project")
@Tag(
        name = "Взаимодействие с проектом",
        description = "Методы добавления, обновления и др")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @Operation(
            summary = "Добавление проекта",
            description = "Сохранение сущности в бд")
    public void createProject(@RequestParam ProjectRequest projectRequest) {

        projectService.createProject(projectRequest);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление проекта",
            description = "Обновление сущности в бд")
    public void updateProject(@PathVariable Long id,
                              @RequestParam ProjectRequest projectRequest) {

        projectService.updateProject(id, projectRequest);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение проекта по ID",
            description = "Для получения отправьте ID")
    public Optional<ProjectResponse> getByProjectID(@PathVariable Long id) {

        return projectService.getProject(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех проектов")
    public List<ProjectResponse> getAllProjects() {

        return projectService.getAllProjects();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление проекта по ID",
            description = "Для удаления отправьте ID")
    public void deleteProjectByID(@PathVariable Long id) {

        projectService.deleteProject(id);
    }
}