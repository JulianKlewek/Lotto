package pl.lotto.jwtgenerator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
class JwtUtils {

    private final Clock clock;
    private final JwtUtilsPropertyConfigurable jwtPropertyConfig;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtPropertyConfig.getSecret()));
    }

    public String generateToken(String username, TokenType tokenType) {
        Instant instantNow = Instant.now(clock);
        Date now = Date.from(instantNow);
        long expirationMillis = tokenType.equals(TokenType.ACCESS)
                ? jwtPropertyConfig.getAccessTokenExpiration()
                : jwtPropertyConfig.getRefreshTokenExpiration();
        Date expiration = new Date(now.getTime() + expirationMillis);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key())
                .compact();
    }
}
