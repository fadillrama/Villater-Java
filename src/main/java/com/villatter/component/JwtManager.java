package com.villatter.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtManager {

    private final String SECRET_KEY = "generate-secret-key-for-client-side-request";

    private Date getIssueDate(){
        var now = LocalDateTime.now();
        var systemTime = now.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(systemTime);
    }

    private Date getExpireDate(){
        var expire = LocalDateTime.now().plusMinutes(60);
        var systemTime = expire.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(systemTime);
    }

    public String generateToken(String username, String subject, String role, String audience, String secretKey){
        var builder = Jwts.builder();
        builder = builder
                .setAudience(audience)
                .setSubject(subject)
                .claim("username", username)
                .claim("role", role)
                .setIssuer("http://localhost:7077")
                .setIssuedAt(getIssueDate())
                .setExpiration(getExpireDate())
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return builder.compact();
    }

    public Claims getClaims(String token){
        var parser = Jwts.parser().setSigningKey(SECRET_KEY);
        var signatureAndClaims = parser.parseClaimsJws(token);
        var claims = signatureAndClaims.getBody();
        return claims;
    }

    public String getUsername(String token){
        var claims = getClaims(token);
        return claims.get("username", String.class);
    }
}