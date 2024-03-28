package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintStatusTest {
    /**
     * Test class for {@link SprintStatus#equals(Object)}.
     */
    @Test
    void constructorTest_descriptionBlank() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintStatus("");
        });

        assertEquals("status cannot be empty", exception.getMessage());
    }
    @Test
    void constructorTest_descriptionEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintStatus("   ");
        });

        assertEquals("status cannot be empty", exception.getMessage());
    }
    @Test
    void constructorTest_descriptionNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintStatus(null);
        });

        assertEquals("status cannot be null", exception.getMessage());
    }

    @Test
    void constructorTest_descriptionNotValid() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintStatus("invalid");
        });

        assertEquals("status is not valid", exception.getMessage());
    }

    @Test
    void ensureSameInstance() {
        assertInstanceOf(SprintStatus.class, new SprintStatus("Open"));
    }

    @Test
    void ensureDifferentInstance() {
        SprintStatus status = new SprintStatus("Open");
        Object other = new Object();

        assertNotEquals(status, other);
    }

    @Test
    void equals_ensureSameObject() {
        SprintStatus status_1 = new SprintStatus("Open");
        SprintStatus status_2 = status_1;

        assertEquals(status_1, status_2);
    }

    @Test
    void equals_ensureEqualObject() {
        SprintStatus status_1 = new SprintStatus("Open");
        SprintStatus status_2 = new SprintStatus("Open");

        assertEquals(status_1, status_2);
    }

    @Test
    void equals_ensureDifferentObject_description_1() {
        SprintStatus status_1 = new SprintStatus("Open");
        SprintStatus status_2 = new SprintStatus("Closed");

        assertNotEquals(status_1, status_2);
    }

    @Test
    void equals_ensureDifferentObject_null() {
        SprintStatus status_1 = new SprintStatus("Open");
        SprintStatus status_2 = null;

        assertNotEquals(status_1, status_2);
    }

    /**
     * Test class for {@link SprintStatus#hashCode()}.
     */
    @Test
    void hashCode_ensureEqualObject() {
        SprintStatus status_1 = new SprintStatus("Open");
        SprintStatus status_2 = new SprintStatus("Open");

        assertEquals(status_1.hashCode(), status_2.hashCode());
    }

    @Test
    void hashCode_ensureDifferentObject_description() {
        SprintStatus status_1 = new SprintStatus("Open");
        SprintStatus status_2 = new SprintStatus("Closed");

        assertNotEquals(status_1.hashCode(), status_2.hashCode());
    }

    /**
     * Test class for {@link SprintStatus#getDescription()}.
     */
    @Test
    void getDescription_ensureSameDescription() {
        //Arrange
        SprintStatus status = new SprintStatus("Open");
        String expected = "Open";

        //Act
        String result = status.getDescription();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getDescription_ensureDifferentDescription() {
        //Arrange
        SprintStatus status = new SprintStatus("Open");
        String expected = "Closed";

        //Act
        String result = status.getDescription();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test class for {@link SprintStatus#sameAs(SprintStatus)}.
     */
    @Test
    void sameAs_ensureIsTrueTheDescriptionIsTheSame() {
        //Arrange
        SprintStatus status = new SprintStatus("Open");
        SprintStatus otherStatus = new SprintStatus("Open");

        //Act
        boolean result = status.sameAs(otherStatus);

        //Assert
        assertTrue(result);
    }

    @Test
    void sameAs_ensureIsFalseWhenTheDescriptionIsNotTheSame() {
        //Arrange
        SprintStatus status = new SprintStatus("Open");
        SprintStatus otherStatus = new SprintStatus("Closed");

        //Act
        boolean result = status.sameAs(otherStatus);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameAs_ensureIsFalseWhenTheComparableIsNull() {
        //Arrange
        SprintStatus status = new SprintStatus("planned");
        SprintStatus otherStatus = null;

        //Act
        boolean result = status.sameAs(otherStatus);

        //Assert
        assertFalse(result);
    }

}
