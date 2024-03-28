package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link UserStoryController}.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UserStoryController.class)
class UserStoryControllerTest {

    @MockBean
    UserStory userStory;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    UserStoryID userStoryID;
    @MockBean
    UserStoryNumber userStoryNumber;
    @MockBean
    Name actor;
    @MockBean
    Description userStoryText;
    @MockBean
    UserStoryStatus userStoryStatus;
    @MockBean
    Priority priority;
    @Autowired
    UserStoryController controllerUnderTest;
    @MockBean
    private CreateUserStoryService userStoryService;
    @MockBean
    private ListUserStoriesService listUserStoriesService;
    @MockBean
    private AddUserStoryToSprintBacklogService addUserStoryToSprintBacklogService;
    @MockBean
    private ViewScrumBoardService viewScrumBoardService;
    @MockBean
    private UpdateUserStoryStatusService updateUserStoryStatusService;
    @MockBean
    private UpdateProductBacklogService updateProductBacklogService;

    /**
     * Unit Test for {@link UserStoryController#createUserStoryAndAddToProductBacklog(String, UserStoryDTO)}.
     */
    @Test
    void ensureUserStoryIsAdded() {
        //Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStory.getActor()).thenReturn(actor);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getUserStoryText()).thenReturn(userStoryText);
        when(userStory.getPriority()).thenReturn(priority);

        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryID.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryNumber.getNumber()).thenReturn(userStoryNumberInput);
        when(actor.getValue()).thenReturn(actorInput);
        when(userStoryStatus.getDescription()).thenReturn(userStoryStatusInput);
        when(userStoryText.getDescription()).thenReturn(userStoryTextInput);
        when(priority.getIndex()).thenReturn(priorityInput);

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setProjectCode(projectCodeInput);
        userStoryDTO.setUserStoryID(userStoryIDInput);
        userStoryDTO.setUserStoryNumber(userStoryNumberInput);
        userStoryDTO.setActor(actorInput);
        userStoryDTO.setStatus(userStoryStatusInput);
        userStoryDTO.setUserStoryText(userStoryTextInput);
        userStoryDTO.setPriority(priorityInput);

