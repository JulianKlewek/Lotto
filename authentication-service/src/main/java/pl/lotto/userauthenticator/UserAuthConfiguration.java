package pl.lotto.userauthenticator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.lotto.jwtgenerator.JwtGeneratorFacade;

@Configuration
public class UserAuthConfiguration {

    @Bean
    public UserAuthFacade createUserAuthFacade(UserRepository userRepository,
                                               JwtGeneratorFacade jwtGeneratorFacade) {
        return new UserAuthFacadeImpl(userRepository, passwordEncoder(), jwtGeneratorFacade);
    }

    public UserAuthFacade createUserAuthFacadeForTests(UserRepository userRepository,
                                                       JwtGeneratorFacade jwtGeneratorFacade) {
        return createUserAuthFacade(userRepository, jwtGeneratorFacade);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserRepository userRepository) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(userRepository));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
