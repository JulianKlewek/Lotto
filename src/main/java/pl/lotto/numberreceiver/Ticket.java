package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "tickets")
class Ticket {

    String hash;
    Set<Integer> userNumbers;
    ZonedDateTime drawDate;
}
