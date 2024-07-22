package pl.lotto.userauthenticator.dto;

import lombok.Builder;

import java.util.Arrays;
import java.util.Objects;

@Builder
public record UserLoginRequest(String username, char[] password) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginRequest userLoginRequest = (UserLoginRequest) o;
        return username.equals(userLoginRequest.username)
                && Arrays.equals(password, userLoginRequest.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, Arrays.hashCode(password));
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "username=" + username +
                ", password=" + "[MASKED]" +
                '}';
    }
}
