package com.rpdevelopment.user_service_api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rpdevelopment.user_service_api.entity.Address;
import com.rpdevelopment.user_service_api.entity.User;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({ "id", "road", "number", "neighborhood", "complement", "city", "zipCode"})
public class AddressDto {

    //Atributos
    private Long id;
    @NotBlank(message = "Campo rua requerido")
    private String road;
    @NotBlank(message = "Campo numero requerido")
    private String number;
    @NotBlank(message = "Campo bairro requerido")
    private String neighborhood;
    @NotBlank(message = "Campo complemento requerido")
    private String complement;
    @NotBlank(message = "Campo cidade requerido")
    private String city;
    @NotBlank(message = "Campo cep requerido")
    private String zipCode;

    //Construtores
    public AddressDto() {
    }

    public AddressDto(Long id, String road, String number, String neighborhood, String complement, String city, String zipCode, User user) {
        this.id = id;
        this.road = road;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.city = city;
        this.zipCode = zipCode;
    }

    public AddressDto(Address entity) {
        this.id = entity.getId();
        this.road = entity.getRoad();
        this.number = entity.getNumber();
        this.neighborhood = entity.getNeighborhood();
        this.complement = entity.getComplement();
        this.city = entity.getCity();
        this.zipCode = entity.getZipCode();
    }

    //Getter
    public Long getId() {
        return id;
    }

    public String getRoad() {
        return road;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

}
