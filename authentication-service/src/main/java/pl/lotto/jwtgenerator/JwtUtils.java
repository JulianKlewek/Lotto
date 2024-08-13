package pl.lotto.jwtgenerator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
class JwtUtils {

    private static final Logger logger = LogManager.getLogger(JwtUtils.class);
    private final Clock clock;
    private final JwtUtilsPropertyConfigurable jwtPropertyConfig;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtPropertyConfig.getSecret()));
    }

    public String generateToken(UserTokenRequest request) {
        Instant instantNow = Instant.now(clock);
        Date now = Date.from(instantNow);
        String tokenType = TokenType.ACCESS.toString();
        long expirationMillis = request.tokenType().equals(tokenType)
                ? jwtPropertyConfig.getAccessTokenExpiration()
                : jwtPropertyConfig.getRefreshTokenExpiration();
        Date expiration = new Date(now.getTime() + expirationMillis);
        logger.info("Generating {}-JWT for user ID: [{}] with expiration: [{}] ", tokenType, request.id(), expiration);
        return Jwts.builder()
                .id(String.valueOf(request.id()))
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key())
                .compact();
    }
}
