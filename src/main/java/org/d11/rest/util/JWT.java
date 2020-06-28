package org.d11.rest.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.d11.rest.model.jpa.User;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JWT {

    private static String SECRET_KEY = "SECRET_KEY";

    @Value("${jwt.secret_key}")
    protected void setSecretKey(String secretKey) {
        JWT.SECRET_KEY = secretKey;
    }

    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        String token = Jwts.builder()
                           .setClaims(claims)
                           .setSubject(user.getUsername())
                           .setIssuedAt(new Date(System.currentTimeMillis()))
                           .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                           .signWith(SignatureAlgorithm.HS256, JWT.SECRET_KEY)
                           .compact();

        return token;
    }

    public static String getUsername(String token) {
        Claims claims = getClaims(token);
        String username = getValue(claims, Claims::getSubject);
        return username;
    }

    public static boolean validateToken(String token) {
        Claims claims = getClaims(token);
        String username = getValue(claims, Claims::getSubject);
        Date expirationDate = getValue(claims, Claims::getExpiration);
        return username != null && expirationDate != null && !expirationDate.before(new Date());
    }

    private static Claims getClaims(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(JWT.SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (SignatureException e) {
            return null;
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    private static <T> T getValue(Claims claims, Function<Claims, T> function) {
        if (claims != null) {
            return function.apply(claims);
        }
        return null;
    }

}
