package pl.lotto.jwtgenerator;

import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;

public interface JwtGeneratorFacade {

    JwtResponse generateToken(UserTokenRequest request);
}
