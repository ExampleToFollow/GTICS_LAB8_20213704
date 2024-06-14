package com.example.gtics_lab8_20213704.Controller;

import com.example.gtics_lab8_20213704.Dao.MovieDao;
import com.example.gtics_lab8_20213704.Repository.MovieRepository;
import com.example.gtics_lab8_20213704.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class HomeController {

    private final MovieDao movieDao ;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository ;

    public HomeController(MovieDao movieDao,MovieRepository movieRepository,  UserRepository userRepository){
        this.movieDao = movieDao;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }
    //Pregunta 1
    @GetMapping(value="/peliculasEstreno")
    public Object getPeliculasEstreno() {
        Object o = movieDao.getListaPeliculas();
        LinkedHashMap<String, Object> ola = (LinkedHashMap<String, Object> ) o ;
        ArrayList<LinkedHashMap<String, Object>> list = ( ArrayList<LinkedHashMap<String, Object>>) ola.get("results");
        ArrayList<String> listPeliculas = new ArrayList<String>();
        for(LinkedHashMap<String, Object> aux : list){
            String nombre = (String) aux.get("original_title");
            listPeliculas.add(nombre);}
        LinkedHashMap<String , Object > response = new LinkedHashMap<>();
       response.put("Peliculas en estreno", listPeliculas);
        return ResponseEntity.ok(response);
    }

}
