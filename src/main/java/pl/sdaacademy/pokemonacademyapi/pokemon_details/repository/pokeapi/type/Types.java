package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Types {

    @JsonProperty("type")
    private TypeDetails type;

    public TypeDetails getType() {
        return type;
    }

    public void setType(TypeDetails type) {
        this.type = type;
    }
}
