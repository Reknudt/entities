package org.pavlov.dto.request;

import org.pavlov.model.Department;

import java.util.List;

public record ProjectRequest(

        String name,

        List<Department> department
) {}