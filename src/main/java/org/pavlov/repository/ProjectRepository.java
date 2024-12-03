package org.pavlov.repository;

import org.pavlov.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p from Project p join fetch p.departments where p.id = :id")
    Optional<Project> getOneWith(Long id);
}