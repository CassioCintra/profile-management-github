package br.com.mutant.profilemanagementgithub.adapter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.OffsetDateTime;
import java.util.Date;


@Slf4j
@Component
public class JwtTokenUtils {

    private final String jwtSecret;
    private final Long jwtExpiration;
    private final Long jwtRenew;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret,
                         @Value("${jwt.expiration}") Long jwtExpirationInMs,
                         @Value("${jwt.renew-at}") Long jwtRenewInMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpirationInMs;
        this.jwtRenew = jwtRenewInMs;
    }

    public String generateToken(String login) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime expiryDate = now.plusSeconds(jwtExpiration);
        OffsetDateTime renewDate = now.plusSeconds(jwtRenew);
        return Jwts.builder()
                .subject(login)
                .claim("createdAt", now.toString())
                .claim("renewAt", renewDate.toString())
                .issuedAt(Date.from(now.toInstant()))
                .expiration(Date.from(expiryDate.toInstant()))
                .signWith(key())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SignatureException ex) {
            log.debug("Invalid JWT signature: {}", ex.getMessage());
            return false;
        } catch (ExpiredJwtException ex) {
            log.debug("Expired JWT token: {}", ex.getMessage());
            return false;
        } catch (Exception ex) {
            log.debug("Invalid JWT token: {}", ex.getMessage());
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getLoginFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public OffsetDateTime getCreationDate(String token) {
        return OffsetDateTime.parse(
                getClaims(token).get("createdAt").toString());
    }

    public OffsetDateTime getRenewDate (String token) {
        return  OffsetDateTime.parse(
                getClaims(token).get("renewAt").toString());
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
