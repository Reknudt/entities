package org.pavlov.service;

import org.pavlov.dto.request.ProjectRequest;
import org.pavlov.dto.response.ProjectResponse;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    void createProject(ProjectRequest projectRequest);

    void updateProject(Long id, ProjectRequest projectRequest);

    Optional<ProjectResponse> getProject(Long id);

    List<ProjectResponse> getAllProjects();

    void deleteProject(Long id);
}