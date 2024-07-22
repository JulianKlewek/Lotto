package pl.lotto.userauthenticator.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

class PasswordValidatorTest implements PasswordValidationTestConstants {

    @Test
    @DisplayName("More than one lowercase")
    void should_return_valid_rule_result_when_array_contains_more_than_one_lower_case_char() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.LowerCase));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Exactly one lowercase")
    void should_return_valid_rule_result_when_array_contains_one_lower_case_char() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.LowerCase));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "PASSWORd".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Less than one lowercase")
    void should_return_invalid_rule_result_when_array_contains_less_than_one_lower_case_char() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.LowerCase));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "PASSWORD".toCharArray();
        List<String> errorsList = List.of("INSUFFICIENT_LOWERCASE");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("More than one uppercase")
    void should_return_valid_rule_result_when_array_contains_more_than_one_upper_case_char() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.UpperCase));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "PASSWORD".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Exactly one uppercase")
    void should_return_valid_rule_result_when_array_contains_exactly_one_upper_case_char() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.UpperCase));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Less than one uppercase")
    void should_return_invalid_rule_result_when_array_contains_less_than_one_upper_case_char() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.UpperCase));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "password".toCharArray();
        List<String> errorsList = List.of("INSUFFICIENT_UPPERCASE");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("More than one digit")
    void should_return_valid_rule_result_when_array_contains_more_than_one_digit() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.Digit));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "PASSWORD12".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Exactly one digit")
    void should_return_valid_rule_result_when_array_contains_exactly_one_digit() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.Digit));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password1".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Less than one digit")
    void should_return_invalid_rule_result_when_array_contains_less_than_one_digit() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.Digit));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "password".toCharArray();
        List<String> errorsList = List.of("INSUFFICIENT_DIGIT");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("More than one special character")
    void should_return_valid_rule_result_when_array_contains_more_than_one_special_character() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.Digit));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "PASSWORD12!@#$".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Exactly one special character")
    void should_return_valid_rule_result_when_array_contains_exactly_one_special_character() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.Digit));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password1!".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Less than one special character")
    void should_return_invalid_rule_result_when_array_contains_less_than_one_special_character() {
        //given
        List<PasswordRule> rules = List.of(new CharacterRule(BasicCharacterData.Digit));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "password".toCharArray();
        List<String> errorsList = List.of("INSUFFICIENT_DIGIT");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("Does not contain white spaces")
    void should_return_valid_rule_result_when_array_not_contains_white_spaces() {
        //given
        List<PasswordRule> rules = List.of(new WhitespaceRule());
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password12#$%".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Contains white spaces")
    void should_return_invalid_rule_result_when_array_contains_white_space() {
        //given
        List<PasswordRule> rules = List.of(new WhitespaceRule());
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "passwor d".toCharArray();
        List<String> errorsList = List.of("ILLEGAL_WHITESPACE");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("Length is correct")
    void should_return_valid_rule_result_when_array_length_is_correct() {
        //given
        List<PasswordRule> rules = List.of(new LengthRule(8, 30));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password12#$%".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @Test
    @DisplayName("Length is too short")
    void should_return_invalid_rule_result_when_array_length_is_shorter_than_rule_min_length() {
        //given
        List<PasswordRule> rules = List.of(new LengthRule(4, 10));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "123".toCharArray();
        List<String> errorsList = List.of("TOO_SHORT");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("Length equal to rule min length")
    void should_return_invalid_rule_result_when_array_length_is_equal_to_rule_min_length() {
        //given
        List<PasswordRule> rules = List.of(new LengthRule(4, 10));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "1234".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
    }

    @Test
    @DisplayName("Longer than rule max length")
    void should_return_invalid_rule_result_when_array_length_is_longer_than_rule_max_length() {
        //given
        List<PasswordRule> rules = List.of(new LengthRule(8, 10));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "12345678901".toCharArray();
        List<String> errorsList = List.of("TOO_LONG");
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(errorsList);
    }

    @Test
    @DisplayName("Length equal to rule max length")
    void should_return_invalid_rule_result_when_array_length_is_equal_to_rule_max_length() {
        //given
        List<PasswordRule> rules = List.of(new LengthRule(8, 10));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "1234567890".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
    }

    @Test
    @DisplayName("Correct password with all rules applied")
    void should_return_valid_rule_result_when_password_correct_with_all_rules_applied() {
        //given
        List<PasswordRule> rules = Arrays.asList(
                new CharacterRule(BasicCharacterData.LowerCase),
                new CharacterRule(BasicCharacterData.UpperCase),
                new CharacterRule(BasicCharacterData.Digit),
                new CharacterRule(BasicCharacterData.Special),
                new WhitespaceRule(),
                new LengthRule(8, 30));
        PasswordValidator validator = new PasswordValidator(rules);
        char[] password = "Password12#$%".toCharArray();
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isTrue();
        Assertions.assertThat(result.getErrors()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("providePasswordsAndErrorsForIncorrectPassword")
    @DisplayName("Incorrect password with all rules applied")
    void should_return_invalid_rule_result_password_incorrect_with_all_rules_applied(char[] password, List<String> expectedErrors) {
        //given
        List<PasswordRule> rules = Arrays.asList(
                new CharacterRule(BasicCharacterData.LowerCase),
                new CharacterRule(BasicCharacterData.UpperCase),
                new CharacterRule(BasicCharacterData.Digit),
                new CharacterRule(BasicCharacterData.Special),
                new WhitespaceRule(),
                new LengthRule(5, 15));
        PasswordValidator validator = new PasswordValidator(rules);
        //when
        RuleResult result = validator.validate(password);
        //then
        Assertions.assertThat(result.isValid()).isFalse();
        Assertions.assertThat(result.getErrors()).isEqualTo(expectedErrors);
    }

}