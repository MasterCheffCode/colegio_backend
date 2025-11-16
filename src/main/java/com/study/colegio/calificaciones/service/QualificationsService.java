package com.study.colegio.calificaciones.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.calificaciones.controller.dto.NotasDTO;
import com.study.colegio.calificaciones.controller.dto.QualificationsDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;
import com.study.colegio.calificaciones.repository.QualificationsRepository;
import com.study.colegio.calificaciones.repository.entity.QualificationsEntity;
import com.study.colegio.calificaciones.repository.mapper.QualificationsMapper;

@Service
public class QualificationsService {
    
    @Autowired
    QualificationsRepository qualificationsRepository;

    @Autowired
    private QualificationsMapper qualificationsMapper;


    public List<QualificationsDTO> getAllQualificationes(){
        List<QualificationsEntity> qualificationsEntities = qualificationsRepository.findAll();
        System.err.println(qualificationsEntities);
        List<QualificationsDTO> dtos = qualificationsEntities.stream().map(entidad -> qualificationsMapper.toDTO(entidad))
        .collect(Collectors.toList());
        return dtos;
    }

    public List<QualificationsEntity> getQualificationsByDocumento(Long documentoIdentidad) {
        return qualificationsRepository.findByDocumentoIdentidad(documentoIdentidad);

    }

    public RequestDTO saveQualifications(RequestDTO dto){
        System.err.println(dto);
        QualificationsEntity entity = qualificationsMapper.requestToEntity(dto);
        System.err.println(entity);
        return  qualificationsMapper.requestToDTO(qualificationsRepository.save(entity));        
    }

    
        
    }


    
    

