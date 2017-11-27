package com.sda.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private String pesel;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(String firstname, String lastname, String pesel, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.username = username;
        this.password = password;
    }
}
