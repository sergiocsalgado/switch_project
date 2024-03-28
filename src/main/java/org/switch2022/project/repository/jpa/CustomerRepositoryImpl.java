package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.CustomerJpa;
import org.switch2022.project.datamodel.jpa.assemblers.CustomerJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.repository.jpa.interfaces.CustomerJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository("customerJPARepository")
public class CustomerRepositoryImpl implements Repository<CustomerID, Customer> {

    private final CustomerJpaRepository jpaRepository;

    /**
     * Constructs a CustomerRepositoryImpl with the provided CustomerJpaRepository.
     *
     * @param jpaRepository The CustomerJpaRepository to use for data access.
     */
    public CustomerRepositoryImpl(CustomerJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Saves the provided Customer entity in the repository.
     *
     * @param customer The Customer entity to save.
     * @return The saved Customer entity.
     */
    public Customer save(Customer customer) {
        CustomerJpa customerJPAToSave = CustomerJpaAssembler.toDataModel(customer);

        jpaRepository.save(customerJPAToSave);

        return customer;
    }

    /**
     * Retrieves an Optional containing the Customer entity with the specified identity.
     *
     * @param id The identity (CustomerID) of the Customer entity.
     * @return An Optional containing the Customer entity if found, or an empty Optional if not found.
     */
    public Optional<Customer> ofIdentity(CustomerID id) {

        Optional<CustomerJpa> customerJpa = jpaRepository.findByCustomerID(id.getIdOfCustomer());

        if (customerJpa.isPresent()) {
            return Optional.of(CustomerJpaAssembler.toDomain(customerJpa.get()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks if the repository contains a Customer entity with the specified identity.
     *
     * @param id The identity (CustomerID) of the Customer entity.
     * @return true if the Customer entity with the specified identity exists in the repository, false otherwise.
     */
    public boolean containsOfIdentity(CustomerID id) {
        return jpaRepository.existsByCustomerID(id.getIdOfCustomer());
    }

    /**
     * Retrieves all the Customer entities from the repository.
     *
     * @return A list of all the Customer entities.
     */
    @Override
    public List<Customer> findAll() {
        List<CustomerJpa> savedCustomers = jpaRepository.findAll();

        List<Customer> customers = new ArrayList<>();

        for (CustomerJpa ctmsJPA : savedCustomers) {
            customers.add(CustomerJpaAssembler.toDomain(ctmsJPA));
        }
        return customers;
    }
}