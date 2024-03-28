package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProfileDTO;
import org.switch2022.project.mapper.ProfileMapper;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.ProfileID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListAllProfilesServiceServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListAllProfilesServiceImpl.class)
class ListAllProfilesServiceImplTest {

    @MockBean
    @Qualifier("profileJPARepository")
    Repository<ProfileID, Profile> profileRepository;
    @Autowired
    ListAllProfilesServiceImpl serviceUnderTest;

    /**
     * Test class for {@link ListAllProfilesServiceImpl#getProfiles()}  .
     */
    @Test
    void getProfiles_ensureAllTheProfilesAreReturned() {

        Profile profile1 = mock(Profile.class);
        Profile profile2 = mock(Profile.class);

        List<Profile> profiles = List.of(profile1, profile2);
        when(profileRepository.findAll()).thenReturn(profiles);

        ProfileDTO profileDTO1 = mock(ProfileDTO.class);
        ProfileDTO profileDTO2 = mock(ProfileDTO.class);
        List<ProfileDTO> expectedProfileDTOs = List.of(profileDTO1, profileDTO2);

        try (MockedStatic<ProfileMapper> mapperDouble = Mockito.mockStatic(ProfileMapper.class)) {

            mapperDouble.when(() -> ProfileMapper.getProfilesDTO(profiles)).thenReturn(expectedProfileDTOs);

            // Act
            List<ProfileDTO> result = serviceUnderTest.getProfiles();

            // Assert
            assertEquals(expectedProfileDTOs, result);
        }
    }

    @Test
    void getProfiles_ensureAnEmptyListWhenThereAreNoProfilesInRepository() {
        // Arrange
        List<ProfileDTO> expected = List.of();

        // Act
        List<ProfileDTO> profileDTOS = serviceUnderTest.getProfiles();

        // Assert
        assertEquals(expected, profileDTOS);
    }
}
