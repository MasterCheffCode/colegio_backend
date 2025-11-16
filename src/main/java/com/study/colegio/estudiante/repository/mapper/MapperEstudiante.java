package com.study.colegio.estudiante.repository.mapper;


import org.springframework.stereotype.Component;

import com.study.colegio.estudiante.controller.dto.EstudianteDTO;
import com.study.colegio.estudiante.repository.entity.EstudianteEntity;

@Component
public class MapperEstudiante {
    
    public EstudianteDTO toDTO(EstudianteEntity estudianteEntity){
        if (estudianteEntity == null) {
            return null;
        }

        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudianteEntity.getId());
        dto.setDocumentoIdentidad(estudianteEntity.getDocumentoIdentidad());
        dto.setNombre(estudianteEntity.getNombre());
        dto.setApellido(estudianteEntity.getApellido());
        dto.setEdad(estudianteEntity.getEdad());
        dto.setCurso(estudianteEntity.getCurso());
        return dto;

    }


    public EstudianteEntity toEntity(EstudianteDTO estudianteDTO){
        if(estudianteDTO == null){
            return null;
        }

        EstudianteEntity entity = new EstudianteEntity();
        entity.setId(estudianteDTO.getId());
        entity.setDocumentoIdentidad(estudianteDTO.getDocumentoIdentidad());
        entity.setNombre(estudianteDTO.getNombre());
        entity.setApellido(estudianteDTO.getApellido());
        entity.setEdad(estudianteDTO.getEdad()); 
        entity.setCurso(estudianteDTO.getCurso());

        return entity;
    }

}
