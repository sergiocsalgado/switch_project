package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ViewScrumBoardServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ViewScrumBoardServiceImpl.class)
class ViewScrumBoardServiceImplTest {
    @MockBean
    ProjectCode projectCode;
    @MockBean
    Project project;
    @MockBean
    Sprint sprint;
    @MockBean
    SprintStatus sprintStatus;

    @MockBean
    @Qualifier("sprintJPARepository")
    private Repository<SprintID, Sprint> sprintRepository;
    @MockBean
    @Qualifier("storyJPARepository")
    private Repository<UserStoryID, UserStory> userStoryRepository;
    @MockBean
    @Qualifier("projectJPARepository")
    private Repository<ProjectCode, Project> projectRepository;

    @Autowired
    private ViewScrumBoardServiceImpl serviceUnderTest;

    /**
     * Unit Tests for {@link ViewScrumBoardServiceImpl#getScrumBoard(String)} .
     */
    @Test
    void getScrumBoard_shouldReturnTheScrumBoardOrderedByUserStoryStatus_Success() {
        //Arrange
        String sprintStatusDescription = "open";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);
        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);

        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        Optional<Sprint> optionalSprint = mock(Optional.class);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);

        //create and save user stories in repository
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);

        when(userStory1.getStatus()).thenReturn(planned);
        when(userStory2.getStatus()).thenReturn(running);
        when(userStory3.getStatus()).thenReturn(finished);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStoryRepository.findAll()).thenReturn(List.of(userStory1, userStory2, userStory3));

        // get scrumBoardList from the ongoing sprint
        List<UserStoryID> scrumBoard = List.of(userStoryID3, userStoryID1, userStoryID2);
        when(sprint.getScrumBoardList()).thenReturn(scrumBoard);

        List<UserStory> expected = List.of(userStory3, userStory1, userStory2);

        //Act
        List<UserStory> result = serviceUnderTest.getScrumBoard(projectCodePassed);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getScrumBoard_shouldFailWhenIsNotOrdered() {
        //Arrange
        String sprintStatusDescription = "open";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        Optional<Sprint> optionalSprint = mock(Optional.class);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);

        //create and save user stories in repository
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);

        when(userStory1.getStatus()).thenReturn(planned);
        when(userStory2.getStatus()).thenReturn(running);
        when(userStory3.getStatus()).thenReturn(finished);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStoryRepository.findAll()).thenReturn(List.of(userStory1, userStory2, userStory3));

        // get scrumBoardList from the ongoing sprint
        List<UserStoryID> scrumBoard = List.of(userStoryID3, userStoryID1, userStoryID2);
        when(sprint.getScrumBoardList()).thenReturn(scrumBoard);

        List<UserStory> expected = List.of(userStory1, userStory2, userStory3);

        //Act
        List<UserStory> result = serviceUnderTest.getScrumBoard(projectCodePassed);

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void getScrumBoard_shouldReturnAnEmptyListWhenDoNotHavePlannedRunningAndFinishedUserStories() {
        //Arrange
        String sprintStatusDescription = "open";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        Optional<Sprint> optionalSprint = mock(Optional.class);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);

        //create and save user stories in repository
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);
        UserStoryStatus blocked = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);

        when(userStory1.getStatus()).thenReturn(blocked);
        when(userStory2.getStatus()).thenReturn(blocked);
        when(userStory3.getStatus()).thenReturn(blocked);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");
        when(blocked.getDescription()).thenReturn("blocked");

        when(userStoryRepository.findAll()).thenReturn(List.of(userStory1, userStory2, userStory3));

        // get scrumBoardList from the ongoing sprint
        List<UserStoryID> scrumBoard = List.of(userStoryID1, userStoryID2, userStoryID3);
        when(sprint.getScrumBoardList()).thenReturn(scrumBoard);

        List<UserStory> expected = Collections.emptyList();

        //Act
        List<UserStory> result = serviceUnderTest.getScrumBoard(projectCodePassed);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getScrumBoard_shouldFailWhenTheProjectCodeIsInvalid() {
        //Arrange
        String projectCodePassed = "";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.getScrumBoard(projectCodePassed);
        });

        //Assert
        assertEquals("Project code cannot be empty", exception.getMessage());
    }

    @Test
    void getScrumBoard_shouldFailWhenIsNotTheCurrentSprint_NotStartedSprint() {
        //Arrange
        String sprintStatusDescription = "planned";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.getScrumBoard(projectCodePassed);
        });

        //Assert
        assertEquals("There is no sprint going on.", exception.getMessage());
    }

    @Test
    void getScrumBoard_shouldFailWhenIsNotTheCurrentSprint_FinishedSprint() {
        //Arrange
        String sprintStatusDescription = "closed";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.getScrumBoard(projectCodePassed);
        });

        //Assert
        assertEquals("There is no sprint going on.", exception.getMessage());
    }

    @Test
    void getScrumBoard_shouldFailWhenThereIsNoSprintsForTheGivenProjectCode() {
        //Arrange
        String sprintStatusDescription = "open";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        ProjectCode projectCodeSprint = new ProjectCode("PRJ2");

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCodeSprint);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.getScrumBoard(projectCodePassed);
        });

        //Assert
        assertEquals("There is no sprint going on.", exception.getMessage());
    }

    @Test
    void getScrumBoard_shouldFailWhenTheProjectCodeDoesNotExists() {
        //Arrange
        String projectCodePassed = "PRJ2";

        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);


        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.getScrumBoard(projectCodePassed);
        });

        //Assert
        assertEquals("Project does not exists.", exception.getMessage());
    }

    @Test
    void getScrumBoard_shouldFailWhenScrumBoardNotContainsUserStoryID_Success() {
        //Arrange
        String sprintStatusDescription = "open";
        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        String projectCodePassed = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodePassed);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //verify if sprint is the ongoing sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        Optional<Sprint> optionalSprint = mock(Optional.class);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);

        //create and save user stories in repository
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);

        when(userStory1.getStatus()).thenReturn(planned);
        when(userStory2.getStatus()).thenReturn(running);
        when(userStory3.getStatus()).thenReturn(finished);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStoryRepository.findAll()).thenReturn(List.of(userStory1, userStory2));

        // get scrumBoardList from the ongoing sprint
        List<UserStoryID> scrumBoard = List.of(userStoryID3);
        when(sprint.getScrumBoardList()).thenReturn(scrumBoard);

        List<UserStory> expected = Collections.emptyList();

        //Act
        List<UserStory> result = serviceUnderTest.getScrumBoard(projectCodePassed);

        //Assert
        assertEquals(expected, result);
    }

}