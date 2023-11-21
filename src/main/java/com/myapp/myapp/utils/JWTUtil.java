package com.myapp.myapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import jakarta.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author Mahesh
 *
 */
@Component
public class JWTUtil {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory
            .getLogger(JWTUtil.class);

    public String createJWT(String id, String subject) {

        // The JWT signature algorithm used to sign the token

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  sign JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .id(id)
                .issuedAt(now)
                .subject(subject)
                .issuer(issuer)
                .expiration(new Date(ttlMillis))
                .signWith(secretKey);

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    // Sample method to validate and read the JWT
    public String parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getPayload();

        return claims.getSubject();
    }

    public String getValue(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        return claims.getSubject();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getKey(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();


        return claims.getId();
    }
}