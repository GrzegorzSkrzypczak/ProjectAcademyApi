package pl.sdaacademy.pokemonacademyapi.pokemon_details.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.common.repository.PokemonRepository;
import pl.sdaacademy.pokemonacademyapi.common.service.NoPokemonFoundException;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetailsRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.PokeapiPokemonDetailsRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.PokemonDetailsReponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonDetailService {


    private final PokemonRepository pokemonRepository;
    private final PokeapiPokemonDetailsRepository pokeapiPokemonDetailsRepository;
    private final PokemonDetailsTransformer pokemonDetailsTransformer;
    private final PokemonDetailsRepository pokemonDetailsRepository;

    @Autowired
    public PokemonDetailService(PokemonRepository pokemonRepository, PokeapiPokemonDetailsRepository pokeapiPokemonDetailsRepository, PokemonDetailsTransformer pokemonDetailsTransformer, PokemonDetailsRepository pokemonDetailsRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokeapiPokemonDetailsRepository = pokeapiPokemonDetailsRepository;
        this.pokemonDetailsTransformer = pokemonDetailsTransformer;
        this.pokemonDetailsRepository = pokemonDetailsRepository;
    }


//    public PokemonDetails getPokemonDetails(String name) {
//
//        Pokemon pokemon = pokemonRepository.findByName(name).orElseThrow(() ->
//        {
//            throw new NoPokemonFoundException(name);
//        });
//
//        return providePokemonDetails(pokemon);
//    }

    public List<PokemonDetails> getPokemonDetailsList(List<String> pokemonNames) {
        List<PokemonDetails> pokemonDetails = pokemonNames.stream()
                .map(pokemonRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::providePokemonDetails)
                .collect(Collectors.toList());

        pokemonDetails.forEach(this::savePokemonDetailsToRepo);
        return pokemonDetails;

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


    private PokemonDetails providePokemonDetails(Pokemon pokemon) {
        return pokemonDetailsRepository
                .findById(pokemon.getName())
                .orElseGet(() -> {
                    PokemonDetails pokemonDetails = getPokemonDetailsFromApi(pokemon.getUrl());
                    savePokemonDetailsToRepo(pokemonDetails);
                    return pokemonDetails;
                });
    }

    private PokemonDetails getPokemonDetailsFromApi(String url) {
        PokemonDetailsReponse response = pokeapiPokemonDetailsRepository
                .getPokemonDetailsReponse(url);
        return pokemonDetailsTransformer.transformToPokemon(response);
    }


    private void savePokemonDetailsToRepo(PokemonDetails details) {
        pokemonDetailsRepository.findById(details.getName())
                .orElseGet(() -> pokemonDetailsRepository.save(details));
    }

}


