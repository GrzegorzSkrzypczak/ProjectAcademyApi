package pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.PokemonDetailsReponse;

@Repository
public class PokeApiPokemonListItemRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public PokeApiPokemonListItemRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonListItemResponse getPokemonListItemReponse(String url) {
        return restTemplate.getForObject(url, PokemonListItemResponse.class);
    }

}
