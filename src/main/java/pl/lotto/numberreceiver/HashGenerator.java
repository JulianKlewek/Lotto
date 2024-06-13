package pl.lotto.numberreceiver;

import static java.util.UUID.randomUUID;

class HashGenerator {
    public String getHash() {
        return randomUUID().toString();
    }
}
