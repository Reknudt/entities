package org.pavlov.dto.request;

import org.pavlov.model.Employee;
import org.pavlov.model.Project;

import java.util.List;

public record DepartmentRequest(

        String name,

        Long boss_id,

        List<Project> projects,

        List<Employee> employees
) {}
