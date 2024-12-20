package org.howudoin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtHelper {

    // Method to generate a JWT token
    // Method to generate a JWT token
    public String generateToken(String username) {
        // Set expiration time for the token (optional)
        long expirationTime = 1000 * 60 * 60; // 1 hour

        // Replace with your secret key
        String secretKey = "yourSecretKeyLongihatewritinglongkeysandidontevenknowifthisislongenough";

        return Jwts.builder()
                .setSubject(username) // Set the subject of the token (username in this case)
                .setIssuedAt(new Date()) // Set the issued date
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Set expiration date
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign the token using HS256 and the secret key
                .compact(); // Build the token
    }


}
