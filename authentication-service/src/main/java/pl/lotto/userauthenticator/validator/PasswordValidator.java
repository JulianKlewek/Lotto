package pl.lotto.userauthenticator.validator;

import java.util.List;

class PasswordValidator implements PasswordRule {

    private final List<? extends PasswordRule> passwordRules;

    public PasswordValidator(List<? extends PasswordRule> rules) {
        this.passwordRules = rules;
    }

    @Override
    public RuleResult validate(char[] password) {
        RuleResult result = new RuleResult();
        RuleResult rule;
        for (PasswordRule passwordRule : this.passwordRules) {
            rule = passwordRule.validate(password);
            if (!rule.isValid()) {
                result.addErrors(rule.getErrors());
            }
        }
        return result;
    }

    public List<String> getErrorMessages(RuleResult result) {
        return result.getErrors();
    }
}
