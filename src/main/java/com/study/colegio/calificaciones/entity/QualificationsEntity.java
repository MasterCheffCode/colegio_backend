package com.study.colegio.calificaciones.entity;
import com.study.colegio.estudiante.entity.StudientEntity;

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

    private Double nota;
    
    private Integer periodo;

    @Column(unique = true)
    private Integer numeroNota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estudiantes_documento_identidad")
    private StudientEntity estudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;

}
