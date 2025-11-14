package com.study.colegio.calificaciones.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.study.colegio.calificaciones.entity.MateriaEntity;
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

    @PostMapping("/create_qualifications/{documentoIdentidad}")
    public ResponseEntity<?> createQualifications(@PathVariable String documentoIdentidad,@RequestBody QualificationsEntity request) {
       
        String materia = request.getMateria().getMateria();
        Double nota =  request.getNota(); 
        Integer periodo =  request.getPeriodo();
        Integer numeroNota =  request.getNumeroNota();
        
        Optional<StudientEntity> studientOpt = studientService.findStudientByDocumento(documentoIdentidad);
        if (studientOpt.isEmpty()) {
            throw new RuntimeException( "Estudiante no encontrado con documento de identidad: " + documentoIdentidad);
        }

        StudientEntity studient = studientOpt.get();

        QualificationsEntity qualification = new QualificationsEntity();
        qualification.setMateria(new MateriaEntity(null, materia));
        qualification.setNota(nota);
        qualification.setEstudiante(studient);
        qualification.setNumeroNota(numeroNota);
        qualification.setPeriodo(periodo);        

        try{
            QualificationsEntity result = qualificationsService.saveQualifications(qualification);
            return ResponseEntity.ok(result);
        } catch(RuntimeException e){
        
            if ("Se ha alcanzado el limite notas por este periodo".equals(e.getMessage())) {
                List<QualificationsEntity> quals = qualificationsService.getQualificationsByDocumento(documentoIdentidad);

                return ResponseEntity.badRequest().body(Map.of("mensaje",e.getMessage(),"PromedioFinal",qualificationsService.qualificationFinal(quals)));
            }
            throw e;
        }

    }

    @GetMapping("/{documentoIdentidad}")
    public List<Map<String,Object>> getQualificationsByDocumento(@PathVariable String documentoIdentidad) {

        List<QualificationsEntity> quals = qualificationsService.getQualificationsByDocumento(documentoIdentidad);
    
        return qualificationsService.mappeQualifications(quals);
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
