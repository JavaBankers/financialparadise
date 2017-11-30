package com.sda.spring.entity;

import com.sda.spring.validation.PeselValidator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @PeselValidator()
    private String pesel;
    private String email;
    private String password;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String pesel, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.email = email;
        this.password = password;
    }
}
