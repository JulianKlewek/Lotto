//package pl.lotto;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
//
//import java.util.regex.Pattern;
//
//@AutoConfigureMockMvc
//@SpringBootTest(classes = {LottoEngine.class, IntegrationConfiguration.class})
//@Testcontainers
//@ActiveProfiles("integration")
//public class BaseIntegrationTest {
//
//    @Autowired
//    protected MockMvc mockMvc;
//    @Autowired
//    protected NumbersGeneratorFacade numbersGeneratorFacade;
//    @Autowired
//    protected ResultCheckerFacade resultCheckerFacade;
//    @Autowired
//    protected ObjectMapper objectMapper;
//    @Container
//    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
//
//    @DynamicPropertySource
//    private static void propertyOverride(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    protected static final Pattern UUID_REGEX =
//            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
//}
