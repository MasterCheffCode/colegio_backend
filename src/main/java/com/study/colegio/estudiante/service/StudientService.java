package com.study.colegio.estudiante.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.estudiante.controller.dto.StudientDTO;
import com.study.colegio.estudiante.repository.StudientRepository;
import com.study.colegio.estudiante.repository.entity.StudientEntity;
import com.study.colegio.estudiante.repository.mapper.StudientMapper;

@Service
public class StudientService {

    @Autowired
    private StudientRepository studientRepository;

    @Autowired
    private StudientMapper studientMapper;

    //obtener todos los estudiantes
    public List<StudientDTO> getAllStudients(){
        return studientRepository.findAll().stream()
        .map(studientMapper::toDTO)
        .collect(Collectors.toList());
    }

    public StudientDTO findStudientByDocumento(Long documentoIdentidad){
        StudientEntity entity = studientRepository.findByDocumentoIdentidad(documentoIdentidad).orElseThrow(()-> new RuntimeException("estudiante no encontrado")) ;
        return  studientMapper.toDTO(entity);
    }

    public StudientDTO saveStudient(StudientDTO studientDTO){
        StudientEntity studientEntity = studientMapper.toEntity(studientDTO);
        return studientMapper.toDTO(studientRepository.save(studientEntity));
    }

   

    
}
