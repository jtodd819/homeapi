package com.api.homeapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.api.homeapi.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.api.homeapi.security.Config.EXPIRATION_TIME;
import static com.api.homeapi.security.Config.HEADER_STRING;
import static com.api.homeapi.security.Config.SECRET;
import static com.api.homeapi.security.Config.TOKEN_PREFIX;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            final UserAccount creds = new ObjectMapper().readValue(request.getInputStream(), UserAccount.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(),
                    creds.getPassword(), new ArrayList<>()));
        } catch (final IOException e) {
            throw new RuntimeException("Could not process request" + e);
        }
    }

    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain,
    final Authentication authentication) {
        String token = Jwts.builder()
            .setSubject(((User) authentication.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
            .compact();
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
    
}
