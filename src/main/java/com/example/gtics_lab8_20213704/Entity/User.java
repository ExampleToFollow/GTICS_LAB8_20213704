package com.example.gtics_lab8_20213704.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "oreos")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "password", length = 500)
    private String password;

    @OneToMany(mappedBy = "idUser")
    private Set<Movie> movies = new LinkedHashSet<>();

}