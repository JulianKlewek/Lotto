package pl.lotto.userauthenticator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserLoginResponse(
        @Schema(description = "User details")
        UserInfoResponse user,
        @Schema(description = "Access token",
                example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVc2VyMTEiLCJpYXQiOjE3MjE3NDgwMDMsImV4cCI6MTcyMTc0ODAwM30." +
                        "Diu774nEgvzxdF-ATENrVibDAc8GHQb04xDmSDDLJv-CSVIno0f8jUS0_8MDdSTQ3rxI5H0OkG9hklZPFrzj5A")
        String accessToken) {
}
