package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Cost}.
 */
class CostTest {

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method throws an IllegalArgumentException.
     * with the message "invalid cost value" when passed a value that is 0.
     */
    @Test
    void create_shouldThrowException_whenValueIsZero() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cost(0, "USD");
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("invalid cost value", exception.getMessage());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method throws an IllegalArgumentException.
     * with the message "invalid cost value" when passed a value that is negative.
     */
    @Test
    void create_shouldThrowException_whenValueIsNegative() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cost(-10, "USD");
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("invalid cost value", exception.getMessage());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method throws an IllegalArgumentException.
     * with the message "invalid currency" when passed a currency that is blank.
     */
    @Test
    void create_shouldThrowException_whenCurrencyIsBlank() {
        //Arrange
        String stringToCheck = "";
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cost(23,stringToCheck);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("currency cannot be empty", exception.getMessage());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method throws an IllegalArgumentException.
     * with the message "invalid currency" when passed a currency that is empty.
     */
    @Test
    void create_shouldThrowException_whenCurrencyIsEmpty() {
        //Arrange
        String stringToCheck = " ";
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cost(23,stringToCheck);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("currency cannot be empty", exception.getMessage());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method throws an NullPointerException.
     * when passed a currency that is null.
     */
    @Test
    void create_shouldThrowException_whenCurrencyIsNull() {
        //Arrange
        String stringToCheck = null;
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cost(23,stringToCheck);
        });

        //Assert
        assertEquals( IllegalArgumentException.class, exception.getClass());
        assertEquals("currency cannot be null", exception.getMessage());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method throws an IllegalArgumentException.
     * with the message "invalid currency" when passed a currency that is not "EUR" or "USD".
     */
    @Test
    void create_shouldThrowException_whenCurrencyIsNotValid() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cost(10, "GBP");
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("invalid currency", exception.getMessage());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method returns an instance of Cost.
     * when passed a value 10 and a currency that is "EUR".
     */
    @Test
    void create_shouldReturnCostInstance_whenEURCurrencyIsPassed() {
        //Arrange
        //Act
        Cost cost = new Cost(10, "EUR");

        //Assert
        assertNotNull(cost);
        assertEquals(10, cost.getValue());
        assertEquals("EUR", cost.getCurrency());
    }

    /**
     * Test class method for {@link Cost#(double, String)}.
     * This test checks if the method returns an instance of Cost.
     * when passed a value 10 and a currency that is "USD".
     */
    @Test
    void create_shouldReturnCostInstance_whenUSDCurrencyIsPassed() {
        //Arrange
        //Act
        Cost cost = new Cost(10, "USD");

        //Assert
        assertNotNull(cost);
        assertEquals(10, cost.getValue());
        assertEquals("USD", cost.getCurrency());
    }

    /**
     * Test class method for {@link Cost#equals(Object)} and {@link Cost#hashCode()}}.
     * This test checks if the method returns true.
     * when the value and currency of two instances of Cost are equal.
     */
    @Test
    void equals_shouldReturnTrue_whenArgumentsAreEqual() {
        //Arrange
        //Act
        Cost cost1 = new Cost(10, "EUR");
        Cost cost2 = new Cost(10, "EUR");

        //Assert
        assertEquals(cost1, cost2);
        assertEquals(cost1.hashCode(), cost2.hashCode());
        assertEquals(cost1.getValue(), cost2.getValue());
        assertEquals(cost1.getCurrency(), cost2.getCurrency());
    }

    /**
     * Test class method for {@link Cost#equals(Object)} and {@link Cost#hashCode()}}.
     * This test checks if the method returns true.
     * when the second object points to the first memory reference.
     */
    @Test
    void equals_shouldReturnTrue_whenSameObject() {
        //Arrange
        //Act
        Cost cost1 = new Cost(10, "EUR");
        Cost cost2 = cost1;

        //Assert
        assertEquals(cost1, cost2);
        assertEquals(cost1.hashCode(), cost2.hashCode());
        assertEquals(cost1.getValue(), cost2.getValue());
        assertEquals(cost1.getCurrency(), cost2.getCurrency());
    }

    /**
     * Test class method for {@link Cost#equals(Object)}.
     * This test checks if the method returns false.
     * when the first object is a regular instance of cost the second is null.
     */
    @Test
    void equals_shouldReturnFalse_whenCompareToNullObject() {
        //Arrange
        //Act
        Cost cost1 = new Cost(10, "EUR");
        Cost cost2 = null;

        //Assert
        assertNotEquals(cost1,cost2);
    }

    /**
     * Test class method for {@link Cost#equals(Object)}.
     * This test checks if the method returns false.
     * when the first object is an instance of cost the second is not.
     */
    @Test
    void equals_shouldReturnFalse_whenCompareToOtherInstanceObject() {
        //Arrange
        //Act
        Cost cost1 = new Cost(10, "EUR");
        Object compare = "compare";

        //Assert
        assertNotEquals(cost1, compare);
    }

    /**
     * Test class method for {@link Cost#equals(Object)} and {@link Cost#hashCode()}}.
     * This test checks if the method returns false.
     * when the two instances of Cost has different attribute values.
     */
    @Test
    void equals_shouldReturnFalse_whenArgumentsAreDifferent() {
        //Arrange
        //Act
        Cost cost1 = new Cost(11, "EUR");
        Cost cost2 = new Cost(22, "USD");

        //Assert
        assertNotEquals(cost1, cost2);
        assertNotEquals(cost1.hashCode(), cost2.hashCode());
        assertNotEquals(cost1.getValue(), cost2.getValue());
        assertNotEquals(cost1.getCurrency(), cost2.getCurrency());
    }

    /**
     * Test class method for {@link Cost#equals(Object)} and {@link Cost#hashCode()}}.
     * This test checks if the method returns false.
     * when the value of the two objects are different.
     */
    @Test
    void equals_shouldReturnFalse_whenValueIsDifferent() {
        //Arrange
        //Act
        Cost cost1 = new Cost(11, "EUR");
        Cost cost2 = new Cost(22, "EUR");

        //Assert
        assertNotEquals(cost1, cost2);
        assertNotEquals(cost1.hashCode(), cost2.hashCode());
        assertNotEquals(cost1.getValue(), cost2.getValue());
    }

    /**
     * Test class method for {@link Cost#equals(Object)} and {@link Cost#hashCode()}}.
     * This test checks if the method returns false.
     * when the currency of the two objects are different
     */
    @Test
    void equals_shouldReturnFalse_whenCurrencyIsDifferent() {
        //Arrange
        //Act
        Cost cost1 = new Cost(10, "EUR");
        Cost cost2 = new Cost(10, "USD");

        //Assert
        assertNotEquals(cost1, cost2);
        assertNotEquals(cost1.hashCode(), cost2.hashCode());
        assertNotEquals(cost1.getCurrency(), cost2.getCurrency());
    }

    /**
     * Test class method for {@link Cost#toString()}.
     * This test checks if the method the String as defined(Cost{value=10.0, currency='EUR'}).
     * when the value passed for the Cost object is 10 and the currency is EUR.
     */
    @Test
    void testToString_shouldReturnTheValueAndCurrencyOfObjectCost_Success() {
        //Arrange
        Cost cost1 = new Cost(10, "EUR");
        String expected = "10.0EUR";

        //Act
        String result = cost1.toString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class method for {@link Cost#sameAs(Cost)}.
     */
    @Test
    void sameAs_ensureNotTheSameWithNullComparable() {
        //Arrange
        Cost cost1 = new Cost(10, "EUR");
        Cost comparable = null;

        //Act
        boolean result = cost1.sameAs(comparable);

        //Assert
        assertFalse(result);
    }
}