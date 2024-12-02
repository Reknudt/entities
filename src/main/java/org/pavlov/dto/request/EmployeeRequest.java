package org.pavlov.dto.request;

public record EmployeeRequest(

        String name,

        Long boss_id,

        Long department_id
) {}
