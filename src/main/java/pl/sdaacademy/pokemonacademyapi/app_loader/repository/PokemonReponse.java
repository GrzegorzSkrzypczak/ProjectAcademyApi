package pl.sdaacademy.pokemonacademyapi.app_loader.repository;

import java.util.List;

public class PokemonReponse {

    private String next;
    private List<PokemonResult> results;

    public String getNext() {
        return next;
    }

    public List<PokemonResult> getResult() {
        return results;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setResults(List<PokemonResult> results) {
        this.results = results;
    }
}
