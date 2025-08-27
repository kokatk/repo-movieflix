package com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieflix.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    @Value("${MOVIEFLIX_SECURITY_SECRET}")
    private String secret;

    public String generateToken(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        Algorithm algorithm = Algorithm.HMAC256(secret);
        
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userid", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API MovieFlix")
                .sign(algorithm);
    }

    public Optional<JwtUserData> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

            return Optional.of(JwtUserData
                    .builder()
                    .Id(jwt.getClaim("userid").asLong())
                    .name(jwt.getClaim("name").asString())
                    .email(jwt.getSubject())
                    .build());
        } catch (JWTVerificationException ex) {
            // Adicione logging aqui, ex.: logger.warn("Invalid JWT: {}", ex.getMessage());
            return Optional.empty();
        }
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getSubject();
}

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getExpiresAt().before(new Date());
    }
}
