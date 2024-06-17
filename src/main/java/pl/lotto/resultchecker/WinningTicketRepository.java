package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WinningTicketRepository extends MongoRepository<WinningTicket, Long> {

    Optional<WinningTicket> findByHash(String hash);
}
