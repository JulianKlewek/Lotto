package pl.lotto.infrastructure.emailsenderservice;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
