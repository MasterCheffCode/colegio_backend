package com.study.colegio.calificaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.calificaciones.controller.dto.QualificationsDTO;
import com.study.colegio.calificaciones.repository.MateriaRepository;
import com.study.colegio.calificaciones.repository.QualificationsRepository;
import com.study.colegio.calificaciones.repository.entity.QualificationsEntity;
import com.study.colegio.calificaciones.repository.mapper.QualificationsMapper;

@Service
public class QualificationsService {
    
    @Autowired
    QualificationsRepository qualificationsRepository;

    @Autowired
    private QualificationsMapper qualificationsMapper;

    @Autowired
    MateriaRepository materiaRepository;

    public List<QualificationsEntity> getAllQualificationes(){
        return qualificationsRepository.findAll();
    }

    public List<QualificationsEntity> getQualificationsByDocumento(Long documentoIdentidad) {
        return qualificationsRepository.findByDocumentoIdentidad(documentoIdentidad);

    }

    public QualificationsDTO saveQualifications(QualificationsDTO dto){
        QualificationsEntity entity = qualificationsMapper.toEntity(dto);
        return  qualificationsMapper.toDTO(qualificationsRepository.save(entity));        
    }

    
        
    }


    
    

