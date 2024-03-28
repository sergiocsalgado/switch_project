package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryStatusTest {
    /**
     * Test method for the UserStoryStatus constructor.
     * This test checks if the constructor throws an IllegalArgumentException.
     * with the message "description is not valid" when passed a description that is blank.
     */
    @Test
    void constructorTest_descriptionBlank() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryStatus("");
        });

        assertEquals("description cannot be empty", exception.getMessage());
    }
    /**
     * Test method for the UserStoryStatus constructor.
     * This test checks if the constructor throws an IllegalArgumentException.
     * with the message "description is not valid" when passed a description that is empty.
     */
    @Test
    void constructorTest_descriptionEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryStatus("   ");
        });

        assertEquals("description cannot be empty", exception.getMessage());
    }
    /**
     * Test method for the UserStoryStatus constructor.
     * This test checks if the constructor throws an IllegalArgumentException
     * with the message "description can not be null" when passed a description that is null.
     */
    @Test
    void constructorTest_descriptionNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryStatus(null);
        });

        assertEquals("description cannot be null", exception.getMessage());
    }
    /**
     * Test method for the UserStoryStatus constructor.
     * This test checks if the constructor throws an IllegalArgumentException
     * with the message "description is not valid" when passed a description that is not planned, running, finished or blocked.
     */
    @Test
    void constructorTest_descriptionNotValid() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryStatus("invalid");
        });

        assertEquals("description is not valid", exception.getMessage());
    }

    /**
     * Test class for {@link UserStoryStatus#equals(Object)}.
     */
    @Test
    void ensureSameInstance() {
        assertInstanceOf(UserStoryStatus.class, new UserStoryStatus("planned"));
    }

    @Test
    void ensureDifferentInstance() {
        UserStoryStatus status = new UserStoryStatus("planned");
        Object other = new Object();

        assertNotEquals(status, other);
    }

    @Test
    void equals_ensureSameObject() {
        UserStoryStatus status_1 = new UserStoryStatus("planned");
        UserStoryStatus status_2 = status_1;

        assertEquals(status_1, status_2);
    }

    @Test
    void equals_ensureEqualObject() {
        UserStoryStatus status_1 = new UserStoryStatus("planned");
        UserStoryStatus status_2 = new UserStoryStatus("planned");

        assertEquals(status_1, status_2);
    }

    @Test
    void equals_ensureDifferentObject_description_1() {
        UserStoryStatus status_1 = new UserStoryStatus("planned");
        UserStoryStatus status_2 = new UserStoryStatus("finished");

        assertNotEquals(status_1, status_2);
    }
    @Test
    void equals_ensureDifferentObject_description_2() {
        UserStoryStatus status_1 = new UserStoryStatus("running");
        UserStoryStatus status_2 = new UserStoryStatus("blocked");

        assertNotEquals(status_1, status_2);
    }@Test
    void equals_ensureDifferentObject_description_3() {
        UserStoryStatus status_1 = new UserStoryStatus("finished");
        UserStoryStatus status_2 = new UserStoryStatus("running");

        assertNotEquals(status_1, status_2);
    }

    @Test
    void equals_ensureDifferentObject_null() {
        UserStoryStatus status_1 = new UserStoryStatus("planned");
        UserStoryStatus status_2 = null;

        assertNotEquals(status_1, status_2);
    }

    /**
     * Test class for {@link UserStoryStatus#hashCode()}.
     */
    @Test
    void hashCode_ensureEqualObject() {
        UserStoryStatus status_1 = new UserStoryStatus("planned");
        UserStoryStatus status_2 = new UserStoryStatus("planned");

        assertEquals(status_1.hashCode(), status_2.hashCode());
    }

    @Test
    void hashCode_ensureDifferentObject_description() {
        UserStoryStatus status_1 = new UserStoryStatus("planned");
        UserStoryStatus status_2 = new UserStoryStatus("finished");

        assertNotEquals(status_1.hashCode(), status_2.hashCode());
    }

    /**
     * Test class for {@link UserStoryStatus#getDescription()}.
     */
    @Test
    void getDescription_ensureSameDescription() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("planned");
        String expected = "planned";

        //Act
        String result = status.getDescription();

        //Assert
        assertEquals(expected, result);
    }
    @Test
    void getDescription_ensureDifferentDescription() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("planned");
        String expected = "running";

        //Act
        String result = status.getDescription();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test class for {@link UserStoryStatus#sameAs(UserStoryStatus)}.
     */
    @Test
    void sameAs_ensureIsTrueTheDescriptionIsTheSame(){
        //Arrange
        UserStoryStatus status = new UserStoryStatus("planned");
        UserStoryStatus otherStatus = new UserStoryStatus("planned");

        //Act
        boolean result = status.sameAs(otherStatus);

        //Assert
        assertTrue(result);
    }

    @Test
    void sameAs_ensureIsFalseWhenTheDescriptionIsNotTheSame(){
        //Arrange
        UserStoryStatus status = new UserStoryStatus("planned");
        UserStoryStatus otherStatus = new UserStoryStatus("running");

        //Act
        boolean result = status.sameAs(otherStatus);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameAs_ensureIsFalseWhenTheComparableIsNull(){
        //Arrange
        UserStoryStatus status = new UserStoryStatus("planned");
        UserStoryStatus otherStatus = null;

        //Act
        boolean result = status.sameAs(otherStatus);

        //Assert
        assertFalse(result);
    }
}