package com.study.colegio.calificaciones.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {
    
    private Integer  idCurso;

    private List<MateriaDTO> materias;

}
