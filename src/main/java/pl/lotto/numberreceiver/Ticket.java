package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@Document(collation = "ticket")
class Ticket {

    String hash;
    Set<Integer> userNumbers;
    Instant drawDate;
}
