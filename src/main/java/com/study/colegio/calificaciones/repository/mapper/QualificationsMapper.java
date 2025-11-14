package com.study.colegio.calificaciones.repository.mapper;

import org.springframework.stereotype.Component;

import com.study.colegio.calificaciones.controller.dto.QualificationsDTO;
import com.study.colegio.calificaciones.repository.entity.QualificationsEntity;

@Component
public class QualificationsMapper {
    
    public QualificationsDTO toDTO(QualificationsEntity qualificationsEntity){
        if (qualificationsEntity == null) {
            return null;
        }

        QualificationsDTO dto = new QualificationsDTO();
        dto.setDocumentoIdentidad(qualificationsEntity.getDocumentoIdentidad());
        dto.setCurso(qualificationsEntity.getCurso());
        dto.setPeriodo(qualificationsEntity.getPeriodo());
        dto.setMateria(qualificationsEntity.getMateria());
        dto.setNota(qualificationsEntity.getNota());

        return dto;
    }

    public QualificationsEntity toEntity(QualificationsDTO qualificacionsDTO){
        if(qualificacionsDTO == null){
            return null;
        }

        QualificationsEntity entity = new QualificationsEntity();
        entity.setDocumentoIdentidad(qualificacionsDTO.getDocumentoIdentidad());
        entity.setCurso(qualificacionsDTO.getCurso());
        entity.setPeriodo(qualificacionsDTO.getPeriodo());
        entity.setMateria(qualificacionsDTO.getMateria());
        entity.setNota(qualificacionsDTO.getNota()); 

        return entity;
    }

}
