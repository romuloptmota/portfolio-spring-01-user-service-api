package com.rpdevelopment.user_service_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpdevelopment.user_service_api.dto.UserPersonAddressDto;
import com.rpdevelopment.user_service_api.exception.DuplicateResourceException;
import com.rpdevelopment.user_service_api.exception.ResourceNotFoundException;
import com.rpdevelopment.user_service_api.service.UserPersonAddressService;
import com.rpdevelopment.user_service_api.tests.UserFactoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserPersonAddressController.class)
public class UserControllerTest {

    //IDS
    private Long existingId;
    private Long nonExistingId;

    //DEPENDENTES
    @MockBean
    private UserPersonAddressService userPersonAddressService;

    private UserPersonAddressDto userPersonAddressDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        userPersonAddressDto = UserFactoryDto.createUserFactoryDto();
    }

    //GET ID EXISTENTE
    @Test
    public void findByIdShouldReturnUserWhenIdExists() throws Exception {

        //CHAMADA E RETORNO DO METODO
        Mockito.when(userPersonAddressService.usersFindById(existingId)).thenReturn(userPersonAddressDto);

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
        Mockito.when(userPersonAddressService.usersFindById(nonExistingId))
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

    //CREATE
    @Test
    public void createShouldReturn201AndUserDto() throws Exception {

        //CHAMADA E RETORNO DO METODO
        Mockito.when(userPersonAddressService.save(Mockito.any(UserPersonAddressDto.class)))
                .thenReturn(userPersonAddressDto);

        //CORPO DA REQUISIÇÃO
        String jsonBody = objectMapper.writeValueAsString(userPersonAddressDto);

        //POST
        ResultActions resultActions = mockMvc.perform(post("/users")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //VALIDAÇÃO
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(header().exists("Location")) //Validação URI
                .andExpect(jsonPath("$.name").value("Novo Usuario"))
                .andExpect(jsonPath("$.email").value("usuario@gmail.com"))
                .andExpect(jsonPath("$.person.document").value("507.332.198-64"))
                .andExpect(jsonPath("$.addresses[1].road").value("Rua A"));
    }

    //CREATE EMAIL DUPLICADO
    @Test
    public void createShouldReturn409WhenEmailAlreadyExists() throws Exception {

        //CHAMADA E RETORNO DO METODO
        Mockito.when(userPersonAddressService.save(Mockito.any(UserPersonAddressDto.class)))
                .thenThrow(new DuplicateResourceException("Email already exists"));

        //CORPO DA REQUISIÇÃO
        String jsonBody = objectMapper.writeValueAsString(userPersonAddressDto);

        //POST
        ResultActions resultActions = mockMvc.perform(post("/users")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //RESPOSTA ESPERADA
        resultActions.andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Email already exists"))
                .andExpect(jsonPath("$.path").value("/users"));;

    }

    //VALIDAÇÃO
    @Test
    public void createShouldReturn422WhenValidationFails() throws Exception {

        // DTO INVALIDO (ex: nome vazio se tiver @NotBlank)
        UserPersonAddressDto invalidDto = new UserPersonAddressDto(
                null,
                "",          // inválido @NotBlank
                "email-invalido",  // inválido @Email
                null,              // inválido @NotNull
                "123",             // inválido @Size
                null,
                null
        );


        //CORPO DA REQUISIÇÃO
        String jsonBody = objectMapper.writeValueAsString(invalidDto);

        //POST
        ResultActions resultActions = mockMvc.perform(post("/users")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //RESPOSTA ESPERADA
        resultActions.andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.error").exists());

        //VERIFICAÇÃO - service não chamado
        Mockito.verify(userPersonAddressService, Mockito.never()).save(Mockito.any());
    }
}
