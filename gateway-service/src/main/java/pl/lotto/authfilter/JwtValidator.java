package pl.lotto.authfilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtValidator {

    private final JwtValidatorConfigurable jwtValidatorConfig;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtValidatorConfig.getSecret()));
    }

    public boolean validate(String token) {
        log.debug("Validating jwt: [{}]", token);
        SecretKey secret = Keys.hmacShaKeyFor(key().getEncoded());
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return false;
        } catch (SignatureException e) {
            log.error("JWT signature does not match locally computed signature: {}", e.getMessage());
            return false;
        }
        return true;
    }

}
