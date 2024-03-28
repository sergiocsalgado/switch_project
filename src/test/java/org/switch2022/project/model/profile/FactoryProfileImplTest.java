package org.switch2022.project.model.profile;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This class contains unit tests for the {@link FactoryProfileImpl} class.
 */
public class FactoryProfileImplTest {

    /**
     * Unit test for verifying successful creation of a {@link Profile} object.
     */
    @Test
    void createProfile_Success_Unit() {
        // Arrange
        FactoryProfileImpl factoryProfile = new FactoryProfileImpl();
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile expected = profile;

        // Act
        Profile result = factoryProfile.createProfile(profileID, description);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Unit test for verifying failed creation of a {@link Profile} object.
     */
    @Test
    void createProfile_Fail_Unit() {
        // Arrange
        FactoryProfileImpl factoryProfile = new FactoryProfileImpl();
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = mock(Profile.class);
        factoryProfile.createProfile(profileID, description);
        Profile expected = profile;

        // Act
        Profile result = factoryProfile.createProfile(profileID, description);

        // Assert
        assertNotEquals(expected, result);
    }

}
