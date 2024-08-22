package pl.lotto.resultchecker;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryWinningTicketRepository implements WinningTicketRepository {

    private final Map<String, WinningTicket> database = new ConcurrentHashMap<>();

    @Override
    public List<WinningTicket> saveAll(List<WinningTicket> winningTickets) {
        winningTickets.forEach(
                ticket -> database.put(ticket.hash, ticket));
        return database.values()
                .stream()
                .toList();
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public Optional<WinningTicket> findByHash(String hash) {
        return database.values().stream()
                .filter(winningTicket -> winningTicket.hash.equals(hash))
                .findFirst();
    }

    @Override
    public boolean existsByDrawDate(Instant drawDate) {
        long resultsForDate = database.values()
                .stream()
                .filter(ticket -> ticket.drawDate.equals(drawDate))
                .count();
        return resultsForDate != 0;
    }

    @Override
    public List<WinningTicket> findAllByDrawDate(Instant drawDate) {
        return database.values()
                .stream()
                .filter(ticket -> ticket.drawDate.equals(drawDate))
                .toList();
    }

}
