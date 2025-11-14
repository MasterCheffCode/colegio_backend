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

 

    private Long documentoIdentidad;

    private Integer curso;
    private Integer periodo;
    private Integer materia;
    private Integer nota;

}
