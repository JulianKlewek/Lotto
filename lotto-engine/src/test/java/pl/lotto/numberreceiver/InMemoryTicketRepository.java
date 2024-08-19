package pl.lotto.numberreceiver;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryTicketRepository implements TicketRepository {

    Map<String, Ticket> database = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        database.put(ticket.hash, ticket);
        return database.get(ticket.hash);
    }

    @Override
    public List<Ticket> findAllByDrawDate(Instant drawDate) {
        return database.values()
                .stream()
                .filter(e -> e.drawDate.equals(drawDate))
                .toList();
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public List<Ticket> findAll() {
        return database.values()
                .stream()
                .toList();
    }

}
