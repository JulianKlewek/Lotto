package pl.lotto.infrastructure.rabbitmqconfig;

import pl.lotto.infrastructure.accountcreated.AccountCreatedEvent;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("pl.lotto.infrastructure.emailsenderservice.dto");
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("pl.lotto.infrastructure.emailsenderservice.dto.AccountCreatedEvent",
                AccountCreatedEvent.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

}
