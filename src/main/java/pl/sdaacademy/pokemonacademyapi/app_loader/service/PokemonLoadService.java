package pl.sdaacademy.pokemonacademyapi.app_loader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.PokeApiRepository;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.PokemonReponse;
import pl.sdaacademy.pokemonacademyapi.app_loader.repository.PokemonResult;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.common.repository.PokemonRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonLoadService {

    private final PokemonRepository pokemonRepository;
    private final PokeApiRepository pokeapiRepository;
    private final PokemonTransformer pokemonTransformer;
    private final int startOffset;
    private final int limiter;

    @Autowired
    public PokemonLoadService(PokemonRepository pokemonRepository, PokeApiRepository pokeapiRepository,
                              PokemonTransformer pokemonTransformer,
                              @Value("${pokeapi.star_offset}") int startOffset,
                              @Value("${pokeapi.limit}") int limiter) {
        this.pokemonRepository = pokemonRepository;
        this.pokeapiRepository = pokeapiRepository;
        this.pokemonTransformer = pokemonTransformer;
        this.startOffset = startOffset;
        this.limiter = limiter;
    }

    @PostConstruct
    public void loadPokemonsList() {

        List<PokemonResult> pokemonResultList = new ArrayList<>();
        int offset = startOffset;
        int limit = limiter;
        PokemonReponse pokemonReponse;

        do {
            pokemonReponse = pokeapiRepository.getPokemonReponse(limit, offset);
            pokemonResultList.addAll(pokemonReponse.getResult());
            offset += limit;

        } while (pokemonReponse.getNext() != null);
        List<Pokemon> pokemons = pokemonTransformer.transformToPokemonList(pokemonResultList);
        pokemonRepository.saveAll(pokemons);
    }
}
