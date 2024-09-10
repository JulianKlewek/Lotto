package pl.lotto.infrastructure.emailsenderservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
class EmailSenderRabbitMqConfig {

    private final EmailSenderPropertyConfig emailSenderPropertyConfig;

    public EmailSenderRabbitMqConfig(EmailSenderPropertyConfig emailSenderPropertyConfig) {
        this.emailSenderPropertyConfig = emailSenderPropertyConfig;
    }

    @Bean
    Queue queue() {
        String queue = emailSenderPropertyConfig.getQueue();
        return new Queue(queue);
    }

    @Bean
    DirectExchange exchange() {
        String exchange = emailSenderPropertyConfig.getExchange();
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding() {
        String registrationRoutingKey = emailSenderPropertyConfig.getRoutingKey().registration();
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(registrationRoutingKey);
    }

    @Bean
    @Profile("integration")
    public MessageConverter jsonToMapMessageConverter() {
        String trustedDtoPackage = "pl.lotto.infrastructure.emailsenderservice.dto";
        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
        defaultClassMapper.setTrustedPackages(trustedDtoPackage);
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
        return jackson2JsonMessageConverter;
    }
}
