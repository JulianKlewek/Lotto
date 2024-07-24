package pl.lotto.userauthenticator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserInfoResponse(
        @Schema(description = "User id", example = "1")
        Long id,
        @Schema(description = "Username", example = "User1")
        String username,
        @Schema(description = "User email", example = "user1@gmail.com")
        String email) {
}
