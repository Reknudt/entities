package org.pavlov.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.pavlov.model.Task;
import org.pavlov.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
@Tag(
        name = "Task manipulations",
        description = "CRUD methods to manipulate with tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Assign task",
            description = "Fill the request with information and send request to add it to data base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    public void createTask(@RequestBody @Valid Task taskRequest) {
        taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update task fields",
            description = "Send ID as a path variable to select a necessary task and fill the request with the information" +
                    "you would like to change to")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    public void updateTask(@PathVariable Long id, @RequestBody @Valid Task taskRequest) {
        taskService.updateTask(id, taskRequest);
    }

    @PutMapping("addEmployee/{id}")
    @Operation(
            summary = "Assign employee for task",
            description = "Send task id as path variable and employee's id " +
                    "as a parametr to assign new employee for task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    public void addTaskEmployee(@PathVariable Long id, @RequestParam @Valid Long employeeId) {
        taskService.addEmployee(id, employeeId);
    }

    @PutMapping("removeEmployee/{id}")                      //
    @Operation(
            summary = "Remove employee from task",
            description = "Send task's id and employee's id to remove employee from task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    public void removeTaskEmployee(@PathVariable Long id, @RequestParam @Valid Long employeeId) {
        taskService.removeEmployee(id, employeeId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(
            summary = "Get task by ID",
            description = "Send ID as a path variable to get information about this task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request succeed",
                    content = @Content)})
    public Task getByTaskID(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping
    @Operation(
            summary = "Get all tasks",
            description = "Send get request to get information about all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request succeed",
                    content = @Content)})
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete task by ID",
            description = "Send ID as a path variable to delete necessary task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted",
                    content = @Content)})
    public void deleteTaskByID(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}