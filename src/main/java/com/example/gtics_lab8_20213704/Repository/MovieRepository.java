package com.example.gtics_lab8_20213704.Repository;

import com.example.gtics_lab8_20213704.Entity.Movie;
import com.example.gtics_lab8_20213704.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
