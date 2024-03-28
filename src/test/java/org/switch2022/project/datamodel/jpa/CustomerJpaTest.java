package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.CustomerID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test methods to verify the behavior of the CustomerJpa class.
 */
public class CustomerJpaTest {

    /**
     * Test method to ensure that the constructor of CustomerJpa creates a valid CustomerJpa object.
     * It verifies that the provided values are correctly assigned to the corresponding fields.
     */
    @Test
    void ensureConstructorCreatesValidCustomer() {
        //Arrange
        String id = "User";
        String name = "Name";
        String nif = "240223658";

        //Act
        CustomerJpa customerJpa = new CustomerJpa(id, name, nif);

        //Assert
        assertEquals(id, customerJpa.getCustomerID());
        assertEquals(name, customerJpa.getName());
        assertEquals(nif, customerJpa.getNif());
    }


    //Test getters and setters

    /**
     * Test method to verify the setter and getter for the customerID field in CustomerJpa.
     * It sets the customerID and ensures that the getter returns the same value.
     */
    @Test
    void setAndGetCustomerID() {
        //Arrange
        String id = "User";
        CustomerJpa customerJpa = new CustomerJpa();
        customerJpa.setCustomerID(id);

        //Assert
        assertEquals(id, customerJpa.getCustomerID());
    }

    /**
     * Test method to verify the equals() method of CustomerJpa when comparing the same object.
     * It creates a CustomerJpa object and ensures that it is equal to itself.
     */
    @Test
    void testEqualsSameObject() {
        //Arrange
        String id = "User";
        CustomerID customerID = new CustomerID(id);

        CustomerJpa customerJpa = new CustomerJpa();
        customerJpa.setCustomerID(String.valueOf(customerID));

        //Act + Assert
        assertEquals(customerJpa, customerJpa);
    }

    /**
     * Test method to verify that a CustomerJpa object is not equal to null.
     * It creates a CustomerJpa object and ensures that it is not equal to null.
     */
    @Test
    void oneCustomerJpaIsNotNull() {
        //Arrange
        String id = "User";
        CustomerID customerID = new CustomerID(id);

        CustomerJpa customerJpa = new CustomerJpa();
        customerJpa.setCustomerID(String.valueOf(customerID));

        //Act + Assert
        assertNotEquals(null, customerJpa);
    }

    /**
     * Test method to verify that a CustomerJpa object is not equal to another class.
     * It creates a CustomerJpa object and another unrelated object, and ensures that they are not equal.
     */
    @Test
    void oneCustomerJpaIsNotAnotherClass() {
        //Arrange
        String id = "User";
        CustomerID customerID = new CustomerID(id);

        CustomerJpa customerJpa = new CustomerJpa();
        customerJpa.setCustomerID(String.valueOf(customerID));

        ProjectJPA projectJPA = new ProjectJPA();

        //Act + Assert
        assertNotEquals(customerJpa, projectJPA);
    }
    @Test
    void setCustomerIDTest() {
        //Arrange
        CustomerJpa customer = new CustomerJpa();
        customer.setCustomerID("123456");
        //Act + Assert
        Assertions.assertEquals("123456", customer.getCustomerID());
    }

    @Test
    void setNameTest() {
        //Arrange
        CustomerJpa customer = new CustomerJpa();
        customer.setName("John Doe");
        //Act + Assert
        Assertions.assertEquals("John Doe", customer.getName());
    }

    @Test
    void setNifTest() {
        //Arrange
        CustomerJpa customer = new CustomerJpa();
        customer.setNif("123456789");
        //Act + Assert
        Assertions.assertEquals("123456789", customer.getNif());
    }
}