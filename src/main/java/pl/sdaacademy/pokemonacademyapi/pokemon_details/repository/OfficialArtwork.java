package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficialArtwork {

    @JsonProperty("front_default")
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
