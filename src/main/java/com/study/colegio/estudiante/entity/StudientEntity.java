package com.study.colegio.estudiante.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estudiantes")
@Data
public class StudientEntity {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentoIdentidad;

    private String nombres;

    private String apellidos;

    private String curso;

    private Integer edad;

    //Constructores

    public StudientEntity(){}



}
