package pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service;

import org.springframework.stereotype.Component;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonListItem;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonListItemResponse;

@Component
public class PokemonLitsItemTransformer {

    public PokemonListItem transformToPokemonListItem(PokemonListItemResponse pokemonListItemResponse) {
        String name = pokemonListItemResponse.getName();
        int lvl = pokemonListItemResponse.getBaseExperience();
        String imageUrl = pokemonListItemResponse.getImages().getFrontDefault();
        return new PokemonListItem(name, lvl, imageUrl);

    }
}
