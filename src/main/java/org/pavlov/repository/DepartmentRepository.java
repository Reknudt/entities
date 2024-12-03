package org.pavlov.repository;

import org.pavlov.model.Department;
import org.pavlov.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d from Department d join d.employees where d.id = :id")
    Optional<Department> getOneWith(Long id);
}