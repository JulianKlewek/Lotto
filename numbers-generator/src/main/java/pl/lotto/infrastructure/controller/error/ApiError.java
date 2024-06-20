package pl.lotto.infrastructure.controller.error;

import java.util.List;

public record ApiError(List<String> errors) {
}
