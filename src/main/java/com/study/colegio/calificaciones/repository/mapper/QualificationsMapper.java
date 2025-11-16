package com.study.colegio.calificaciones.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.study.colegio.calificaciones.controller.dto.CursoDto;
import com.study.colegio.calificaciones.controller.dto.MateriaDTO;
import com.study.colegio.calificaciones.controller.dto.PeriodoDTO;
import com.study.colegio.calificaciones.controller.dto.QualificationsDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;
import com.study.colegio.calificaciones.controller.dto.NotasDTO;
import com.study.colegio.calificaciones.repository.entity.QualificationsEntity;

@Component
public class QualificationsMapper {
    
    public QualificationsDTO toDTO(QualificationsEntity qualificationsEntity){
        if (qualificationsEntity == null) {
            return null;
        }

        QualificationsDTO qualificationsDTO = new QualificationsDTO();

        CursoDto cursoDto = new CursoDto();
        MateriaDTO materiaDTO = new MateriaDTO();
        PeriodoDTO periodoDTO = new PeriodoDTO();
        NotasDTO notasDTO = new NotasDTO();

        cursoDto.setIdCurso(qualificationsEntity.getCurso());
        materiaDTO.setIdMateria(qualificationsEntity.getMateria());
        periodoDTO.setIdPeriodo(qualificationsEntity.getPeriodo());
        notasDTO.setId(qualificationsEntity.getId());
        notasDTO.setNota(qualificationsEntity.getNota());


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

        qualificationsDTO.setCursos(cursos);
        

        return qualificationsDTO;
    }

    public QualificationsEntity toEntity(NotasDTO qualificacionsDTO){
        if(qualificacionsDTO == null){
            return null;
        }

        QualificationsEntity entity = new QualificationsEntity();
        entity.setId(qualificacionsDTO.getId());
        entity.setNota(qualificacionsDTO.getNota()); 

        return entity;
    }

     public QualificationsEntity requestToEntity(RequestDTO requestDTO){
        if(requestDTO == null){
            return null;
        }

        QualificationsEntity entity = new QualificationsEntity();
        entity.setDocumentoIdentidad(requestDTO.getIdEstudiante());
        entity.setCurso(requestDTO.getIdCurso());
        entity.setPeriodo(requestDTO.getIdPeriodo());
        entity.setMateria(requestDTO.getIdMateria());
        entity.setNota(requestDTO.getNota()); 

        return entity;
    }

     public RequestDTO requestToDTO(QualificationsEntity entity){
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
