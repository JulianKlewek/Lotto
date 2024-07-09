package pl.lotto.jwtgenerator;

import lombok.AllArgsConstructor;
import pl.lotto.jwtgenerator.dto.JwtResponse;

@AllArgsConstructor
public class JwtGeneratorFacadeImpl implements JwtGeneratorFacade {

    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse generateToken(String username, TokenType tokenType) {
        String token = jwtUtils.generateToken(username, tokenType);
        return JwtResponse.builder()
                .token(token)
                .build();
    }
}
