package pl.lotto.authfilter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Log4j2
@Component
class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator routeValidator;
    private final JwtValidator jwtValidator;

    public AuthenticationFilter(RouteValidator routeValidator, JwtValidator jwtValidator) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.jwtValidator = jwtValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (routeValidator.isSecured.test(request)) {
                log.debug("Processing request");
                if (authMissing(exchange.getRequest())) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                final String token = parseJwt(request);
                if (!jwtValidator.validate(token)) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
            }
            return chain.filter(exchange);
        };
    }

    private static String parseJwt(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);
        if (!StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            log.info("Token not valid, header: [{}]", authHeader);
            return null;
        }
        log.info("Token parsed successfully, header: [{}]", authHeader);
        return authHeader.substring(7);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    public static class Config {
    }
}
