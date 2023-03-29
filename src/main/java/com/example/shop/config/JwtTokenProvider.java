package com.example.shop.config;

import com.example.shop.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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

    public Boolean validateToken(String token) {
        if (token == null){
            return false;
        }
        try {
            Claims claims = extractAllClaims(token);
            Date date = claims.getExpiration();
            Date now = new Date();
            return now.before(date);
        } catch (Exception ex){
            return false;
        }
    }

    public User getUserFromToken(String token){
        try {
            Claims claims = extractAllClaims(token);
            User user = new User();
            user.setEmail(claims.get("email").toString());
            user.setId(Long.parseLong(claims.get("id").toString()));
            user.setFirstName(claims.get("name").toString());
            return user;
        } catch (Exception ex){
            return null;
        }
    }

    public Date extractExpirationDate(String token) throws Exception {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception{
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws Exception {
        try {
            return Jwts.parser()
                    .setSigningKey(securityKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception exception){
            throw new Exception("token");
        }
    }

    private String refreshToken(){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + securityKey);
        Date refreshExpiryDate = new Date(now.getTime() + timeExpired);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setNotBefore(now)
                .claim("refresh_exp", refreshExpiryDate)
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
    }
}
