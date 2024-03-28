package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryNumberTest {

    @Test
    void doNotCreateUserStoryNumber_invalidArgument_NegativeNumber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryNumber("-5");
        });
        assertEquals("UserStoryNumber must be a positive " +
                "number", exception.getMessage());
    }
    @Test
    void doNotCreateUserStoryNumber_invalidArgument_Letters() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryNumber("u5s");
        });
        assertEquals("UserStoryNumber must be a positive " +
                "number", exception.getMessage());
    }

    @Test
    void getNumber() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");

        String expected = "4";
        String result = userStoryNumber.getNumber();

        assertEquals(expected, result);

    }


    @Test
    void ensureObjectsAreEqual() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        UserStoryNumber userStoryNumber1 = userStoryNumber;

        assertEquals(userStoryNumber, userStoryNumber1);
    }

    @Test
    void ensureObjectsHaveSameContent() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("4");

        assertEquals(userStoryNumber, userStoryNumber1);
    }

    @Test
    void ensureObjectsAreNotEqual() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("5");

        assertNotEquals(userStoryNumber, userStoryNumber1);
    }

    @Test
    void ensureObjectsAreFromDifferentClasses() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        // V.O. Profile Class
        ProfileID profileIDNumber =  new ProfileID("1");
        Description description = new Description("Administrator");

        Profile profile = new Profile(profileIDNumber, description);

        assertNotEquals(userStoryNumber, profile);
    }

    @Test
    void ensureObjectsAreNotEqual_Null() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        UserStoryNumber userStoryNumber1 = null;

        assertFalse(userStoryNumber.sameAs(userStoryNumber1));
        assertNotEquals(userStoryNumber, userStoryNumber1);
    }

    @Test
    void sameHashCodeSuccess() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        UserStoryNumber userStoryNumber1 = userStoryNumber;

        assertEquals(userStoryNumber, userStoryNumber1);
        assertEquals(userStoryNumber.hashCode(), userStoryNumber1.hashCode());
    }

    @Test
    void sameHashCodeUnsuccessful() {
        UserStoryNumber userStoryNumber = new UserStoryNumber("4");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("5");

        assertNotEquals(userStoryNumber, userStoryNumber1);
        assertNotEquals(userStoryNumber.hashCode(), userStoryNumber1.hashCode());
    }
    @Test
    void ensureObjectsAreTheSame(){
        UserStoryNumber userStoryNumber = new UserStoryNumber("3");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("3");

        boolean expected = true;

        boolean result = userStoryNumber.sameAs(userStoryNumber1);

        assertEquals(expected,result);
    }
    @Test
    void ensureObjectsAreNotTheSame(){
        UserStoryNumber userStoryNumber = new UserStoryNumber("3");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("2");

        boolean expected = false;

        boolean result = userStoryNumber.sameAs(userStoryNumber1);

        assertEquals(expected,result);
    }
}