        when(userStoryService.createUserStory(projectCodeInput, userStoryIDInput,
                userStoryNumberInput, actorInput, userStoryTextInput, priorityInput)).thenReturn(userStory);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoryDTO, HttpStatus.CREATED);

        //ACT
        ResponseEntity<Object> result = controllerUnderTest.createUserStoryAndAddToProductBacklog(
                projectCodeInput, userStoryDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureUserStoryIsNotAdded_WrongParameters() {
        //Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStory.getActor()).thenReturn(actor);
        when(userStory.getUserStoryText()).thenReturn(userStoryText);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getPriority()).thenReturn(priority);

        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryID.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryNumber.getNumber()).thenReturn(userStoryNumberInput);
        when(actor.getValue()).thenReturn(actorInput);
        when(userStoryText.getDescription()).thenReturn(userStoryTextInput);
        when(userStoryStatus.getDescription()).thenReturn(userStoryStatusInput);
        when(priority.getIndex()).thenReturn(priorityInput);

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setProjectCode(projectCodeInput);
        userStoryDTO.setUserStoryID(userStoryIDInput);
        userStoryDTO.setUserStoryNumber(userStoryNumberInput);
        userStoryDTO.setActor(actorInput);
        userStoryDTO.setUserStoryText(userStoryTextInput);
        userStoryDTO.setStatus(userStoryStatusInput);
        userStoryDTO.setPriority(priorityInput);

        IllegalArgumentException exception = new IllegalArgumentException("Invalid input parameters to create UserStory.");
        when(userStoryService.createUserStory(projectCodeInput, userStoryIDInput,
                userStoryNumberInput, actorInput, userStoryTextInput, priorityInput)).thenThrow(exception);

        String expected = exception.getMessage();

        //ACT
        ResponseEntity<Object> result = controllerUnderTest.createUserStoryAndAddToProductBacklog(
                projectCodeInput, userStoryDTO);

        //Assert
        assertEquals(expected, result.getBody());
    }

    @Test
    void ensureUserStoryIsNotAdded_ProjectDontExist() {
        //Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStory.getActor()).thenReturn(actor);
        when(userStory.getUserStoryText()).thenReturn(userStoryText);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getPriority()).thenReturn(priority);

        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryID.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryNumber.getNumber()).thenReturn(userStoryNumberInput);
        when(actor.getValue()).thenReturn(actorInput);
        when(userStoryText.getDescription()).thenReturn(userStoryTextInput);
        when(userStoryStatus.getDescription()).thenReturn(userStoryStatusInput);
        when(priority.getIndex()).thenReturn(priorityInput);

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setProjectCode(projectCodeInput);
        userStoryDTO.setUserStoryID(userStoryIDInput);
        userStoryDTO.setUserStoryNumber(userStoryNumberInput);
        userStoryDTO.setActor(actorInput);
        userStoryDTO.setUserStoryText(userStoryTextInput);
        userStoryDTO.setStatus(userStoryStatusInput);
        userStoryDTO.setPriority(priorityInput);

        IllegalStateException exception = new IllegalStateException("ProjectCode does not exist!");
        when(userStoryService.createUserStory(projectCodeInput, userStoryIDInput,
                userStoryNumberInput, actorInput, userStoryTextInput, priorityInput)).thenThrow(exception);

        String expected = exception.getMessage();

        //ACT
        ResponseEntity<Object> result = controllerUnderTest.createUserStoryAndAddToProductBacklog(
                projectCodeInput, userStoryDTO);

        //Assert
        assertEquals(expected, result.getBody());
    }

    /**
     * Unit Tests (with Mockito framework) for {@link UserStoryController#getProductBacklogDTO(String)}.
     */

    @Test
    void mock_ensureUserStoriesAreListedCorrectly_Success() {

        String projectCodeGiven = "PRJ1";

        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);

        List<UserStory> userStories = List.of(us1, us2);
        when(listUserStoriesService.getUserStories(projectCodeGiven)).thenReturn(userStories);

        UserStoryDTO usDTO1 = mock(UserStoryDTO.class);
        UserStoryDTO usDTO2 = mock(UserStoryDTO.class);

        List<UserStoryDTO> userStoryDTOS = List.of(usDTO1, usDTO2);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoryDTOS, HttpStatus.OK);

        try (MockedStatic<UserStoryMapper> mapper = Mockito.mockStatic(UserStoryMapper.class)) {
            mapper.when(() -> UserStoryMapper.getUserStoriesDTO(userStories)).thenReturn(userStoryDTOS);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.getProductBacklogDTO(projectCodeGiven);

            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void mock_ensureThrowsExceptionInvalidProject() {

        String projectCodeGiven = "PPR1";

        IllegalArgumentException exception =
                new IllegalArgumentException("ProjectCode must start with PRJ followed by a number");

        when(listUserStoriesService.getUserStories(projectCodeGiven)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.getProductBacklogDTO(projectCodeGiven);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }

    /**
     * This next tests are for the addUSFromProductBacklogToSprintBacklog method
     * which is responsible for testing the behavior using Spring Boot mock injections.
     */
    @Test
    public void testAddUSFromProductBacklogToSprintBacklog_withValidInputs_returnsTrue() {
        //Arrange
        String projectCodes = "PRJ1";
        String sprintIDs = "SPR1";
        String userStoryIDs = "US1";

        UserStoryID userStoryID = new UserStoryID(userStoryIDs);
        SprintID sprintID = new SprintID(sprintIDs);
        ProjectCode projectCode = new ProjectCode(projectCodes);

        when(addUserStoryToSprintBacklogService.addUserStoryToSprintBacklog(userStoryID, sprintID, projectCode)).thenReturn(true);

        ResponseEntity<Object> expected = new ResponseEntity<>(true, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controllerUnderTest.addUserStoryFromProductBacklogToSprintBacklog(projectCodes, sprintIDs, userStoryIDs);
        //Assert
        assertEquals(expected, result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(true, result.getBody());
    }

    @Test
    public void testAddUSFromProductBacklogToSprintBacklog_withInvalidUserStoryID_returnsFalse() {
        //Arrange
        String projectCodes = "";
        String sprintIDs = "";
        String userStoryIDs = "";

        IllegalArgumentException exception = new IllegalArgumentException("User Story ID cannot be empty");
        when(addUserStoryToSprintBacklogService.addUserStoryToSprintBacklog(null, null, null)).thenThrow(exception);

        ResponseEntity<Object> expected = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.addUserStoryFromProductBacklogToSprintBacklog(projectCodes, sprintIDs, userStoryIDs);

        //Assert
        assertEquals(expected, result);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals(exception.getMessage(), result.getBody());
    }

    /**
     * This next unit tests (with Mockito framework) are for the viewScrumBoard
     * method, which is responsible for obtaining the Scrum Board of the ongoing
     * Sprint of a given Project.
     */
    @Test
    void viewScrumBoard_UnitTest_ensureScrumBoardViewIsASortedByStatusDescriptionListOfUserStoriesDTO_Unit() {
        //Arrange
        String project = "PRJ1";
        when(projectCode.getProjectCode()).thenReturn(project);

        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);

        List<UserStory> scrumBoard = List.of(userStoryDouble);
        when(viewScrumBoardService.getScrumBoard(project)).thenReturn(scrumBoard);

        List<UserStoryDTO> userStoriesDTO = List.of(userStoryDTODouble);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoriesDTO, HttpStatus.OK);

        try (MockedStatic<UserStoryMapper> mapperDouble = Mockito.mockStatic(UserStoryMapper.class)) {
            mapperDouble.when(() -> UserStoryMapper.getUserStoriesDTO(scrumBoard)).thenReturn(userStoriesDTO);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.viewScrumBoard(project);

            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void viewScrumBoard_UnitTest_ensureThrowsExceptionWhenInvalidProjectCode() {
        //Arrange
        String project = "";

        IllegalArgumentException exception = new IllegalArgumentException("Invalid project code.");

        when(viewScrumBoardService.getScrumBoard(project)).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.viewScrumBoard(project);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void viewScrumBoard_UnitTest_ensureThrowsExceptionWhenProjectNotFound() {
        //Arrange
        String project = "PRJ1";

        IllegalStateException exception = new IllegalStateException("Project does not exists.");

        when(viewScrumBoardService.getScrumBoard(project)).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.viewScrumBoard(project);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void viewScrumBoard_UnitTest_ensureThrowsExceptionWhenOngoingSprintNotFound() {
        //Arrange
        String project = "PRJ1";

        IllegalStateException exception = new IllegalStateException("There is no sprint going on.");

        when(viewScrumBoardService.getScrumBoard(project)).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.viewScrumBoard(project);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    /**
     * Unit Test for {@link UserStoryController#updateUserStoryStatus(UserStoryDTO)} .
     */
    @Test
    void updateUserStoryStatus_ensureTheUserStoryStatusIsChanged_Success() {
        //Arrange
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);

        when(updateUserStoryStatusService.updateUserStoryStatus(userStoryDTODouble)).thenReturn(userStory);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoryDTODouble, HttpStatus.OK);

        try (MockedStatic<UserStoryMapper> mapperDouble = Mockito.mockStatic(UserStoryMapper.class)) {
            mapperDouble.when(() -> UserStoryMapper.toDTO(userStory)).thenReturn(userStoryDTODouble);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.updateUserStoryStatus(userStoryDTODouble);

            //Assert
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(expected, result);
        }
    }

    @Test
    void updateUserStoryStatus_ensureTheUserStoryStatusIsNotChangedWhenProjectDoesNotExists() {
        //Arrange
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);

        IllegalStateException exception = new IllegalStateException("Project does not exist.");
        when(updateUserStoryStatusService.updateUserStoryStatus(userStoryDTODouble)).thenThrow(exception);

        ResponseEntity<Object> expected = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.updateUserStoryStatus(userStoryDTODouble);

        //Assert
        assertEquals(expected, result);
        assertEquals(404, result.getStatusCodeValue());
        assertEquals(exception.getMessage(), result.getBody());
    }

    @Test
    void updateUserStoryStatus_ensureTheUserStoryStatusIsNotChangedWhenUserStoryDoesNotExists() {
        //Arrange
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);

        IllegalStateException exception = new IllegalStateException("User Story does not exist.");
        when(updateUserStoryStatusService.updateUserStoryStatus(userStoryDTODouble)).thenThrow(exception);

        ResponseEntity<Object> expected = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.updateUserStoryStatus(userStoryDTODouble);

        //Assert
        assertEquals(expected, result);
        assertEquals(404, result.getStatusCodeValue());
        assertEquals(exception.getMessage(), result.getBody());
    }

    @Test
    void updateUserStoryStatus_ensureTheUserStoryStatusIsNotChangedWhenInvalidParametersGiven() {
        //Arrange
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);

        IllegalArgumentException exception = new IllegalArgumentException("Project code can not be empty");
        when(updateUserStoryStatusService.updateUserStoryStatus(userStoryDTODouble)).thenThrow(exception);

        ResponseEntity<Object> expected = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.updateUserStoryStatus(userStoryDTODouble);

        //Assert
        assertEquals(expected, result);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals(exception.getMessage(), result.getBody());
    }

    @Test
    void ensureUpdateProductBacklog() {
        //Arrange
        String projCode = "PRJ1";

        List<UserStory> userStories = Collections.singletonList(userStory);

        when(updateProductBacklogService.updateProductBacklog(projCode)).thenReturn(userStories);

        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);
        List<UserStoryDTO> userStoryDTOS = Collections.singletonList(userStoryDTO);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoryDTOS, HttpStatus.OK);

        try (MockedStatic<UserStoryMapper> mapperDouble = Mockito.mockStatic(UserStoryMapper.class)) {
            mapperDouble.when(() -> UserStoryMapper.getUserStoriesDTO(userStories)).thenReturn(userStoryDTOS);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.updateProductBacklog(projCode);

            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureDoNotUpdateProductBacklog_badRequest() {
        //Arrange
        String projCode = "";

        IllegalArgumentException exception = new IllegalArgumentException("Project code cannot be empty");

        when(updateProductBacklogService.updateProductBacklog(projCode)).thenThrow(exception);

        ResponseEntity<Object> expected = new ResponseEntity<>("Project code cannot be empty", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.updateProductBacklog(projCode);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureDoNotUpdateProductBacklog_projectNotFound() {
        //Arrange
        String projCode = "PRJ3";

        IllegalStateException exception = new IllegalStateException("Project does not exist");

        when(updateProductBacklogService.updateProductBacklog(projCode)).thenThrow(exception);

        ResponseEntity<Object> expected = new ResponseEntity<>("Project does not exist", HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.updateProductBacklog(projCode);

        //Assert
        assertEquals(expected, result);
    }
}

