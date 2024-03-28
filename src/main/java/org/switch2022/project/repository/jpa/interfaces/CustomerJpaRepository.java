package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.CustomerJpa;
import org.switch2022.project.model.value_objects.CustomerID;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpa, CustomerID> {

    /**
     * Retrieves an Optional containing the CustomerJpa entity with the specified customer ID.
     *
     * @param customerID The customer ID of the CustomerJpa entity.
     * @return An Optional containing the CustomerJpa entity if found, or an empty Optional if not found.
     */
    Optional<CustomerJpa> findByCustomerID(String customerID);

    /**
     * Checks if a CustomerJpa entity with the specified customer ID exists in the repository.
     *
     * @param customerID The customer ID of the CustomerJpa entity.
     * @return true if a CustomerJpa entity with the specified customer ID exists, false otherwise.
     */
    boolean existsByCustomerID(String customerID);
}