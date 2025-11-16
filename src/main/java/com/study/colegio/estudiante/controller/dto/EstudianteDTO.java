package com.study.colegio.estudiante.controller.dto;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long documentoIdentidad;

    private String nombre;

    private String apellido;

    private Integer curso;

    private Integer edad;

}