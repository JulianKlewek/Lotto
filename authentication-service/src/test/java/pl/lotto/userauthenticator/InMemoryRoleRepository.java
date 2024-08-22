package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryRoleRepository implements RoleRepository {

    private final Map<Long, Role> database = new ConcurrentHashMap<>();
    private static final AtomicLong roleId = new AtomicLong(0L);

    @Override
    public Role findByName(UserRole name) {
        return database.values().stream()
                .filter(role -> role.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RoleDoesNotExistsException("Role \"" + name + "\" does not exists in map."));
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
    public List<Role> saveAll(Iterable<Role> roles) {
        for (Role role : roles) {
            if (role.getId() == null) {
                role.setId(roleId.getAndIncrement());
            }
            database.put(role.getId(), role);
        }
        return new ArrayList<>(database.values());
    }

}

