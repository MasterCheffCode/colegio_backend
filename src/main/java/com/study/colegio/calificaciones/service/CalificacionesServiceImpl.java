package com.study.colegio.calificaciones.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.calificaciones.controller.dto.CalificacionesDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;
import com.study.colegio.calificaciones.repository.CalificacionesRepository;
import com.study.colegio.calificaciones.repository.agrupador.AgrupadorCalificaciones;
import com.study.colegio.calificaciones.repository.entity.CalificacionesEntity;
import com.study.colegio.calificaciones.repository.mapper.CalificacionesMapper;

@Service
public class CalificacionesServiceImpl implements CalificacionesService{
    
    @Autowired
    CalificacionesRepository calificacionesRepository;

    @Autowired
    private CalificacionesMapper calificacionesMapper;


    @Override
    public List<CalificacionesDTO> obtenerTodas() {
        return AgrupadorCalificaciones.agrupar(calificacionesRepository.findAll().stream().map(entidad -> calificacionesMapper.toDTO(entidad)).collect(Collectors.toList()));
    }

    @Override
    public RequestDTO crearCalificacion(RequestDTO requestDTO) {
        CalificacionesEntity entity = calificacionesMapper.requestToEntity(requestDTO);
        return  calificacionesMapper.requestToDTO(calificacionesRepository.save(entity));   
    }

    
        
    }


    
    

