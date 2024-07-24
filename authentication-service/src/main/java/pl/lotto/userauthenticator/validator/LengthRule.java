package pl.lotto.userauthenticator.validator;

public class LengthRule implements PasswordRule {

    public static final String ERROR_CODE_MIN = "TOO_SHORT";
    public static final String ERROR_CODE_MAX = "TOO_LONG";

    private final int minimumLength;
    private final int maximumLength;

    public LengthRule(final int minLength, final int maxLength) {
        this.minimumLength = minLength;
        this.maximumLength = maxLength;
    }

    @Override
    public RuleResult validate(char[] password) {
        final RuleResult result = new RuleResult();
        final int length = password.length;
        if (length < minimumLength) {
            result.addError(ERROR_CODE_MIN);
        } else if (length > maximumLength) {
            result.addError(ERROR_CODE_MAX);
        }
        return result;
    }
}
