package pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.common.repository.PokemonRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokeApiPokemonListItemRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonList;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonListItem;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonListService {

    private final PokemonRepository pokemonRepository;
    private final PokemonLitsItemTransformer pokemonLitsItemTransformer;
    private final PokeApiPokemonListItemRepository pokeApiPokemonListItemRepository;
    private final String pagingUrl;


    @Autowired
    public PokemonListService(PokemonRepository pokemonRepository,
                              PokeApiPokemonListItemRepository pokeApiPokemonListItemRepository,
                              PokemonLitsItemTransformer pokemonLitsItemTransformer,
                              @Value("${paa.paging.url}") String pagingUrl) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiPokemonListItemRepository = pokeApiPokemonListItemRepository;
        this.pokemonLitsItemTransformer = pokemonLitsItemTransformer;
        this.pagingUrl = pagingUrl;
    }


    public PokemonList getPokemonList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Pokemon> pageItem = pokemonRepository.findAll(pageable);

        List<PokemonListItem> items = pageItem
                .stream()
                .map(Pokemon::getUrl)
                .map(pokeApiPokemonListItemRepository::getPokemonListItemReponse)
                .map(pokemonLitsItemTransformer::transformToPokemonListItem)
                .collect(Collectors.toList());
        return new PokemonList(pageItem.getTotalElements(),
                getNextLink(page, size, pageItem.getTotalPages()),
                getPrevLink(page, size),
                items);
    }

    private String getNextLink(int page, int size, int totalPages) {
        String next = null;


        if (page < totalPages) {
            next = String.format(pagingUrl, page + 1, size);
        }
        return next;


    }


    private String getPrevLink(int page, int size) {
        String prev = null;

        if (page > 1) {
            prev = String.format(pagingUrl, page - 1, size);
        }
        return prev;
    }
}
