package org.pavlov.repository;

import org.pavlov.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByName(String name);

    @Query("select e from Employee e where bossId = :id")
    List<Employee> findByBossId(Long id);
}