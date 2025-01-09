package org.pavlov.repository;

import org.pavlov.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    List<Employee> findByName(String name);

    @Query("select e from Employee e where e.bossId = :id")
    List<Employee> findByBossId(Long id);

    @Query(value = """
            WITH RECURSIVE r AS (
            SELECT e.id FROM Employee e WHERE e.boss_id = :id
            UNION ALL
            SELECT e.id FROM r JOIN Employee e on r.id = e.boss_id
            ) SELECT * FROM r;
            """,
            nativeQuery = true)
    List<Long> findByBossIdRecursive(Long id);
}