package pl.lotto.numberreceiver;

import java.time.Instant;
import java.util.List;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    List<Ticket> findAll();

    List<Ticket> findAllByDrawDate(Instant drawDate);

    void deleteAll();

}
