package pl.sdaacademy.pokemonacademyapi.pokemon_details.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.PokemonRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PokemonService {


    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemons () {
        return pokemonRepository.findAll();
    }

    public Pokemon getPokemon (String name) {
            return pokemonRepository
                    .findByName(name)
                    .stream().findAny()
                    .orElseThrow(() -> {throw new NoPokemonFoundException(name);
                    });
        }
}
