package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class ProfileIDTest {

    @Test
    void ensureCreationOfObjectIsOk() {
        String number = "1234";
        ProfileID profileID = new ProfileID(number);
        ProfileID profileID1 = new ProfileID(number);
        String res2 = "PROFILE1234";
        assertEquals(profileID, profileID1, res2);
    }

    @Test
    void ensureProfileIDCannotBeNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkNull("Profile ID", stringToCheck);
        });
        assertEquals("Profile ID cannot be null", exception.getMessage());
    }

    @Test
    void ensureProfileIDCannotBeEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Profile ID", stringToCheck);
        });
        assertEquals("Profile ID cannot be empty", exception.getMessage());
    }

    @Test
    void ensureProfileIDCannotBeBlank() {
        String stringToCheck = "    ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Profile ID", stringToCheck);
        });
        assertEquals("Profile ID cannot be empty", exception.getMessage());
    }

    @Test
    void ensureGetProfileIDReturnCorrectProfileID() {
        String number = "1234";
        ProfileID profileID = new ProfileID(number);
        String exp = number;
        String res = profileID.getProfileID();
        assertEquals(exp, res);
    }


    @Test
    void ensureObjectsAreDifferent() {
        ProfileID profileID1 = new ProfileID("1");
        ProfileID profileID = new ProfileID("2");

        assertNotEquals(profileID1, profileID);

    }

    @Test
    void toStringShouldReturnCorrectValue() {
        String number = "123";
        ProfileID profileID = new ProfileID(number);
        assertEquals("Profile ID{id='123'}", profileID.toString());
    }

    @Test
    void ensureObjectAreTheSame() {
        ProfileID profileID = new ProfileID("1");
        ProfileID profileID1 = new ProfileID("1");

        assertTrue(profileID.sameAs(profileID1));

    }

    @Test
    void ensureObjectAreDifferent() {
        ProfileID profileID = new ProfileID("1");
        ProfileID profileID2 = new ProfileID("2");

        assertFalse(profileID.sameAs(profileID2));

    }
    @Test
    void ensureObjectAreDifferent_ObjectNull() {
        ProfileID profileID = new ProfileID("1");
        ProfileID profileID2 = null;

        assertFalse(profileID.sameAs(profileID2));

    }

    @Test
    void ensureEqualsMethodIsOk() {
        String number = "4532";
        ProfileID profileID = new ProfileID(number);
        ProfileID profileID1 = new ProfileID(number);
        assertTrue(profileID1.equals(profileID));
    }

    @Test
    void ensureEqualsMethodFailWhenDifferentObject() {
        String number = "4532";
        String number1 = "4333";
        ProfileID profileID = new ProfileID(number);
        ProfileID profileID1 = new ProfileID(number1);
        assertFalse(profileID1.equals(profileID));
    }

    @Test
    void ensureEqualsMethodFailWhenNull() {
        String number = "4532";
        ProfileID profileID = new ProfileID(number);
        assertFalse(profileID.equals(null));
    }

    @Test
    void ensureEqualMethodReturnTrueForTheSameObject() {
        String number = "4532";
        ProfileID profileID = new ProfileID(number);
        assertTrue(profileID.equals(profileID));
    }

    @Test
    void ensureObjectsAreFromDifferentClasses(){
        //Arrange
        ProfileID profileID = new ProfileID("2");

        Object o = new Object();

        boolean expected = false;

        //Act
        boolean result = profileID.equals(o);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureHashcodeIsTheSame() {
        String number = "4532";
        ProfileID profileID = new ProfileID(number);
        ProfileID profileID1 = new ProfileID(number);
        assertEquals(profileID.hashCode(), profileID1.hashCode());
    }

    @Test
    void ensureHashcodeIsNotTheSame() {
        String number = "4532";
        String number1 = "4533";
        ProfileID profileID = new ProfileID(number);
        ProfileID profileID1 = new ProfileID(number1);
        assertNotEquals(profileID.hashCode(), profileID1.hashCode());
    }

}
