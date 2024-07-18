package pl.lotto.userauthenticator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pl.lotto.userauthenticator.validator.ValidPassword;

import java.util.Arrays;
import java.util.Objects;

@Builder
public record UserRegisterRequest(
        @NotNull(message = "CANNOT_BE_NULL")
        @NotEmpty(message = "CANNOT_BE_EMPTY")
        @Size(min = 4, max = 20, message = "MUST_BE_IN_RANGE_4_TO_20")
        String username,

        @NotNull(message = "CANNOT_BE_NULL")
        @NotEmpty(message = "CANNOT_BE_EMPTY")
        @Email
        @Size(min = 8, max = 50, message = "MUST_BE_IN_RANGE_8_TO_50")
        String email,

        @ValidPassword
        char[] password) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisterRequest userRegisterRequest = (UserRegisterRequest) o;
        return username.equals(userRegisterRequest.username)
                && email.equals(userRegisterRequest.email)
                && Arrays.equals(password, userRegisterRequest.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, email, Arrays.hashCode(password));
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "username=" + username +
                ", email=" + email +
                ", password=" + "[MASKED]" +
                '}';
    }
}
