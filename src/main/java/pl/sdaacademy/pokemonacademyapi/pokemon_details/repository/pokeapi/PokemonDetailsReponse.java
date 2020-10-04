package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.ability.AbilitiesDetails;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.image.Sprites;
import pl.sdaacademy.pokemonacademyapi.pokemon_details.repository.pokeapi.type.Types;

import java.util.List;

@Repository
public class PokemonDetailsReponse {

    private String name;

    @JsonProperty("abilities")
    private List<AbilitiesDetails> abilitiesList;

    private int height;
    private int weight;

    @JsonProperty("types")
    private List<Types> types;

    @JsonProperty("sprites")
    private Sprites sprites;

    public PokemonDetailsReponse() {
    }

    public PokemonDetailsReponse(String name, List<AbilitiesDetails> abilitiesList,
                                 int hight, int waight, List<Types> types, Sprites sprites) {
        this.name = name;
        this.abilitiesList = abilitiesList;
        this.height = hight;
        this.weight = waight;
        this.types = types;
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AbilitiesDetails> getAbilitiesList() {
        return abilitiesList;
    }

    public void setAbilitiesList(List<AbilitiesDetails> abilitiesList) {
        this.abilitiesList = abilitiesList;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
