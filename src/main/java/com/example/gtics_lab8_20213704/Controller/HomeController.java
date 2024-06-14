package com.example.gtics_lab8_20213704.Controller;

import com.example.gtics_lab8_20213704.Dao.MovieDao;
import com.example.gtics_lab8_20213704.Entity.Movie;
import com.example.gtics_lab8_20213704.Entity.User;
import com.example.gtics_lab8_20213704.Repository.MovieRepository;
import com.example.gtics_lab8_20213704.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
        try {
            Object o = movieDao.getListaPeliculas();
            LinkedHashMap<String, Object> ola = (LinkedHashMap<String, Object>) o;
            ArrayList<LinkedHashMap<String, Object>> list = (ArrayList<LinkedHashMap<String, Object>>) ola.get("results");
            ArrayList<String> listPeliculas = new ArrayList<String>();
            for (LinkedHashMap<String, Object> aux : list) {
                String nombre = (String) aux.get("original_title");
                listPeliculas.add(nombre);
            }
            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            response.put("Peliculas en estreno", listPeliculas);
            return ResponseEntity.ok(response);
        }catch(Exception err){
            LinkedHashMap<String, Object> resp = new LinkedHashMap<String, Object>();
            resp.put("Error", "ocurrio un error inesperado");
            resp.put("Date", "" + LocalDateTime.now());
            return ResponseEntity.badRequest().body(resp);
        }
    }
    //Pregunta 2
    @PostMapping("/guardarPeliculaPorUsuario")
    public Object guardarPeliculaPorUsuario(@RequestParam(value = "username",required = false) String username,
                                            @RequestParam(value = "movieName",required = false) String movieName) {
        try {
            Object o = movieDao.getListaPeliculas();
            LinkedHashMap<String, Object> ola = (LinkedHashMap<String, Object>) o;
            ArrayList<LinkedHashMap<String, Object>> list = (ArrayList<LinkedHashMap<String, Object>>) ola.get("results");
            ArrayList<String> listPeliculas = new ArrayList<String>();
            for (LinkedHashMap<String, Object> aux : list) {
                String name = (String) aux.get("original_title");
                listPeliculas.add(name);
            }
            //listaPeliculas
            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            Boolean validacion=true;
            if(username==null||username.isEmpty()){
                response.put("Error", "debe poner su nombre de usuario");
                response.put("Date", "" + LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            if(movieName==null|| movieName.isEmpty()){
                response.put("Error", "debe poner el nombre de la pelicula");
                response.put("Date", "" + LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            User u = userRepository.findByUsername(username);
            if(u == null){
                response.put("Error", "no se encontro su usuario en la pagina");
                response.put("Date", "" + LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            if( ! listPeliculas.contains(movieName)){
                response.put("Error", "No existe la pelicula ingresada");
                response.put("Date", "" + LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            //Todo validadado entonces procedemos a agregar la pelicula a la bd
            Movie m = new Movie();
            m.setName(movieName);
            m.setIdUser(u);
            movieRepository.save(m);
            response.put("State", "Se guardo con exito la pelicula");
            response.put("Date", "" + LocalDateTime.now());
            return ResponseEntity.ok(response);
        }catch(Exception err){
            LinkedHashMap<String, Object> resp = new LinkedHashMap<String, Object>();
            resp.put("Error", "ocurrio un error inesperado");
            resp.put("Date", "" + LocalDateTime.now());
            return ResponseEntity.badRequest().body(resp);
        }

    }


}
