package pl.lotto.numberreceiver;

enum ValidationError {

    MORE_THAN_SIX_NUMBERS("User provided more than six numbers"),
    LESS_THAN_SIX_NUMBERS("User provided less than six numbers"),
    OUT_OF_RANGE("User provided numbers out of range"),
    DUPLICATED_NUMBERS("User provided duplicated numbers");

    final String errorMessage;

    ValidationError(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
