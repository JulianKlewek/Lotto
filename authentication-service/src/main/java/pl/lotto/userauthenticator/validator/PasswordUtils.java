package pl.lotto.userauthenticator.validator;

final class PasswordUtils {

    private PasswordUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int countMatchingCharacters(String characters, char[] password) {
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
