package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Project;
import org.pavlov.repository.ProjectRepository;
import org.pavlov.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Project getProject(Long id) {
        return findByIdOrThrow(id);
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
                        () -> new ResourceNotFoundException("Project " + id + " not found"));
    }
}
