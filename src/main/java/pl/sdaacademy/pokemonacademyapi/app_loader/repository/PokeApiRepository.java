package pl.sdaacademy.pokemonacademyapi.app_loader.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PokeApiRepository {

    private final String urlAddress;
    private final RestTemplate restTemplate;

    public PokeApiRepository(@Value("${pokeapi.url.address}") String urlAddress, RestTemplate restTemplate) {
        this.urlAddress = urlAddress;
        this.restTemplate = restTemplate;
    }

    public PokemonReponse getPokemonReponse(int limit, int offset) {
        return restTemplate.getForObject(
                String.format(urlAddress, limit, offset), PokemonReponse.class);
    }
}
