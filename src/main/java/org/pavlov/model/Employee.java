package org.pavlov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank
//    @Column(name = "name")
    private String name;

//    @Column(name = "boss_id")
    private Long bossId;

//    @Column(name = "department_id")
    private Long departmentId;

    @JsonIgnore
    @ManyToMany(mappedBy = "employees")
    private List<Task> tasks;
}
