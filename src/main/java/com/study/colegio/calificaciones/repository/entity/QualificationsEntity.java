package com.study.colegio.calificaciones.repository.entity;
import java.util.List;


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

    private Long documentoIdentidad;

    private List<Integer> curso;
    private List<Integer> periodo;
    private List<Integer> materia;
    private List<Integer> nota;

}
