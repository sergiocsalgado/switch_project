package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the UserStoryEntityID class.
 */
class UserStoryEntityIDTest {

    private UserStoryID USid1;
    private UserStoryID USid2;

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when an empty user story ID is passed.
     */
    @Test
    void ensureNullUserStoryID() {
        // Test null User Story ID
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkNull("User story ID", stringToCheck);
        });
        assertEquals("User story ID cannot be null", exception.getMessage());
    }

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when a user story ID containing special characters is passed.
     */
    @Test
    void ensureEmptyUserStoryID() {
        // Test empty User Story ID
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("User story ID", stringToCheck);
        });
        assertEquals("User story ID cannot be empty", exception.getMessage());
    }

    /**
     * Tests that the user story ID is correctly incremented when creating new instances of {@link UserStoryID}.
     */
    @Test
    void ensureUserStoryIDContainsSpecialCharacters() {
        // Test User Story ID contains special characters
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryID("US0!@");
        });
        assertEquals("User story ID cannot contain special characters", exception.getMessage());
    }

    /**
     * Tests that the user story ID is not incorrectly incremented when creating new instances of {@link UserStoryID}.
     */
    @Test
    void ensureIDIsAttributedCorrectlyByIncrementation_Success() {

        USid1 = new UserStoryID("US1");
        USid2 = new UserStoryID("US2");

        String expected = "US2";
        String result = USid2.getUserStoryID();

        assertEquals(expected, result);
    }

    /**
     * Tests the equality and hash code methods for two instances of {@link UserStoryID} with the same ID.
     */
    @Test
    void ensureIDIsAttributedCorrectlyByIncrementation_Fail() {

        USid1 = new UserStoryID("US1");
        USid2 = new UserStoryID("US2");

        String expected = "US5";
        String result = USid2.getUserStoryID();

        assertNotEquals(expected, result);
    }

    /**
     * Tests the equality and hash code methods for two instances of {@link UserStoryID} with different IDs.
     */
    @Test
    void testForEqualsAndHashCodeOfSameValueObjects() {

        USid1 = new UserStoryID("US1");
        USid2 = USid1;

        assertEquals(USid1, USid2);
        assertEquals(USid1.hashCode(), USid2.hashCode());
        assertEquals(USid1.getUserStoryID(), USid2.getUserStoryID());
    }

    /**
     * Tests the equality and hash code methods for two instances of {@link UserStoryID} with different IDs.
     */
    @Test
    void testForEqualsAndHashCodeOfDifferentValueObjects() {

        USid1 = new UserStoryID("US1");
        USid2 = new UserStoryID("US2");

        assertNotEquals(USid1, USid2);
        assertNotEquals(USid1.hashCode(), USid2.hashCode());
        assertNotEquals(USid1.getUserStoryID(), USid2.getUserStoryID());
    }

    /**
     * Tests that the {@link UserStoryID} instance is not equal to an instance of a different class.
     */
    @Test
    void ensureReturnFalse_whenCompareToOtherInstanceObject() {

        USid1 = new UserStoryID("US1");
        Project project = mock(Project.class);

        assertNotEquals(USid1, project);
    }

    /**
     * Tests that the {@link UserStoryID} instance is not equal to {@code null}.
     */
    @Test
    void ensureReturnFalse_whenCompareToNullObject() {

        USid1 = new UserStoryID("US1");
        USid2 = null;

        assertNotEquals(USid1, USid2);
    }

    /**
     * Tests the {@link UserStoryID(UserStoryID)} method for two instances of {@link UserStoryID} that have the same ID.
     */
    @Test
    void ensureSameValueAsOtherObject_Success() {

        USid1 = new UserStoryID("US1");
        USid2 = USid1;
        boolean expected = true;

        boolean result = USid1.sameAs(USid2);

        assertEquals(expected, result);
    }

    /**
     * Tests the {@link UserStoryID(UserStoryID)} method for two instances of {@link UserStoryID} that have different IDs.
     */
    @Test
    void ensureIsNotSameValueAsOtherObject_Fail() {

        USid1 = new UserStoryID("US1");
        USid2 = new UserStoryID("US2");
        boolean expected = false;

        boolean result = USid1.sameAs(USid2);

        assertEquals(expected, result);
    }

    /**
     * Tests the {@link UserStoryID(UserStoryID)} method for a {@link UserStoryID} instance and {@code null}.
     */
    @Test
    void ensureIsNotSameValueAsOtherObject_Null() {

        USid1 = new UserStoryID("US1");
        USid2 = null;
        boolean expected = false;

        //Assert
        assertFalse(USid1.sameAs(USid2));
    }

    /**
     * Tests the creation of a {@link UserStoryID} instance with a valid user story ID.
     */
    @Test
    void testValidUserStoryID() {
        // Test valid User Story ID
        UserStoryID userStoryID = new UserStoryID("US001");
        assertEquals("US001", userStoryID.getUserStoryID());
    }

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when creating a {@link UserStoryID} instance with a null user story ID.
     */
    @Test
    void testNullUserStoryID() {
        // Test null User Story ID
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryID(null);
        });
    }

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when creating a {@link UserStoryID} instance with an empty user story ID.
     */
    @Test
    void testEmptyUserStoryID() {
        // Test empty User Story ID
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryID("");
        });
    }

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when creating a {@link UserStoryID} instance with a user story ID containing special characters.
     */
    @Test
    void testUserStoryIDContainsSpecialCharacters() {
        // Test User Story ID contains special characters
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryID("US00!@");
        });
    }

    /**
     * Tests that a {@link UserStoryID} instance with a valid user story ID is not equal to a user story ID with the same ID but different case.
     */
    @Test
    void testInvalidUserStoryIDWithMixedCase() {
        // Test valid User Story ID with mixed case
        UserStoryID userStoryID = new UserStoryID("US001");
        assertNotEquals("us001", userStoryID.getUserStoryID());
    }

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when creating a {@link UserStoryID} instance with a user story ID containing whitespace.
     */
    @Test
    void testUserStoryIDWithWhitespace() {
        // Test User Story ID with whitespace
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryID("US 001");
        });
    }

    /**
     * Tests that an {@link IllegalArgumentException} is thrown when creating a {@link UserStoryID} instance with a user story ID containing special characters and digits.
     */
    @Test
    void testUserStoryIDContainsSpecialCharactersAndDigits() {
        // Test User Story ID contains special characters and digits
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryID("US00#$");
        });
    }

    /**
     * Tests the {@link UserStoryID#equals(Object)} method when comparing with {@code null}.
     */
    @Test
    void testEqualsWithNull() {
        // Test equals method with null
        UserStoryID userStoryID = new UserStoryID("US001");
        assertFalse(userStoryID.equals(null));
    }

    /**
     * Tests the {@link UserStoryID#equals(Object)} method when comparing with an object of a different type.
     */
    @Test
    void testEqualsWithDifferentObjectType() {
        // Test equals method with different object type
        UserStoryID userStoryID = new UserStoryID("US001");
        assertFalse(userStoryID.equals("US001"));
    }

    /**
     * Tests the {@link UserStoryID#equals(Object)} method for two instances of {@link UserStoryID} with the same ID.
     */
    @Test
    void testEquals() {
        // Test equals method
        UserStoryID userStoryID1 = new UserStoryID("US001");
        UserStoryID userStoryID2 = new UserStoryID("US001");
        UserStoryID userStoryID3 = new UserStoryID("US002");
        assertTrue(userStoryID1.equals(userStoryID2));
        assertFalse(userStoryID1.equals(userStoryID3));
    }

    /**
     * Tests the {@link UserStoryID#hashCode()} method for two instances of {@link UserStoryID} with the same ID.
     */
    @Test
    void testHashCode() {
        // Test hashCode method
        UserStoryID userStoryID1 = new UserStoryID("US001");
        UserStoryID userStoryID2 = new UserStoryID("US001");
        UserStoryID userStoryID3 = new UserStoryID("US002");
        assertEquals(userStoryID1.hashCode(), userStoryID2.hashCode());
        assertNotEquals(userStoryID1.hashCode(), userStoryID3.hashCode());
    }
}