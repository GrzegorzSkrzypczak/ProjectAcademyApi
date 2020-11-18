package pl.sdaacademy.pokemonacademyapi.registration.service;

public class PokemonApiUserDto {

    private String login;
    private String password;

    public PokemonApiUserDto() {
    }

    public PokemonApiUserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
