package pl.lotto.infrastructure.emailsenderservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

//    @Bean
//    public SimpleMessageConverter converter() {
//        SimpleMessageConverter converter = new SimpleMessageConverter();
//        converter.setAllowedListPatterns(List.of("pl.lotto.infrastructure.emailsenderservice.dto"));
//        return converter;
//    }
}
