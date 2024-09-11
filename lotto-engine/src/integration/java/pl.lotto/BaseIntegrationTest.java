package pl.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.lotto.infrastructure.scheduler.resultchecker.ResultCheckerScheduler;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.util.regex.Pattern;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@AutoConfigureMockMvc
@SpringBootTest(classes = {LottoEngine.class, IntegrationConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public abstract class BaseIntegrationTest implements MongoTestContainer {

    protected static final String WIREMOCK_SERVER_HOST = "http://localhost";

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected WinningNumbersPort winningNumbersPort;
    @Autowired
    protected ResultCheckerFacade resultCheckerFacade;
    @Autowired
    protected ObjectMapper objectMapper;
    @SpyBean
    protected ResultCheckerScheduler resultCheckerScheduler;

    @DynamicPropertySource
    private static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("lotto.number-generator.service.url", () -> WIREMOCK_SERVER_HOST + ":" + wireMockServer.getPort());
        registry.add("spring.cloud.loadbalancer.enabled", () -> false);
    }

    @RegisterExtension
    protected static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    protected static final Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

}
