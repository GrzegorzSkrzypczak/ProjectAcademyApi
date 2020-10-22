package pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonListItemResponse {

    private String name;

    @JsonProperty("base_experience")
    private int baseExperience;

    @JsonProperty("sprites")
    private Images images;

    public String getName() {
        return name;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public Images getImages() {
        return images;
    }
}
