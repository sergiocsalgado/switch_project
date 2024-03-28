package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link BusinessSectorID}.
 */
class BusinessSectorIDTest {

    /**
     * Test class method checks that the constructor of the BusinessSectorID class throws an IllegalArgumentException
     * when passed a null value for the code parameter. It uses the assertThrows method to ensure that the
     * expected exception is thrown, and then asserts that the exception message is as expected.
     */
    @Test
    void constructs_shouldThrowAnIllegalArgumentExceptionWhenPassedANullValueCode() {
        String stringToCheck = null;
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new BusinessSectorID(stringToCheck);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Business Sector ID cannot be null", exception.getMessage());
    }

    @Test
    void constructs_shouldThrowAnIllegalArgumentExceptionWhenPassedAnEmptyValueCode() {
        String stringToCheck = "";
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

                new BusinessSectorID(stringToCheck);

        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Business Sector ID cannot be empty", exception.getMessage());
    }

    /**
     * Test class method checks that the constructor of the BusinessSectorID class does not throw
     * an IllegalArgumentException when passed a not null code as parameter.
     */
    @Test
    void constructs_ShouldCreateAValidBusinessSectorID() {
        //Arrange
        //Act
        try {
            new BusinessSectorID("valid-code");
        } catch (Exception e) {
            // If an exception was thrown, fail the test
            fail(e.getMessage());
        }

        //Assert
        assertTrue(true);
    }

    /**
     * Test class method for {@link BusinessSectorID#toString()}.
     */
    @Test
    void testToString_shouldReturnTheValueAndCurrencyOfObjectCost_Success() {
        //Arrange
        BusinessSectorID id = new BusinessSectorID("valid-code");
        String expected = "BusinessSectorID{id='valid-code'}";

        //Act
        String result = id.toString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class method for {@link BusinessSectorID#sameAs(Object)} .
     */
    @Test
    void sameAs_ensureNotTheSame_withNullComparable() {
        //Arrange
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");

        //Act
        boolean result = id_1.sameAs(null);

        //Assert
        assertFalse(result);
    }
    @Test
    void sameAs_ensureIsTheSameBusinessSectorID_whenIDIsEqual() {
        //Arrange
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");
        BusinessSectorID id_2 = new BusinessSectorID("valid-code");

        //Act
        boolean result = id_1.sameAs(id_2);

        //Assert
        assertTrue(result);
    }

    /**
     * Test class method for {@link BusinessSectorID#equals(Object)}, {@link BusinessSectorID#hashCode()}
     * and {@link BusinessSectorID#getId()}.
     */
    @Test
    void equals_shouldReturnTrue_whenArgumentsAreEqual() {
        //Arrange
        //Act
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");
        BusinessSectorID id_2 = new BusinessSectorID("valid-code");

        //Assert
        assertEquals(id_1, id_2);
        assertEquals(id_1.hashCode(), id_2.hashCode());
        assertEquals(id_1.getId(), id_2.getId());
    }
    @Test
    void equals_shouldReturnTrue_whenSameObject() {
        //Arrange
        //Act
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");
        BusinessSectorID id_2 = id_1;

        //Assert
        assertEquals(id_1, id_2);
        assertEquals(id_1.hashCode(), id_2.hashCode());
        assertEquals(id_1.getId(), id_2.getId());
    }
    @Test
    void equals_shouldReturnFalse_whenCompareToNullObject() {
        //Arrange
        //Act
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");

        //Assert
        assertNotEquals(null, id_1);
    }
    @Test
    void equals_shouldReturnFalse_whenCompareToOtherInstanceObject() {
        //Arrange
        //Act
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");
        Object compare = "compare";

        //Assert
        assertNotEquals(id_1, compare);
    }
    @Test
    void equals_shouldReturnFalse_whenCodeIsDifferent() {
        //Arrange
        //Act
        BusinessSectorID id_1 = new BusinessSectorID("valid-code");
        BusinessSectorID id_2 = new BusinessSectorID("code");

        //Assert
        assertNotEquals(id_1, id_2);
        assertNotEquals(id_1.hashCode(), id_2.hashCode());
        assertNotEquals(id_1.getId(), id_2.getId());
    }
    @Test
    void ensureObjectsAreNotEquals_objectNull(){
        BusinessSectorID businessSectorID = new BusinessSectorID("code");
        BusinessSectorID businessSectorID1 = null;

        boolean expected = false;

        boolean result = businessSectorID.equals(businessSectorID1);

        assertEquals(expected,result);
    }
}