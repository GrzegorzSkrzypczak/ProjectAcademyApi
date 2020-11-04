package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class PokemonDetails {

    @ElementCollection
    private List<String> abilities;
    @ElementCollection
    private List<String> types;
    private int height;
    private int weight;
    private String image;
    @Id
    private String name;

    public PokemonDetails() {
    }

    public PokemonDetails(String name, List<String> ability,
                          int hight, int waight, List<String> type, String image) {
        this.name = name;
        this.abilities = ability;
        this.height = hight;
        this.weight = waight;
        this.types = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> ability) {
        this.abilities = ability;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int hight) {
        this.height = hight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int waight) {
        this.weight = waight;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> type) {
        this.types = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
