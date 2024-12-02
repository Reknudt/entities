package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.dto.request.ProjectRequest;
import org.pavlov.dto.response.ProjectResponse;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.mapper.ProjectMapper;
import org.pavlov.model.Project;
import org.pavlov.repository.ProjectRepository;
import org.pavlov.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    
    @Override
    public void createProject(ProjectRequest projectRequest) {
        Project project = projectMapper.createRequestToEntity(projectRequest);

        projectRepository.save(project);
    }

    @Override
    public void updateProject(Long id, ProjectRequest projectRequest) {
        Project project = findByIdOrThrow(id);

        project = projectMapper.updateProjectFromRequest(projectRequest, project);
        projectRepository.save(project);
    }

    @Override
    public Optional<ProjectResponse> getProject(Long id) {
        Project project = findByIdOrThrow(id);

        return Optional.ofNullable(projectMapper.entityToResponse(project));
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toResponse)
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
