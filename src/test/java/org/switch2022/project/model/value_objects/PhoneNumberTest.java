package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PhoneNumber ()}.
 * PN = Phone Number
 */

class PhoneNumberTest {


    /**
     * Test class for {@link PhoneNumber ()}.
     * PN = Phone Number
     */

    @Test
    void ensureCreationObjectIsOk() {
        PhoneNumber phoneNumber = new PhoneNumber("262393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("262393043");
        assertEquals(phoneNumber, phoneNumber1);
    }

    @Test
    void ensurePNCannotBeNull() {
        String text=null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(text);
        });
        assertEquals("Phone Number cannot be null", exception.getMessage());
    }

    @Test
    void ensurePNCannotBeEmpty() {
        String text = " ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(text);
        });
        assertEquals("Phone Number cannot be empty", exception.getMessage());
    }

    @Test
    void ensurePNHasOnly9Digits() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
          new PhoneNumber("96239304");
        });
        assertEquals("The phone number should have nine digits", exception.getMessage());
    }

    @Test
    void ensurePNHasOnly9Digits_2() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
             new PhoneNumber("9623930433");
        });
        assertEquals("The phone number should have nine digits", exception.getMessage());
    }

    @Test
    void ensurePNCannotContainLetters() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber("96239304E");
        });
        assertEquals("The phone number can only contain numbers", exception.getMessage());
    }

    @Test
    void ensurePNCannotContainSymbol() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
             new PhoneNumber("96239304$");
        });
        assertEquals("The phone number can only contain numbers", exception.getMessage());
    }

    @Test
    void ensurePNCannotContainSymbol_2() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber("9623=9304");
        });
        assertEquals("The phone number can only contain numbers", exception.getMessage());
    }

    @Test
    void ensurePNCannotHaveBlankBetweenNumbers() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber("962 93043");
        });
        assertEquals("The phone number can only contain numbers", exception.getMessage());
    }

    @Test
    void ensurePNCanOnlyStartWith2or9() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber("162043393");
        });
        assertEquals("The phone number can only start with a 6 or a 9", exception.getMessage());
    }

    @Test
    void ensurePNCanOnlyStartWith2or9_2() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
           new PhoneNumber("562043393");
        });
        assertEquals("The phone number can only start with a 6 or a 9", exception.getMessage());
    }

    /**
     * Unit Test for {@link PhoneNumber#equals(Object)}.
     */

    @Test
    void ensureObjectAreTheSame_Unit() {

        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = phoneNumber;

        assertEquals(phoneNumber, phoneNumber1);

    }

    @Test
    void ensureObjectsAreTheSame_2_Unit() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("962393043");
        assertEquals(phoneNumber, phoneNumber1);
    }

    @Test
    void ensureObjectsAreDifferent_Unit() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("962393044");
        assertNotEquals(phoneNumber, phoneNumber1);
    }

    @Test
    void ensureObjectsAreDifferent_2_Unit() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        Object object = new Object();
        assertNotEquals(phoneNumber, object);
    }

    @Test
    void ensureObjectsAreNotTheSame_Null_Unit() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = null;
        assertFalse(phoneNumber.equals(phoneNumber1));
        assertFalse(phoneNumber.sameAs(phoneNumber1));
    }

    /**
     * Test for {@link PhoneNumber#hashCode()}.
     */

    @Test
    void testHashCode_Success() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("962393043");

        assertEquals(phoneNumber1.hashCode(), phoneNumber.hashCode());
    }

    @Test
    void testHashCode_Fail() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("962393044");

        assertNotEquals(phoneNumber1.hashCode(), phoneNumber.hashCode());
    }

    /**
     * Unit Test for {@link PhoneNumber#getPhoneNumber()}.
     */

    @Test
    void ensureSameObjects() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        String expected = "962393043";
        String res = phoneNumber.getPhoneNumber();
        assertEquals(expected, res);
    }

    @Test
    void ensureDifferentObjects() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        String expected = "962393045";
        String res = phoneNumber.getPhoneNumber();
        assertNotEquals(expected, res);
    }

    /**
     * Test class for {@link PhoneNumber#sameAs(PhoneNumber)}
     */
    @Test
    void sameAs_ensureISTrueIfThePNAreTheSame() {

        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("962393043");
        boolean result = phoneNumber.sameAs(phoneNumber1);
        assertTrue(result);
    }

    @Test
    void sameAs_ensureIsFalseWhenThePNIsNotTheSame() {
        PhoneNumber phoneNumber = new PhoneNumber("962393043");
        PhoneNumber phoneNumber1 = new PhoneNumber("962393042");
        boolean result = phoneNumber.sameAs(phoneNumber1);
        assertFalse(result);
    }

}