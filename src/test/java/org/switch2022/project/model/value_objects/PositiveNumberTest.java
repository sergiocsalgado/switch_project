package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveNumberTest {

    @Test
    void throwExceptionWhenNumberisLessThanZero_Success() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new PositiveNumber(-3);
        });
        assertEquals("it cannot be lower than zero", exception.getMessage());
    }

    @Test
    void getNumber_Success() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        int expected = 5;
        int result = positiveNumber.getNumber();
        assertEquals(expected, result);
    }

    @Test
    void getNumber0_Success() {
        PositiveNumber positiveNumber = new PositiveNumber(0);
        int expected = 0;
        int result = positiveNumber.getNumber();
        assertEquals(expected, result);
    }

    @Test
    void testEquals_SameObject_Success() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        PositiveNumber positiveNumber1 = positiveNumber;
        assertEquals(positiveNumber1, positiveNumber);
        assertEquals(positiveNumber1.getNumber(), positiveNumber.getNumber());
    }

    @Test
    void testEquals_SameObject() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        PositiveNumber positiveNumber1 = positiveNumber;
        positiveNumber1.getNumber();
        assertEquals(positiveNumber1, positiveNumber);
        assertTrue(positiveNumber1.equals(positiveNumber));
    }

    @Test
    void testEquals_SameObjectDifferentArguments() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        PositiveNumber positiveNumber1 = new PositiveNumber(2);
        assertNotEquals(positiveNumber1, positiveNumber);
        assertNotEquals(positiveNumber1.getNumber(), positiveNumber.getNumber());
    }

    @Test
    void testEquals_DifferntObjects() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        Object differentObject = "compare";
        assertNotEquals(positiveNumber, differentObject);
    }

    @Test
    void testEquals_Null() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        PositiveNumber positiveNumber1 = null;
        assertNotEquals(positiveNumber, positiveNumber1);
    }

    @Test
    void testHashCode() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        PositiveNumber positiveNumber1 = positiveNumber;
        assertEquals(positiveNumber.hashCode(), positiveNumber1.hashCode());
    }

    @Test
    void sameAs_Success() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        boolean expected = true;
        boolean result = positiveNumber.sameAs(positiveNumber);
        assertEquals(expected, result);
    }

    @Test
    void sameAs_Fail() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        PositiveNumber positiveNumber2 = new PositiveNumber(10);
        positiveNumber.getNumber();
        boolean expected = false;
        boolean result = positiveNumber.sameAs(positiveNumber2);
        assertEquals(expected, result);
    }

    @Test
    void sameAs_Null() {
        PositiveNumber positiveNumber = new PositiveNumber(5);
        ;
        PositiveNumber positiveNumber1 = null;
        assertFalse(positiveNumber.sameAs(positiveNumber1));
    }

}