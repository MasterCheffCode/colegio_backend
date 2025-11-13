package com.study.colegio.calificaciones.controller;

import java.lang.foreign.Linker.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.*;

import com.study.colegio.calificaciones.entity.QualificationsEntity;
import com.study.colegio.calificaciones.service.QualificationsService;
import com.study.colegio.estudiante.entity.StudientEntity;
import com.study.colegio.estudiante.service.StudientService;





@RestController
@RequestMapping("/qualifications")
public class QualificationsController {
    
    private final QualificationsService qualificationsService;
    private final StudientService studientService;  // Agregar esto
    
    public QualificationsController(QualificationsService qualificationsService, StudientService studientService){
        this.qualificationsService = qualificationsService;
        this.studientService = studientService;
    }

    @GetMapping("/get_qualifications")
    public List<QualificationsEntity> getQualifications() {
        return qualificationsService.getAllQualificationes();
    }

    @PostMapping("/create_qualifications")
    public QualificationsEntity createQualifications(@RequestBody Map<String,Object> request) {
       
        String documentoIdentidad = (String) request.get("documentoIdentidad");
        String materia = (String) request.get("materia");
        Double nota = (Double) request.get("nota"); 
        Integer periodo = (Integer) request.get("periodo");
        Integer numeroNota = (Integer) request.get("numeroNota");
        
        Optional<StudientEntity> studientOpt = studientService.findStudientByDocumento(documentoIdentidad);

        if (studientOpt.isEmpty()) {
            throw new RuntimeException( "Estudiante no encontrado con documento de identidad: " + documentoIdentidad);
        }

        StudientEntity studient = studientOpt.get();

        QualificationsEntity qualification = new QualificationsEntity();
        qualification.setMateria(materia);
        qualification.setNota(nota);
        qualification.setEstudiante(studient);
        qualification.setNumeroNota(numeroNota);
        qualification.setPeriodo(periodo);

        return qualificationsService.saveQualifications(qualification);
    }

    @GetMapping("/{documentoIdentidad}")
    public List<QualificationsEntity> getQualificationsByDocumento(@PathVariable String documentoIdentidad) {
        return qualificationsService.getQualificationsByDocumento(documentoIdentidad);
    }

    @GetMapping("/student_with_qualifications/{documentoIdentidad}")
    public Map<String, Object> getStudentWithQualifications(@PathVariable String documentoIdentidad) {
        StudientEntity student = studientService.findStudientByDocumento(documentoIdentidad)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        List<QualificationsEntity> qualifications = qualificationsService.getQualificationsByDocumento(documentoIdentidad);
    
        Map<String, Object> response = new HashMap<>();
        response.put("estudiante", student);
        response.put("calificaciones", qualifications);
        return response;
    }

    @PutMapping("/update_qualifications/{periodo}/{documentoIdentidad}/{nota}/{materia}")
    public QualificationsEntity updateQualifications(@PathVariable Integer periodo,@PathVariable String documentoIdentidad,@PathVariable("nota") Integer numeroNota,@PathVariable String materia, @RequestBody QualificationsEntity entity) {
        
        return qualificationsService.editQualification(periodo, documentoIdentidad, numeroNota,materia,entity);
    }

    @DeleteMapping("/delete_qualification/{periodo}/{documentoIdentidad}/{nota}/{materia}")
    public void deleteQuelification(@PathVariable Integer periodo,@PathVariable String documentoIdentidad,@PathVariable("nota") Integer numeroNota,@PathVariable String materia){
        qualificationsService.deleteQualificationByDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(periodo, documentoIdentidad, numeroNota, materia);
    }
    
    

}
