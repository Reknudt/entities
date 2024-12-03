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

    @Query(value = "WITH RECURSIVE r AS ( SELECT e.id, e.name, e.bossId FROM Employee e WHERE e.id = :id UNION SELECT e.id, e.name, e.bossId FROM r INNER JOIN Employee e on r.id = e.bossId ) SELECT * FROM r;",
            nativeQuery = true)
    List<Employee> findByBossIdRecursive(Long id);
}