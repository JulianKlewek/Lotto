package pl.lotto.userauthenticator.validator;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public interface PasswordValidationTestConstants {

    static Stream<Arguments> providePasswordsAndErrorsForIncorrectPassword() {
        return Stream.of(
                Arguments.of("PASSWORD! ".toCharArray(), List.of(
                        BasicCharacterData.LowerCase.getErrorCode(),
                        BasicCharacterData.Digit.getErrorCode(),
                        WhitespaceRule.ERROR_CODE
                )),
                Arguments.of("PASSWOR D".toCharArray(), List.of(
                        BasicCharacterData.LowerCase.getErrorCode(),
                        BasicCharacterData.Digit.getErrorCode(),
                        BasicCharacterData.Special.getErrorCode(),
                        WhitespaceRule.ERROR_CODE
                )),
                Arguments.of("password !1".toCharArray(), List.of(
                        BasicCharacterData.UpperCase.getErrorCode(),
                        WhitespaceRule.ERROR_CODE
                )),
                Arguments.of("password 1".toCharArray(), List.of(
                        BasicCharacterData.UpperCase.getErrorCode(),
                        BasicCharacterData.Special.getErrorCode(),
                        WhitespaceRule.ERROR_CODE
                )),
                Arguments.of("123424141 !1".toCharArray(), List.of(
                        BasicCharacterData.LowerCase.getErrorCode(),
                        BasicCharacterData.UpperCase.getErrorCode(),
                        WhitespaceRule.ERROR_CODE
                )),
                Arguments.of("12345678".toCharArray(), List.of(
                        BasicCharacterData.LowerCase.getErrorCode(),
                        BasicCharacterData.UpperCase.getErrorCode(),
                        BasicCharacterData.Special.getErrorCode()
                )),
                Arguments.of("!@#$%^&*".toCharArray(), List.of(
                        BasicCharacterData.LowerCase.getErrorCode(),
                        BasicCharacterData.UpperCase.getErrorCode(),
                        BasicCharacterData.Digit.getErrorCode()
                )),
                Arguments.of("!@#$%^&* ".toCharArray(), List.of(
                        BasicCharacterData.LowerCase.getErrorCode(),
                        BasicCharacterData.UpperCase.getErrorCode(),
                        BasicCharacterData.Digit.getErrorCode(),
                        WhitespaceRule.ERROR_CODE
                ))
        );
    }
}
