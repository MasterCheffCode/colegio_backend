package com.study.colegio.calificaciones.service;

import java.util.List;

import com.study.colegio.calificaciones.controller.dto.CalificacionesDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;

public interface CalificacionesService {
    RequestDTO crearCalificacion(RequestDTO requestDTO);
    List<CalificacionesDTO> obtenerTodas();
}
