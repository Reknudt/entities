package org.pavlov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true)
    private Long id;

    @NotBlank
//    @Column(name = "name")
    private String name;

//    @Column(name = "boss_id")
    private Long bossId;

//    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "departmentId")
    private List<Employee> employees;

    @JsonIgnore
    @ManyToMany(mappedBy = "departments")
    private List<Project> projects;
}
