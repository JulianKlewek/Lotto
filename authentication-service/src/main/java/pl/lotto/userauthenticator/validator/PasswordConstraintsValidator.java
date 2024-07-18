package pl.lotto.userauthenticator.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

class PasswordConstraintsValidator implements ConstraintValidator<ValidPassword, char[]> {

    @Override
    public boolean isValid(char[] password, ConstraintValidatorContext validatorContext) {
        List<PasswordRule> rules = getPasswordRules();
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
                new CharacterRule(BasicCharacterData.LowerCase),
                new CharacterRule(BasicCharacterData.UpperCase),
                new CharacterRule(BasicCharacterData.Digit),
                new CharacterRule(BasicCharacterData.Special),
                new WhitespaceRule());
    }
}
