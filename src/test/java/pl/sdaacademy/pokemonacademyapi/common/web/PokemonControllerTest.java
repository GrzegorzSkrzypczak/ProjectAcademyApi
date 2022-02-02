package pl.sdaacademy.pokemonacademyapi.common.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.service.PokemonDetailService;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonList;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonListItem;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service.PokemonListService;
import pl.sdaacademy.pokemonacademyapi.registration.service.PokemonUserApiService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class PokemonControllerTest {

    @Mock
    private PokemonDetailService pokemonDetailService;
    @InjectMocks
    private PokemonListService pokemonListService;
    @Mock
    private PokemonUserApiService pokemonUserApiService;
    @Mock
    private PokemonController pokemonController;
    @Mock
    private PokemonDetails pokemonDetails;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPokemon() {

        pokemonController =
                new PokemonController(pokemonDetailService,pokemonListService,pokemonUserApiService);

        List<String> abilitiesList = new ArrayList<>();
        abilitiesList.add("Thunder");
        List<String> typeList = new ArrayList<>();
        typeList.add("grass");

        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pikatchu");
        String pokemonName = pokemon.getName();

        pokemonDetails = getPokemonDetails("Pikatchu",10,
                20,"testImage", abilitiesList,typeList);

        when(pokemonController.getPokemon(pokemonName)).thenReturn(pokemonDetails);

        PokemonDetails pokemon1 = pokemonController.getPokemon(pokemonName);
        String name = pokemon1.getName();

        Assertions.assertEquals(pokemonName, name);
        Assertions.assertNotEquals("Charlizard",name);

    }

    @Test
    void getPokemons() {
    }

    @Test
    void getPokemonList() {
    }

    @Test
    void addUser() {
    }

    private PokemonDetails getPokemonDetails(String name, int height, int weight
            , String imageUrl, List<String> abilities, List<String> type) {

        pokemonDetails = new PokemonDetails();
        pokemonDetails.setHeight(height);
        pokemonDetails.setImage(imageUrl);
        pokemonDetails.setWeight(weight);
        pokemonDetails.setName(name);
        pokemonDetails.setAbilities(abilities);
        pokemonDetails.setTypes(type);

        return pokemonDetails;
    }
}