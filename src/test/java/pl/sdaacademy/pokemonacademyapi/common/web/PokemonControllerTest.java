package pl.sdaacademy.pokemonacademyapi.common.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.sdaacademy.pokemonacademyapi.common.repository.Pokemon;
import pl.sdaacademy.pokemonacademyapi.common.repository.PokemonRepository;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.service.PokemonDetailService;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonList;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository.PokemonListItem;
import pl.sdaacademy.pokemonacademyapi.pokemon_list_item.service.PokemonListService;
import pl.sdaacademy.pokemonacademyapi.registration.service.PokemonUserApiService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonControllerTest {

    private static final String PIKATCHU = "Pikatchu";
    private static final String CHARLIZARD = "Charlizard";

    @Mock
    private PokemonDetailService pokemonDetailService;
    @Mock
    private PokemonListService pokemonListService;
    @Mock
    private PokemonUserApiService pokemonUserApiService;
    @Mock
    private PokemonController pokemonController;
    @Mock
    private PokemonDetails pokemonDetails;
    @Mock
    private PokemonRepository pokemonRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPokemon() {

        pokemonController =
                new PokemonController(pokemonDetailService,pokemonListService,pokemonUserApiService);

        List<String> abilitiesList = new ArrayList<>();
        abilitiesList.add("Thunder");
        List<String> typeList = new ArrayList<>();
        typeList.add("grass");

        Pokemon pokemon = new Pokemon();
        pokemon.setName(PIKATCHU);
        String pokemonName = pokemon.getName();

        pokemonDetails = getPokemonDetails(PIKATCHU,10,
                20,"testImage", abilitiesList,typeList);

        when(pokemonController.getPokemon(pokemonName)).thenReturn(pokemonDetails);

        PokemonDetails pokemon1 = pokemonController.getPokemon(pokemonName);
        String name = pokemon1.getName();

        Assertions.assertEquals(pokemonName, name);
        Assertions.assertNotEquals(CHARLIZARD,name);

    }

    @Test
    public void getPokemons() {
        pokemonController =
                new PokemonController(pokemonDetailService,pokemonListService,pokemonUserApiService);

        List<String> pikatchuAbilitiesList = new ArrayList<>();
        pikatchuAbilitiesList.add("Thunder");
        List<String> pikatchuTypeList = new ArrayList<>();
        pikatchuAbilitiesList.add("grass");

        List<String> charlizardAbilitiesList = new ArrayList<>();
        charlizardAbilitiesList.add("Thunder");
        List<String> charlizardTypeList = new ArrayList<>();
        charlizardTypeList.add("grass");

        PokemonDetails pikatchuDetails = getPokemonDetails(PIKATCHU,10,20
                ,"pic",pikatchuAbilitiesList, pikatchuTypeList);

        PokemonDetails charlizardDetails = getPokemonDetails(CHARLIZARD,30,60
                ,"pic",charlizardAbilitiesList, charlizardTypeList);

        List<PokemonDetails> pokemonDetailsList = new LinkedList<PokemonDetails>();
        pokemonDetailsList.add(pikatchuDetails);
        pokemonDetailsList.add(charlizardDetails);

        List<String> pokemonNamesList = new LinkedList<>();
        pokemonNamesList.add(PIKATCHU);
        pokemonNamesList.add(CHARLIZARD);

        when(pokemonController.getPokemons(pokemonNamesList)).thenReturn(pokemonDetailsList);

        List<PokemonDetails> pokemonsResult = pokemonController.getPokemons(pokemonNamesList);
        Assertions.assertEquals(2,pokemonsResult.size());
        Assertions.assertEquals(pokemonsResult.get(0).getName(), PIKATCHU);
        Assertions.assertEquals(pokemonsResult.get(1).getName(), CHARLIZARD);
    }

    @Test
    public void getPokemonList() {
        pokemonController =
                new PokemonController(pokemonDetailService,pokemonListService,pokemonUserApiService);

        Pageable pageable = PageRequest.of(2 - 1, 2);
        when(pokemonRepository.findAll()).thenReturn(List.of()); //TODO List<Pokemon>
        Page<Pokemon> pageItem = pokemonRepository.findAll(pageable);

        pokemonController.getPokemonList(1,2);
    }

    @Test
    public void addUser() {
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