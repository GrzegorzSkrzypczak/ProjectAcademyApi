package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.image;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficialArtwork {

    @JsonProperty("front_default")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
