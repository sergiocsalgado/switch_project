package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {

    @Test
    void ensureCustomerNifCanNotBeEmpty() {
        String text = " ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Nif(text);

        });
        assertEquals("NIF cannot be empty", exception.getMessage());
    }

    @Test
    void CustomerNifIsCannotBeNull() {
        String text = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Nif(text);
        });
        assertEquals("NIF cannot be null", exception.getMessage());
    }


    @Test
    void ensureCustomerNifIsValid_Unsuccessful() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(new CustomerID("cstm1"), new Name("André"), new Nif("11111111"));
        });
        assertEquals("Nif is not valid!", exception.getMessage());
    }

    @Test
    void ensureCustomerNifIsValid_Unsuccessful_BiggerThan9Digits() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(new CustomerID("cstm1"), new Name("André"), new Nif("2406060199"));
        });
        assertEquals("Nif is not valid!", exception.getMessage());
    }

    @Test
    void ensureCustomerNifIsValid_Unsuccessful_HaveLetters() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(new CustomerID("cstm1"), new Name("André"), new Nif("24edf4521"));
        });
        assertEquals("Nif is not valid!", exception.getMessage());
    }

    @Test
    void ensureCustomerNifIsNotValid_Unsuccessful_HaveMoreThanNineDigits() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(new CustomerID("cstm1"), new Name("André"), new Nif("242510000000000"));
        });
        assertEquals("Nif is not valid!", exception.getMessage());
    }

    @Test
    void getNif() {
        Nif nif = new Nif("240606019");
        String expected = "240606019";
        String result = nif.getNif();

        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Nif#equals(Object)}.
     */
    @Test
    void ensureObjectsAreEqual() {
        Nif nif = new Nif("240606019");
        Nif nif1 = nif;

        assertEquals(nif, nif1);
    }

    @Test
    void ensureObjectsHaveSameContent() {
        Nif nif = new Nif("240606019");
        Nif nif1 = new Nif("240606019");

        assertEquals(nif, nif1);
        assertTrue(nif.sameAs(nif1));
    }

    @Test
    void ensureObjectsClassAreNotEqual() {
        //Arrange
        Nif nif = new Nif("240606019");

        Object o = new Object();

        //Act
        boolean isEquals = nif.equals(o);

        //Assert
        assertFalse(isEquals);
        assertFalse(nif.sameAs(o));
    }

    /**
     * Test class for different objects of the same class
     */
    @Test
    void ensureObjectsAreNotEqual() {
        //Arrange
        Nif nif = new Nif("240606019");

        Nif nif1 = new Nif("212595024");

        //Act
        boolean isEquals = nif.equals(nif1);

        //Assert
        assertFalse(isEquals);
        assertFalse(nif.sameAs(nif1));
    }

    /**
     * Test class for null object.
     */
    @Test
    void ensureObjectsAreNotEqual_objectNull() {
        Nif nif = new Nif("240606019");

        Nif nif1 = null;

        assertNotEquals(nif, nif1);
        assertFalse(nif.sameAs(nif1));
    }

    /**
     * Test class for {@link Nif#hashCode()}.
     */
    @Test
    void ensureHashCodeAreEqual() {
        Nif nif = new Nif("240606019");

        Nif nif1 = new Nif("240606019");


        assertEquals(nif.hashCode(), nif1.hashCode());
    }

    @Test
    void ensureHashCodeAreNotEqual() {
        Nif nif = new Nif("240606019");

        Nif nif1 = new Nif("212595024");

        assertNotEquals(nif.hashCode(), nif1.hashCode());
    }
}
