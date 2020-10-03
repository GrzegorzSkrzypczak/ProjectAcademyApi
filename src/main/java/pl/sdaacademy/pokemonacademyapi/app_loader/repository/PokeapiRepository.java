package pl.sdaacademy.pokemonacademyapi.app_loader.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PokeapiRepository {

    private static final String URL = "https://pokeapi.co/api/v2/pokemon?limit=%d&offset=%d";

    public PokemonReponse getPokemonReponse(int limit, int offset) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                String.format(URL,limit,offset), PokemonReponse.class);
    }
}
