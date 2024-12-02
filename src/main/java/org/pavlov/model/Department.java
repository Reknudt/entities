package org.pavlov.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "boss_id")
    private Long boss_id;

//    @Column(name = "teacher_id")
    @OneToMany
    @JoinColumn(name = "department_id")
    private List<Employee> employees;

    @ManyToMany(mappedBy = "departments")
    private List<Project> projects;
}
