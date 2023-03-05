package com.example.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user", schema="public")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    private String surname;
    private String patronymic;

    private String email;
    private double balance;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}
