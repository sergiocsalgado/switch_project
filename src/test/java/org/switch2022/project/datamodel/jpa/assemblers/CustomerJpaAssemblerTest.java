package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.*;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerJpaAssembler.class
)

/**
 * Javadoc for the CustomerJpaAssemblerTest class.
 * This class contains test methods to verify the behavior of the CustomerJpaAssembler class.
 */

class CustomerJpaAssemblerTest {

    @MockBean
    CustomerJpa customerJpa;

    @MockBean
    Customer customer;

    @MockBean
    CustomerID customerID;

    @MockBean
    Name name;

    @MockBean
    Nif nif;

    /**
     * Test method to ensure that the toDomain() method of CustomerJpaAssembler converts a CustomerJpa object to a Customer object.
     * It mocks the necessary dependencies and verifies that the converted Customer object contains the expected values.
     */
    @Test
    void ensureGetCustomerToDataModel() {
        // Arrange
        String id = "1";
        String name = "Joao Manuel";
        String nif = "240606019";

        when(customerJpa.getCustomerID()).thenReturn(id);
        when(customerJpa.getName()).thenReturn(name);
        when(customerJpa.getNif()).thenReturn(nif);

        // Act
        Customer customer = CustomerJpaAssembler.toDomain(customerJpa);

        // Assert
        assertEquals(id, customer.getCustomerID().getIdOfCustomer());
        assertEquals(customer.getName().getValue(), name);
        assertEquals(customer.getNif().getNif(), nif);
    }

    /**
     * Test method to ensure that the toDataModel() method of CustomerJpaAssembler converts a Customer object to a CustomerJpa object.
     * It mocks the necessary dependencies, creates a Customer object, and verifies that the converted CustomerJpa object contains the expected values.
     */
    @Test
    void toDataModel_shouldReturnCustomerJpa() {
        // Arrange
        String id = "1";
        String name = "Joao Manuel";
        String nif = "240606019";

        CustomerJpa customerJpa = new CustomerJpa(id, name, nif);

        CustomerID customerID1 = new CustomerID(id);
        Name name1 = new Name(name);
        Nif nif1 = new Nif(nif);

        Customer customer = new Customer(customerID1, name1, nif1);

        //Act
        CustomerJpa customerJpaResult = CustomerJpaAssembler.toDataModel(customer);

        //Assert
        assertEquals(customerJpa.getCustomerID(), customerJpaResult.getCustomerID());
        assertEquals(customerJpa.getName(), customerJpaResult.getName());
        assertEquals(customerJpa.getNif(), customerJpaResult.getNif());
    }

    /**
     * Test method to ensure that the toDomain() method of CustomerJpaAssembler converts a CustomerJpa object to a Customer object.
     * It mocks the necessary dependencies and verifies that the converted Customer object contains the expected values.
     */
    @Test
    void ensureGetCustomerToDomain() {
        // Arrange
        String id = "1";
        String name = "Joao Manuel";
        String nif = "240606019";

        when(customerJpa.getCustomerID()).thenReturn(id);
        when(customerJpa.getName()).thenReturn(name);
        when(customerJpa.getNif()).thenReturn(nif);

        // Act
        Customer customer = new Customer(
                new CustomerID(customerJpa.getCustomerID()),
                new Name(customerJpa.getName()),
                new Nif(customerJpa.getNif())
        );

        // Assert
        assertEquals(customer.getCustomerID().getIdOfCustomer(), id);
        assertEquals(customer.getName().getValue(), name);
        assertEquals(customer.getNif().getNif(), nif);
    }
}