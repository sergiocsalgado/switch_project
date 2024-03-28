package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.switch2022.project.datamodel.jpa.ProfileJpa;
import org.switch2022.project.datamodel.jpa.assemblers.ProfileJpaAssembler;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.repository.jpa.interfaces.ProfileJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProfileRepositoryImplTest {

    /**
     * Test class for {@link ProfileRepositoryImpl#save(Profile)} .
     */
    @Test
    void save_shouldSaveAndReturnTheProfileSaved_Success() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        ProfileJpa profileJpaToSave = ProfileJpaAssembler.toDataModel(profile);

        when(jpaRepository.save(profileJpaToSave)).thenReturn(profileJpaToSave);

        // Act
        Profile savedProfile = repository.save(profile);

        // Assert
        assertNotNull(savedProfile);
        assertEquals(profile, savedProfile);
    }

    /**
     * Test class for {@link ProfileRepositoryImpl#findAll()}  .
     */
    @Test
    void findAll_shouldReturnAnEmptyList() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        List<ProfileJpa> jpaProfiles = new ArrayList<>();

        when(jpaRepository.findAll()).thenReturn(jpaProfiles);

        // Act
        List<Profile> profiles = repository.findAll();

        // Assert
        assertNotNull(profiles);
        assertEquals(jpaProfiles.size(), profiles.size());
    }

    @Test
    void findAll_shouldReturnAListWithThreeProfiles() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        ProfileID profileID1 = new ProfileID("P1");
        ProfileID profileID2 = new ProfileID("P2");
        ProfileID profileID3 = new ProfileID("P3");

        Description description1 = new Description("administrator");
        Description description2 = new Description("manager");
        Description description3 = new Description("user");

        ProfileJpa profileJpa1 = new ProfileJpa("P1", "administrator");
        ProfileJpa profileJpa2 = new ProfileJpa("P2", "manager");
        ProfileJpa profileJpa3 = new ProfileJpa("P3", "user");

        List<ProfileJpa> jpaProfiles = List.of(profileJpa1, profileJpa2, profileJpa3);

        when(jpaRepository.findAll()).thenReturn(jpaProfiles);

        Profile profile1 = new Profile(profileID1, description1);
        Profile profile2 = new Profile(profileID2, description2);
        Profile profile3 = new Profile(profileID3, description3);


        List<Profile> expected = List.of(profile1, profile2, profile3);

        // Act
        List<Profile> profiles = repository.findAll();

        // Assert
        assertEquals(expected, profiles);
        assertEquals(expected.size(), profiles.size());
        assertEquals(jpaProfiles.size(), profiles.size());
    }

    /**
     * Test class for {@link ProfileRepositoryImpl#ofIdentity(ProfileID)}  .
     */
    @Test
    void ofIdentity_shouldReturnAValidOptionalObjectOfProfile() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        String profID = "P1";
        String profDescription = "user";

        ProfileID profileID = mock(ProfileID.class);
        when(profileID.getProfileID()).thenReturn(profID);
        Description description = mock(Description.class);
        when(description.getDescription()).thenReturn(profDescription);

        Profile profile = mock(Profile.class);
        when(profile.getProfileID()).thenReturn(profileID);
        when(profile.getDescription()).thenReturn(description);

        ProfileJpa profileJpa = mock(ProfileJpa.class);
        when(profileJpa.getProfileID()).thenReturn(profID);
        when(profileJpa.getDescription()).thenReturn(profDescription);

        when(jpaRepository.findByProfileID(profileID.getProfileID())).thenReturn(Optional.of(profileJpa));

        // Act
        Optional<Profile> optionalProfile = repository.ofIdentity(profileID);

        try (MockedStatic<ProfileJpaAssembler> mapperDouble = Mockito.mockStatic(ProfileJpaAssembler.class)) {
            mapperDouble.when(() -> ProfileJpaAssembler.toDataModel(optionalProfile.get())).thenReturn(profileJpa);

            // Assert
            assertNotNull(optionalProfile);
            assertTrue(optionalProfile.isPresent());
            assertEquals(profileJpa, ProfileJpaAssembler.toDataModel(optionalProfile.get()));
        }
    }

    @Test
    void ofIdentity_shouldReturnAnEmptyOptionalObjectOfProfile() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        ProfileID profileID = mock(ProfileID.class);

        when(jpaRepository.findByProfileID(profileID.getProfileID())).thenReturn(Optional.empty());

        // Act
        Optional<Profile> optionalProfile = repository.ofIdentity(profileID);

        // Assert
        assertNotNull(optionalProfile);
        assertFalse(optionalProfile.isPresent());
    }

    /**
     * Test class for {@link ProfileRepositoryImpl#containsOfIdentity(ProfileID)}  .
     */
    @Test
    void containsOfIdentity_shouldReturnTrueWhenExistsAProfileWithTheGivenProfileID() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        ProfileID profileID = mock(ProfileID.class);

        when(jpaRepository.existsByProfileID(profileID.getProfileID())).thenReturn(true);

        // Act
        boolean containsIdentity = repository.containsOfIdentity(profileID);

        // Assert
        assertTrue(containsIdentity);
    }

    /**
     * Test class for {@link ProfileRepositoryImpl#containsOfIdentity(ProfileID)}  .
     */
    @Test
    void containsOfIdentity_shouldReturnFalseWhenDoesNotExistsAProfileWithTheGivenProfileID() {
        // Arrange
        ProfileJpaRepository jpaRepository = Mockito.mock(ProfileJpaRepository.class);
        ProfileRepositoryImpl repository = new ProfileRepositoryImpl(jpaRepository);

        ProfileID profileID = mock(ProfileID.class);

        when(jpaRepository.existsByProfileID(profileID.getProfileID())).thenReturn(false);

        // Act
        boolean containsIdentity = repository.containsOfIdentity(profileID);

        // Assert
        assertFalse(containsIdentity);
    }
}
