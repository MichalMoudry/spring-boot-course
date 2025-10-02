package com.example.todos.service

import groovy.transform.CompileStatic
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

import javax.crypto.SecretKey
import java.util.function.Function

@Service
@CompileStatic
class JwtService implements IJwtService {
    @Value('${spring.jwt.secret}')
    private String secretKey
    @Value('${spring.jwt.expiration}')
    private long jwtExpiration

    @Override
    String extractUserName(String token) {
        extractClaims(token, Claims::getSubject)
    }

    @Override
    boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token)
        username == userDetails.username && !isTokenExpired(token)
    }

    @Override
    String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        Date now = new Date(System.currentTimeMillis())
        Jwts.builder()
            .claims(claims)
            .subject(userDetails.username)
            .issuedAt(now)
            .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact()
    }

    private boolean isTokenExpired(String token) {
        Date exp = extractClaims(token, Claims::getExpiration)
        exp.before(new Date())
    }

    /*private Date extractExpirationDate(String token) {
        extractClaims(token, Claims::getExpiration)
    }*/

    <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token)
        claimsResolver(claims)
    }

    private Claims extractAllClaims(String token) {
        Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .payload
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey)
        Keys.hmacShaKeyFor(keyBytes)
    }
}
