package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class WinningTicketRepositoryTestImpl implements WinningTicketRepository {

    Map<String, WinningTicket> database = new ConcurrentHashMap<>();

    @Override
    public <S extends WinningTicket> List<S> saveAll(Iterable<S> winningTickets) {
        winningTickets.forEach(
                ticket -> database.put(ticket.hash, ticket));
        return (List<S>) database.values()
                .stream()
                .toList();
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public Optional<WinningTicket> findByHash(String hash) {
        return database.values().stream()
                .filter(winningTicket -> winningTicket.hash.equals(hash))
                .findFirst();
    }

    @Override
    public boolean existsByDrawDate(Instant drawDate) {
        long resultsForDate = database.values()
                .stream()
                .filter(ticket -> ticket.drawDate.equals(drawDate))
                .count();
        return resultsForDate != 0;
    }

    @Override
    public List<WinningTicket> findAllByDrawDate(Instant drawDate) {
        return database.values()
                .stream()
                .filter(ticket -> ticket.drawDate.equals(drawDate))
                .toList();
    }

    @Override
    public <S extends WinningTicket> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends WinningTicket> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends WinningTicket> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinningTicket> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends WinningTicket> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends WinningTicket> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningTicket> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinningTicket> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinningTicket, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends WinningTicket> S save(S entity) {
        return null;
    }

    @Override
    public Optional<WinningTicket> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<WinningTicket> findAll() {
        return null;
    }

    @Override
    public List<WinningTicket> findAllById(Iterable<Long> longs) {
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
    public void delete(WinningTicket entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinningTicket> entities) {

    }

    @Override
    public List<WinningTicket> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<WinningTicket> findAll(Pageable pageable) {
        return null;
    }
}
