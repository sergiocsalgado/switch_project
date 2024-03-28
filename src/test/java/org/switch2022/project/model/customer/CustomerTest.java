package org.switch2022.project.model.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class CustomerTest {
    @Mock
    CustomerID customerID;
    @Mock
    Name name;
    @Mock
    Nif nif;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test class for {@link Customer#Customer(CustomerID, Name, Nif)}  Customer} .
     */
    @Test
    void ensureCustomerIDCanNotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null, name, nif);
        });
        assertEquals("CustomerID can not be null", exception.getMessage());
    }

    /**
     * Test class for {@link Customer#Customer(CustomerID, Name, Nif)}  Customer} .
     */
    @Test
    void ensureCustomerNameCanNotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(customerID, null, nif);
        });
        assertEquals("Name can not be null", exception.getMessage());
    }

    /**
     * Test class for {@link Customer#Customer(CustomerID, Name, Nif)}  Customer} .
     */
    @Test
    void ensureCustomerNifCanNotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(customerID, name, null);
        });
        assertEquals("Nif can not be null", exception.getMessage());
    }

    /**
     * Test class for {@link Customer#verifyName(Name)}
     */
    @Test
    void ensureNamesAreEqual() {
        //ARRANGE
        Customer customer = new Customer(customerID, name, nif);
        boolean expected = true;
        //ACT
        boolean result = customer.verifyName(name);
        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureNamesAreDifferent() {
        //ARRANGE
        Customer customer = new Customer(customerID, name, nif);
        boolean expected = false;
        Name name2 = mock(Name.class);
        //ACT
        boolean result = customer.verifyName(name2);
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Customer#copy()}.
     */
    @Test
    void ensureGetCustomerCopy() {
        //Arrange
        Customer customer = new Customer(customerID, name, nif);
        Customer expected = customer;
        //Act
        Customer result = customer.copy();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Customer#getName()}.
     */
    @Test
    void ensureGetCustomerName() {
        //Arrange
        Customer customer = new Customer(customerID, name, nif);
        Name expected = name;
        //Act
        Name result = customer.getName();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Customer#getCustomerID()}
     */
    @Test
    void ensureGetCustomerID() {
        //ARRANGE
        Customer customer = new Customer(customerID, name, nif);
        CustomerID expected = customerID;
        //ACT
        CustomerID result = customer.getCustomerID();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Customer#getNif()}
     */
    @Test
    void ensureGetNif() {
        //ARRANGE
        Customer customer = new Customer(customerID, name, nif);
        Nif expected = nif;
        //ACT
        Nif result = customer.getNif();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Customer#equals(Object)}.
     */
    @Test
    void ensureObjectsAreEqual() {
        Customer customer1 = new Customer(customerID, name, nif);
        Customer customer2 = customer1;
        assertEquals(customer1, customer2);
    }

    /**
     * Test class for {@link Customer#equals(Object)}.
     * Test class for {@link Customer#sameIDAs(Object)}.
     */
    @Test
    void ensureObjectsClassAreNotEqual() {
        //Arrange
        Customer customer = new Customer(customerID, name, nif);
        Object o = new Object();
        //Act
        boolean isEquals = customer.equals(o);
        //Assert
        assertFalse(isEquals);
        assertFalse(customer.sameIDAs(o));
    }

    /**
     * Test class for {@link Customer#equals(Object)}.
     * Test class for {@link Customer#sameIDAs(Object)}.
     */
    @Test
    void ensureObjectsAreNotEqual() {
        //Arrange
        Customer customer = new Customer(customerID, name, nif);
        CustomerID customerID1 = mock(CustomerID.class);
        Name name1 = mock(Name.class);
        Nif nif1 = mock(Nif.class);
        Customer customer1 = new Customer(customerID1, name1, nif1);
        //Act
        boolean isEquals = customer.equals(customer1);
        //Assert
        assertFalse(isEquals);
        assertFalse(customer.sameIDAs(customer1));
    }

    /**
     * Test class for {@link Customer#equals(Object)}.
     */
    @Test
    void ensureObjectsAreNotEqual_objectNull() {
        Customer customer = new Customer(customerID, name, nif);
        Customer customer1 = null;
        assertNotEquals(customer, customer1);
    }

    /**
     * Test class for {@link Customer#hashCode()}.
     */
    @Test
    void ensureHashCodeAreEqual() {
        Customer customer = new Customer(customerID, name, nif);
        Customer customer1 = new Customer(customerID, name, nif);
        assertEquals(customer.hashCode(), customer1.hashCode());
    }

    @Test
    void ensureHashCodeAreDifferent() {
        Customer customer = new Customer(customerID, name, nif);
        CustomerID customerID2 = mock(CustomerID.class);
        Name name2 = mock(Name.class);
        Nif nif2 = mock(Nif.class);
        Customer customer1 = new Customer(customerID2, name2, nif2);
        assertNotEquals(customer.hashCode(), customer1.hashCode());
    }

    /**
     * Test class for {@link Customer#identity()}
     */
    @Test
    void ensureCustomerIdentity() {
        //ARRANGE
        Customer customer = new Customer(customerID, name, nif);
        CustomerID expectedIdentity = customerID;
        //ACT
        CustomerID resultIdentity = customer.identity();
        //ASSERT
        assertEquals(expectedIdentity, resultIdentity);
    }

    @Test
    void ensureSameIdAsOtherObject_differentCustomerID() {
        //Arrange
        Customer customer1 = new Customer(customerID, name, nif);
        CustomerID customerID2 = mock(CustomerID.class);
        Name name2 = mock(Name.class);
        Customer customer2 = new Customer(customerID2, name2, nif);

        boolean expected = false;

        //Act
        boolean result = customer1.sameIDAs(customer2);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSameIdAsOtherObject_differentNif() {
        //Arrange
        Customer customer1 = new Customer(customerID, name, nif);
        Name name2 = mock(Name.class);
        Nif nif2 = mock(Nif.class);
        Customer customer2 = new Customer(customerID, name2, nif2);

        boolean expected = false;

        //Act
        boolean result = customer1.sameIDAs(customer2);

        //Assert
        assertEquals(expected, result);
    }
}