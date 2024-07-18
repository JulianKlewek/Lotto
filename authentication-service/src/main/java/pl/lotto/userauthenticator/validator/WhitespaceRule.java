package pl.lotto.userauthenticator.validator;

class WhitespaceRule implements PasswordRule {

    public static final String ERROR_CODE = "ILLEGAL_WHITESPACE";

    @Override
    public RuleResult validate(char[] password) {
        for (char c : password) {
            if (Character.isWhitespace(c)) {
                return new RuleResult(false, ERROR_CODE);
            }
        }
        return new RuleResult(true);
    }
}