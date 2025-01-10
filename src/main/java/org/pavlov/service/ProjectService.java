package org.pavlov.service;

import org.pavlov.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    void createProject(Project projectRequest);

    void updateProject(Long id, Project projectRequest);

    Project getProject(Long id);

    List<Project> getAllProjects();

    void deleteProject(Long id);
}