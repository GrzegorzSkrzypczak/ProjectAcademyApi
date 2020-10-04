package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficialArtwork {

    private String front_default;

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public String getFront_default() {
        return front_default;
    }
}
