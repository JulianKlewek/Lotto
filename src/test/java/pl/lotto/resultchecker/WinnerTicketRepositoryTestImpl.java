package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class WinnerTicketRepositoryTestImpl implements WinningTicketRepository {

    Map<String, WinningTicket> database = new ConcurrentHashMap<>();

    @Override
    public List<WinningTicket> saveAll(List<WinningTicket> winningTickets) {
        winningTickets.forEach(
                ticket -> database.put(ticket.hash, ticket));
        return database.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<WinningTicket> findByHash(String hash) {
        return database.values().stream()
                .filter(winningTicket -> winningTicket.hash.equals(hash))
                .findFirst();
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
    public <S extends WinningTicket> List<S> saveAll(Iterable<S> entities) {
        return null;
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
    public void deleteAll() {

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
