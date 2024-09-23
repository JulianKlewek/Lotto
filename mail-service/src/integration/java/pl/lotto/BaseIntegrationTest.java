package pl.lotto;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("integration")
public abstract class BaseIntegrationTest implements RabbitTestContainer {

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    protected GreenMail greenMail;

    @BeforeEach
    public void setup() {
        greenMail = new GreenMail(new ServerSetup(3025, null, "smtp"));
        greenMail.start();
    }

    @AfterEach
    public void tearDown() {
        greenMail.stop();
    }


    @Bean
    public MessageConverter jsonToMapMessageConverter() {
        String trustedDtoPackage = "pl.lotto.infrastructure.accountcreated.dto";
        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
        defaultClassMapper.setTrustedPackages(trustedDtoPackage);
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
        return jackson2JsonMessageConverter;
    }

    @Bean
    MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
