package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.ProfileDTO;
import org.switch2022.project.mapper.ProfileMapper;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.service.interfaces.CreateProfileService;
import org.switch2022.project.service.interfaces.ListAllProfilesService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 The ProfileControllerTest class is a test class for the ProfileController class.
 It is responsible for testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProfileController.class)
class ProfileControllerTest {
    @MockBean
    ProfileID mockProfileID;
    @MockBean
    Description mockDescription;
    @MockBean
    Profile mockProfile;
    @Autowired
    ProfileController controllerUnderTest;
    @MockBean
    private CreateProfileService mockCreateProfileService;
    @MockBean
    private ListAllProfilesService mockListAllProfilesService;

    /**
     Unit Tests class for the {@link ProfileController#createProfile(ProfileDTO)} method.
     Tests the successful creation of a profile.
     */
    @Test
    public void testCreateProfile_Success() {
        // ARRANGE
        String descriptionInput = "user";
        String profileIDInput = "1";
        when(mockProfile.getProfileID()).thenReturn(mockProfileID);
        when(mockProfile.getDescription()).thenReturn(mockDescription);
        when(mockProfileID.getProfileID()).thenReturn(profileIDInput);
        when(mockDescription.getDescription()).thenReturn(descriptionInput);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileID(profileIDInput);
        profileDTO.setDescription(descriptionInput);

        Profile createdProfile = new Profile(mockProfileID, mockDescription);
        when(mockCreateProfileService.createProfile(profileDTO.getProfileID(), profileDTO.getDescription()))
                .thenReturn(createdProfile);

        // ACT
        ResponseEntity<Object> responseEntity = controllerUnderTest.createProfile(profileDTO);

        // ASSERT
        ProfileDTO profileCreated = ProfileMapper.toDTO(createdProfile);
        assertEquals(profileCreated, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    /**
     Unit Tests class for the {@link ProfileController#createProfile(ProfileDTO)} method.
     Tests the failure case when a profile cannot be created because it already exists.
     */
    @Test
    public void testCreateProfile_Fail() {
        // ARRANGE
        String descriptionInput = "user";
        String profileIDInput = "1";
        when(mockProfile.getProfileID()).thenReturn(mockProfileID);
        when(mockProfile.getDescription()).thenReturn(mockDescription);
        when(mockProfileID.getProfileID()).thenReturn(profileIDInput);
        when(mockDescription.getDescription()).thenReturn(descriptionInput);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileID(profileIDInput);
        profileDTO.setDescription(descriptionInput);
        when(mockCreateProfileService.createProfile(profileDTO.getProfileID(), profileDTO.getDescription()))
                .thenThrow(new IllegalArgumentException("Could not create Profile, this profile already exists."));

        // ACT
        ResponseEntity<Object> responseEntity = controllerUnderTest.createProfile(profileDTO);

        // ASSERT
        assertEquals("Could not create Profile, this profile already exists.", responseEntity.getBody());
    }

    /**
     Unit Tests class for the {@link ProfileController#createProfile(ProfileDTO)} method.
     Tests the failure case when a profile cannot be created due to invalid input parameters.
     */
    @Test
    public void testCreateProfile_Fail_2() {
        // ARRANGE
        String descriptionInput = "user";
        String profileIDInput = "1";
        when(mockProfile.getProfileID()).thenReturn(mockProfileID);
        when(mockProfile.getDescription()).thenReturn(mockDescription);
        when(mockProfileID.getProfileID()).thenReturn(profileIDInput);
        when(mockDescription.getDescription()).thenReturn(descriptionInput);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileID(profileIDInput);
        profileDTO.setDescription(descriptionInput);
        when(mockCreateProfileService.createProfile(profileDTO.getProfileID(), profileDTO.getDescription()))
                .thenThrow(new IllegalArgumentException("Invalid input parameters"));

        // ACT
        ResponseEntity<Object> responseEntity = controllerUnderTest.createProfile(profileDTO);

        // ASSERT
        assertEquals("Invalid input parameters", responseEntity.getBody());
    }

    /**
     Unit Tests class for the {@link ProfileController#getProfiles()} method.
     Tests the case where there are no profiles in the repository, expecting an empty list.
     */
    @Test
    public void getProfiles_ensureReturnAnEmptyListWhenThereAreNotProfilesInRepository() {
        // ARRANGE
        List<ProfileDTO> profilesDTO = List.of();
        when(mockListAllProfilesService.getProfiles()).thenReturn(profilesDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(profilesDTO, HttpStatus.OK);

        // ACT
        ResponseEntity<Object> result = controllerUnderTest.getProfiles();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     Unit Tests class for the {@link ProfileController#getProfiles()} method.
     Tests the case where there is only one profile in the repository, expecting a list with one profile.
     */
    @Test
    public void getProfiles_ensureReturnAListWithOneProfileWhenThereAreOnlyOneProfileInRepository() {
        // ARRANGE
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        List<ProfileDTO> profilesDTO = List.of(profileDTO);
        when(mockListAllProfilesService.getProfiles()).thenReturn(profilesDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(profilesDTO, HttpStatus.OK);

        // ACT
        ResponseEntity<Object> result = controllerUnderTest.getProfiles();

        // ASSERT
        assertEquals(expected, result);
    }
}



