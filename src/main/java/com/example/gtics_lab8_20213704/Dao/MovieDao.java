package com.example.gtics_lab8_20213704.Dao;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class MovieDao {
    public Object getListaPeliculas(){
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDViZGIzNmQ4NzI5MzlhZjJkZGNkODU0ZTk5ZmU0MCIsInN1YiI6IjY2NmI4YzNkMWVjN2EyMTcyOTI0ODVjZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9FWm5B9edw1SuFLFoXxi1l5jUKIbGsIUPvEgnzvh1Yo")
                .build();
        ResponseEntity<Object> response = restTemplate.getForEntity(
                    "https://api.themoviedb.org/3/discover/movie", Object.class);
        return response.getBody();
    }
}
