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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documentoIdentidad;

    private List<Integer> curso;
    private List<Integer> periodo;
    private List<Integer> materia;
    private List<Integer> nota;

}
