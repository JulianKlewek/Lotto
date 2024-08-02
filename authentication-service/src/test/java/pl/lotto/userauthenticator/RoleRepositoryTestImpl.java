package pl.lotto.userauthenticator;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.userauthenticator.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class RoleRepositoryTestImpl implements RoleRepository {

    Map<Long, Role> database = new ConcurrentHashMap<>();
    private static AtomicLong roleId = new AtomicLong(0L);

    @Override
    public Optional<Role> findByName(UserRole name) {
        return database.values().stream()
                .filter(role -> role.getName().equals(name))
                .findFirst();
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            role.setId(roleId.getAndIncrement());
        }
        database.put(role.getId(), role);
        return database.get(role.getId());
    }

    @Override
    public <S extends Role> List<S> saveAll(Iterable<S> roles) {
        for (Role role : roles) {
            if (role.getId() == null) {
                role.setId(roleId.getAndIncrement());
            }
            database.put(role.getId(), role);
        }
        return (List) new ArrayList<Role>(database.values());
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Role> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Role> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Role> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Role getOne(Long aLong) {
        return null;
    }

    @Override
    public Role getById(Long aLong) {
        return null;
    }

    @Override
    public Role getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Role> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Role> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Role> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Role, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public List<Role> findAllById(Iterable<Long> longs) {
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
    public void delete(Role entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Role> entities) {

    }

    @Override
    public List<Role> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }
}
