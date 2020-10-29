package pl.sdaacademy.pokemonacademyapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.sdaacademy.pokemonacademyapi.registration.repository.PokemonApiUser;
import pl.sdaacademy.pokemonacademyapi.registration.service.UserNotFoundException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final long TOKEN_EXPIRATION_TIME = 3_600_000;
    private final AuthenticationManager authenticationManager;
    private final String securityKey;
    private final String authorizationHeaderName;
    private final String authorizationType;


    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                String securityKey,
                                String authorizationType,
                                String authorizationHeaderName) {
        this.authenticationManager = authenticationManager;
        this.securityKey = securityKey;
        this.authorizationHeaderName = authorizationHeaderName;
        this.authorizationType = authorizationType;
        setFilterProcessesUrl("/pokemons/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Optional<PokemonApiUser> pokemonUserOptional = Optional.empty();

        try {
            pokemonUserOptional = Optional.ofNullable(new ObjectMapper().readValue(request.getInputStream(),
                    PokemonApiUser.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
       pokemonUserOptional.<UserNotFoundException>orElseThrow(() -> {
           throw new UserNotFoundException("no user to authenticate");
       });
        PokemonApiUser pokemonApiUser = pokemonUserOptional.get();
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(pokemonApiUser.getLogin(),
                pokemonApiUser.getPassword(),
                Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
       String token = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .setSubject(((User)authResult.getPrincipal()).getUsername())
                .signWith(SignatureAlgorithm.HS512, securityKey.getBytes())
                .compact();
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
       response.addHeader(authorizationHeaderName, authorizationType + " " + token);
    }
}
