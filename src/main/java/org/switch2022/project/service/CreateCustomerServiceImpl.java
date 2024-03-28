package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.customer.FactoryCustomer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;
import org.switch2022.project.service.interfaces.CreateCustomerService;

import java.util.List;

/**
 * Service implementation class for creating customers.
 * This class provides methods to create and save customers,
 * as well as checking for existing customers based on ID and name.
 * It uses a factory for customer creation and a customer repository for data storage.
 */
@Service
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private final FactoryCustomer factoryCustomer;

    private final Repository<CustomerID, Customer> customerRepository;

    /**
     * Constructs a new instance of {@code CreateCustomerServiceImpl}.
     *
     * @param factoryCustomer    the factory for creating customers
     * @param customerRepository the customer repository for data storage
     */
    public CreateCustomerServiceImpl(
            FactoryCustomer factoryCustomer,
            @Qualifier("customerJPARepository") Repository<CustomerID, Customer> customerRepository) {
        this.factoryCustomer = factoryCustomer;
        this.customerRepository = customerRepository;
    }

    /**
     * Checks if a customer with the same ID or name already exists in the repository.
     *
     * @param customer the customer to check
     * @return {@code true} if a customer with the same ID or name exists, {@code false} otherwise
     */
    public boolean containsIDOrDescription(Customer customer) {
        return customerRepository.containsOfIdentity(customer.getCustomerID())
                || existsName(customer.getName());
    }

    private boolean existsName(Name name) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            if (customer.getName().getValue().equalsIgnoreCase(name.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and saves a new customer with the provided ID, name, and NIF.
     *
     * @param id   the ID of the customer
     * @param name the name of the customer
     * @param nif  the NIF (tax identification number) of the customer
     * @return the created and saved customer
     * @throws IllegalArgumentException if the provided parameters
     * are invalid or if a customer with the same ID or name already exists
     */
    public Customer createAndSaveCustomer(String id, String name, String nif) {
        CustomerID customerID;
        Name customerName;
        Nif customerNif;

        try {
            customerID = new CustomerID(id);
            customerName = new Name(name);
            customerNif = new Nif(nif);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Invalid Parameters");
        }

        Customer customer = factoryCustomer.createCustomer(customerID, customerName, customerNif);

        if (!containsIDOrDescription(customer)) {
            return customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer already exists");
        }
    }
}