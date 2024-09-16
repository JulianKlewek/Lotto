package pl.lotto.mailservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class EmailGeneratorConfiguration {

    @Bean
    @Primary
    public EmailGeneratorFacade createEmailGeneratorFacade(EmailSender emailSender) {
        SpringTemplateEngine templateEngine = thymeleafTemplateEngine(thymeleafTemplateResolver());
        BodyGenerator bodyGenerator = new BodyGenerator(templateEngine);
        EmailDetailsAssigner emailDetailsAssigner = new EmailDetailsAssigner(bodyGenerator);
        return new EmailGeneratorFacadeImpl(emailSender, emailDetailsAssigner);
    }

    @Bean
    public EmailGeneratorFacade createEmailGeneratorFacadeForTests(EmailSender emailSender) {
        return createEmailGeneratorFacade(emailSender);
    }


    @Bean
    public ITemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("mail-templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}
