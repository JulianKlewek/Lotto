package pl.lotto.numberreceiver;

import java.util.UUID;

class HashGenerator {
    public String getHash() {
        return UUID.randomUUID().toString();
    }
}
