package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceInProjectIDTest {

    @Test
    void throwExceptionWhenResourcesInProjectIDNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ResourceInProjectID(stringToCheck);
        });
        assertEquals("Resource in Project ID cannot be null", exception.getMessage());
    }

    @Test
    void throwExceptionWhenResourcesInProjectIDEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ResourceInProjectID(stringToCheck);
        });
        assertEquals("Resource in Project ID cannot be empty", exception.getMessage());
    }

    @Test
    void ensureThatCreatesResourceInProjectID_Success() {
        //Arrange
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource2");
        String expected = "resource2";
        //Act
        String result = resourceInProjectID.getResourceOfProjectID();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void testForEqualsAndHashCodeOfDifferentValueObjects() {
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource1");
        ResourceInProjectID resourceInProjectID1 = new ResourceInProjectID("resource2");

        assertNotEquals(resourceInProjectID, resourceInProjectID1);
        assertNotEquals(resourceInProjectID.hashCode(), resourceInProjectID1.hashCode());
        assertNotEquals(resourceInProjectID.getResourceOfProjectID(), resourceInProjectID1.getResourceOfProjectID());
    }

    @Test
    void testForEqualsAndHashCodeOfEqualValueObjects() {
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource1");
        ResourceInProjectID resourceInProjectID1 = resourceInProjectID;

        assertEquals(resourceInProjectID, resourceInProjectID1);
        assertEquals(resourceInProjectID.hashCode(), resourceInProjectID1.hashCode());
        assertEquals(resourceInProjectID.getResourceOfProjectID(), resourceInProjectID1.getResourceOfProjectID());
    }

    @Test
    void ensureReturnFalse_whenCompareToOtherInstanceObject() {
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource0");
        Object object = new Object();

        assertNotEquals(resourceInProjectID, object);
    }

    @Test
    void ensureReturnFalse_whenCompareToNullObject() {
        //Arrange
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource0");
        ResourceInProjectID resourceInProjectID1 = null;
        //Assert
        assertNotEquals(resourceInProjectID, resourceInProjectID1);
    }

    @Test
    void ensureSameValueAsOtherObject_Success() {
        //Arrange
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource1");
        ResourceInProjectID resourceInProjectID1 = resourceInProjectID;
        boolean expected = true;
        //Act
        boolean result = resourceInProjectID.sameAs(resourceInProjectID1);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureIsNotSameValueAsOtherObject_Fail() {
        //Arrange
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource1");
        ResourceInProjectID resourceInProjectID1 = new ResourceInProjectID("resource2");
        boolean expected = false;
        //Act
        boolean result = resourceInProjectID.sameAs(resourceInProjectID1);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureIsNotSameValueAsOtherObject_Null() {
        //Arrange
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID("resource0");
        ResourceInProjectID resourceInProjectID1 = null;
        //Assert
        assertFalse(resourceInProjectID.sameAs(resourceInProjectID1));
    }
}
