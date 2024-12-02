package org.pavlov.dto.response;

import org.pavlov.model.Employee;
import org.pavlov.model.Project;

import java.util.List;

public record DepartmentResponse(

        Long id,

        String name,

        Long boss_id,

        List<Project> projects,

        List<Employee> employees
) {}