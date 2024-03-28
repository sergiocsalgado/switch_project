package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;

import java.util.*;

@org.springframework.stereotype.Repository("customerMemoryRepository")
public class InMemoryCustomerRepository implements Repository<CustomerID, Customer> {

    private final Map<CustomerID, Customer> data = new HashMap<>();

    /**
     * Saves the provided Customer entity in the repository.
     *
     * @param entity The Customer entity to save.
     * @return The saved Customer entity.
     */
    public Customer save(Customer entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    /**
     * Retrieves all the Customer entities from the repository.
     *
     * @return A list of all the Customer entities.
     */
    public List<Customer> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Retrieves an Optional containing the Customer entity with the specified identity.
     *
     * @param id The identity (CustomerID) of the Customer entity.
     * @return An Optional containing the Customer entity if found, or an empty Optional if not found.
     */
    public Optional<Customer> ofIdentity(CustomerID id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }

    /**
     * Checks if the repository contains a Customer entity with the specified identity.
     *
     * @param id The identity (CustomerID) of the Customer entity.
     * @return true if the Customer entity with the specified identity exists in the repository, false otherwise.
     */
    public boolean containsOfIdentity(CustomerID id) {
        return data.containsKey(id);
    }
}