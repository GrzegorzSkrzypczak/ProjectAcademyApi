package pl.sdaacademy.pokemonacademyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://localhost:8080/h2-console
// swagger link do strony: http://localhost:8080/swagger-ui.html

@SpringBootApplication
public class PokemonAcademyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonAcademyApiApplication.class, args);
    }

}
