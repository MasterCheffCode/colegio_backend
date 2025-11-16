package com.study.colegio.calificaciones.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Integer idEstudiante;
    private Integer idCurso;
    private Integer idMateria;
    private Integer idPeriodo;
    private Double nota;
}
