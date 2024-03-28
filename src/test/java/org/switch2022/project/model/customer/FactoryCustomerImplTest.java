package org.switch2022.project.model.customer;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class FactoryCustomerImplTest {

    /**
     * Test class for {@link FactoryCustomerImpl#createCustomer(CustomerID, Name, Nif)}.
     */
    @Test
    void ensureCreatesCustomer_Success() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        Name name = mock(Name.class);
        Nif nif = mock(Nif.class);

        Customer customer = new Customer(customerID, name, nif);
        FactoryCustomer factoryCustomer = new FactoryCustomerImpl();

        Customer expected = customer;

        //Act
        Customer result = factoryCustomer.createCustomer(customerID, name, nif);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureCreatesOtherCustomer_Fail() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerID customerID2 = mock(CustomerID.class);
        Name name = mock(Name.class);
        Nif nif = mock(Nif.class);
        Nif nif2 = mock(Nif.class);

        Customer customer = new Customer(customerID, name, nif);
        FactoryCustomer factoryCustomer = new FactoryCustomerImpl();

        Customer expected = customer;

        //Act
        Customer result = factoryCustomer.createCustomer(customerID2, name, nif2);

        //Assert
        assertNotEquals(expected, result);
    }
}