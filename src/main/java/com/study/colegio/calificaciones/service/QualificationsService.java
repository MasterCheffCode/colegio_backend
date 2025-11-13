package com.study.colegio.calificaciones.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.calificaciones.entity.MateriaEntity;
import com.study.colegio.calificaciones.entity.QualificationsEntity;
import com.study.colegio.calificaciones.repository.MateriaRepository;
import com.study.colegio.calificaciones.repository.QualificationsRepository;
import com.study.colegio.estudiante.entity.StudientEntity;
import com.study.colegio.estudiante.repository.StudientRepository;

@Service
public class QualificationsService {
    
    @Autowired
    QualificationsRepository qualificationsRepository;

    @Autowired
    StudientRepository studientRepository;

    @Autowired
    MateriaRepository materiaRepository;

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
        
        MateriaEntity materia = materiaRepository.save(qualificationsEntity.getMateria());
        Integer nextNumero = (maxNumero == null) ? 1 : maxNumero + 1;
        if (nextNumero <= qualificationsEntity.getMateria().getNotasTotal()) {
            qualificationsEntity.setNumeroNota(nextNumero);
            return qualificationsRepository.save(qualificationsEntity);
        }else{
            throw new RuntimeException("Se ha alcanzado el limite notas por este periodo"); 
        }



    }

    public Optional<QualificationsEntity> getQualificationByDocumentoPeriodoNotaAndMateria(String documentoIdentidad,Integer periodo,Integer numeroNota,String nombreMateria){
        return qualificationsRepository.findByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(documentoIdentidad, periodo, numeroNota,nombreMateria);
    }

    public QualificationsEntity editQualification(Integer periodo,String documentoIdentidad,Integer numeroNota,String nombreMateria,QualificationsEntity qualificationsEntity){
        
        Optional<QualificationsEntity> quOptional = qualificationsRepository.findByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(documentoIdentidad,periodo,numeroNota,nombreMateria);

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


    public List<Map<String,Object>> mappeQualifications(List<QualificationsEntity> quals ){
        if (quals.isEmpty()) {
            return Collections.emptyList();
        }

        StudientEntity estudiante = quals.get(0).getEstudiante();

        Double promedioFinal = 0.0;
        Double notasTotalPromedio = 0.0;

        Map<String,List<QualificationsEntity>> byMateria = quals.stream()
        .collect(Collectors.groupingBy(q -> q.getMateria().getMateria()));

        List<Map<String,Object>> result = new ArrayList<>();

        for (Map.Entry<String,List<QualificationsEntity>> entry : byMateria.entrySet()){
            Map<String,Object> item = new HashMap<>();

            Map<String,Object> estMap = new HashMap<>();

            estMap.put("documentoIdentidad", estudiante.getDocumentoIdentidad());

            estMap.put("nombre", estudiante.getNombre());

            estMap.put("apellido", estudiante.getApellido());

            estMap.put("curso", estudiante.getCurso());

            estMap.put("edad", estudiante.getEdad());

            estMap.put("estudiante", estMap);

            Map<String, Object> matMap = new HashMap<>();

                matMap.put("nombreMateria", entry.getKey());

                matMap.put("notasTotal", entry.getValue().get(0).getMateria().getNotasTotal());

            Map<String,Object> notasMap = new HashMap<>();

            List<QualificationsEntity> notas = entry.getValue();

            for (int i = 0; i < notas.size(); i++) {
            QualificationsEntity q = notas.get(i);
            Map<String, Object> notaMap = new HashMap<>();
            notaMap.put("nota", q.getNota());
            notaMap.put("periodo", q.getPeriodo());
            notasMap.put("nota" + (i + 1), notaMap);
            notasTotalPromedio = notasTotalPromedio + q.getNota();
            if(entry.getValue().get(0).getMateria().getNotasTotal()==notas.size()){
                promedioFinal = notasTotalPromedio/ entry.getValue().get(0).getMateria().getNotasTotal();
            }
        }
        matMap.put("notas", notasMap);
        notasMap.put("promedioFinal", promedioFinal);
        
            item.put("materia", matMap);
            result.add(item);
        }

        return result;
    }

    public List<Map<String,Object>> qualificationFinal(List<QualificationsEntity> quals ){
        if (quals.isEmpty()) {
            return Collections.emptyList();
        }


        Double promedioFinal = 0.0;
        Double notasTotalPromedio = 0.0;

        Map<String,List<QualificationsEntity>> byMateria = quals.stream()
        .collect(Collectors.groupingBy(q -> q.getMateria().getMateria()));

        List<Map<String,Object>> result = new ArrayList<>();

        for (Map.Entry<String,List<QualificationsEntity>> entry : byMateria.entrySet()){
            Map<String,Object> item = new HashMap<>();

            Map<String, Object> matMap = new HashMap<>();

                matMap.put("notasTotal", entry.getValue().get(0).getMateria().getNotasTotal());

            Map<String,Object> notasMap = new HashMap<>();

            List<QualificationsEntity> notas = entry.getValue();

            for (int i = 0; i < notas.size(); i++) {
            QualificationsEntity q = notas.get(i);
            notasTotalPromedio = notasTotalPromedio + q.getNota();
            if(entry.getValue().get(0).getMateria().getNotasTotal()==notas.size()){
                promedioFinal = notasTotalPromedio/ entry.getValue().get(0).getMateria().getNotasTotal();
            }
        }
        notasMap.put("materia", entry.getValue().get(0).getMateria());
        notasMap.put("promedioFinal", promedioFinal);
        
            item.put("materia", notasMap);
            result.add(item);
        }

        return result;
    }
}
