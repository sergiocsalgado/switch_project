package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains unit tests for the {@link ProfileDTO} class.
 */
public class ProfileDTOTest {

    /**
     * Unit test for verifying that the getDescription method
     * returns the correct description.
     */
    @Test
    void testForSetAndGetDescription() {
        // Arrange
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setDescription("user");

        // Act + Assert
        assertEquals("user", profileDTO.getDescription());
    }

    /**
     * Unit test for verifying that the getProfileID method
     * returns the correct profileID.
     */
    @Test
    void TestForgetAndSetProfileID() {
        // Arrange
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileID("1");

        // Act + Assert
        assertEquals("1", profileDTO.getProfileID());
    }

    /**
     * Unit test for verifying that the equals method correctly
     * compares two ProfileDTO objects for equality.
     */
    @Test
    void testEquals() {
        // Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setProfileID("1");
        profileDTO1.setDescription("user");

        ProfileDTO profileDTO2 = new ProfileDTO();
        profileDTO2.setProfileID("1");
        profileDTO2.setDescription("user");

        ProfileDTO profileDTO3 = new ProfileDTO();
        profileDTO3.setProfileID("3");
        profileDTO3.setDescription("user3");

        // Act + Assert
        assertEquals(profileDTO1, profileDTO2);
        assertNotEquals(profileDTO1, profileDTO3);
    }

    /**
     * Unit test for verifying that the hashCode method
     * generates the same hash code for equal objects.
     */
    @Test
    void testHashCode() {
        // Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setProfileID("1");
        profileDTO1.setDescription("user");

        ProfileDTO profileDTO2 = new ProfileDTO();
        profileDTO2.setProfileID("1");
        profileDTO2.setDescription("user");

        // Act + Assert
        assertEquals(profileDTO1.hashCode(), profileDTO2.hashCode());
    }

    /**
     * Unit test for verifying that the hashCode method
     * generates different hash codes for different objects.
     */
    @Test
    public void testHashCode_Different() {
        // Arrange
        ProfileDTO profile1 = new ProfileDTO();
        profile1.setDescription("Description 1");
        profile1.setProfileID("ID1");

        ProfileDTO profile2 = new ProfileDTO();
        profile2.setDescription("Description 2");
        profile2.setProfileID("ID2");

        // Act + Assert
        assertNotEquals(profile1.hashCode(), profile2.hashCode());
    }

    /**
     * Unit test for verifying that the equals method returns true
     * when the same object is passed as a parameter.
     */
    @Test
    public void testEquals_SameObject() {
        // Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setProfileID("1");
        profileDTO1.setDescription("user");

        ProfileDTO profileDTO2 = profileDTO1;

        // Act + Assert
        assertEquals(profileDTO1, profileDTO2);
    }

    /**
     * Unit test for verifying that the equals method returns false
     * when a null object is passed as a parameter.
     */
    @Test
    public void testEquals_NullObject() {
        // Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setProfileID("1");
        profileDTO1.setDescription("user");

        // Act + Assert
        assertNotEquals(null, profileDTO1);
    }

    /**
     * Unit test for verifying that the equals method
     * returns false when a different class is passed as a parameter.
     */
    @Test
    public void testEquals_DifferentClass() {
        // Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setProfileID("1");
        profileDTO1.setDescription("user");

        String otherObject = "Not a ProfileDTO";

        // Act + Assert
        assertNotEquals(profileDTO1, otherObject);
    }
}