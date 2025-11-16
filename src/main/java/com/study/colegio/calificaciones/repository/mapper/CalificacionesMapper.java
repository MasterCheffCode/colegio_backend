package com.study.colegio.calificaciones.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.study.colegio.calificaciones.controller.dto.CursoDto;
import com.study.colegio.calificaciones.controller.dto.MateriaDTO;
import com.study.colegio.calificaciones.controller.dto.PeriodoDTO;
import com.study.colegio.calificaciones.controller.dto.CalificacionesDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;
import com.study.colegio.calificaciones.controller.dto.NotasDTO;
import com.study.colegio.calificaciones.repository.entity.CalificacionesEntity;

@Component
public class CalificacionesMapper {
    
    public CalificacionesDTO toDTO(CalificacionesEntity calificacionesEntity){
        if (calificacionesEntity == null) {
            return null;
        }

        CalificacionesDTO calificacionesDTO = new CalificacionesDTO();

        CursoDto cursoDto = new CursoDto();
        MateriaDTO materiaDTO = new MateriaDTO();
        PeriodoDTO periodoDTO = new PeriodoDTO();
        NotasDTO notasDTO = new NotasDTO();


        cursoDto.setIdCurso(calificacionesEntity.getCurso());
        materiaDTO.setIdMateria(calificacionesEntity.getMateria());
        periodoDTO.setIdPeriodo(calificacionesEntity.getPeriodo());
        notasDTO.setId(calificacionesEntity.getId());
        notasDTO.setNota(calificacionesEntity.getNota());


        List<NotasDTO> notas = new ArrayList<>();
        notas.add(notasDTO);

        List<PeriodoDTO> periodos = new ArrayList<>();
        periodoDTO.setCalificaciones(notas);
        periodos.add(periodoDTO);

        List<MateriaDTO> materias = new ArrayList<>();
        materiaDTO.setPeriodos(periodos);
        materias.add(materiaDTO);

        List<CursoDto> cursos = new ArrayList<>();
        cursoDto.setMaterias(materias);
        cursos.add(cursoDto);

        calificacionesDTO.setIdEstudiante(calificacionesEntity.getDocumentoIdentidad());
        calificacionesDTO.setCursos(cursos);
        

        return calificacionesDTO;
    }

     public CalificacionesEntity requestToEntity(RequestDTO requestDTO){
        if(requestDTO == null){
            return null;
        }

        CalificacionesEntity entity = new CalificacionesEntity();
        entity.setDocumentoIdentidad(requestDTO.getIdEstudiante());
        entity.setCurso(requestDTO.getIdCurso());
        entity.setPeriodo(requestDTO.getIdPeriodo());
        entity.setMateria(requestDTO.getIdMateria());
        entity.setNota(requestDTO.getNota()); 

        return entity;
    }

     public RequestDTO requestToDTO(CalificacionesEntity entity){
        if(entity == null){
            return null;
        }

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setIdEstudiante(entity.getDocumentoIdentidad());
        requestDTO.setIdCurso(entity.getCurso());
        requestDTO.setIdPeriodo(entity.getPeriodo());
        requestDTO.setIdMateria(entity.getMateria());
        requestDTO.setNota(entity.getNota()); 

        return requestDTO;
    }


}
