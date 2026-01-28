package com.rpdevelopment.user_service_api.entitie;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_address")
public class Address {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String road;
    private String number;
    private String neighborhood;
    private String complement;
    private String city;
    private String zipCode;

    //Atributos associados
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Construtores
    public Address() {
    }

    public Address(Long id, String road, String number, String neighborhood, String complement, String city, String zipCode, User user) {
        this.id = id;
        this.road = road;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.city = city;
        this.zipCode = zipCode;
        this.user = user;
    }

    //Getter|Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
