package pl.lotto.infrastructure.emailsenderservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.lotto.infrastructure.emailsenderservice.dto.AccountCreatedEvent;
import pl.lotto.userauthenticator.EmailSenderPort;

@Service
@Log4j2
class EmailSenderRabbitProducer implements EmailSenderPort {

    private final RabbitTemplate rabbitTemplate;
    private final EmailSenderPropertyConfig emailSenderPropertyConfig;

    public EmailSenderRabbitProducer(RabbitTemplate rabbitTemplate, EmailSenderPropertyConfig emailSenderPropertyConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.emailSenderPropertyConfig = emailSenderPropertyConfig;
    }

    @Override
    public void sendAccountCreatedEvent(AccountCreatedEvent message) {
        String exchange = emailSenderPropertyConfig.getExchange();
        String routingKey = emailSenderPropertyConfig.getRoutingKey().registration();
        log.debug("Processing registration email message with exchange: [{}], routingKey: [{}], confToken: [{}]",
                exchange,
                routingKey,
                message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
