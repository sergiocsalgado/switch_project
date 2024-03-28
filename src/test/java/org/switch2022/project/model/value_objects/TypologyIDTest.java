package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TypologyID class.
 */
class TypologyIDTest {

    /**
     * Test that an IllegalArgumentException is thrown when the code is null.
     */
    @Test
    void throwsExceptionWhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new TypologyID(null));
    }

    /**
     * Test that getIdOfTypology() returns the correct value.
     */
    @Test
    void getIdShouldReturnCorrectValue() {
        String code = "123";
        TypologyID typologyID = new TypologyID(code);
        assertEquals(code, typologyID.getIdOfTypology());
    }

    /**
     * Test that toString() returns the correct value.
     */
    @Test
    void toStringShouldReturnCorrectValue() {
        String code = "123";
        TypologyID typologyID = new TypologyID(code);
        assertEquals("TypologyID{id='123'}", typologyID.toString());
    }

    /**
     * Test that sameAs() returns true for equal TypologyIDs.
     */
    @Test
    void sameAsShouldReturnTrueForEqualTypologyIDs() {
        String code = "123";
        TypologyID typologyID1 = new TypologyID(code);
        TypologyID typologyID2 = new TypologyID(code);
        assertTrue(typologyID1.sameAs(typologyID2));
    }

    /**
     * Test that sameAs() returns false for different TypologyIDs.
     */
    @Test
    void sameAsShouldReturnFalseForDifferentTypologyIDs() {
        String code1 = "123";
        String code2 = "456";
        TypologyID typologyID1 = new TypologyID(code1);
        TypologyID typologyID2 = new TypologyID(code2);
        assertFalse(typologyID1.sameAs(typologyID2));
    }

    /**
     * Test that equals() returns true for equal TypologyIDs.
     */
    @Test
    void equalsShouldReturnTrueForEqualTypologyIDs() {
        String code = "123";
        TypologyID typologyID1 = new TypologyID(code);
        TypologyID typologyID2 = new TypologyID(code);
        assertTrue(typologyID1.equals(typologyID2));
    }

    /**
     * Test that equals() returns false for different TypologyIDs.
     */
    @Test
    void equalsShouldReturnFalseForDifferentTypologyIDs() {
        String code1 = "123";
        String code2 = "456";
        TypologyID typologyID1 = new TypologyID(code1);
        TypologyID typologyID2 = new TypologyID(code2);
        assertFalse(typologyID1.equals(typologyID2));
    }

    /**
     * Test that equals() returns false for null object.
     */
    @Test
    void equalsShouldReturnFalseForNullObject() {
        String code = "123";
        TypologyID typologyID = new TypologyID(code);
        assertFalse(typologyID.equals(null));
    }

    /**
     * Test that equals() returns true for same object.
     */
    @Test
    void equalsShouldReturnTrueForSameObject() {
        String code = "123";
        TypologyID typologyID = new TypologyID(code);
        assertTrue(typologyID.equals(typologyID));
    }

    /**
     * Test that equals() returns false for different class object.
     */
    @Test
    void equalsShouldReturnFalseForDifferentClassObject() {
        String code = "123";
        TypologyID typologyID = new TypologyID(code);
        assertFalse(typologyID.equals(new Object()));
    }

    /**
     * Test that hashCode() returns the same value for equal TypologyIDs.
     */
    @Test
    void sameValueForEqualIDsInHashCode() {
        String code = "123";
        TypologyID typologyID1 = new TypologyID(code);
        TypologyID typologyID2 = new TypologyID(code);
        assertEquals(typologyID1.hashCode(), typologyID2.hashCode());
    }

    /**
     * Test that hashCode() returns different values for different TypologyIDs.
     */
    @Test
    void differentValueForDifferentIDsInHashCode() {
        String code1 = "123";
        String code2 = "456";
        TypologyID typologyID1 = new TypologyID(code1);
        TypologyID typologyID2 = new TypologyID(code2);
        assertNotEquals(typologyID1.hashCode(), typologyID2.hashCode());
    }

    /**
     * Test that sameAs() returns false when comparing to null object.
     */
    @Test
    void ensureObjectsAreNotTheSame_objectNull(){
        //Arrange
        TypologyID typologyID = new TypologyID("typ1");
        TypologyID typologyID1 = null;

        boolean expected = false;
        //Act
        boolean result = typologyID.sameAs(typologyID1);

        //Assert
        assertEquals(expected,result);
    }
}