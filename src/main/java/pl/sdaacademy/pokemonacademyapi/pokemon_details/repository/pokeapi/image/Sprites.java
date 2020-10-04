package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.image;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sprites {

    @JsonProperty("other")
    private Other other;

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }
}
