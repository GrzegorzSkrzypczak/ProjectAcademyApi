package pl.sdaacademy.pokemonacademyapi.pokemon_list_item.repository;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonListItem {

    private String name;
    @JsonProperty("base_experience")
    private int lvl;
    private String imageUrl;

    public PokemonListItem() {
    }

    public PokemonListItem(String name, int lvl, String imageUrl) {
        this.name = name;
        this.lvl = lvl;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
