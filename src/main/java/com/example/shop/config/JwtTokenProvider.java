package com.example.shop.config;

import com.example.shop.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.key}")
    private String securityKey;

    @Value("${jwt.token.expired}")
    private long timeExpired;

    public String generateToken(User user){
        return createToken(user.getFirstName(), user.getId(), user.getEmail());
    }

    private String createToken(String subject, Long id, String email) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + timeExpired);
        return Jwts.builder()
                .claim("id", id.toString())
                .claim("email", email)
                .claim("name", subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
    }

    public Boolean validateToken(String token, User userDetails) {
        final User user = getUserFromToken(token);
        return (user.getFirstName().equals(userDetails.getFirstName()) && !isTokenExpired(token));
    }

    public User getUserFromToken(String token) {
        Claims claims = extractAllClaims(token);
        User user = new User();
        user.setEmail(claims.get("email").toString());
        user.setId(Long.parseLong(claims.get("id").toString()));
        user.setFirstName(claims.get("name").toString());
        return user;
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }
}
