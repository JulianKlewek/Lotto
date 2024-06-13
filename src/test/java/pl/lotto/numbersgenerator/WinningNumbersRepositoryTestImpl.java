package pl.lotto.numbersgenerator;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class WinningNumbersRepositoryTestImpl implements WinningNumbersRepository{

    Map<Long, WinningNumbersDetails> winningNumbersMap = new ConcurrentHashMap<>();

    @Override
    public WinningNumbersDetails save(WinningNumbersDetails winningNumbersDetails) {
        return  winningNumbersMap.put(winningNumbersDetails.lotteryNumber, winningNumbersDetails);
    }

    @Override
    public Long findFirstByLotteryNumberOrderByLotteryNumberDesc() {
        winningNumbersMap.put(100L, new WinningNumbersDetails(new HashSet<Integer>(), ZonedDateTime.now(), 100L));
        return winningNumbersMap.keySet().stream()
                .sorted()
                .findFirst()
                .orElse(0L);
//        return null;
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
