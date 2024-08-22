package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
class NoSqlWinningTicketRepository implements WinningTicketRepository {

    private final MongoWinningTicketRepository ticketRepository;

    public NoSqlWinningTicketRepository(MongoWinningTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<WinningTicket> findByHash(String hash) {
        return ticketRepository.findByHash(hash);
    }

    @Override
    public boolean existsByDrawDate(Instant drawDate) {
        return ticketRepository.existsByDrawDate(drawDate);
    }

    @Override
    public List<WinningTicket> findAllByDrawDate(Instant drawDate) {
        return ticketRepository.findAllByDrawDate(drawDate);
    }

    @Override
    public List<WinningTicket> saveAll(List<WinningTicket> winningTickets) {
        return ticketRepository.saveAll(winningTickets);
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}

@Repository
interface MongoWinningTicketRepository extends MongoRepository<WinningTicket, Long> {

    Optional<WinningTicket> findByHash(String hash);

    boolean existsByDrawDate(Instant drawDate);

    List<WinningTicket> findAllByDrawDate(Instant drawDate);
}