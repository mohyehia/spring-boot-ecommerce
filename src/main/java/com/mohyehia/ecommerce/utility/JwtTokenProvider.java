package com.mohyehia.ecommerce.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JwtTokenProvider {
    private static final String CLAIMS_SUBJECT = "sub";
    private static final String CLAIMS_CREATED = "created";
    private static final String AUTHORITIES = "auth";

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationInMs}")
    private long jwtExpirationInMs;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, LocalDateTime.now());
        claims.put(AUTHORITIES, userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtExpirationInMs * 1000);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String username = retrieveUsernameFromToken(token);
        return username != null
                && username.equals(userDetails.getUsername())
                && isNotExpiredToken(token);
    }

    public String retrieveUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims == null ? null : claims.getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isNotExpiredToken(String token) {
        Claims claims = getClaims(token);
        if (claims == null || claims.getExpiration() == null) {
            return false;
        }
        return claims.getExpiration().before(new Date());
    }
}
