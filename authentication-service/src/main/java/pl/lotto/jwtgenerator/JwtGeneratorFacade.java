package pl.lotto.jwtgenerator;

import pl.lotto.jwtgenerator.dto.JwtResponse;

public interface JwtGeneratorFacade {

    JwtResponse generateToken(String username, TokenType tokenType);
}
