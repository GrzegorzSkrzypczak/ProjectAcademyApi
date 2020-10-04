package pl.sdaacademy.pokemonacademyapi.pokemon_details.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.PokemonRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetailsReponse;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetailsRepository;

import java.util.List;

@Service
public class PokemonService {


    private final PokemonRepository pokemonRepository;
    private final PokemonDetailsRepository pokemonDetailRepository;
    private final PokemonDetailsTransformer pokemonDetailsTransformer;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, PokemonDetailsRepository pokemonDetailRepository, PokemonDetailsTransformer pokemonDetailsTransformer) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonDetailRepository = pokemonDetailRepository;
        this.pokemonDetailsTransformer = pokemonDetailsTransformer;
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }

    public Pokemon getPokemon(String name) {
        return pokemonRepository
                .findByName(name)
                .stream().findAny()
                .orElseThrow(() -> {
                    throw new NoPokemonFoundException(name);
                });
    }

    public PokemonDetails getPokemonDetails(String name) {
        Pokemon pokemon = pokemonRepository.findByName(name).orElseThrow(()->
        {throw  new NoPokemonFoundException(name);
        });

        PokemonDetailsReponse reponse = pokemonDetailRepository.getPokemonDetailsReponse(pokemon.getUrl());
        return pokemonDetailsTransformer.transformToPokemon(reponse);

    }
}
