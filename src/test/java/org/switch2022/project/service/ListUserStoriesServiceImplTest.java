package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListUserStoriesService class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListUserStoriesServiceImpl.class)
class ListUserStoriesServiceImplTest {
    @MockBean
    Project project;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    UserStoryID userStoryID;
    @MockBean
    Name actor;
    @MockBean
    Description userStoryText;
    @MockBean
    UserStoryStatus userStoryStatus;
    @MockBean
    Priority priority;
    @MockBean
    UserStoryNumber userStoryNumber;
    @MockBean
    @Qualifier("storyJPARepository")
    Repository<UserStoryID, UserStory> userStoryRepository;
    @MockBean
    @Qualifier("projectJPARepository")
    Repository<ProjectCode, Project> projectRepository;

    @Autowired
    ListUserStoriesServiceImpl listUserStoriesService;

    /**
     * Test class for {@link ListUserStoriesServiceImpl#getUserStories(String)} .
     */
    @Test
    void unitTest_ensureUserStoriesWithAllTypeOfStatusAreListedCorrectly_Successful() {
        //Arrange
        String projectCodeGiven = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodeGiven);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //create and save user stories
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        UserStoryID userStoryID4 = mock(UserStoryID.class);
        UserStoryID userStoryID5 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);
        UserStoryStatus blocked = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);
        UserStory userStory4 = mock(UserStory.class);
        UserStory userStory5 = mock(UserStory.class);

        Priority priority1 = mock(Priority.class);
        Priority priority2 = mock(Priority.class);
        Priority priority3 = mock(Priority.class);
        Priority priority4 = mock(Priority.class);
        Priority priority5 = mock(Priority.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory4.getUserStoryID()).thenReturn(userStoryID4);
        when(userStory5.getUserStoryID()).thenReturn(userStoryID5);

        when(userStory1.getStatus()).thenReturn(planned);
        when(userStory2.getStatus()).thenReturn(running);
        when(userStory3.getStatus()).thenReturn(planned);
        when(userStory4.getStatus()).thenReturn(finished);
        when(userStory5.getStatus()).thenReturn(blocked);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStory1.getProjectCode()).thenReturn(projectCode);
        when(userStory2.getProjectCode()).thenReturn(projectCode);
        when(userStory3.getProjectCode()).thenReturn(projectCode);
        when(userStory4.getProjectCode()).thenReturn(projectCode);
        when(userStory5.getProjectCode()).thenReturn(projectCode);

        when(priority1.getIndex()).thenReturn(1);
        when(priority2.getIndex()).thenReturn(2);
        when(priority3.getIndex()).thenReturn(3);
        when(priority4.getIndex()).thenReturn(4);
        when(priority5.getIndex()).thenReturn(5);

        when(userStory1.getPriority()).thenReturn(priority1);
        when(userStory2.getPriority()).thenReturn(priority2);
        when(userStory3.getPriority()).thenReturn(priority3);
        when(userStory4.getPriority()).thenReturn(priority4);
        when(userStory5.getPriority()).thenReturn(priority5);

        List<UserStory> userStories = List.of(userStory1, userStory2, userStory3, userStory4, userStory5);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        List<UserStory> expected = List.of(userStory1, userStory3);

        //Act
        List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void unitTest_ensureUserStoriesWithAllTypeOfStatusAreListedCorrectly_FailBecauseWrongPriority() {
        //Arrange
        String projectCodeGiven = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodeGiven);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //create and save user stories
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        UserStoryID userStoryID4 = mock(UserStoryID.class);
        UserStoryID userStoryID5 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);
        UserStoryStatus blocked = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);
        UserStory userStory4 = mock(UserStory.class);
        UserStory userStory5 = mock(UserStory.class);

        Priority priority1 = mock(Priority.class);
        Priority priority2 = mock(Priority.class);
        Priority priority3 = mock(Priority.class);
        Priority priority4 = mock(Priority.class);
        Priority priority5 = mock(Priority.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory4.getUserStoryID()).thenReturn(userStoryID4);
        when(userStory5.getUserStoryID()).thenReturn(userStoryID5);

        when(userStory1.getStatus()).thenReturn(planned);
        when(userStory2.getStatus()).thenReturn(running);
        when(userStory3.getStatus()).thenReturn(planned);
        when(userStory4.getStatus()).thenReturn(finished);
        when(userStory5.getStatus()).thenReturn(blocked);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStory1.getProjectCode()).thenReturn(projectCode);
        when(userStory2.getProjectCode()).thenReturn(projectCode);
        when(userStory3.getProjectCode()).thenReturn(projectCode);
        when(userStory4.getProjectCode()).thenReturn(projectCode);
        when(userStory5.getProjectCode()).thenReturn(projectCode);

        when(priority1.getIndex()).thenReturn(1);
        when(priority2.getIndex()).thenReturn(2);
        when(priority3.getIndex()).thenReturn(3);
        when(priority4.getIndex()).thenReturn(4);
        when(priority5.getIndex()).thenReturn(5);

        when(userStory1.getPriority()).thenReturn(priority1);
        when(userStory2.getPriority()).thenReturn(priority2);
        when(userStory3.getPriority()).thenReturn(priority3);
        when(userStory4.getPriority()).thenReturn(priority4);
        when(userStory5.getPriority()).thenReturn(priority5);

        List<UserStory> userStories = List.of(userStory1, userStory2, userStory3, userStory4, userStory5);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        List<UserStory> expected = List.of(userStory3, userStory1);

        //Act
        List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void unitTest_ensureUserStoriesWithFinishedAndBlockedStatusAreNotListedAndReturnsEmptyList_Successful() {
        //Arrange
        String projectCodeGiven = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodeGiven);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //create and save user stories
        UserStoryID userStoryID4 = mock(UserStoryID.class);
        UserStoryID userStoryID5 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);
        UserStoryStatus blocked = mock(UserStoryStatus.class);

        UserStory userStory4 = mock(UserStory.class);
        UserStory userStory5 = mock(UserStory.class);

        Priority priority4 = mock(Priority.class);
        Priority priority5 = mock(Priority.class);

        when(userStory4.getUserStoryID()).thenReturn(userStoryID4);
        when(userStory5.getUserStoryID()).thenReturn(userStoryID5);

        when(userStory4.getStatus()).thenReturn(finished);
        when(userStory5.getStatus()).thenReturn(blocked);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStory4.getProjectCode()).thenReturn(projectCode);
        when(userStory5.getProjectCode()).thenReturn(projectCode);

        when(priority4.getIndex()).thenReturn(4);
        when(priority5.getIndex()).thenReturn(5);

        when(userStory4.getPriority()).thenReturn(priority4);
        when(userStory5.getPriority()).thenReturn(priority5);

        List<UserStory> userStories = List.of(userStory4, userStory5);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        List<UserStory> expected = new ArrayList<>();

        //Act
        List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void unitTest_ensureThatWithNoUserStoriesReturnsEmptyList_Successful() {
        //Arrange
        String projectCodeGiven = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodeGiven);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        List<UserStory> expected = new ArrayList<>();

        //Act
        List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void unitTest_ensureThatReturnsEmptyListBecauseWrongProjectCode_Successful() {
        //Arrange
        String projectCodeGiven = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodeGiven);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);

        List<UserStory> expected = new ArrayList<>();

        //Act
        List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

        //Assert
        assertEquals(expected, result);
    }
}
