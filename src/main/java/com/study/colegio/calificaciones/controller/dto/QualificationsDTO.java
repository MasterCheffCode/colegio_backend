package com.study.colegio.calificaciones.controller.dto;

import java.util.List;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationsDTO {

    
    private Long documentoIdentidad;

    private Integer curso;
    private Integer periodo;
    private Integer materia;
    private Integer nota;

}
