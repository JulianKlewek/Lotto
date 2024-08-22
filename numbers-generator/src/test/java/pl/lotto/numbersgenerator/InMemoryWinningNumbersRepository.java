package pl.lotto.numbersgenerator;

import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryWinningNumbersRepository implements WinningNumbersRepository {

    private final Map<Long, WinningNumbersDetails> database = new ConcurrentHashMap<>();

    @Override
    public WinningNumbersDetails save(WinningNumbersDetails winningNumbersDetails) {
        database.put(winningNumbersDetails.lotteryNumber, winningNumbersDetails);
        return database.get(winningNumbersDetails.lotteryNumber);
    }

    @Override
    public Optional<WinningNumbersDetails> findTopByOrderByLotteryNumberDesc() {
        return database.values().stream()
                .max(Comparator.comparing(o -> o.lotteryNumber));
    }

    @Override
    public WinningNumbersDetails findByDrawDate(Instant drawDate) {
        return database.values().stream()
                .filter(e -> e.drawDate.equals(drawDate))
                .findFirst()
                .orElseThrow(() -> new WinningNumbersNotFoundException("Winning numbers not found"));
    }

    @Override
    public WinningNumbersDetails findByLotteryNumber(Long lotteryNumber) {
        return database.values().stream()
                .filter(e -> e.lotteryNumber.equals(lotteryNumber))
                .findFirst()
                .orElseThrow(() -> new WinningNumbersNotFoundException("Winning numbers not found"));
    }

    @Override
    public WinningNumbersDetails findFirstByOrderByDrawDate() {
        return database.values().stream()
                .min(Comparator.comparing(o -> o.drawDate))
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not found any winning numbers"));
    }

    @Override
    public boolean existsByDrawDate(Instant drawDate) {
        return database.values().stream()
                .filter(ticket -> ticket.drawDate.equals(drawDate))
                .count() == 1;
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

}
