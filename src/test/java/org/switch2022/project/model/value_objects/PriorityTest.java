package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @Test
    void ensureGetIndex() {
        //Arrange
        Priority priority = new Priority(1);

        int expected = 1;

        //Act
        int result = priority.getIndex();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureIndexCanNotBeLowerThanOne() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Priority(0);
        });
        assertEquals("Priority cannot be lower than one", exception.getMessage());
    }

    @Test
    void ensureObjectsAreEquals() {
        Priority priority = new Priority(1);
        Priority priority1 = priority;

        boolean isEquals = priority.equals(priority1);

        assertTrue(isEquals);
    }

    @Test
    void ensureObjectsAreNotEquals() {
        Priority priority = new Priority(1);
        Priority priority1 = new Priority(2);

        boolean isEquals = priority.equals(priority1);

        assertFalse(isEquals);
    }

    @Test
    void ensureObjectsAreFromDifferentClasses() {
        Priority priority = new Priority(1);

        Object o = new Object();

        boolean isEquals = priority.equals(o);

        assertFalse(isEquals);
    }

    @Test
    void ensureObjectsAreNotTheSame_nullObject() {
        Priority priority = new Priority(1);
        Priority priority1 = null;

        boolean isEquals = priority.equals(priority1);

        assertFalse(isEquals);
    }

    @Test
    void ensureHashCodeIsTheSame() {
        Priority priority = new Priority(1);
        Priority priority1 = priority;

        assertEquals(priority.hashCode(), priority1.hashCode());
    }

    @Test
    void ensureSameAsOtherObject() {
        Priority priority = new Priority(1);
        Priority priority1 = priority;

        boolean isEquals = priority.sameAs(priority1);

        assertTrue(isEquals);
    }

    @Test
    void ensureSameAsOtherObjectEqualsFalse() {
        Priority priority = new Priority(1);
        Priority priority1 = new Priority(2);

        boolean isEquals = priority.sameAs(priority1);

        assertFalse(isEquals);
    }

    @Test
    void ensureSameAsOtherObjectEqualsFalse_ObjectNull() {
        Priority priority = new Priority(1);
        Priority priority1 = null;

        boolean isEquals = priority.sameAs(priority1);

        assertFalse(isEquals);
    }
}