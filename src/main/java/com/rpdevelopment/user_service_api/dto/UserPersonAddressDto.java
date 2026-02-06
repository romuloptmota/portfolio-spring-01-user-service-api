package com.rpdevelopment.user_service_api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rpdevelopment.user_service_api.entitie.Address;
import com.rpdevelopment.user_service_api.entitie.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id","name", "email", "birthDate", "password", "person", "addresses"})
public class UserPersonAddressDto {

    //Atributos
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String password;

    //Atributos associados
    private PersonDto person;
    private List<AddressDto> addresses = new ArrayList<AddressDto>();

    //Construtores
    public UserPersonAddressDto() {
    }

    public UserPersonAddressDto(Long id, String name, String email, LocalDate birthDate, String password, PersonDto person, List<AddressDto> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.person = person;
        this.addresses = addresses;
    }

    public UserPersonAddressDto(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.birthDate = entity.getBirthDate();
        this.password = entity.getPassword();

        this.person = new PersonDto(entity.getPerson());

        for (Address address : entity.getAddresses()) {
            this.addresses.add(new AddressDto(address));
        }
    }

    //Métodos
    public void addAddresses(List<AddressDto> addresses) {
        addresses.addAll(this.addresses);
    }

    //Getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public PersonDto getPerson() {
        return person;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }
}
