package com.rpdevelopment.user_service_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpdevelopment.user_service_api.dto.UserPersonAddressDto;
import com.rpdevelopment.user_service_api.exception.ResourceNotFoundException;
import com.rpdevelopment.user_service_api.service.UserPersonAddressService;
import com.rpdevelopment.user_service_api.tests.UserFactoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserPersonAddressController.class)
public class UserControllerDtoSliceTest {

    //IDS
    private Long existingId;
    private Long nonExistingId;

    //DEPENDENTES
    @MockBean
    private UserPersonAddressService service;

    private UserPersonAddressDto dto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        dto = UserFactoryDto.createUserFactoryDto();
    }

    //GET ID EXISTENTE
    @Test
    public void findByIdShouldReturnUserWhenIdExists() throws Exception {

        //CHAMADA E RETORNO DO METODO
        Mockito.when(service.usersFindById(existingId)).thenReturn(dto);

        //FIND ID - CHAMADA DO ENDPOINT
        ResultActions resultActions =
                mockMvc.perform(get("/users/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));


        //VALIDAÇÃO - DADOS ESPERADOS JASON
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(jsonPath("$.name").value("Novo Usuario"))
                .andExpect(jsonPath("$.email").value("usuario@gmail.com"))
                .andExpect(jsonPath("$.person.document").value("507.332.198-64"))
                .andExpect(jsonPath("$.addresses[1].road").value("Rua A"));
    }

    //GET ID NÃO EXISTENTE
    @Test
    public void findByIdShouldResourceNotFoundExceptionWhenIdNonExistis() throws Exception {

        //CHAMADA E RETORNO DO METODO
        Mockito.when(service.usersFindById(nonExistingId))
                .thenThrow(new ResourceNotFoundException("User not found"));

        //FIND ID - CHAMADA DO ENDPOINT
        ResultActions resultActions =
                mockMvc.perform(get("/users/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        //RESPOSTA ESPERADA
        resultActions.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.path").value("/users/" + nonExistingId));
    }

}
