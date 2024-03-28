package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Allocation class.
 */
class AllocationTest {

    /**
     * Test case: create_shouldThrowException_whenAllocationIsZero
     *
     * Verifies that creating an Allocation object with a zero value throws an IllegalArgumentException.
     */
    @Test
    void create_shouldThrowException_whenAllocationIsZero() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Allocation(0);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Allocation value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Test case: create_shouldThrowException_whenAllocationIsNegative
     *
     * Verifies that creating an Allocation object with a negative value throws an IllegalArgumentException.
     */
    @Test
    void create_shouldThrowException_whenAllocationIsNegative() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
           new Allocation(-10);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Allocation value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Test case: create_shouldThrowException_invalidAllocation
     *
     * Verifies that creating an Allocation object with a value greater than 100 throws an IllegalArgumentException.
     */
    @Test
    void create_shouldThrowException_invalidAllocation() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
           new Allocation (150);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Allocation value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Test case: equals_shouldReturnTrue_whenSameObject
     *
     * Verifies that the equals method returns true when comparing an Allocation object with itself.
     * Also checks hashCode and getAllocation methods.
     */
    @Test
    void equals_shouldReturnTrue_whenSameObject() {
        //Arrange
        //Act
        Allocation allocation1 = new Allocation(50);
        Allocation allocation2 = allocation1;

        //Assert
        assertEquals(allocation1, allocation2);
        assertEquals(allocation1.hashCode(), allocation2.hashCode());
        assertEquals(allocation1.getAllocation(), allocation2.getAllocation());
    }

    /**
     * Test case: equals_shouldReturnFalse_whenCompareToNullObject
     *
     * Verifies that the equals method returns false when comparing an Allocation object with null.
     */
    @Test
    void equals_shouldReturnFalse_whenCompareToNullObject() {
        //Arrange
        //Act
        Allocation allocation1 =new Allocation(10);
        Allocation allocation2 = null;

        //Assert
        assertNotEquals(allocation1, allocation2);
    }

    /**
     * Test case: equals_shouldReturnFalse_whenCompareToOtherInstanceObject
     *
     * Verifies that the equals method returns false when comparing an Allocation object with an instance of a different class.
     */
    @Test
    void equals_shouldReturnFalse_whenCompareToOtherInstanceObject() {
        //Arrange
        //Act
        Allocation allocation1 = new Allocation(50);
        Object compare = "compare";

        //Assert
        assertNotEquals(allocation1, compare);
    }

    /**
     * Test case: equals_shouldReturnFalse_whenAllocationAreDifferent
     *
     * Verifies that the equals method returns false when comparing two Allocation objects with different allocation values.
     * Also checks hashCode and getAllocation methods.
     */
    @Test
    void equals_shouldReturnFalse_whenAllocationAreDifferent() {
        //Arrange
        //Act
        Allocation allocation1 = new Allocation(20);
        Allocation allocation2 =new  Allocation(50);

        //Assert
        assertNotEquals(allocation1, allocation2);
        assertNotEquals(allocation1.hashCode(), allocation2.hashCode());
        assertNotEquals(allocation1.getAllocation(), allocation2.getAllocation());
    }

    /**
     * Test case: testEquals_ThatIsNull
     *
     * Verifies that the equals method returns false when comparing an Allocation object with null.
     */
    @Test
    void testEquals_ThatIsNull() {
        Allocation allocation1 =new  Allocation(15);
        Allocation allocation2 = null;

        assertNotEquals(allocation1,allocation2);
    }

    /**
     * Test case: testEquals_ThatIsDifferentClass
     *
     * Verifies that the equals method returns false when comparing an Allocation object with an instance of a different class.
     */
    @Test
    void testEquals_ThatIsDifferentClass() {
        Allocation allocation1 = new Allocation(50);
        String allocation2 = "Not an Allocation object";

        assertNotEquals(allocation1,allocation2);
    }

    /**
     * Test case: testEquals_ThatHasDifferentAttributes
     *
     * Verifies that the equals method returns false when comparing two Allocation objects with different allocation values.
     */
    @Test
    void testEquals_ThatHasDifferentAttributes() {
        Allocation allocation1 =new Allocation(10);
        Allocation allocation2 = new Allocation(20);

        assertNotEquals(allocation1,allocation2);
    }

    /**
     * Test case: testEquals_ThatHasSameAttributes
     *
     * Verifies that the equals method returns true when comparing two Allocation objects with the same allocation value.
     */
    @Test
    void testEquals_ThatHasSameAttributes() {
        Allocation allocation1 = new Allocation(10);
        Allocation allocation2 = new Allocation(10);

        assertTrue(allocation1.equals(allocation2));
    }
}