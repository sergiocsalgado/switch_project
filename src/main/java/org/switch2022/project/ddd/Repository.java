package org.switch2022.project.ddd;

import java.util.List;
import java.util.Optional;

public interface Repository<ID extends EntityID, T extends AggregateRoot<ID>> {
    T save(T entity);

    List<T> findAll();

    Optional<T> ofIdentity(ID id);

    boolean containsOfIdentity(ID id);
}
