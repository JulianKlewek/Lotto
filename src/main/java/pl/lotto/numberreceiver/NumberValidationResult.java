package pl.lotto.numberreceiver;

import java.util.LinkedList;
import java.util.List;

record NumberValidationResult(String validationStatus, List<String> errorsList) {

    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILURE_MESSAGE = "failure";

    public NumberValidationResult(String validationStatus){
        this(validationStatus, null);
    }

    public static NumberValidationResult success(){
        return new NumberValidationResult(SUCCESS_MESSAGE, new LinkedList<>());
    }

    public static NumberValidationResult failure(List<String> errors){
        return new NumberValidationResult(FAILURE_MESSAGE, errors);
    }

    public boolean isValidationSuccessful(){
        return validationStatus.equals(SUCCESS_MESSAGE);
    }

}
