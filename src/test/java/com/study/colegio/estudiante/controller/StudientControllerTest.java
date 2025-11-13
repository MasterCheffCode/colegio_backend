package com.study.colegio.estudiante.controller;

import com.study.colegio.estudiante.entity.StudientEntity;
import com.study.colegio.estudiante.repository.StudientRepository;
import com.study.colegio.estudiante.service.StudientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(StudientController.class)
public class StudientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudientRepository studientRepository;

    @MockitoBean
    private StudientService studientService;

    @Test
    void getAllStudients() throws Exception {

        StudientEntity s1 = new StudientEntity();
        s1.setId(1L);
        s1.setDocumentoIdentidad("1004345037");
        s1.setNombre("Elian");
        s1.setApellido("Ureche");
        s1.setCurso("4-B");
        s1.setEdad(25);

        StudientEntity s2 = new StudientEntity();
        s2.setId(2L);
        s2.setDocumentoIdentidad("36544286");
        s2.setNombre("Farides");
        s2.setApellido("Lopez");
        s2.setCurso("6-B");
        s2.setEdad(60);

        List<StudientEntity> studients = List.of(
                s1,s2
        );

        when(studientService.getAllStudients())
                .thenReturn(studients);

        mockMvc.perform(get("/studients/get_studients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void postStudiant() throws Exception{
        StudientEntity s1 = new StudientEntity(1L,"254585","Juan", "Perez","5-C",23);

        when(studientService.saveStudient(any(StudientEntity.class))).thenReturn(s1);

        String json = """
            {
                "id": 1,
                "documentoIdentidad": "254585",
                "nombre": "Juan",
                "apellido": "Perez",
                "curso": "5-C",
                "edad": 23
            }
            """;

        mockMvc.perform(post("/studients/create_studient")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.documentoIdentidad").value("254585"));

        verify(studientService, times(1)).saveStudient(any(StudientEntity.class));
    }

    @Test
    void putStudient()throws Exception{
        StudientEntity studientExist = new StudientEntity(1L,"254585","Juan", "Perez","5-C",23);
        
        StudientEntity studientUpdate = new StudientEntity(1L,"254586","Pedro", "Perez","5-C",23);


        when(studientService.editStudient(anyString(),any(StudientEntity.class))).thenReturn(studientUpdate);

        String json = """
            {
                "id": 1,
                "documentoIdentidad": "254586",
                "nombre": "Pedro",
                "apellido": "Perez",
                "curso": "5-C",
                "edad": 23
            }
            """;

        mockMvc.perform(put("/studients/{documentoIdentidad}","254585")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.documentoIdentidad").value("254586"));
        

    }

    @Test
    void deleteStudient()throws Exception{
        String documentoIdentidad = "254585";

    doNothing().when(studientService).deleteStudient(any(String.class));

        mockMvc.perform(delete("/studients/{documentoIdentidad}", documentoIdentidad)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
    
    verify(studientService, times(1)).deleteStudient(documentoIdentidad);
    }
}
