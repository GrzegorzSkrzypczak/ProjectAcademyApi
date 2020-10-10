package pl.sdaacademy.pokemonacademyapi.pokemon_details.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonDetailsRepository extends CrudRepository<PokemonDetails, String> {

}
