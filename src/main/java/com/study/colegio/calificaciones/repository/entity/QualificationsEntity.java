package com.study.colegio.calificaciones.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calficaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer documentoIdentidad;

    private Integer curso;

    private Integer materia;
    private Integer periodo;
    private Double nota;

}
