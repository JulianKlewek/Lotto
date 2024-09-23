package pl.lotto;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("integration")
@Configuration
public class RabbitMqTestConfiguration {

    public static final String TEST_RABBIT_QUEUE = "test.queue";
    public static final String TEST_RABBIT_EXCHANGE = "test.exchange";
    public static final String TEST_RABBIT_ROUTING_KEY = "test.routing.key";

    @Bean
    Queue queue() {
        return new Queue(TEST_RABBIT_QUEUE);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(TEST_RABBIT_EXCHANGE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(TEST_RABBIT_ROUTING_KEY);
    }

}
