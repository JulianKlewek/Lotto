package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "UserTicket")
class Ticket {

    String hash;
    List<Integer> userNumbers;
    Instant drawDate;
}
