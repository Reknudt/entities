package org.pavlov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pavlov.dto.request.ProjectRequest;
import org.pavlov.dto.response.ProjectResponse;
import org.pavlov.model.Project;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {

    Project createRequestToEntity(ProjectRequest projectRequest);

    Project updateProjectFromRequest(ProjectRequest projectRequest, Project project);

    ProjectResponse toResponse(Project project);

    ProjectResponse entityToResponse(Project project);
}