package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.profile.FactoryProfile;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is a test class for the CreateProfileServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateProfileServiceImpl.class)
class CreateProfileServiceImplTest {

    @MockBean
    @Qualifier("profileJPARepository")
    private Repository<ProfileID, Profile> profileRepository;
    @MockBean
    private FactoryProfile mockFactoryProfile;

    /**
     * Mocked profile.
     */
    @MockBean
    private Profile mockProfile;

    /**
     * Autowired instance of {@link CreateProfileServiceImpl}.
     */
    @Autowired
    CreateProfileServiceImpl mockProfileServiceImpl;

    /**
     * Ensures that the createProfile method succeeds when all parameters are valid and the profile does not already exist.
     */
    @Test
    void ensureCreateProfile_Success() {
        // ARRANGE
        String profileID1 = "1234";
        String description1 = "Test profile";

        ProfileID profileID = new ProfileID(profileID1);
        Description description = new Description(description1);

        when(mockFactoryProfile.createProfile(profileID, description)).thenReturn(mockProfile);
        when(profileRepository.containsOfIdentity(profileID)).thenReturn(false);
        when(profileRepository.save(mockProfile)).thenReturn(mockProfile);

        Profile expected = mockProfile;

        // ACT
        Profile result = mockProfileServiceImpl.createProfile(profileID1, description1);

        // ASSERT
        assertEquals(expected, result);
    }


    /**
     * Ensures that the createProfile method succeeds when all parameters are valid and the profile does not already exist.
     */
    @Test
    void ensureCreateProfile_SuccessDescription() {
        // ARRANGE
        String profileID1 = "1234";
        String description1 = "Test profile";

        ProfileID profileID = new ProfileID(profileID1);
        Description description = new Description(description1);

        Profile profileDouble = mock(Profile.class);
        when(profileDouble.getProfileID()).thenReturn(new ProfileID("122312"));
        when(profileDouble.getDescription()).thenReturn(new Description("admin"));

        when(mockFactoryProfile.createProfile(profileID, description)).thenReturn(mockProfile);
        when(profileRepository.containsOfIdentity(profileID)).thenReturn(false);
        when(mockProfile.getProfileID()).thenReturn(profileID);

        List<Profile> profileList = List.of(profileDouble);
        when(profileRepository.findAll()).thenReturn(profileList);

        when(mockProfile.getDescription()).thenReturn(description);
        when(profileRepository.save(mockProfile)).thenReturn(mockProfile);

        Profile expected = mockProfile;

        // ACT
        Profile result = mockProfileServiceImpl.createProfile(profileID1, description1);

        // ASSERT
        assertEquals(expected, result);
    }
    /**
     * Ensures that the createProfile method fails when the profile already exists.
     */
    @Test
    void ensureCreateProfileFail_ProfileIDAlreadyExists() {

        // ARRANGE
        String profileID1 = "1234";
        String description1 = "Test profile";

        ProfileID profileID = new ProfileID(profileID1);
        Description description = new Description(description1);

        Profile profileDouble = mock(Profile.class);
        when(profileDouble.getProfileID()).thenReturn(profileID);
        when(profileDouble.getDescription()).thenReturn(new Description("user"));

        when(mockFactoryProfile.createProfile(profileID, description)).thenReturn(mockProfile);
        when(profileRepository.containsOfIdentity(profileID)).thenReturn(true);
        when(mockProfile.getProfileID()).thenReturn(profileID);

        List<Profile> profileList = List.of(profileDouble);
        when(profileRepository.findAll()).thenReturn(profileList);

        when(mockProfile.getDescription()).thenReturn(description);
        when(profileRepository.save(mockProfile)).thenReturn(mockProfile);

        // ACT
        Throwable exception = assertThrows(IllegalArgumentException.class,() ->{
            mockProfileServiceImpl.createProfile(profileID1,description1);
        });

        // ASSERT
        assertEquals("Could not create Profile, this profile already exists.", exception.getMessage());
    }
    /**
     * Ensures that the createProfile method fails when the profile already exists.
     */
    @Test
    void ensureCreateProfileFail_ProfileDescriptionAlreadyExists() {

        // ARRANGE
        String profileID1 = "1234";
        String description1 = "Test profile";

        ProfileID profileID = new ProfileID(profileID1);
        Description description = new Description(description1);

        Profile profileDouble = mock(Profile.class);
        when(profileDouble.getProfileID()).thenReturn(new ProfileID("122312"));
        when(profileDouble.getDescription()).thenReturn(description);

        when(mockFactoryProfile.createProfile(profileID, description)).thenReturn(mockProfile);
        when(profileRepository.containsOfIdentity(profileID)).thenReturn(false);
        when(mockProfile.getProfileID()).thenReturn(profileID);

        List<Profile> profileList = List.of(profileDouble);
        when(profileRepository.findAll()).thenReturn(profileList);

        when(mockProfile.getDescription()).thenReturn(description);
        when(profileRepository.save(mockProfile)).thenReturn(mockProfile);

        // ACT
        Throwable exception = assertThrows(IllegalArgumentException.class,() ->{
            mockProfileServiceImpl.createProfile(profileID1,description1);
        });

        // ASSERT
        assertEquals("Could not create Profile, this profile already exists.", exception.getMessage());
    }


    /**
     * Ensures that the createProfile method fails when the profile ID is empty.
     */
    @Test
    void ensureCreateProfileFail_EmptyProfileID() {

        // ARRANGE
        String profileID1 = "";
        String description1 = "Test profile";
        CreateProfileServiceImpl profileServiceImpl = new CreateProfileServiceImpl(profileRepository, mockFactoryProfile);

        // ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            profileServiceImpl.createProfile(profileID1, description1);
        });

        // ASSERT
        assertEquals("Profile ID cannot be empty", exception.getMessage());
    }

    /**
     * Ensures that the createProfile method fails when the profile ID is null.
     */
    @Test
    void ensureCreateProfileFail_InvalidParameters() {

        // ARRANGE
        String profileID1 = null;
        String description1 = "Test profile";
        CreateProfileServiceImpl profileServiceImpl = new CreateProfileServiceImpl(profileRepository, mockFactoryProfile);

        // ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            profileServiceImpl.createProfile(profileID1, description1);
        });

        // ASSERT
        assertEquals("Profile ID cannot be null", exception.getMessage());
    }
}
