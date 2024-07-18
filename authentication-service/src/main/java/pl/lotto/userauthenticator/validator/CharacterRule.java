package pl.lotto.userauthenticator.validator;

class CharacterRule implements PasswordRule {

    protected final CharacterData characterData;
    protected int numCharacters;

    public CharacterRule(CharacterData data) {
        this(data, 1);
    }

    public CharacterRule(CharacterData data, int num) {
        this.numCharacters = 1;
        this.setNumberOfCharacters(num);
        this.characterData = data;
    }

    public void setNumberOfCharacters(int n) {
        if (n > 0) {
            this.numCharacters = n;
        } else {
            throw new IllegalArgumentException("argument must be greater than zero");
        }
    }

    @Override
    public RuleResult validate(char[] password) {
        int matchingCharacters = PasswordUtils.countMatchingCharacters(this.characterData.getCharacters(), password);
        return matchingCharacters < this.numCharacters
                ? new RuleResult(false, this.characterData.getErrorCode())
                : new RuleResult(true);
    }
}
