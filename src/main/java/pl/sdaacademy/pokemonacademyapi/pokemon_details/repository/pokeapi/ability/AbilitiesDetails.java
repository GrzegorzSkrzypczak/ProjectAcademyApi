package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.ability;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbilitiesDetails {

    @JsonProperty("ability")
    private Ability ability;


    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Ability getAbility() {
        return ability;
    }
}
