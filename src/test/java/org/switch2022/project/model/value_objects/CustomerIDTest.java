package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIDTest {

    @Test
    void throwExceptionWhenCustomerIDNull_Success() {
        String stringToCheck =null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerID(stringToCheck);
        });
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }

    @Test
    void throwExceptionWhenCustomerIDEmpty_Success() {
        String stringToCheck = " ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerID(stringToCheck);

        });
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    void throwExceptionWhenCustomerIDHigherThan20Characters_Success() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            String id = "CustomerIds1234567890";
            new CustomerID(id);
        });
        assertEquals("Customer ID cannot be more than 20 characters", exception.getMessage());
    }

    @Test
    void ensureGetCustomerID() {
        CustomerID customerID = new CustomerID("ctm1");

        String expected = "ctm1";
        String result = customerID.getIdOfCustomer();

        assertEquals(expected, result);
    }

    @Test
    void ensureObjectsAreEqual() {
        CustomerID customerID = new CustomerID("ctm1");
        CustomerID customerID1 = customerID;

        assertEquals(customerID, customerID1);
    }

    @Test
    void ensureObjectsHaveSameContent() {
        CustomerID customerID = new CustomerID("ctm1");
        CustomerID customerID1 = new CustomerID("ctm1");

        assertEquals(customerID, customerID1);
    }

    @Test
    void ensureObjectsAreNotEqual() {
        CustomerID customerID = new CustomerID("ctm1");
        CustomerID customerID1 = new CustomerID("ctm2");

        assertNotEquals(customerID, customerID1);
    }

    @Test
    void ensureObjectsAreFromDifferentClasses() {
        CustomerID customerID = new CustomerID("ctm1");
        // V.O. Profile Class
        ProfileID profileIDNumber = new ProfileID("1");
        Description description = new Description("administrator");

        Profile profile = new Profile(profileIDNumber, description);

        assertNotEquals(customerID, profile);
    }

    @Test
    void ensureObjectsAreNotEqual_Null() {
        CustomerID customerID = new CustomerID("ctm1");
        CustomerID customerID1 = null;

        assertFalse(customerID.sameAs(customerID1));
        assertNotEquals(customerID, customerID1);
    }

    @Test
    void sameHashCodeSuccess() {
        CustomerID customerID = new CustomerID("ctm1");
        CustomerID customerID1 = customerID;

        assertEquals(customerID, customerID1);
        assertEquals(customerID.hashCode(), customerID1.hashCode());
    }

    @Test
    void sameHashCodeUnsuccessful() {
        CustomerID customerID = new CustomerID("ctm1");
        CustomerID customerID1 = new CustomerID("ctm2");

        assertNotEquals(customerID, customerID1);
        assertNotEquals(customerID.hashCode(), customerID1.hashCode());
    }
}
