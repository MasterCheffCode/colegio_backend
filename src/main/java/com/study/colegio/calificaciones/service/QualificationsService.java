package com.study.colegio.calificaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.calificaciones.entity.QualificationsEntity;
import com.study.colegio.calificaciones.repository.QualificationsRepository;
import com.study.colegio.estudiante.entity.StudientEntity;
import com.study.colegio.estudiante.repository.StudientRepository;

@Service
public class QualificationsService {
    
    @Autowired
    QualificationsRepository qualificationsRepository;

    @Autowired
    StudientRepository studientRepository;

    public List<QualificationsEntity> getAllQualificationes(){
        return qualificationsRepository.findAll();
    }

    public List<QualificationsEntity> getQualificationsByDocumento(String documento) {
        return qualificationsRepository.qualificacionsByDocumento(documento);
    }

    public QualificationsEntity saveQualifications(QualificationsEntity qualificationsEntity){
        // Auto-increment numeroNota per estudiante and periodo
        Integer maxNumero = qualificationsRepository.findMaxNumeroNotaByDocumentoAndPeriodo(
            qualificationsEntity.getEstudiante().getDocumentoIdentidad(),
            qualificationsEntity.getPeriodo()
        );
        Integer nextNumero = (maxNumero == null) ? 1 : maxNumero + 1;
        qualificationsEntity.setNumeroNota(nextNumero);

        return qualificationsRepository.save(qualificationsEntity);
    }

    public Optional<QualificationsEntity> getQualificationByDocumentoPeriodoNotaAndMateria(String documentoIdentidad,Integer periodo,Integer numeroNota,String materia){
        return qualificationsRepository.findByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(documentoIdentidad, periodo, numeroNota,materia);
    }

    public QualificationsEntity editQualification(Integer periodo,String documentoIdentidad,Integer numeroNota,String materia,QualificationsEntity qualificationsEntity){
        
        Optional<QualificationsEntity> quOptional = qualificationsRepository.findByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(documentoIdentidad,periodo,numeroNota,materia);

        if (quOptional.isPresent()) {
            QualificationsEntity qualificationExist = quOptional.get();


                qualificationExist.setNota(qualificationsEntity.getNota());
                qualificationExist.setNumeroNota(numeroNota);       
            
            
            return qualificationsRepository.save(qualificationExist);

            
        }else{
            throw new RuntimeException("Estudiante no encontrado con documentoIdentidad en las calificaciones: " + documentoIdentidad);
        }
    }

    public void deleteQualificationByDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(Integer periodo,String documentoIdentidad,Integer numeroNota,String materia){
        Optional<QualificationsEntity> qualification = qualificationsRepository.findByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(documentoIdentidad,periodo,numeroNota,materia);
        
        if (qualification.isPresent()) {
            qualificationsRepository.delete(qualification.get());
        }else{
            throw new RuntimeException("Calificaion no encontrada con los datos ingresados" );

        }
        
    }
}
