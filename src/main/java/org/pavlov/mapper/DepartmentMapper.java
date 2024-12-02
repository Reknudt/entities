package org.pavlov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pavlov.dto.request.DepartmentRequest;
import org.pavlov.dto.response.DepartmentResponse;
import org.pavlov.model.Department;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    Department createRequestToEntity(DepartmentRequest departmentRequest);

    Department updateDepartmentFromRequest(DepartmentRequest departmentRequest, Department department);

    DepartmentResponse toResponse(Department department);

    DepartmentResponse entityToResponse(Department department);
}