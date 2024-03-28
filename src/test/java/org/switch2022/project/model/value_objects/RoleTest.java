package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    /**
     * Test class for {@link Role#Role(String)} .
     */
    @Test
    void descriptionNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Role(stringToCheck);
        });
        assertEquals("Role cannot be null", exception.getMessage());
    }

    @Test
    void descriptionEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Role(stringToCheck);
        });
        assertEquals("Role cannot be empty", exception.getMessage());
    }

    /**
     * Test class for {@link Role#getDescription()}.
     */
    @Test
    void getDescriiption_Success() {
        Role role = new Role("SCRUM MASTER");
        String description = "scrum master";
        String expected = description;

        String result = role.getDescription();

        assertEquals(expected, result);
    }

    @Test
    void getDescriiption_Fail() {
        Role role = new Role("Scrum Master");
        String description = "product owner";
        String expected = description;

        String result = role.getDescription();

        assertNotEquals(expected, result);
    }

    /**
     * Test class for {@link Role#equals(Object)}.
     */
    @Test
    void objectsAreEqual() {
        Role role = new Role("Scrum Master");
        Role role2 = role;

        assertEquals(role, role2);
    }

    @Test
    void objectsNotEqual_differentInstance() {
        Role role = new Role("Scrum Master");
        ProfileID profileIDNumber = new ProfileID("1");
        Description description = new Description("manager");
        Profile profile = new Profile(profileIDNumber, description);

        assertNotEquals(role, profile);
    }

    @Test
    void objectsAreNotEqual() {
        Role role = new Role("Scrum Master");
        Role role2 = new Role("product owner");

        assertNotEquals(role, role2);
    }

    @Test
    void objectsAreNull() {
        Role role = new Role("Scrum Master");
        Role role2 = null;

        assertNotEquals(role, role2);
    }

    /**
     * Test class for {@link Role#hashCode()}.
     */
    @Test
    void objectsHashCodeAreEqual() {
        Role role = new Role("Scrum Master");
        Role role2 = role;

        assertEquals(role.hashCode(), role2.hashCode());
    }

    @Test
    void objectsHashCodeAreNotEqual() {
        Role role = new Role("Scrum Master");
        Role role2 = new Role("product owner");

        assertNotEquals(role.hashCode(), role2.hashCode());
    }

    /**
     * Test class for {@link Role#sameAs(Role)}.
     */
    @Test
    void ensureIsTheSameRole() {
        //Arrange
        Role role = new Role("Scrum Master");
        Role comparable = new Role("Scrum Master");

        //Act
        boolean result = role.sameAs(comparable);

        //Assert
        assertTrue(result);

    }

    @Test
    void ensureNotTheSameNullComparable() {
        //Arrange
        Role role = new Role("Scrum Master");
        Role comparable = null;

        //Act
        boolean result = role.sameAs(comparable);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureDoNotCreateInvalidRole() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            new Role("pm");
        });
        assertEquals("Role is not valid, see valid roles for more information", exception.getMessage());


    }
}
