package pl.sdaacademy.pokemonacademyapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.sdaacademy.pokemonacademyapi.registration.service.PokemonUserApiProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PokemonUserApiProvider pokemonUserApiProvider;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PokemonUserApiProvider pokemonUserApiProvider, PasswordEncoder passwordEncoder) {
        this.pokemonUserApiProvider = pokemonUserApiProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/pokemons").authenticated()
                .antMatchers("/pokemons/list").authenticated()
                .antMatchers("/pokemons/signup").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pokemonUserApiProvider).passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(("/**"), new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
