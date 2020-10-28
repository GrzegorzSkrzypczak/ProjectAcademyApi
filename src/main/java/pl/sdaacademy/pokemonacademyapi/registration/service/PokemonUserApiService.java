package pl.sdaacademy.pokemonacademyapi.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUser;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUserRepository;

@Service
public class PokemonUserApiService {

    //TODO password encoder bean!
    private final PasswordEncoder passwordEncoder;
    private final PokemonApiUserRepository pokemonApiUserRepository;


    @Autowired
    public PokemonUserApiService(PokemonApiUserRepository pokemonApiUserRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.pokemonApiUserRepository = pokemonApiUserRepository;
    }

    public PokemonApiUser getPokemonUserByLogin(String login) {
       return pokemonApiUserRepository.findById(login)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(String.format("User witch %s could not be found", login));
                });
    }

    public PokemonApiUserDto addUser(PokemonApiUser pokemonApiUser) {
        pokemonApiUserRepository.findById(pokemonApiUser.getLogin()).ifPresent((user) -> {
            throw new UserAllreadyExistsInDatabaseException(String.format("User with %s is already exist in db!",
                    pokemonApiUser.getLogin()));
        });

        pokemonApiUser.setPassword(passwordEncoder.encode(pokemonApiUser.getPassword()));
        PokemonApiUser addedUser = pokemonApiUserRepository.save(pokemonApiUser);
        return new PokemonApiUserDto(addedUser.getLogin());
    }

}
