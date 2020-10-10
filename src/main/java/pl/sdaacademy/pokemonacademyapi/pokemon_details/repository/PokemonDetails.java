package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import java.util.List;

public class PokemonDetails {

    private String name;
    private List<String> ability;
    private int hight;
    private int waight;
    private List<String> type;
    private String image;

    public PokemonDetails() {
    }

    public PokemonDetails(String name, List<String> ability,
                          int hight, int waight, List<String> type, String image) {
        this.name = name;
        this.ability = ability;
        this.hight = hight;
        this.waight = waight;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAbility() {
        return ability;
    }

    public void setAbility(List<String> ability) {
        this.ability = ability;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWaight() {
        return waight;
    }

    public void setWaight(int waight) {
        this.waight = waight;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
