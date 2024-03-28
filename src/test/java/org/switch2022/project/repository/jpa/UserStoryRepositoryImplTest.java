package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.switch2022.project.datamodel.jpa.UserStoryJpa;
import org.switch2022.project.datamodel.jpa.assemblers.UserStoryJpaAssembler;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.repository.jpa.interfaces.UserStoryJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryRepositoryImplTest {

    /**
     * Test class for {@link UserStoryRepositoryImpl#save(UserStory)}  .
     */
    @Test
    void save_shouldSaveAndReturnTheUserStorySavedWhenUserStoryDoesNotExists_Success() {
        // Arrange
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repositoryUnderTest = new UserStoryRepositoryImpl(jpaRepository);

        String userStoryIDValue = "US01";
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        Name actor = mock(Name.class);
        Description userStoryText = mock(Description.class);
        UserStoryStatus status = mock(UserStoryStatus.class);
        Priority priority = mock(Priority.class);

        UserStory userStoryExpected = mock(UserStory.class);
        when(userStoryExpected.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryExpected.getProjectCode()).thenReturn(projectCode);
        when(userStoryExpected.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStoryExpected.getActor()).thenReturn(actor);
        when(userStoryExpected.getUserStoryText()).thenReturn(userStoryText);
        when(userStoryExpected.getStatus()).thenReturn(status);
        when(userStoryExpected.getPriority()).thenReturn(priority);

        when(userStoryID.getUserStoryID()).thenReturn(userStoryIDValue);

        Optional<UserStoryJpa> optionalUserStory = mock(Optional.class);
        when(optionalUserStory.isPresent()).thenReturn(false);
        when(jpaRepository.findByUserStoryID(userStoryID.getUserStoryID())).thenReturn(optionalUserStory);

        UserStoryJpa userStoryJpaToSave = UserStoryJpaAssembler.toDataModel(userStoryExpected);

        try (MockedStatic<UserStoryJpaAssembler> assemblerDouble = Mockito.mockStatic(UserStoryJpaAssembler.class)) {
            assemblerDouble.when(() -> UserStoryJpaAssembler.toDataModel(userStoryExpected)).thenReturn(userStoryJpaToSave);
            assemblerDouble.when(() -> UserStoryJpaAssembler.toDomain(userStoryJpaToSave)).thenReturn(userStoryExpected);

            when(jpaRepository.save(userStoryJpaToSave)).thenReturn(userStoryJpaToSave);

            //Act
            UserStory result = repositoryUnderTest.save(userStoryExpected);

            //Assert
            assertEquals(userStoryExpected, result);
            assertEquals(userStoryExpected.getStatus().getDescription(), userStoryJpaToSave.getStatus());

        }
    }

    @Test
    void save_shouldUpdateSaveAndReturnTheUserStoryWhenExists_Success() {
        // Arrange
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repositoryUnderTest = new UserStoryRepositoryImpl(jpaRepository);

        String userStoryIDValue = "US01";
        String statusValue = "running";
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        Name actor = mock(Name.class);
        Description userStoryText = mock(Description.class);
        UserStoryStatus status = mock(UserStoryStatus.class);
        Priority priority = mock(Priority.class);

        UserStory userStoryExpected = mock(UserStory.class);
        when(userStoryExpected.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryExpected.getProjectCode()).thenReturn(projectCode);
        when(userStoryExpected.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStoryExpected.getActor()).thenReturn(actor);
        when(userStoryExpected.getUserStoryText()).thenReturn(userStoryText);
        when(userStoryExpected.getStatus()).thenReturn(status);
        when(userStoryExpected.getPriority()).thenReturn(priority);

        when(userStoryID.getUserStoryID()).thenReturn(userStoryIDValue);
        when(userStoryExpected.getStatus()).thenReturn(status);
        when(status.getDescription()).thenReturn(statusValue);

        UserStoryJpa userStoryJpa = mock(UserStoryJpa.class);

        Optional<UserStoryJpa> optionalUserStory = mock(Optional.class);
        when(optionalUserStory.isPresent()).thenReturn(true);
        when(optionalUserStory.get()).thenReturn(userStoryJpa);
        when(jpaRepository.findByUserStoryID(userStoryID.getUserStoryID())).thenReturn(optionalUserStory);

        try (MockedStatic<UserStoryJpaAssembler> assemblerDouble = Mockito.mockStatic(UserStoryJpaAssembler.class)) {
            assemblerDouble.when(() -> UserStoryJpaAssembler.toDomain(userStoryJpa)).thenReturn(userStoryExpected);

            when(jpaRepository.save(userStoryJpa)).thenReturn(userStoryJpa);

            //Act
            UserStory result = repositoryUnderTest.save(userStoryExpected);

            //Assert
            assertEquals(userStoryExpected, result);
        }
    }

    /**
     * Test class for {@link UserStoryRepositoryImpl#findAll()}  .
     */
    @Test
    void findAll_shouldReturnAnEmptyList() {
        // Arrange
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repository = new UserStoryRepositoryImpl(jpaRepository);

        List<UserStoryJpa> jpaUserStories = new ArrayList<>();

        when(jpaRepository.findAll()).thenReturn(jpaUserStories);

        // Act
        List<UserStory> userStories = repository.findAll();

        // Assert
        assertNotNull(userStories);
        assertEquals(jpaUserStories.size(), userStories.size());
    }

    @Test
    void findAll_shouldReturnAListWithOneUserStory() {
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repository = new UserStoryRepositoryImpl(jpaRepository);

        String userStoryIDInput = "US1";
        String projectCodeInput = "PRJ1";
        String userStoryNumberInput = "1";
        String actorInput = "actor";
        String userStoryTextInput = "text";
        String statusInput = "planned";
        int priorityInput = 1;

        UserStoryJpa userStoryJpa = new UserStoryJpa(
                userStoryIDInput,
                projectCodeInput,
                userStoryNumberInput,
                actorInput,
                userStoryTextInput,
                statusInput,
                priorityInput
        );

        List<UserStoryJpa> jpaUserStories = List.of(userStoryJpa);

        when(jpaRepository.findAll()).thenReturn(jpaUserStories);

        UserStory userStory = new UserStory(
                new UserStoryID(userStoryIDInput),
                new ProjectCode(projectCodeInput),
                new UserStoryNumber(userStoryNumberInput),
                new Name(actorInput),
                new Description(userStoryTextInput),
                new UserStoryStatus(statusInput),
                new Priority(priorityInput)
        );

        List<UserStory> expected = List.of(userStory);

        // Act
        List<UserStory> userStories = repository.findAll();

        // Assert
        assertEquals(expected, userStories);
        assertEquals(expected.size(), userStories.size());
        assertEquals(jpaUserStories.size(), userStories.size());
    }

    /**
     * Test class for {@link UserStoryRepositoryImpl#ofIdentity(UserStoryID)}  .
     */
    @Test
    void ofIdentity_shouldReturnAValidOptionalObjectOfUserStory() {
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repository = new UserStoryRepositoryImpl(jpaRepository);

        String userStoryIDInput = "US1";
        String projectCodeInput = "PRJ1";
        String userStoryNumberInput = "1";
        String actorInput = "actor";
        String userStoryTextInput = "text";
        String statusInput = "planned";
        int priorityInput = 1;

        UserStoryJpa userStoryJpa = new UserStoryJpa(
                userStoryIDInput,
                projectCodeInput,
                userStoryNumberInput,
                actorInput,
                userStoryTextInput,
                statusInput,
                priorityInput
        );

        List<UserStoryJpa> jpaUserStories = List.of(userStoryJpa);

        when(jpaRepository.findAll()).thenReturn(jpaUserStories);

        UserStoryID userStoryID = mock(UserStoryID.class);

        when(jpaRepository.findByUserStoryID(userStoryID.getUserStoryID())).thenReturn(Optional.of(userStoryJpa));

        // Act
        Optional<UserStory> optionalUserStory = repository.ofIdentity(userStoryID);

        try (MockedStatic<UserStoryJpaAssembler> mapperDouble = Mockito.mockStatic(UserStoryJpaAssembler.class)) {
            mapperDouble.when(() -> UserStoryJpaAssembler.toDataModel(optionalUserStory.get())).thenReturn(userStoryJpa);

            // Assert
            assertNotNull(optionalUserStory);
            assertTrue(optionalUserStory.isPresent());
            assertEquals(userStoryJpa, UserStoryJpaAssembler.toDataModel(optionalUserStory.get()));
        }
    }

    @Test
    void ofIdentity_shouldReturnAnEmptyOptionalObjectOfUserStory() {
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repository = new UserStoryRepositoryImpl(jpaRepository);

        UserStoryID userStoryID = mock(UserStoryID.class);

        when(jpaRepository.findByUserStoryID(userStoryID.getUserStoryID())).thenReturn(Optional.empty());

        // Act
        Optional<UserStory> optionalUserStory = repository.ofIdentity(userStoryID);

        // Assert
        assertNotNull(optionalUserStory);
        assertFalse(optionalUserStory.isPresent());
    }

    /**
     * Test class for {@link UserStoryRepositoryImpl#containsOfIdentity(UserStoryID)} .
     */
    @Test
    void containsOfIdentity_shouldReturnTrueWhenExistsAnUserStoryWithTheGivenUserStoryID() {
        // Arrange
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repository = new UserStoryRepositoryImpl(jpaRepository);

        UserStoryID userStoryID = mock(UserStoryID.class);

        when(jpaRepository.existsByUserStoryID(userStoryID.getUserStoryID())).thenReturn(true);

        // Act
        boolean containsIdentity = repository.containsOfIdentity(userStoryID);

        // Assert
        assertTrue(containsIdentity);
    }

    @Test
    void containsOfIdentity_shouldReturnFalseWhenDoesNotExistsAnUserStoryWithTheGivenUserStoryID() {
        // Arrange
        UserStoryJpaRepository jpaRepository = Mockito.mock(UserStoryJpaRepository.class);
        UserStoryRepositoryImpl repository = new UserStoryRepositoryImpl(jpaRepository);

        UserStoryID userStoryID = mock(UserStoryID.class);

        when(jpaRepository.existsByUserStoryID(userStoryID.getUserStoryID())).thenReturn(false);

        // Act
        boolean containsIdentity = repository.containsOfIdentity(userStoryID);

        // Assert
        assertFalse(containsIdentity);
    }
}
