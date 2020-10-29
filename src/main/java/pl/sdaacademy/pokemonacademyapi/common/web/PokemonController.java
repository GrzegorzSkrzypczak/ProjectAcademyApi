package pl.sdaacademy.pokemonacademyapi.common.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.service.PokemonDetailService;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonList;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service.PokemonListService;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUser;
import pl.sdaacademy.pokemonacademyapi.registration.service.PokemonApiUserDto;
import pl.sdaacademy.pokemonacademyapi.registration.service.PokemonUserApiService;

import java.util.List;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    private final PokemonDetailService pokemonDetailService;
    private final PokemonListService pokemonListService;
    private final PokemonUserApiService pokemonUserApiService;


    @Autowired
    public PokemonController(PokemonDetailService pokemonService, PokemonListService pokemonListService, PokemonUserApiService pokemonUserApiService) {
        this.pokemonDetailService = pokemonService;
        this.pokemonListService = pokemonListService;
        this.pokemonUserApiService = pokemonUserApiService;
    }

    @GetMapping("/{name}")
    @CrossOrigin
    public PokemonDetails getPokemon(@PathVariable String name) {
        return pokemonDetailService.getPokemonDetails(name);
    }

    @GetMapping
    @CrossOrigin
    public List<PokemonDetails> getPokemons(@RequestParam List<String> pokemonNames) {
        return pokemonDetailService.getPokemonDetailsList(pokemonNames);
    }

    @GetMapping("/list")
    @CrossOrigin
    public PokemonList getPokemonList(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {

        return pokemonListService.getPokemonList(page, size);
    }

    @PostMapping("/signup")
    @CrossOrigin
    public PokemonApiUserDto addUser(@RequestBody PokemonApiUser pokemonApiUser) {
        return pokemonUserApiService.addUser(pokemonApiUser);
    }
}


