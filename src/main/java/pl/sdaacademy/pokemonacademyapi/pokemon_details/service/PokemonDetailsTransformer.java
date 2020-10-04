package pl.sdaacademy.pokemonacademyapi.pokemon_details.service;

import org.springframework.stereotype.Component;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.PokemonDetailsReponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonDetailsTransformer {

    public PokemonDetails transformToPokemon(PokemonDetailsReponse pokemonDatailsResponse) {
        String name = pokemonDatailsResponse.getName();
        int hight = pokemonDatailsResponse.getHeight();
        int weight = pokemonDatailsResponse.getWeight();
        String image = pokemonDatailsResponse
                .getSprites()
                .getOther()
                .getOfficialArtwork()
                .getImage();

        List<String> abilities = pokemonDatailsResponse.getAbilitiesList()
                .stream()
                .map(ability -> ability.getAbility().getName())
                .collect(Collectors.toList());

        List<String> types = pokemonDatailsResponse.getTypes()
                .stream()
                .map(type -> type.getType().getName())
                .collect(Collectors.toList());

        return new PokemonDetails(name,abilities,hight,weight,types,image);

    }

}
