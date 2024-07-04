package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface WinningTicketRepository extends MongoRepository<WinningTicket, Long> {

    Optional<WinningTicket> findByHash(String hash);

    boolean existsByDrawDate(Instant drawDate);

    List<WinningTicket> findAllByDrawDate(Instant drawDate);
}
