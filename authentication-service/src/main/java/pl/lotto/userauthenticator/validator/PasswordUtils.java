package pl.lotto.userauthenticator.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class PasswordUtils {

    private static final Logger logger = LogManager.getLogger(PasswordUtils.class);

    private PasswordUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int countMatchingCharacters(String characters, char[] password) {
        logger.debug("Validating password for characters: [{}]", characters);
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
