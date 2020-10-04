package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Types {

    @JsonProperty("type")
    private TypeDetails type;

    public void setType(TypeDetails type) {
        this.type = type;
    }

    public TypeDetails getType() {
        return type;
    }
}
