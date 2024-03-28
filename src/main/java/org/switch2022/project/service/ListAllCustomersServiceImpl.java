package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.mapper.CustomerMapper;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.service.interfaces.ListAllCustomersService;

import java.util.List;

/**
 * Service class for listing all customers.
 */
@Service
public class ListAllCustomersServiceImpl implements ListAllCustomersService {
    private final Repository<CustomerID, Customer> customerRepository;

    /**
     * Constructs a new ListAllCustomersServiceImpl with the given repository.
     *
     * @param customerRepository the repository for customers entities.
     */
    public ListAllCustomersServiceImpl(
            @Qualifier("customerJPARepository") Repository<CustomerID, Customer> customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Returns the CustomerDTO objects representing the customers contained in the customer repository.
     *
     * @return the list of CustomerDTO objects.
     */
    public List<CustomerDTO> getCustomers() {
        return CustomerMapper.listCustomerDTO(customerRepository.findAll());
    }
}
