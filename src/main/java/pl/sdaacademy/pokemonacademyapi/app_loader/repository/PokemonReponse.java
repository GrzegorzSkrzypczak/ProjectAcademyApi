package pl.sdaacademy.pokemonacademyapi.app_loader.repository;

import org.springframework.web.client.RestTemplate;

public class PokeApiRepository  {

    private RestTemplate restTemplate;
    private long count;
    private String next;
    private String previous;
    private String[] results;

}
