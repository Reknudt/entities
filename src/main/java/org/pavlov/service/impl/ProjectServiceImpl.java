package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Project;
import org.pavlov.repository.ProjectRepository;
import org.pavlov.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public void createProject(Project projectRequest) {

        projectRepository.save(projectRequest);
    }

    @Transactional
    @Override
    public void updateProject(Long id, Project projectRequest) {
        Project project = findByIdOrThrow(id);

        project.setName(projectRequest.getName());
        project.setDepartments(projectRequest.getDepartments());

        projectRepository.save(project);
    }

    @Transactional
    @Override
    public Optional<Project> getProject(Long id) {
        return projectRepository.getOneWith(id);
//        Project project = findByIdOrThrow(id);

//        return Optional.ofNullable(project);
    }

    @Transactional
    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll().stream()
                .toList();
    }

    @Override
    public void deleteProject(Long id) {

        projectRepository.deleteById(id);
    }

    private Project findByIdOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resource not found"));
    }
}
