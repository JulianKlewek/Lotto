package pl.lotto.mailservice;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
class BodyGenerator {

    private static final String PATTERN_FORMAT = "dd.MM.yyyy hh:mm:ss";
    private final SpringTemplateEngine templateEngine;

    public BodyGenerator(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generateConfirmationEmail(AccountCreatedProperties details) {
        log.info("Generating confirmation email body for [{}]", details);
        Map<String, String> variables = new HashMap<>();
        variables.put("recipient", details.getRecipient());
        variables.put("token", details.getConfirmationToken());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        String expiryDate = formatter.format(details.getExpirationTime());
        variables.put("expiryDate", expiryDate);
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(Collections.unmodifiableMap(variables));
        return templateEngine.process("account-created.html", thymeleafContext);
    }

    public String generateNewsletterEmail(EmailRecipient properties) {
        throw new NotImplementedException("Method not implemented yet");
    }
}
