package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the CustomerRepository class, which is responsible for
 * testing the behavior of the CustomerRepository class methods.
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryCustomerRepository.class)
class InMemoryCustomerRepositoryTest {

    @InjectMocks
    InMemoryCustomerRepository inMemoryCustomerRepository;

    @MockBean
    Customer customer;

    @MockBean
    CustomerID customerID;

    /**
     * This method is executed before each test case to initialize the CustomerRepository instance.
     *
     * @throws Exception if an error occurs during setup.
     */

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Unit Test for {@link InMemoryCustomerRepository#save(Customer)}.
     * Unit Test for {@link InMemoryCustomerRepository#findAll()}.
     */
    @Test
    void ensureCustomerSavedIsReturned() {
        //ARRANGE
        when(customer.identity()).thenReturn(customerID);
        Customer saveCustomer = inMemoryCustomerRepository.save(customer);
        List<Customer> expected = Arrays.asList(saveCustomer);
        //ACT
        List<Customer> customers = inMemoryCustomerRepository.findAll();
        //ASSERT
        assertEquals(expected, customers);
    }

    /**
     * Unit Test for {@link InMemoryCustomerRepository#save(Customer)}.
     * Unit Test for {@link InMemoryCustomerRepository#ofIdentity(CustomerID)}.
     * Unit Test for {@link InMemoryCustomerRepository#containsOfIdentity(CustomerID)}.
     */
    @Test
    void ensureReturnOptionalOfCustomer() {
        //ARRANGE
        when(customer.identity()).thenReturn(customerID);
        inMemoryCustomerRepository.save(customer);
        Optional<Customer> expected = Optional.of(customer);
        //ACT
        Optional<Customer> result = inMemoryCustomerRepository.ofIdentity(customerID);
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link InMemoryCustomerRepository#save(Customer)}.
     * Unit Test for {@link InMemoryCustomerRepository#ofIdentity(CustomerID)}.
     * Unit Test for {@link InMemoryCustomerRepository#containsOfIdentity(CustomerID)}.
     */
    @Test
    void ensureReturnsEmptyOptional() {
        //ARRANGE
        when(customer.identity()).thenReturn(customerID);
        inMemoryCustomerRepository.save(customer);
        Optional<Customer> expected = Optional.empty();
        CustomerID id = mock(CustomerID.class);
        //ACT
        Optional<Customer> result = inMemoryCustomerRepository.ofIdentity(id);
        //ASSERT
        assertEquals(expected, result);
    }
}