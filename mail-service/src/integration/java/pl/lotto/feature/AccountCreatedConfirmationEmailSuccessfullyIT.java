package pl.lotto.feature;

import com.icegreen.greenmail.util.GreenMailUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.infrastructure.accountcreated.dto.AccountCreatedEvent;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.lotto.RabbitMqTestConfiguration.TEST_RABBIT_EXCHANGE;
import static pl.lotto.RabbitMqTestConfiguration.TEST_RABBIT_ROUTING_KEY;

class AccountCreatedConfirmationEmailSuccessfullyIT extends BaseIntegrationTest {

    @Test
    @DisplayName("Confirmation email send to provided email address")
    void happyPath_shouldReceiveAccountCreatedEvent_andSendConfirmationEmail() throws MessagingException {
        //step 1: service receives AccountCreatedEvent & sends confirmation email
        //given
        String recipient = "example@mail.com";
        String subject = "Mail confirmation";
        Instant expiryAt = Instant.parse("2024-12-12T12:12:12.12Z");
        AccountCreatedEvent accountCreatedEvent = new AccountCreatedEvent(
                recipient,
                "217d8e07-8492-4fb6-8975-d056f279cc26",
                expiryAt);
        String body = """
                <!DOCTYPE html>\r
                <html lang="en">\r
                <head>\r
                    <meta charset="UTF-8">\r
                </head>\r
                <body>\r
                    <p>TEST, example@mail.com</p>\r
                    <p>TEST, 12.12.2024 01:12:12.</p>\r
                    <a href="TEST, 217d8e07-8492-4fb6-8975-d056f279cc26">Confirmation</a>\r
                </body>\r
                </html>""";
        //when
        rabbitTemplate.convertAndSend(TEST_RABBIT_EXCHANGE, TEST_RABBIT_ROUTING_KEY, accountCreatedEvent);
        //then
        greenMail.waitForIncomingEmail(1);
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertThat(receivedMessages).hasSize(1);

        MimeMessage receivedMessage = receivedMessages[0];
        String receivedBody = GreenMailUtil.getBody(receivedMessage);
        String receivedRecipient = Objects.requireNonNull(
                        Arrays.stream(receivedMessage.getAllRecipients())
                                .findFirst()
                                .orElseThrow())
                .toString();
        assertAll(
                () -> assertEquals(recipient, receivedRecipient),
                () -> assertEquals(subject, receivedMessage.getSubject()),
                () -> assertEquals(body, receivedBody)
        );
    }

}
