package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.Email;

import java.util.*;

@org.springframework.stereotype.Repository("accountMemoryRepository")
public class InMemoryAccountRepository implements Repository<Email, Account> {
    private final Map<Email, Account> data = new HashMap<>();

    /**
     * Saves the provided Account entity in the repository.
     *
     * @param entity The Account entity to save.
     * @return The saved Account entity.
     */
    public Account save(Account entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    /**
     * Retrieves all the Account entities from the repository.
     *
     * @return A list of all the Account entities.
     */
    public List<Account> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Retrieves an Optional containing the Account entity with the specified identity.
     *
     * @param id The identity (Email) of the Account entity.
     * @return An Optional containing the Account entity if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Account> ofIdentity(Email id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }

    /**
     * Checks if the repository contains an Account entity with the specified identity.
     *
     * @param id The identity (Email) of the Account entity.
     * @return true if the Account entity with the specified identity exists in the repository, false otherwise.
     */
    public boolean containsOfIdentity(Email id) {
        return data.containsKey(id);
    }
}