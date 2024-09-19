package pl.lotto.infrastructure.accountcreated;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.lotto.infrastructure.accountcreated.dto.AccountCreatedEvent;
import pl.lotto.mailservice.EmailGeneratorFacade;
import pl.lotto.mailservice.EmailType;
import pl.lotto.mailservice.dto.AccountCreatedDetails;

@Component
@Log4j2
class AccountCreatedEventHandler {

    private final EmailGeneratorFacade emailGeneratorFacade;

    public AccountCreatedEventHandler(EmailGeneratorFacade emailGeneratorFacade) {
        this.emailGeneratorFacade = emailGeneratorFacade;
    }

    @RabbitListener(queues = {"${queues.names.account-created-event}"})
    public void handleConfirmationToken(AccountCreatedEvent event) {
        log.info("Received AccountCreatedEvent for email: [{}]", event.email());
        AccountCreatedDetails accountCreatedDetails = new AccountCreatedDetails(event.email(),
                event.token(),
                event.expiryAt());
        emailGeneratorFacade.generateDetails(EmailType.CONFIRMATION_MAIL, accountCreatedDetails);
    }
}
