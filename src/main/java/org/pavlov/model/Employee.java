package org.pavlov.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
//    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "boss_id")
    private Long boss_id;

    @Column(name = "department_id")
    private Long department_id;
}
