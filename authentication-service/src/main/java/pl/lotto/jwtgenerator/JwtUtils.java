package pl.lotto.jwtgenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
class JwtUtils {

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
        return Jwts.builder()
                .id(String.valueOf(request.id()))
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key())
                .compact();
    }

    public Claims extractClaims(String token) {
        byte[] encodedSecret = Encoders.BASE64.encode(key().getEncoded()).getBytes();
        SecretKey secret = Keys.hmacShaKeyFor(encodedSecret);
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Date extractExpirationDate(String token) {
        return extractClaims(token).getExpiration();
    }

    private boolean isExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }
}
