package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.image;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Other {

    @JsonProperty("official-artwork")
    private OfficialArtwork officialArtwork;

    public void setOfficialArtwork(OfficialArtwork officialArtwork) {
        this.officialArtwork = officialArtwork;
    }

    public OfficialArtwork getOfficialArtwork() {
        return officialArtwork;
    }
}
