package pl.lotto.userauthenticator.dto;

import lombok.Builder;

import java.util.Arrays;
import java.util.Objects;

@Builder
public record UserRegisterRequest(String username, String email, char[] password) {

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
