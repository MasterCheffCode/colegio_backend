package com.study.colegio.estudiante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.colegio.estudiante.entity.StudientEntity;
import com.study.colegio.estudiante.repository.StudientRepository;

@Service
public class StudientService {

    @Autowired
    private StudientRepository studientRepository;

    //obtener todos los estudiantes
    public List<StudientEntity> getAllStudients(){
        return studientRepository.findAll();
    }

    public Optional<StudientEntity> findStudientByDocumento(String documentoIdentidad){
        return studientRepository.findByDocumentoIdentidad(documentoIdentidad);
    }

    public StudientEntity saveStudient(StudientEntity studientEntity){
        return studientRepository.save(studientEntity);
    }

    public StudientEntity editStudient(String documentoIdentidad,StudientEntity studientEdit){
        Optional<StudientEntity> studientExistenOp = studientRepository.findByDocumentoIdentidad(documentoIdentidad);

        if (studientExistenOp.isPresent()) {
            StudientEntity studientExist = studientExistenOp.get();

            studientExist.setDocumentoIdentidad(documentoIdentidad);
            studientExist.setNombre(studientEdit.getNombre());
            studientExist.setApellido(studientEdit.getApellido());
            studientExist.setCurso(studientEdit.getCurso());
            studientExist.setEdad(studientEdit.getEdad());
            return studientRepository.save(studientExist);
        }else{
            throw new RuntimeException("Studiante no encontrado con documentoIdentidad: " + documentoIdentidad);
        }
    }

    public void deleteStudient(String documentoIdentidad){
       Optional<StudientEntity> studient = studientRepository.findByDocumentoIdentidad(documentoIdentidad);

       if (studient.isPresent()) {
           studientRepository.delete(studient.get());
       }else {
           throw new RuntimeException("Studiante no encontrado con documentoIdentidad: " + documentoIdentidad);
       }
    }
    
}
