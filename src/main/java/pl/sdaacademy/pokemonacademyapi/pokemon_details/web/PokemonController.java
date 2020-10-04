package pl.sdaacademy.pokemonacademyapi.pokemon_details.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.service.PokemonService;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    private final PokemonService pokemonService;


    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{name}")
    public Pokemon getPokemon(@PathVariable String name) {
        return pokemonService.getPokemon(name);
    }
}
