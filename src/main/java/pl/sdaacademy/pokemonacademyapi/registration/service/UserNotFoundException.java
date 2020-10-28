package pl.sdaacademy.pokemonacademyapi.registration.service;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
