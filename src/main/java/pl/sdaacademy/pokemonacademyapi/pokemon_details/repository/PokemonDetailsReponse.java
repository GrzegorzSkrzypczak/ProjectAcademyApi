package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PokemonDetailsReponse {

    private String name;

    @JsonProperty("abilities")
    List<AbilitiesDetails> abilitiesList;

    private int hight;
    private int weight;

    @JsonProperty("types")
    List<Types> types;

    @JsonProperty("sprites")
    Sprites sprites;

    public PokemonDetailsReponse() {
    }

    public PokemonDetailsReponse(String name, List<AbilitiesDetails> abilitiesList, int hight, int waight, List<Types> types, Sprites sprites) {
        this.name =  name;
        this.abilitiesList = abilitiesList;
        this.hight = hight;
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

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
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
