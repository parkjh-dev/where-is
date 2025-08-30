package com.parkjh.where_is.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private SecretKey secretKey;

    @Setter
    @Getter
    private long expirationTime;
    private final String ISSUER = "where-is-api";

    public JwtUtils() {
        String secretKeyString = System.getenv("JWT_SECRET");
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));

        String expireTimeString  = System.getenv("JWT_EXPIRE_NORMAL");
        this.expirationTime = Long.parseLong(expireTimeString);
    }

    public String generateToken(String username, String role) {
        return generateToken(username, role, new HashMap<>());
    }

    public String generateToken(String username, String role, Map<String, Object> customClaims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + this.expirationTime);

        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setIssuer(ISSUER)
                .signWith(this.secretKey);

        // 커스텀 클레임 추가
        customClaims.forEach(builder::claim);
        return builder.compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) return true;

            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public String refreshToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) return null;

            // 기존 클레임 유지하면서 새로운 만료시간 설정
            return generateToken(
                    claims.getSubject(),
                    claims.get("role", String.class),
                    extractCustomClaims(claims)
            );
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> extractCustomClaims(Claims claims) {
        Map<String, Object> customClaims = new HashMap<>();
        claims.forEach((key, value) -> {
            if (!key.equals("sub") && !key.equals("role") &&
                    !key.equals("iat") && !key.equals("exp") && !key.equals("iss")) {
                customClaims.put(key, value);
            }
        });
        return customClaims;
    }
}
