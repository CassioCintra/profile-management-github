package br.com.mutant.profilemanagementgithub.adapter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenUtilsTest {

    private JwtTokenUtils jwtTokenUtils;

    private static final String TEST_SECRET = "8d18e80d210519a79c09930985223c65c27598c0b2d3e180b9d997235251412a";
    private static final Long TEST_EXPIRATION = 3600L;
    private static final Long TEST_RENEWAL = 1800L;
    private static final String TEST_LOGIN = "testuser";

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(TEST_SECRET));
    }

    @BeforeEach
    void setUp() {
        this.jwtTokenUtils = new JwtTokenUtils(TEST_SECRET, TEST_EXPIRATION, TEST_RENEWAL);
    }

    @Test
    void should_generate_token_successfully() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);

        assertThat(token).isNotNull().isNotEmpty();
        assertThat(token.split("\\.")).hasSize(3);
    }

    @Test
    void should_generate_token_with_correct_claims() {
        OffsetDateTime beforeGeneration = OffsetDateTime.now();

        String token = jwtTokenUtils.generateToken(TEST_LOGIN);
        Claims claims = extractClaims(token);

        assertThat(claims.getSubject()).isEqualTo(TEST_LOGIN);
        assertThat(claims.get("createdAt")).isNotNull();
        assertThat(claims.get("renewAt")).isNotNull();
        assertThat(claims.getExpiration()).isAfter(new Date());

        OffsetDateTime createdAt = OffsetDateTime.parse(claims.get("createdAt").toString());
        OffsetDateTime renewAt = OffsetDateTime.parse(claims.get("renewAt").toString());

        assertThat(createdAt).isAfterOrEqualTo(beforeGeneration.minusSeconds(1));
        assertThat(renewAt).isAfter(createdAt);
    }

    @Test
    void should_validate_valid_token() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);

        assertThat(jwtTokenUtils.validateToken(token)).isTrue();
    }

    @Test
    void should_not_validate_expired_token() {
        String expiredToken = createExpiredToken();

        assertThat(jwtTokenUtils.validateToken(expiredToken)).isFalse();
    }

    @Test
    void should_not_validate_token_with_invalid_signature() {
        String tokenWithInvalidSignature = createTokenWithInvalidSignature();

        assertThat(jwtTokenUtils.validateToken(tokenWithInvalidSignature)).isFalse();
    }

    @Test
    void should_not_validate_malformed_token() {
        String malformedToken = "invalid.token.format";

        assertThat(jwtTokenUtils.validateToken(malformedToken)).isFalse();
    }

    @Test
    void should_not_validate_null_token() {
        assertThat(jwtTokenUtils.validateToken(null)).isFalse();
    }

    @Test
    void should_not_validate_empty_token() {
        assertThat(jwtTokenUtils.validateToken("")).isFalse();
        assertThat(jwtTokenUtils.validateToken("   ")).isFalse();
    }

    @Test
    void should_get_claims_from_valid_token() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);

        Claims claims = jwtTokenUtils.getClaims(token);

        assertThat(claims).isNotNull();
        assertThat(claims.getSubject()).isEqualTo(TEST_LOGIN);
        assertThat(claims.get("createdAt")).isNotNull();
        assertThat(claims.get("renewAt")).isNotNull();
    }

    @Test
    void should_extract_login_from_token() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);

        String extractedLogin = jwtTokenUtils.getLoginFromToken(token);

        assertThat(extractedLogin).isEqualTo(TEST_LOGIN);
    }

    @Test
    void should_extract_expiration_date_from_token() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);

        Date expirationDate = jwtTokenUtils.getExpirationDate(token);

        assertThat(expirationDate).isNotNull().isAfter(new Date());
    }

    @Test
    void should_extract_creation_date_from_token() {
        OffsetDateTime beforeGeneration = OffsetDateTime.now();

        String token = jwtTokenUtils.generateToken(TEST_LOGIN);
        OffsetDateTime creationDate = jwtTokenUtils.getCreationDate(token);

        assertThat(creationDate).isNotNull()
                .isAfterOrEqualTo(beforeGeneration.minusSeconds(1))
                .isBeforeOrEqualTo(OffsetDateTime.now().plusSeconds(1));
    }

    @Test
    void should_extract_renew_date_from_token() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);

        OffsetDateTime renewDate = jwtTokenUtils.getRenewDate(token);
        OffsetDateTime creationDate = jwtTokenUtils.getCreationDate(token);

        assertThat(renewDate).isNotNull().isAfter(creationDate);
    }

    @Test
    void should_generate_different_tokens_for_same_user() throws InterruptedException {
        String token1 = jwtTokenUtils.generateToken(TEST_LOGIN);
        Thread.sleep(10);
        String token2 = jwtTokenUtils.generateToken(TEST_LOGIN);

        assertThat(token1).isNotEqualTo(token2);
        assertThat(jwtTokenUtils.getLoginFromToken(token1)).isEqualTo(TEST_LOGIN);
        assertThat(jwtTokenUtils.getLoginFromToken(token2)).isEqualTo(TEST_LOGIN);
    }

    @Test
    void should_verify_expiration_time_matches_configuration() {
        OffsetDateTime beforeGeneration = OffsetDateTime.now();

        String token = jwtTokenUtils.generateToken(TEST_LOGIN);
        Date expirationDate = jwtTokenUtils.getExpirationDate(token);

        OffsetDateTime expectedExpiration = beforeGeneration.plusSeconds(TEST_EXPIRATION);
        OffsetDateTime actualExpiration = expirationDate.toInstant().atOffset(beforeGeneration.getOffset());

        long differenceInSeconds = Math.abs(actualExpiration.toEpochSecond() - expectedExpiration.toEpochSecond());
        assertThat(differenceInSeconds).isLessThanOrEqualTo(2);
    }

    @Test
    void should_verify_renew_time_matches_configuration() {
        OffsetDateTime beforeGeneration = OffsetDateTime.now();

        String token = jwtTokenUtils.generateToken(TEST_LOGIN);
        OffsetDateTime creationDate = jwtTokenUtils.getCreationDate(token);
        OffsetDateTime renewDate = jwtTokenUtils.getRenewDate(token);

        OffsetDateTime expectedRenewDate = creationDate.plusSeconds(TEST_RENEWAL);
        long differenceInSeconds = Math.abs(renewDate.toEpochSecond() - expectedRenewDate.toEpochSecond());

        assertThat(differenceInSeconds).isLessThanOrEqualTo(1);
    }

    @Test
    void should_handle_different_error_scenarios() {
        assertThat(jwtTokenUtils.validateToken(null)).isFalse();
        assertThat(jwtTokenUtils.validateToken("")).isFalse();
        assertThat(jwtTokenUtils.validateToken("   ")).isFalse();
        assertThat(jwtTokenUtils.validateToken("invalid.token.here")).isFalse();
        assertThat(jwtTokenUtils.validateToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.invalid.signature")).isFalse();
    }

    @Test
    void should_verify_claims_types_are_strings() {
        String token = jwtTokenUtils.generateToken(TEST_LOGIN);
        Claims claims = jwtTokenUtils.getClaims(token);

        assertThat(claims.get("createdAt")).isInstanceOf(String.class);
        assertThat(claims.get("renewAt")).isInstanceOf(String.class);

        String createdAtStr = claims.get("createdAt").toString();
        String renewAtStr = claims.get("renewAt").toString();
        assertThat(createdAtStr).isNotEmpty();
        assertThat(renewAtStr).isNotEmpty();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createExpiredToken() {
        return Jwts.builder()
                .subject(TEST_LOGIN)
                .issuedAt(Date.from(Instant.now().minusSeconds(3600)))
                .expiration(Date.from(Instant.now().minusSeconds(1800)))
                .signWith(getSecretKey())
                .compact();
    }

    private String createTokenWithInvalidSignature() {
        SecretKey wrongKey = Keys.hmacShaKeyFor("wrongsecretkeythatdoesnotmatchtheoriginal".getBytes());
        return Jwts.builder()
                .subject(TEST_LOGIN)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(wrongKey)
                .compact();
    }
}