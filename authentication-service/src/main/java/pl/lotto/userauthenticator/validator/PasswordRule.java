package pl.lotto.userauthenticator.validator;

public interface PasswordRule {

    RuleResult validate(char[] password);
}
