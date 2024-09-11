package pl.lotto;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lotto.numbersgenerator.WinningNumbersGenerable;

import java.util.HashSet;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(classes = {NumberGeneratorRunner.class})
@Testcontainers
public abstract class BaseIntegrationTest {

    @Bean
    @Primary
    WinningNumbersGenerable winningNumbersGenerable() {
        return () -> {
            List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
            return new HashSet<>(integers);
        };
    }
}
