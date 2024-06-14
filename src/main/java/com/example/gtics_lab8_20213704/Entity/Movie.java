package com.example.gtics_lab8_20213704.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie", schema = "oreos")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmovie", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User idUser;
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "overview", length = 1000)
    private String overview;
    @Column(name = "popularity", length = 45)
    private String popularity;
    @Column(name = "releasedate", length = 45)
    private String releaseDate;

}