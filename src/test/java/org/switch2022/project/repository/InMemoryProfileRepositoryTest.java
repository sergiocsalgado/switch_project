package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.ProfileID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryProfileRepository.class)
class InMemoryProfileRepositoryTest {
    @MockBean
    Profile profile;
    @MockBean
    ProfileID profileID;
    @InjectMocks
    private InMemoryProfileRepository inMemoryProfileRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for {@link InMemoryProfileRepository#save(Profile)}.
     */
    @Test
    void shouldSaveProfile() {
        //ARRANGE
        when(profile.identity()).thenReturn(profileID);
        //ACT
        Profile profileSaved = inMemoryProfileRepository.save(profile);

        //ASSERT
        assertEquals(profile, profileSaved);
        assertEquals(profile.identity(), profileSaved.identity());
    }

    /**
     * Test for {@link InMemoryProfileRepository#findAll()}.
     */
    @Test
    void shouldReturnEmptyList() {
        //ARRANGE
        List<Profile> profileListEmpty = new ArrayList<>();

        //ACT
        List<Profile> profiles = inMemoryProfileRepository.findAll();

        //ASSERT
        assertEquals(profileListEmpty, profiles);
    }

    /**
     * Test for {@link InMemoryProfileRepository#findAll()}.
     */
    @Test
    void shouldReturnTwoProfiles() {
        //ARRANGE
        when(profile.identity()).thenReturn(profileID);
        Profile profileSaved = inMemoryProfileRepository.save(profile);
        List<Profile> profileList = Arrays.asList(profileSaved);

        //ACT
        List<Profile> profiles = inMemoryProfileRepository.findAll();

        //ASSERT
        assertEquals(profileList, profiles);
    }

    /**
     * Test for {@link InMemoryProfileRepository#ofIdentity(ProfileID)}.
     */

    @Test
    void ofIdentity_Success() {
        //ARRANGE
        when(profile.identity()).thenReturn(profileID);
        inMemoryProfileRepository.save(profile);
        Optional<Profile> exp = Optional.of(profile);

        //ACT
        Optional<Profile> res = inMemoryProfileRepository.ofIdentity(profileID);

        //ASSERT
        assertEquals(exp, res);
    }

    /**
     * Test for {@link InMemoryProfileRepository#ofIdentity(ProfileID)}.
     */
    @Test
    void ofIdentity_Fail() {
        //ARRANGE
        when(profile.identity()).thenReturn(profileID);
        inMemoryProfileRepository.save(profile);

        Optional<Profile> exp = Optional.empty();

        ProfileID wrongProfileID = mock(ProfileID.class);

        //ACT
        Optional<Profile> res = inMemoryProfileRepository.ofIdentity(wrongProfileID);

        //ASSERT
        assertEquals(exp, res);
    }

    /**
     * Test for {@link InMemoryProfileRepository#containsOfIdentity(ProfileID)}.
     */
    @Test
    void containsOfIdentity_Success() {
        //ARRANGE
        when(profile.identity()).thenReturn(profileID);
        inMemoryProfileRepository.save(profile);

        //ACT
        boolean res = inMemoryProfileRepository.containsOfIdentity(profileID);

        //ASSERT
        assertTrue(res);
    }

    /**
     * Test for {@link InMemoryProfileRepository#containsOfIdentity(ProfileID)}.
     */

    @Test
    void containsOfIdentity_Fail() {
        //ARRANGE
        when(profile.identity()).thenReturn(profileID);
        inMemoryProfileRepository.save(profile);

        ProfileID wrongProfileID = mock(ProfileID.class);

        //ACT
        boolean res = inMemoryProfileRepository.containsOfIdentity(wrongProfileID);

        //ASSERT
        assertFalse(res);
    }
}