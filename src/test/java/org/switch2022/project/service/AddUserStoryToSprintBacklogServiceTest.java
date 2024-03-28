package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.FactorySprint;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the AddUserStoryToSprintBacklogServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AddUserStoryToSprintBacklogServiceImpl.class)
class AddUserStoryToSprintBacklogServiceTest {
    @MockBean
    Sprint sprint;
    @MockBean
    SprintID sprintID;
    @MockBean
    Period period;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    UserStoryID userStoryID;
    @MockBean
    UserStory userStory;

    @MockBean
    FactorySprint factorySprint;
    @MockBean
    @Qualifier("sprintJPARepository")
    Repository<SprintID, Sprint> sprintRepository;
    @MockBean
    @Qualifier("storyJPARepository")
    Repository<UserStoryID, UserStory> userStoryRepository;

    @Autowired
    private AddUserStoryToSprintBacklogServiceImpl serviceUnderTest;

    /**
     * Unit Tests for {@link AddUserStoryToSprintBacklogServiceImpl#addUserStoryToSprintBacklog(UserStoryID, SprintID, ProjectCode)} .
     */
    @Test
    void addUserStoryToSprintBacklog_Success() {
        // ARRANGE
        String sprintStatusDescription = "planned";
        Optional<Sprint> optional = mock(Optional.class);

        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        // Handle Sprint
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprintRepository.ofIdentity(sprintID)).thenReturn(optional);
        when(optional.isPresent()).thenReturn(true);
        when(optional.get()).thenReturn(sprint);

        //is planned
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        when(sprint.addUserStoryToSprintBacklog(userStoryID)).thenReturn(true);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        // ACT
        boolean result = serviceUnderTest.addUserStoryToSprintBacklog(userStoryID, sprintID, projectCode);

        // ASSERT
        assertTrue(result);

    }

    @Test
    void addUserStoryToSprintBacklog_Fail_SprintAlreadyStarted() {
        // ARRANGE
        String sprintStatusDescription = "open";
        Optional<Sprint> optional = mock(Optional.class);

        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(optional);
        when(optional.isPresent()).thenReturn(true);
        when(optional.get()).thenReturn(sprint);

        //is planned
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        // ACT
        boolean result = serviceUnderTest.addUserStoryToSprintBacklog(userStoryID, sprintID, projectCode);

        // ASSERT
        assertFalse(result);
    }

    @Test
    void addUserStoryToSprintBacklog_Fail_SprintDoesntExists() {
        // ARRANGE
        Optional<Sprint> optionalSprint = mock(Optional.class);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.empty());
        when(optionalSprint.isPresent()).thenReturn(false);

        // ACT
        boolean result = serviceUnderTest.addUserStoryToSprintBacklog(userStoryID, sprintID, projectCode);

        // ASSERT
        assertFalse(result);
    }

    @Test
    void addUserStoryToSprintBacklog_Fail_UserStoryAlreadyInSprintBacklog() {
        // ARRANGE
        String sprintStatusDescription = "planned";
        Optional<Sprint> optionalSprint = mock(Optional.class);

        SprintStatus sprintStatus = new SprintStatus(sprintStatusDescription);

        // Handle Sprint and UserStory
        when(sprintRepository.ofIdentity(sprintID)).thenReturn(optionalSprint);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);

        //is planned
        when(sprintRepository.findAll()).thenReturn(List.of(sprint));
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintStatus()).thenReturn(sprintStatus);

        when(sprint.addUserStoryToSprintBacklog(userStoryID)).thenReturn(false);

        // ACT
        boolean result = serviceUnderTest.addUserStoryToSprintBacklog(userStoryID, sprintID, projectCode);

        // ASSERT
        assertFalse(result);
    }
}
