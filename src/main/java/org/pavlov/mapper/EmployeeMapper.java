package org.pavlov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pavlov.dto.request.EmployeeRequest;
import org.pavlov.dto.response.EmployeeResponse;
import org.pavlov.model.Employee;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    Employee createRequestToEntity(EmployeeRequest employeeRequest);

    Employee updateStudentFromRequest(EmployeeRequest employeeRequest, Employee employee);

    EmployeeResponse toResponse(Employee employee);

    EmployeeResponse entityToResponse(Employee employee);
}