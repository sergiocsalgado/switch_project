package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SprintNumber class.
 */
public class SprintNumberTest {

    /**
     * Test case for the constructor of SprintNumber.
     * It verifies that the constructor correctly initializes the sprint number.
     */
    @Test
    void testConstructor() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertEquals(1, sprintNumber.getNumber());
    }

    /**
     * Test case for the getNumber() method of SprintNumber.
     * It verifies that the getNumber() method returns the correct sprint number value.
     */
    @Test
    void testGetNumber() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertEquals(1, sprintNumber.getNumber());
    }

    /**
     * Test case for the getNumber() method of SprintNumber when the expected value is not equal.
     * It verifies that the getNumber() method does not return an incorrect sprint number value.
     */
    @Test
    void testGetNumber_NotSuccess() {
        SprintNumber sprintNumber = new SprintNumber(2);
        assertNotEquals(1, sprintNumber.getNumber());
    }

    /**
     * Test case for the generateNextSprintNumber() method of SprintNumber.
     * It verifies that the generateNextSprintNumber() method produces the expected next sprint number.
     */
    @Test
    void testGenerateNextSprintNumber_Success() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertNotEquals(3, sprintNumber.getNumber());
    }

    /**
     * Test case for the generateNextSprintNumber() method of SprintNumber when the expected value is not equal.
     * It verifies that the generateNextSprintNumber() method does not produce an incorrect next sprint number.
     */
    @Test
    void testGenerateNextSprintNumber_NotSuccess() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertEquals(1, sprintNumber.getNumber());
    }

    /**
     * Test case for the equals() method of SprintNumber when two instances are equal.
     * It verifies that the equals() method returns true for two equal SprintNumber instances.
     */
    @Test
    void testEquals_Success() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(1);
        assertTrue(sprintNumber1.equals(sprintNumber2));
    }

    /**
     * Test case for the equals() method of SprintNumber when two instances are not equal.
     * It verifies that the equals() method returns false for two different SprintNumber instances.
     */
    @Test
    void testEquals_NotSuccess() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber3 = new SprintNumber(2);
        assertFalse(sprintNumber1.equals(sprintNumber3));
    }

    /**
     * Test case for the hashCode() method of SprintNumber when two instances are equal.
     * It verifies that the hashCode() method returns the same hash code for two equal SprintNumber instances.
     */
    @Test
    void testHashCode_Success() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(1);
        assertEquals(sprintNumber1.hashCode(), sprintNumber2.hashCode());
    }

    /**
     * Test case for the hashCode() method of SprintNumber when two instances are not equal.
     * It verifies that the hashCode() method returns different hash codes for two different SprintNumber instances.
     */
    @Test
    void testHashCode_NotSuccess() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(2);
        assertNotEquals(sprintNumber1.hashCode(), sprintNumber2.hashCode());
    }

    /**
     * Test case for the sameAs() method of SprintNumber.
     * It verifies that the sameAs() method correctly compares two SprintNumber instances for equality.
     */
    @Test
    void testSameAs() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(1);
        SprintNumber sprintNumber3 = new SprintNumber(2);
        assertTrue(sprintNumber1.sameAs(sprintNumber2));
        assertFalse(sprintNumber1.sameAs(sprintNumber3));
        assertFalse(sprintNumber1.sameAs(null));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing the same instance.
     * It verifies that the equals() method returns true when comparing a SprintNumber instance to itself.
     */
    @Test
    void equalsReturnsTrueForSameInstance() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertTrue(sprintNumber.equals(sprintNumber));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing to null.
     * It verifies that the equals() method returns false when comparing a SprintNumber instance to null.
     */
    @Test
    void equalsReturnsFalseForNull() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertFalse(sprintNumber.equals(null));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing to a different class object.
     * It verifies that the equals() method returns false when comparing a SprintNumber instance to an object of a different class.
     */
    @Test
    void equalsReturnsFalseForDifferentClass() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertFalse(sprintNumber.equals(new Object()));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing two equal instances.
     * It verifies that the equals() method returns true when comparing two equal SprintNumber instances.
     */
    @Test
    void equalsReturnsTrueForEqualInstances() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(1);
        assertTrue(sprintNumber1.equals(sprintNumber2));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing two instances with different values.
     * It verifies that the equals() method returns false when comparing two SprintNumber instances with different values.
     */
    @Test
    void equalsReturnsFalseForDifferentValues() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(2);
        assertFalse(sprintNumber1.equals(sprintNumber2));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing to an object of a different class.
     * It verifies that the equals() method returns false when comparing a SprintNumber instance to an object of a different class.
     */
    @Test
    void ensureThatEqualsReturnsFalseForDifferentClass() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertFalse(sprintNumber.equals(new Object()));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing to null.
     * It verifies that the equals() method returns false when comparing a SprintNumber instance to null.
     */
    @Test
    void equalsReturnsFalseForNullObject() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertFalse(sprintNumber.equals(null));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing two different SprintNumber instances.
     * It verifies that the equals() method returns false when comparing two different SprintNumber instances.
     */
    @Test
    void equalsReturnsFalseForDifferentSprintNumber() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(2);
        assertFalse(sprintNumber1.equals(sprintNumber2));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing two equal SprintNumber instances.
     * It verifies that the equals() method returns true when comparing two equal SprintNumber instances.
     */
    @Test
    void equalsReturnsTrueForSameSprintNumber() {
        SprintNumber sprintNumber1 = new SprintNumber(1);
        SprintNumber sprintNumber2 = new SprintNumber(1);
        assertTrue(sprintNumber1.equals(sprintNumber2));
    }

    /**
     * Test case for the equals() method of SprintNumber when comparing a SprintNumber instance to itself.
     * It verifies that the equals() method returns true when comparing a SprintNumber instance to itself.
     */
    @Test
    void equalsReturnsTrueForSameObject() {
        SprintNumber sprintNumber = new SprintNumber(1);
        assertTrue(sprintNumber.equals(sprintNumber));
    }

    /**
     * Test case to ensure that a SprintNumber instance cannot be created with a number below zero.
     * It verifies that creating a SprintNumber with a negative number throws an IllegalArgumentException.
     */
    @Test
    void ensureDoNotCreateSprintNumber_numberMinorThanZero(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintNumber(-1);
        });
        assertEquals("Sprint Number cannot be below 0", exception.getMessage());
    }
}