package pl.sdaacademy.pokemonacademyapi.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUser;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUserRepository;

@Service
public class PokemonUserApiService {

    private final PasswordEncoder passwordEncoder;
    private final PokemonApiUserRepository pokemonApiUserRepository;


    @Autowired
    public PokemonUserApiService(PokemonApiUserRepository pokemonApiUserRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.pokemonApiUserRepository = pokemonApiUserRepository;
    }

    public PokemonApiUser getPokemonApiUserByLogin(String login) {
       return pokemonApiUserRepository.findById(login)
                .<UserNotFoundException>orElseThrow(() -> {
                    throw new UserNotFoundException(String.format("User witch %s could not be found in db", login));
                });
    }

    public PokemonApiUserDto addUser(PokemonApiUser pokemonApiUser) {
        pokemonApiUserRepository.findById(pokemonApiUser.getLogin()).ifPresent((user) -> {
            throw new UserAllreadyExistsInDatabaseException(String.format("User with %s is already existing in db!",
                    pokemonApiUser.getLogin()));
        });

        pokemonApiUser.setPassword(passwordEncoder.encode(pokemonApiUser.getPassword()));
        PokemonApiUser addedUser = pokemonApiUserRepository.save(pokemonApiUser);
        return new PokemonApiUserDto(addedUser.getLogin(), addedUser.getPassword());
    }

}
