package pl.sdaacademy.pokemonacademyapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.sdaacademy.pokemonacademyapi.registration.service.PokemonUserApiProvider;
import pl.sdaacademy.pokemonacademyapi.security.AuthenticationFilter;
import pl.sdaacademy.pokemonacademyapi.security.AuthorizationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PokemonUserApiProvider pokemonUserApiProvider;
    private final PasswordEncoder passwordEncoder;
    private final String securityKey;
    private final String authorizationHeaderName;
    private final String authorizationType;

    @Autowired
    public SecurityConfig(PokemonUserApiProvider pokemonUserApiProvider,
                          PasswordEncoder passwordEncoder,
                          @Value("${paa.secret_key}") String securityKey,
                          @Value("${paa.authorization_header}") String authorizationHeaderName,
                          @Value("${paa.authorization_type}") String authorizationType) {
        this.pokemonUserApiProvider = pokemonUserApiProvider;
        this.passwordEncoder = passwordEncoder;
        this.securityKey = securityKey;
        this.authorizationHeaderName = authorizationHeaderName;
        this.authorizationType = authorizationType;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/pokemons").permitAll()
                .antMatchers("/pokemons/list").permitAll()
                .antMatchers("/pokemons/signup").permitAll();
        http.csrf().disable();
        http.cors();
        http.addFilter(new AuthenticationFilter(authenticationManager(),
                securityKey,
                authorizationType,
                authorizationHeaderName));
        http.addFilter(new AuthorizationFilter(securityKey,
                authorizationHeaderName,
                authorizationType,
                authenticationManager()));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pokemonUserApiProvider).passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
