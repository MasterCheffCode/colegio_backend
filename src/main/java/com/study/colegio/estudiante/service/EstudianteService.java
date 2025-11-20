package com.study.colegio.estudiante.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.estudiante.controller.dto.EstudianteDTO;
import com.study.colegio.estudiante.repository.EstudianteRepository;
import com.study.colegio.estudiante.repository.entity.EstudianteEntity;
import com.study.colegio.estudiante.repository.mapper.MapperEstudiante;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository studientRepository;

    @Autowired
    private MapperEstudiante studientMapper;

    public List<EstudianteDTO> obtenerTodosEstudiantes(){
        return studientRepository.findAll().stream()
        .map(studientMapper::toDTO)
        .collect(Collectors.toList());
    }


    public EstudianteDTO guardarEstudiante(EstudianteDTO studientDTO){
        EstudianteEntity studientEntity = studientMapper.toEntity(studientDTO);
        return studientMapper.toDTO(studientRepository.save(studientEntity));
    }

   

    
}
