package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
class NoSqlTicketRepository implements TicketRepository {

    private final MongoTicketRepository ticketRepository;

    public NoSqlTicketRepository(MongoTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllByDrawDate(Instant drawDate) {
        return ticketRepository.findAllByDrawDate(drawDate);
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}

@Repository
interface MongoTicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findAllByDrawDate(Instant drawDate);
}
