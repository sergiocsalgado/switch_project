package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.CustomerJpa;
import org.switch2022.project.datamodel.jpa.assemblers.CustomerJpaAssembler;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.repository.jpa.interfaces.CustomerJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerRepositoryImpl.class)
class CustomerRepositoryImplTest {

    @MockBean
    Customer customer;

    @MockBean
    CustomerJpa customerJpa;

    @MockBean
    Optional optionalCustomerJpa;

    @MockBean
    CustomerID customerID;

    @MockBean
    CustomerJpaRepository customerJpaRepository;

    @Autowired
    CustomerRepositoryImpl customerRepositoryImpl;

    /**
     * This method tests the save() method of the CustomerRepositoryImpl class.
     * It verifies that the save operation returns the expected Customer object.
     */
    @Test
    void shouldSaveCostumer() {

        // Arrange
        Customer expected = customer;

        try (MockedStatic<CustomerJpaAssembler> assemblerDouble = Mockito.mockStatic(CustomerJpaAssembler.class)) {
            assemblerDouble.when(() -> CustomerJpaAssembler.toDataModel(customer)).thenReturn(customerJpa);

            when(customerJpaRepository.save(customerJpa)).thenReturn(customerJpa);
            // Act
            Customer result = customerRepositoryImpl.save(customer);
            // Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Tests the retrieval of an Optional<Customer> using the ofIdentity() method of CustomerRepositoryImpl.
     * It verifies that the expected Optional<Customer> is returned when a customer is found with the given ID.
     */
    @Test
    void ensureGetOptionalCustomer() {

        // Arrange
        when(customerID.getIdOfCustomer()).thenReturn("1L");
        when(customerJpaRepository.findByCustomerID("1L")).thenReturn(optionalCustomerJpa);
        when(optionalCustomerJpa.isPresent()).thenReturn(true);
        when(optionalCustomerJpa.get()).thenReturn(customerJpa);

        Optional<Customer> expected = Optional.of(customer);

        try (MockedStatic<CustomerJpaAssembler> assemblerDouble = Mockito.mockStatic(CustomerJpaAssembler.class)) {
            assemblerDouble.when(() -> CustomerJpaAssembler.toDomain(customerJpa)).thenReturn(customer);

            when(customerJpaRepository.findByCustomerID("1L")).thenReturn(optionalCustomerJpa);
            // Act
            Optional<Customer> result = customerRepositoryImpl.ofIdentity(customerID);
            // Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Tests the retrieval of an empty Optional<Customer> using the ofIdentity() method of CustomerRepositoryImpl.
     * It verifies that an empty Optional<Customer> is returned when no customer is found with the given ID.
     */
    @Test
    void ensureEmptyOptional() {

        // Arrange
        Optional<Customer> expected = Optional.empty();

        try (MockedStatic<CustomerJpaAssembler> assemblerDouble = Mockito.mockStatic(CustomerJpaAssembler.class)) {
            assemblerDouble.when(() -> CustomerJpaAssembler.toDomain(customerJpa)).thenReturn(customer);

            when(customerJpaRepository.findByCustomerID("1L")).thenReturn(optionalCustomerJpa);
            // Act
            Optional<Customer> result = customerRepositoryImpl.ofIdentity(customerID);
            // Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Tests the checking of whether a customer exists using the containsOfIdentity() method of CustomerRepositoryImpl.
     * It verifies that true is returned when a customer with the given ID exists.
     */
    @Test
    void ensureContainsCustomer() {
        // Arrange
        when(customerID.getIdOfCustomer()).thenReturn("1L");
        when(customerJpaRepository.existsByCustomerID("1L")).thenReturn(true);

        boolean expected = true;
        // Act
        boolean result = customerRepositoryImpl.containsOfIdentity(customerID);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void findAll() {
        List<CustomerJpa> savedCustomers = Collections.singletonList(customerJpa);
        when(customerJpaRepository.findAll()).thenReturn(savedCustomers);

        List<Customer> expected = Collections.singletonList(customer);

        try (MockedStatic<CustomerJpaAssembler> assemblerDouble =
                     Mockito.mockStatic(CustomerJpaAssembler.class)) {

            assemblerDouble.when(() ->
                    CustomerJpaAssembler.toDomain(customerJpa)).thenReturn(customer);

            List<Customer> result = customerRepositoryImpl.findAll();

            assertEquals(expected, result);
        }
    }
}
