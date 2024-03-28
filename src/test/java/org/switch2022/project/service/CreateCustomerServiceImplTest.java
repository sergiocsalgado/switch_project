package org.switch2022.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.customer.FactoryCustomer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the CreateCustomerServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateCustomerServiceImpl.class)
class CreateCustomerServiceImplTest {

    @MockBean
    FactoryCustomer factoryCustomer;

    @MockBean
    @Qualifier("customerJPARepository")
    Repository<CustomerID, Customer> customerRepository;

    @MockBean
    Customer customer;

    CreateCustomerServiceImpl createCustomerServiceImpl;

    /**
     * This method is executed before each test case to set up the required dependencies
     * and initialize the CreateCustomerServiceImpl instance.
     *
     * @throws Exception if an error occurs during setup.
     */
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        createCustomerServiceImpl = new CreateCustomerServiceImpl(factoryCustomer, customerRepository);
    }

    /**
     * Test case for {@link CreateCustomerServiceImpl#createAndSaveCustomer(String, String, String)}.
     * It tests the creation and saving of a valid customer with valid attributes.
     */
    @Test
    void shouldCreateAndSaveAValidCustomerWithValidAttributes_expectTrue_1() {
        // Arrange
        String cID = "customer1";
        String cName = "Adidas";
        String cNif = "240606019";

        CustomerID customerID = new CustomerID(cID);
        Name customerName = new Name(cName);
        Nif customerNif = new Nif(cNif);

        when(factoryCustomer.createCustomer(customerID, customerName, customerNif)).thenReturn(customer);
        when(customerRepository.containsOfIdentity(customerID)).thenReturn(false);
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer expected = customer;

        // Act
        Customer result = createCustomerServiceImpl.createAndSaveCustomer(cID, cName, cNif);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test case for {@link CreateCustomerServiceImpl#createAndSaveCustomer(String, String, String)}.
     * It tests the creation and saving of a valid customer with valid attributes and verifies the behavior
     * when there are existing customers with different IDs but the same name.
     */
    @Test
    void shouldCreateAndSaveAValidCustomerWithValidAttributes_expectTrue_2() {
        // Arrange
        String cID = "customer1";
        String cName = "Adidas";
        String cNif = "240606019";

        CustomerID customerID = new CustomerID(cID);
        Name customerName = new Name(cName);
        Nif customerNif = new Nif(cNif);

        Customer customerDouble = mock(Customer.class);
        when(customerDouble.getCustomerID()).thenReturn(new CustomerID("customer2"));
        when(customerDouble.getName()).thenReturn(new Name("name"));
        when(customerDouble.getNif()).thenReturn(new Nif("000000000"));

        when(factoryCustomer.createCustomer(customerID, customerName, customerNif)).thenReturn(customer);
        when(customerRepository.containsOfIdentity(customerID)).thenReturn(false);
        when(customer.getCustomerID()).thenReturn(customerID);

        List<Customer> customersList = List.of(customerDouble);
        when(customerRepository.findAll()).thenReturn(customersList);

        when(customer.getName()).thenReturn(customerName);
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer expected = customer;

        // Act
        Customer result = createCustomerServiceImpl.createAndSaveCustomer(cID, cName, cNif);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test case for {@link CreateCustomerServiceImpl#createAndSaveCustomer(String, String, String)}.
     * It tests the behavior when attempting to save a customer that already exists in the repository.
     * Expects an IllegalArgumentException to be thrown.
     */
    @Test
    void shouldNotSaveCustomerThatAlreadyExists_expectFalse() {
        // Arrange
        String cID = "ctmr1";
        String cName = "Adidas";
        String cNif = "240606019";

        CustomerID customerID = new CustomerID(cID);
        Name customerName = new Name(cName);
        Nif customerNif = new Nif(cNif);

        when(factoryCustomer.createCustomer(customerID, customerName, customerNif)).thenReturn(customer);

        when(customer.getCustomerID()).thenReturn(customerID);
        when(customer.getName()).thenReturn(customerName);
        when(customer.getNif()).thenReturn(customerNif);

        when(customerRepository.containsOfIdentity(customerID)).thenReturn(true);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createCustomerServiceImpl.createAndSaveCustomer(cID, cName, cNif);
        });

        // Assert
        assertEquals("Customer already exists", exception.getMessage());
    }

    /**
     * Test case for {@link CreateCustomerServiceImpl#createAndSaveCustomer(String, String, String)}.
     * It tests the behavior when attempting to create a customer with a name that already exists in the repository.
     * Expects an IllegalArgumentException to be thrown.
     */
    @Test
    void shouldNotCreateCustomerBecauseNameAlreadyExists() {
        // Arrange
        String cID = "customer1";
        String cName = "Adidas";
        String cNif = "240606019";

        CustomerID customerID = new CustomerID(cID);
        Name customerName = new Name(cName);
        Nif customerNif = new Nif(cNif);

        Customer customerDouble = mock(Customer.class);
        when(customerDouble.getCustomerID()).thenReturn(customerID);
        when(customerDouble.getName()).thenReturn(customerName);
        when(customerDouble.getNif()).thenReturn(customerNif);

        when(factoryCustomer.createCustomer(customerID, customerName, customerNif)).thenReturn(customer);
        when(customerRepository.containsOfIdentity(customerID)).thenReturn(false);
        when(customer.getCustomerID()).thenReturn(customerID);

        List<Customer> customersList = List.of(customerDouble);
        when(customerRepository.findAll()).thenReturn(customersList);

        when(customer.getName()).thenReturn(customerName);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createCustomerServiceImpl.createAndSaveCustomer(cID, cName, cNif);
        });

        // Assert
        assertEquals("Customer already exists", exception.getMessage());
    }

    /**
     * Test case for {@link CreateCustomerServiceImpl#createAndSaveCustomer(String, String, String)}.
     * It tests the behavior when providing invalid parameters (null or empty strings).
     * Expects an IllegalArgumentException to be thrown.
     */
    @Test
    void ensureCustomerIsAdded_ExpectFalse() {
        // Arrange
        String customerID = "costumer1";
        String name = "Adidas";
        String nif = "123456788";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createCustomerServiceImpl.createAndSaveCustomer(customerID, name, nif);
        });

        // Assert
        assertEquals("Invalid Parameters", exception.getMessage());
    }
}
