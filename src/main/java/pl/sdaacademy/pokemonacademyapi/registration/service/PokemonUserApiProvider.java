package pl.sdaacademy.pokemonacademyapi.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUser;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUserRepository;

import java.util.Collections;

@Component
public class PokemonUserApiProvider implements UserDetailsService {

    private final PokemonApiUserRepository pokemonApiUserRepository;

    @Autowired
    public PokemonUserApiProvider(PokemonApiUserRepository pokemonApiUserRepository) {
        this.pokemonApiUserRepository = pokemonApiUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PokemonApiUser pokemonApiUser = pokemonApiUserRepository.findById(username).orElseThrow(() -> {
            throw new UserNotFoundException(username);
        });
        return new User(pokemonApiUser.getLogin(), pokemonApiUser.getPassword(), Collections.emptyList());

    }
}
