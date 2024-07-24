package pl.lotto.userauthenticator;

import java.util.Arrays;

class PasswordCleaner {

    public static void cleanPassword(char[] password) {
        Arrays.fill(password, '*');
    }

}
