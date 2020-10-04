package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PokemonDetailsRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public PokemonDetailsRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonDetailsReponse getPokemonDetailsReponse (String url){
        return restTemplate.getForObject(url, PokemonDetailsReponse.class);
    }
}
