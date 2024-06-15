package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WinningTicketRepository extends MongoRepository<WinningTicket, Long> {

    List<WinningTicket> saveAll(List<WinningTicket> winningTickets);
}
