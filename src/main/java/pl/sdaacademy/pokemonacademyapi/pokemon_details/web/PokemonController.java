package pl.sdaacademy.pokemonacademyapi.pokemon_details.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.service.PokemonDetailService;

import java.util.List;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    private final PokemonDetailService pokemonDetailService;


    @Autowired
    public PokemonController(PokemonDetailService pokemonService) {
        this.pokemonDetailService = pokemonService;
    }

    @GetMapping("/{name}")
    public PokemonDetails getPokemon(@PathVariable String name) {
        return pokemonDetailService.getPokemonDetails(name);
    }

    @GetMapping
    public List<PokemonDetails> getPokemons(@RequestParam List<String> pokemonNames) {
        return pokemonDetailService.getMorePokemonsByName(pokemonNames);
    }



}
