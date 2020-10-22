package pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.common.repository.PokemonRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokeApiPokemonListItemRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonListItem;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonListService {

    private final PokemonRepository pokemonRepository;
    private final PokemonLitsItemTransformer pokemonLitsItemTransformer;
    private final PokeApiPokemonListItemRepository pokeApiPokemonListItemRepository;


    @Autowired
    public PokemonListService(PokemonRepository pokemonRepository,
                              PokeApiPokemonListItemRepository pokeApiPokemonListItemRepository,
                              PokemonLitsItemTransformer pokemonLitsItemTransformer) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiPokemonListItemRepository = pokeApiPokemonListItemRepository;
        this.pokemonLitsItemTransformer =  pokemonLitsItemTransformer;
    }


    public List<PokemonListItem> getPokemonListItem () {
        return pokemonRepository.findAll()
                .stream()
                .map(Pokemon::getUrl)
                .map(pokeApiPokemonListItemRepository::getPokemonListItemReponse)
                .map(pokemonLitsItemTransformer::transformToPokemonListItem)
                .collect(Collectors.toList());
    }
}
