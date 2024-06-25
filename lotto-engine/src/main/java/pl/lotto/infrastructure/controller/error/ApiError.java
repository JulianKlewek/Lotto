package pl.lotto.infrastructure.controller.error;

import lombok.Builder;

import java.util.List;

@Builder
public record ApiError(List<String> errors) {
}
