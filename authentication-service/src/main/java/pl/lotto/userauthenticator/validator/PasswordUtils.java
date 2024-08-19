package pl.lotto.userauthenticator.validator;

import lombok.extern.log4j.Log4j2;

@Log4j2
final class PasswordUtils {

    private PasswordUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int countMatchingCharacters(String characters, char[] password) {
        log.debug("Validating password for characters: [{}]", characters);
        int count = 0;
        for (char passChar : password) {
            for (char c : characters.toCharArray()) {
                if (passChar == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
