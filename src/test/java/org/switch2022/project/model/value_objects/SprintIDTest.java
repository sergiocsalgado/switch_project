package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class SprintIDTest {

    @Test
    void createSprintID_Success() {
        SprintID sprintID1 = new SprintID("sprint1");
        String expected = "sprint1";
        String result = sprintID1.getSprintID();

        assertEquals(expected, result);
    }

    @Test
    void sprintIDNull_ThrowException() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintID(stringToCheck);
        });
        assertEquals("Sprint ID cannot be null", exception.getMessage());
    }

    @Test
    void sprintIDEmpty_ThrowException() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Sprint ID", stringToCheck);
        });
        assertEquals("Sprint ID cannot be empty", exception.getMessage());

    }

    @Test
    void testForEqualsAndHashCodeOfDifferentValueObjects() {

        SprintID sprintID1 = new SprintID("sprint1");
        SprintID sprintID2 = new SprintID("sprint2");

        assertNotEquals(sprintID1, sprintID2);
        assertNotEquals(sprintID1.hashCode(), sprintID2.hashCode());
        assertNotEquals(sprintID1.getSprintID(), sprintID2.getSprintID());
    }

    @Test
    void testForEqualsAndHashCodeOfEqualValueObjects() {

        SprintID sprintID1 = new SprintID("sprint1");
        SprintID sprintID2 = sprintID1;

        assertEquals(sprintID1, sprintID2);
        assertEquals(sprintID1.hashCode(), sprintID2.hashCode());
        assertEquals(sprintID1.getSprintID(), sprintID2.getSprintID());
    }

    @Test
    void ensureReturnFalse_whenCompareToOtherInstanceObject() {

        SprintID sprintID1 = new SprintID("sprint1");
        ProfileID profileIDNumber = new ProfileID("1");
        Description description = new Description("Administrator");

        Profile profile = new Profile(profileIDNumber, description);

        assertNotEquals(sprintID1, profile);
    }

    @Test
    void ensureReturnFalse_whenCompareToNullObject() {
        //Arrange
        SprintID sprintID1 = new SprintID("sprint1");
        SprintID sprintID2 = null;

        //Assert
        assertFalse(sprintID1.sameAs(sprintID2));
        assertNotEquals(sprintID1, sprintID2);
    }

    @Test
    void ensureSameValueAsOtherObject_Success() {
        //Arrange
        SprintID sprintID1 = new SprintID("sprint1");
        SprintID sprintID2 = sprintID1;
        boolean expected = true;

        //Act
        boolean result = sprintID1.sameAs(sprintID2);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureIsNotSameValueAsOtherObject_Fail() {
        //Arrange
        SprintID sprintID1 = new SprintID("sprint1");
        SprintID sprintID2 = new SprintID("sprint2");
        boolean expected = false;

        //Act
        boolean result = sprintID1.sameAs(sprintID2);

        //Assert
        assertEquals(expected, result);
    }
}
