package pl.sdaacademy.pokemonacademyapi.pokemon_details.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.common.repository.PokemonRepository;
import pl.sdaacademy.pokemonacademyapi.common.service.NoPokemonFoundException;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.PokemonDetailsReponse;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.PokemonDetailsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonDetailService {


    private final PokemonRepository pokemonRepository;
    private final PokemonDetailsRepository pokemonDetailRepository;
    private final PokemonDetailsTransformer pokemonDetailsTransformer;

    @Autowired
    public PokemonDetailService(PokemonRepository pokemonRepository, PokemonDetailsRepository pokemonDetailRepository, PokemonDetailsTransformer pokemonDetailsTransformer) {
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
        Pokemon pokemon = pokemonRepository.findByName(name).orElseThrow(() ->
        {
            throw new NoPokemonFoundException(name);
        });

        PokemonDetailsReponse reponse = pokemonDetailRepository.getPokemonDetailsReponse(pokemon.getUrl());
        return pokemonDetailsTransformer.transformToPokemon(reponse);

    }

    public List<PokemonDetails> getMorePokemonsByName(List<String> pokemonNames) {
        return pokemonNames.stream()
                .map(pokemonRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Pokemon::getUrl)
                .map(pokemonDetailRepository::getPokemonDetailsReponse)
                .map(pokemonDetailsTransformer::transformToPokemon)
                .collect(Collectors.toList());

        // wersja pierwotna:

        /*
        return pokemonNames.stream().map((String pokemonName)-> {
            return pokemonRepository.findByName(pokemonName);
        }).filter((Optional<Pokemon> pokemonOptional)->{
            return pokemonOptional.isPresent();
        }).map((Optional<Pokemon> pokemonDetails)->{
            return pokemonDetails.get();
        }).map((Pokemon pokemon) ->{
            return pokemonDetailsRepository
                    .getPokemonDetailsResponse(pokemon.getUrl());
        }).map((PokemonDetailsResponse pokemonDetailsResponse)->{
            return pokemonDetailsTransformer.transformToPokemonDetails(pokemonDetailsResponse);
        }).collect(Collectors.toList());
         */


    }

}
