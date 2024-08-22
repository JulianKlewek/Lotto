package pl.lotto.userauthenticator.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
class PasswordConstraintsValidator implements ConstraintValidator<ValidPassword, char[]> {

    @Override
    public boolean isValid(char[] password, ConstraintValidatorContext validatorContext) {
        List<PasswordRule> rules = getPasswordRules();
        log.debug("Validating password with rules {}", rules);
        PasswordValidator validator = new PasswordValidator(rules);
        RuleResult result = validator.validate(password);
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getErrorMessages(result);
        String messageTemplate = String.join(",", messages);
        validatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

    private static List<PasswordRule> getPasswordRules() {
        return Arrays.asList(
                new CharacterRule(BasicCharacterData.LowerCase, 2),
                new CharacterRule(BasicCharacterData.UpperCase),
                new CharacterRule(BasicCharacterData.Digit),
                new CharacterRule(BasicCharacterData.Special),
                new WhitespaceRule(),
                new LengthRule(8, 30));
    }
}
