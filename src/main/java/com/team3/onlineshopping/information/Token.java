/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.information;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Token {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(int id, String email, int time) {
        Key key = SECRET_KEY;

        long expirationTimeMillis = System.currentTimeMillis() + time * 60000;

        return Jwts.builder()
                .setExpiration(new Date(expirationTimeMillis))
                .claim("id", id)          // Thêm claim "name" vào payload
                .claim("email", email) // Thêm claim "email" vào payload
                .signWith(key)
                .compact();
    }
    
    public static boolean isTokenValid(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            
            // If no exception is thrown, the token is valid, but we can also check expiration separately
            Date expirationDate = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            
            // Get the expiration time in seconds
            long expirationTime = expirationDate.toInstant().getEpochSecond();

            // Get the current time in seconds
            long currentTime = Instant.now().getEpochSecond();

            // Compare the current time with the expiration time of the token
            return currentTime < expirationTime;
        } catch (Exception e) {
            // If there's any exception during parsing, the token is invalid
            return false;
        }
    }
}
