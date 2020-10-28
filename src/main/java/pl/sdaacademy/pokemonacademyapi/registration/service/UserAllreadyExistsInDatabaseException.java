package pl.sdaacademy.pokemonacademyapi.registration.service;

public class UserAllreadyExistsInDatabaseException extends RuntimeException {

    public UserAllreadyExistsInDatabaseException(String message) {
        super(message);
    }
}
