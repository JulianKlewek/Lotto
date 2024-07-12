package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.dto.UserInfoResponse;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;
import pl.lotto.userauthenticator.entity.User;

class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UserRegisterResponse entityToResponse(User user) {
        return UserRegisterResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static UserInfoResponse userDetailsToInfoResponse(UserDetailsImpl userDetails) {
        return UserInfoResponse.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .build();
    }
}
