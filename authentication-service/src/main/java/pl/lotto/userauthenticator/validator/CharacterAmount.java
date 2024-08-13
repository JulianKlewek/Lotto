package pl.lotto.userauthenticator.validator;

record CharacterAmount(int amount) {

    public CharacterAmount {
        if (amount <= 0) {
            throw new IllegalArgumentException("Argument must be greater than zero");
        }
    }
}
