package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link ProfileMapper} class.
 */
public class ProfileMapperTest {

    /**
     * Test case for ensuring that the private constructor of {@link ProfileMapper} throws an exception when called.
     *
     * @throws InvocationTargetException if an exception occurs during the invocation of the constructor
     * @throws InstantiationException    if the class represents an abstract class, an interface, an array class,
     *                                   a primitive type, or void; or if the class has no nullary constructor;
     *                                   or if the instantiation fails for some other reason.
     * @throws IllegalAccessException    if the constructor is inaccessible.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor()
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // Retrieve all constructors of ProfileMapper
        final Constructor<?>[] constructors = ProfileMapper.class.getDeclaredConstructors();

        // Check that all constructors are 'private'
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        // Call the private constructor
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test case for mapping an empty list of {@link Profile} objects to a list of {@link ProfileDTO}.
     */
    @Test
    void testListDTO_EmptyList() {
        List<Profile> profiles = Collections.emptyList();
        List<ProfileDTO> profilesDTO = ProfileMapper.getProfilesDTO(profiles);
        assertEquals(0, profilesDTO.size());
    }

    /**
     * Test case for mapping a list containing one {@link Profile} object to a list of {@link ProfileDTO}.
     */
    @Test
    void testListDTO_OneProfile() {
        // Create input objects
        ProfileID profileIDInput = new ProfileID("1");
        Description descriptionInput = new Description("user");

        // Create a single profile object
        Profile profile = new Profile(profileIDInput, descriptionInput);
        List<Profile> profiles = Collections.singletonList(profile);

        // Map profiles to DTOs
        List<ProfileDTO> profilesDTO = ProfileMapper.getProfilesDTO(profiles);
        assertEquals(1, profilesDTO.size());

        // Validate the mapped DTO
        ProfileDTO profileDTO = profilesDTO.get(0);
        assertEquals(profileIDInput.getProfileID(), profileDTO.getProfileID());
        assertEquals(descriptionInput.getDescription(), profileDTO.getDescription());
    }

    /**
     * Test case for mapping a list containing two {@link Profile} objects to a list of {@link ProfileDTO}.
     */
    @Test
    void testListDTO_TwoProfile() {
        // Create input objects
        ProfileID profileIDInput = new ProfileID("1");
        Description descriptionInput = new Description("user1");

        ProfileID profileID2Input = new ProfileID("2");
        Description description2Input = new Description("user2");

        // Create two profile objects
        Profile profile = new Profile(profileIDInput, descriptionInput);
        Profile profile2 = new Profile(profileID2Input, description2Input);

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile);
        profiles.add(profile2);

        // Map profiles to DTOs
        List<ProfileDTO> profilesDTO = ProfileMapper.getProfilesDTO(profiles);
        assertEquals(2, profilesDTO.size());

        // Validate the mapped DTOs
        ProfileDTO profileDTO1 = profilesDTO.get(0);
        assertEquals(profileIDInput.getProfileID(), profileDTO1.getProfileID());
        assertEquals(descriptionInput.getDescription(), profileDTO1.getDescription());

        ProfileDTO profileDTO2 = profilesDTO.get(1);
        assertEquals(profileID2Input.getProfileID(), profileDTO2.getProfileID());
        assertEquals(description2Input.getDescription(), profileDTO2.getDescription());
    }

    /**
     * Test case for mapping a null {@link Profile} object to a {@link ProfileDTO}.
     */
    @Test
    public void testToDTO_NullProfile() {
        assertThrows(NullPointerException.class, () -> {
            ProfileMapper.toDTO(null);
        });
    }

    /**
     * Test case for mapping a valid {@link Profile} object to a {@link ProfileDTO}.
     */
    @Test
    public void testToDTO_ValidProfile() {
        // Arrange
        ProfileID profileIDInput = new ProfileID("1");
        Description descriptionInput = new Description("user1");

        Profile profile = new Profile(profileIDInput, descriptionInput);

        // Act
        ProfileDTO profileDTO = ProfileMapper.toDTO(profile);

        // Assert
        assertEquals(profileIDInput.getProfileID(), profileDTO.getProfileID());
        assertEquals(descriptionInput.getDescription(), profileDTO.getDescription());
    }
}
