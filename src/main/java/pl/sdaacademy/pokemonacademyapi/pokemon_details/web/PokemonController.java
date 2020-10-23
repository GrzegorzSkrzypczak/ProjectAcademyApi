package pl.sdaacademy.pokemonacademyapi.pokemon_details.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.service.PokemonDetailService;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonList;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service.PokemonListService;

import java.util.List;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    private final PokemonDetailService pokemonDetailService;
    private final PokemonListService pokemonListService;


    @Autowired
    public PokemonController(PokemonDetailService pokemonService, PokemonListService pokemonListService) {
        this.pokemonDetailService = pokemonService;
        this.pokemonListService = pokemonListService;
    }

//    @GetMapping("/{name}")
//    public PokemonDetails getPokemon(@PathVariable String name) {
//        return pokemonDetailService.getPokemonDetails(name);
//    }

    @GetMapping
    public List<PokemonDetails> getPokemons(@RequestParam List<String> pokemonNames) {
        return pokemonDetailService.getPokemonDetailsList(pokemonNames);
    }

    @GetMapping("/list")
    public PokemonList getPokemonList(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {

        return pokemonListService.getPokemonList(page, size);
    }


}
