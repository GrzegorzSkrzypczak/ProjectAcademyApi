package pl.sdaacademy.pokemonacademyapi.app_loader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonLoadService {

    private final PokemonRepository pokemonRepository;
    private final PokeapiRepository pokeapiRepository;

    @Autowired
    public PokemonLoadService(PokemonRepository pokemonRepository, PokeapiRepository pokeapiRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokeapiRepository = pokeapiRepository;

    }

    @PostConstruct
   public void loadPokemonsList(){

        List<PokemonResult> pokemonResultList =  new ArrayList<>();
        int offset = 0;
        int limit = 20;
        PokemonReponse pokemonReponse;

        do {
            pokemonReponse = pokeapiRepository.getPokemonReponse(limit, offset);
            pokemonResultList.addAll(pokemonReponse.getResult());
            offset += limit;


        } while(pokemonReponse.getNext() != null);
        List<Pokemon> pokemons = pokemonResultList.stream()
                .map(pokemonResult -> {String [] urlData = pokemonResult.getUrl().split("/");

                int id = Integer.parseInt(urlData[urlData.length-1]);
                return new Pokemon(id, pokemonResult.getName(),pokemonResult.getUrl());
                }).collect(Collectors.toList());
        pokemonRepository.saveAll(pokemons);
       }
}
