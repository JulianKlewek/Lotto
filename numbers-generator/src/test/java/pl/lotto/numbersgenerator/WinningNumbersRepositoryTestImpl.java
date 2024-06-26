package pl.lotto.numbersgenerator;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class WinningNumbersRepositoryTestImpl implements WinningNumbersRepository {

    Map<Long, WinningNumbersDetails> database = new ConcurrentHashMap<>();

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
    public Optional<WinningNumbersDetails> findByDrawDate(Instant drawDate) {
        Optional<WinningNumbersDetails> winningNumbers = database.values().stream()
                .filter(e -> e.drawDate.equals(drawDate))
                .findFirst();
        return winningNumbers;
    }

    @Override
    public Optional<WinningNumbersDetails> findByLotteryNumber(Long lotteryNumber) {
        Optional<WinningNumbersDetails> winningNumbers = database.values().stream()
                .filter(e -> e.lotteryNumber.equals(lotteryNumber))
                .findFirst();
        return winningNumbers;
    }

    @Override
    public Optional<WinningNumbersDetails> findFirstByOrderByDrawDate() {
        return database.values().stream()
                .sorted(Comparator.comparing(o -> o.drawDate))
                .findFirst();
    }

    @Override
    public boolean existsByDrawDate(Instant drawDate) {
        return database.values().stream()
                .filter(ticket -> ticket.drawDate.equals(drawDate))
                .count() == 1;
    }

    @Override

    public <S extends WinningNumbersDetails> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends WinningNumbersDetails> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends WinningNumbersDetails> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinningNumbersDetails> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends WinningNumbersDetails> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends WinningNumbersDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningNumbersDetails> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinningNumbersDetails> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinningNumbersDetails, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends WinningNumbersDetails> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WinningNumbersDetails> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<WinningNumbersDetails> findAll() {
        return null;
    }

    @Override
    public List<WinningNumbersDetails> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(WinningNumbersDetails entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinningNumbersDetails> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<WinningNumbersDetails> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<WinningNumbersDetails> findAll(Pageable pageable) {
        return null;
    }
}
