package pl.lotto.jwtgenerator;

import lombok.AllArgsConstructor;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;

@AllArgsConstructor
class JwtGeneratorFacadeImpl implements JwtGeneratorFacade {

    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse generateToken(UserTokenRequest request) {
        String token = jwtUtils.generateToken(request);
        return JwtResponse.builder()
                .token(token)
                .build();
    }
}
