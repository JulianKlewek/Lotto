package pl.lotto.userauthenticator.validator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class RuleResult {

    private boolean valid;
    private final List<String> errors;

    public RuleResult() {
        this(true);
    }

    public RuleResult(boolean valid, String errorDetails) {
        this.valid = valid;
        this.errors = new ArrayList<>();
        this.errors.add(errorDetails);
    }

    public RuleResult(boolean valid) {
        this.valid = valid;
        this.errors = new ArrayList<>();
    }

    public void addErrors(List<String> errors) {
        this.setValid(false);
        this.errors.addAll(errors);
    }

    public void addError(String error) {
        this.setValid(false);
        this.errors.add(error);
    }
}
