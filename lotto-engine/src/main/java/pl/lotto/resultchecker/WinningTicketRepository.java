package pl.lotto.resultchecker;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface WinningTicketRepository {

    Optional<WinningTicket> findByHash(String hash);

    boolean existsByDrawDate(Instant drawDate);

    List<WinningTicket> findAllByDrawDate(Instant drawDate);

    List<WinningTicket> saveAll(List<WinningTicket> winningTickets);

    void deleteAll();
}
