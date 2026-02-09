package com.rpdevelopment.user_service_api.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_person")
public class Person {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;

    //Atributos Associados
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    //Construtores
    public Person() {
    }

    public Person(Long id, String document, Type type, User user) {
        this.id = id;
        this.document = document;
        this.type = type;
        this.user = user;
    }

    public Person(Person entity) {
        this.id = entity.getId();
        this.document = entity.getDocument();
        this.type = entity.getType();
        this.user = entity.getUser();
    }

    //Getter|Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //equals|hashcode - document
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(document, person.document);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(document);
    }
}
