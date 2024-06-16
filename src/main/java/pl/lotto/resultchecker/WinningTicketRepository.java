package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface WinningTicketRepository extends MongoRepository<WinningTicket, Long> {

    List<WinningTicket> saveAll(List<WinningTicket> winningTickets);

    Optional<WinningTicket> findByNumbersAndDrawDate(List<Integer> numbers, Instant drawDate);
    Optional<WinningTicket> findByNumbersAndLotteryNumber(List<Integer> numbers, Long lotteryNumber);
    Optional<WinningTicket> findByHash(String hash);
}