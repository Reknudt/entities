package org.pavlov.dto.response;

import org.pavlov.model.Department;

import java.util.List;

public record ProjectResponse(

        Long id,

        String name,

        List<Department> department
) {}