package com.rpdevelopment.user_service_api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rpdevelopment.user_service_api.entitie.Person;
import com.rpdevelopment.user_service_api.entitie.Type;

@JsonPropertyOrder({ "id", "document", "type"})
public class PersonDto {

    //Atributos
    private Long id;
    private String document;

    //Atributos associados
    private Type type;

    //Construtores
    public PersonDto() {
    }

    public PersonDto(Long id, Type type, String document) {
        this.id = id;
        this.type = type;
        this.document = document;
    }

    public PersonDto(Person entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.document = entity.getDocument();
    }

    //Getter
    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getDocument() {
        return document;
    }
}
