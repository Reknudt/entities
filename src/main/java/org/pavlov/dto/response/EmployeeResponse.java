package org.pavlov.dto.response;

public record EmployeeResponse(

        Long id,

        String name,

        Long boss_id,

        Long department_id
) {}