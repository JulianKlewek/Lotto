package pl.lotto.userauthenticator.validator;

import lombok.ToString;

@ToString
class CharacterRule implements PasswordRule {

    private final CharacterData characterData;
    private final CharacterAmount characterAmount;

    public CharacterRule(CharacterData data) {
        this(data, 1);
    }

    public CharacterRule(CharacterData data, int amount) {
        this.characterAmount = new CharacterAmount(amount);
        this.characterData = data;
    }

    @Override
    public RuleResult validate(char[] password) {
        int matchingCharacters = PasswordUtils.countMatchingCharacters(this.characterData.getCharacters(), password);
        int requiredAmount = this.characterAmount.amount();
        return matchingCharacters < requiredAmount
                ? new RuleResult(false, this.characterData.getErrorCode())
                : new RuleResult(true);
    }

}
