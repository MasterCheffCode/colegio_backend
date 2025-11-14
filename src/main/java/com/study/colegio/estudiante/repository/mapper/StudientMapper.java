package com.study.colegio.estudiante.repository.mapper;


import org.springframework.stereotype.Component;

import com.study.colegio.estudiante.controller.dto.StudientDTO;
import com.study.colegio.estudiante.repository.entity.StudientEntity;

@Component
public class StudientMapper {
    
    public StudientDTO toDTO(StudientEntity studientEntity){
        if (studientEntity == null) {
            return null;
        }

        StudientDTO dto = new StudientDTO();
        dto.setDocumentoIdentidad(studientEntity.getDocumentoIdentidad());
        dto.setNombre(studientEntity.getNombre());
        dto.setApellido(studientEntity.getApellido());
        dto.setEdad(studientEntity.getEdad());
        dto.setCurso(studientEntity.getCurso());
        return dto;

    }


    public StudientEntity toEntity(StudientDTO studientDTO){
        if(studientDTO == null){
            return null;
        }

        StudientEntity entity = new StudientEntity();
        entity.setDocumentoIdentidad(studientDTO.getDocumentoIdentidad());
        entity.setNombre(studientDTO.getNombre());
        entity.setApellido(studientDTO.getApellido());
        entity.setEdad(studientDTO.getEdad()); 
        entity.setCurso(studientDTO.getCurso());

        return entity;
    }

}
