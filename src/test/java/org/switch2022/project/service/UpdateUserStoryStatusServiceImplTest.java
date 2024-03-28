package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.FactoryUserStory;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the UpdateUserStoryStatusImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UpdateUserStoryStatusServiceImpl.class)
class UpdateUserStoryStatusServiceImplTest {

    @MockBean
    UserStory userStory;
    @MockBean
    UserStoryDTO userStoryDTO;

    @MockBean
    @Qualifier("storyJPARepository")
    Repository<UserStoryID, UserStory> userStoryRepository;
    @MockBean
    @Qualifier("projectJPARepository")
    Repository<ProjectCode, Project> projectRepository;
    @MockBean
    FactoryUserStory factoryUserStory;

    @Autowired
    UpdateUserStoryStatusServiceImpl serviceUnderTest;

    /**
     * Test class for {@link UpdateUserStoryStatusServiceImpl#updateUserStoryStatus(UserStoryDTO)} .
     */
    @Test
    void updateUserStoryStatus_ShouldUpdateTheUserStoryStatusWhenProjectAnUserStoryExists() {
        // Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStoryDTO.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryDTO.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryDTO.getUserStoryNumber()).thenReturn(userStoryNumberInput);
        when(userStoryDTO.getActor()).thenReturn(actorInput);
        when(userStoryDTO.getUserStoryText()).thenReturn(userStoryTextInput);
        when(userStoryDTO.getStatus()).thenReturn(userStoryStatusInput);
        when(userStoryDTO.getPriority()).thenReturn(priorityInput);

        ProjectCode projectCode = new ProjectCode(projectCodeInput);
        UserStoryID userStoryID = new UserStoryID(userStoryIDInput);
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryNumberInput);
        Name actor = new Name(actorInput);
        Description userStoryText = new Description(userStoryTextInput);
        UserStoryStatus userStoryStatus = new UserStoryStatus(userStoryStatusInput);
        Priority priority = new Priority(priorityInput);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);
        when(userStoryRepository.containsOfIdentity(userStoryID)).thenReturn(true);

        when(factoryUserStory.createUserStory(userStoryID, projectCode,
                userStoryNumber, actor, userStoryText, userStoryStatus
                , priority)).thenReturn(userStory);

        when(userStoryRepository.save(userStory)).thenReturn(userStory);

        // Act
        UserStory userStoryUpdated = serviceUnderTest.updateUserStoryStatus(userStoryDTO);

        // Assert
        assertEquals(userStory, userStoryUpdated);
    }
    @Test
    void updateUserStoryStatus_shouldThrowIllegalStateExceptionWhenProjectDoesNotExists() {
        // Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStoryDTO.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryDTO.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryDTO.getUserStoryNumber()).thenReturn(userStoryNumberInput);
        when(userStoryDTO.getActor()).thenReturn(actorInput);
        when(userStoryDTO.getUserStoryText()).thenReturn(userStoryTextInput);
        when(userStoryDTO.getStatus()).thenReturn(userStoryStatusInput);
        when(userStoryDTO.getPriority()).thenReturn(priorityInput);

        ProjectCode projectCode = new ProjectCode(projectCodeInput);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.updateUserStoryStatus(userStoryDTO);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalStateException.class);
        assertEquals("Project does not exist.", exception.getMessage());
    }
    @Test
    void updateUserStoryStatus_shouldThrowIllegalStateExceptionWhenUserStoryDoesNotExists() {
        // Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStoryDTO.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryDTO.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryDTO.getUserStoryNumber()).thenReturn(userStoryNumberInput);
        when(userStoryDTO.getActor()).thenReturn(actorInput);
        when(userStoryDTO.getUserStoryText()).thenReturn(userStoryTextInput);
        when(userStoryDTO.getStatus()).thenReturn(userStoryStatusInput);
        when(userStoryDTO.getPriority()).thenReturn(priorityInput);

        ProjectCode projectCode = new ProjectCode(projectCodeInput);
        UserStoryID userStoryID = new UserStoryID(userStoryDTO.getUserStoryID());

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);
        when(userStoryRepository.containsOfIdentity(userStoryID)).thenReturn(false);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.updateUserStoryStatus(userStoryDTO);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalStateException.class);
        assertEquals("User Story does not exist.", exception.getMessage());
    }
    @Test
    void updateUserStoryStatus_shouldThrowIllegalStateExceptionWhenPassedInvalidParameters() {
        // Arrange
        String projectCodeInput = "";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStoryDTO.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryDTO.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryDTO.getUserStoryNumber()).thenReturn(userStoryNumberInput);
        when(userStoryDTO.getActor()).thenReturn(actorInput);
        when(userStoryDTO.getUserStoryText()).thenReturn(userStoryTextInput);
        when(userStoryDTO.getStatus()).thenReturn(userStoryStatusInput);
        when(userStoryDTO.getPriority()).thenReturn(priorityInput);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.updateUserStoryStatus(userStoryDTO);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Project code cannot be empty", exception.getMessage());
    }
}