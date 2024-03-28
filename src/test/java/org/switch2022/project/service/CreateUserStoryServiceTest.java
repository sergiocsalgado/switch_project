package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.FactoryUserStory;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the CreateUserStoryServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateUserStoryServiceImpl.class)
class CreateUserStoryServiceTest {
    @MockBean
    UserStoryNumber userStoryNumber;
    @MockBean
    FactoryUserStory factoryUserStory;
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
    UserStory userStory;
    @MockBean
    Effort effort;

    @MockBean
    @Qualifier("storyJPARepository")
    Repository<UserStoryID, UserStory> userStoryRepository;
    @MockBean
    @Qualifier("projectJPARepository")
    Repository<ProjectCode, Project> projectRepository;

    @Autowired
    private CreateUserStoryServiceImpl userStoryService;

    /**
     * Unit Test for {@link CreateUserStoryServiceImpl#createUserStory(String,
     * String, String, String, String, int)}
     */
    @Test
    void createUserStory_Successful_EmptyRepository() {
        //ARRANGE
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        ProjectCode projectCode = new ProjectCode(projectCodeInput);
        UserStoryID userStoryID = new UserStoryID(userStoryIDInput);
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryNumberInput);
        Name actor = new Name(actorInput);
        Description userStoryText = new Description(userStoryTextInput);
        UserStoryStatus userStoryStatus = new UserStoryStatus(userStoryStatusInput);
        Priority priority = new Priority(priorityInput);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        when(factoryUserStory.createUserStory(userStoryID, projectCode,
                userStoryNumber, actor, userStoryText, userStoryStatus
                , priority)).thenReturn(userStory);

        when(userStory.getPriority()).thenReturn(priority);
        List<UserStory> userStories = new ArrayList<>();
        when(userStoryRepository.findAll()).thenReturn(userStories);


        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);

        when(userStory.getProjectCode()).thenReturn(projectCode);

        when(userStory.getUserStoryID()).thenReturn(userStoryID);

        when(userStoryRepository.save(userStory)).thenReturn(userStory);
        when(userStoryRepository.containsOfIdentity(userStoryID)).thenReturn(true);

        UserStory expected = factoryUserStory.createUserStory(userStoryID, projectCode,
                userStoryNumber, actor, userStoryText, userStoryStatus
                , priority);

        //ACT
        UserStory result = userStoryService.createUserStory(projectCode.getProjectCode(),
                userStoryID.getUserStoryID(), userStoryNumber.getNumber(),
                actor.getValue(), userStoryText.getDescription(), priority.getIndex());

        //ASSERT
        assertEquals(result, expected);
    }

    @Test
    void createUserStory_Unsuccessful_IllegalArgument() {
        String code = "PRJ1";
        String usId = "";
        String usNumber = "1";
        String actorName = "name";
        String text = "desc";
        int priorityNumber = 1;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userStoryService.createUserStory(code, usNumber, usId,
                    actorName, text, priorityNumber);
        });

        assertEquals("UserStoryNumber must be a positive number", exception.getMessage());
    }

    /**
     * Exception when the Second UserStory is created (duplicated UserStory)
     */
    @Test
    void createUserStory_Unsuccessful_DuplicatedUserStory() {
        String code = "PRJ1";
        String usId = "US1";
        String usNumber = "1";
        String actorName = "name";
        String text = "desc";
        String status = "planned";
        int priorityNumber = 1;

        ProjectCode projectCode1 = new ProjectCode(code);
        UserStoryNumber userStoryNumber = new UserStoryNumber(usNumber);
        UserStoryID userStoryID = new UserStoryID(usId);
        Name actor = new Name(actorName);
        Description userStoryText = new Description(text);
        UserStoryStatus userStoryStatus = new UserStoryStatus(status);
        Priority priority = new Priority(priorityNumber);

        when(projectRepository.containsOfIdentity(projectCode1)).thenReturn(true);

        when(factoryUserStory.createUserStory(userStoryID, projectCode1,
                userStoryNumber, actor, userStoryText, userStoryStatus
                , priority)).thenReturn(userStory);

        List<UserStory> userStories = Collections.singletonList(userStory);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getProjectCode()).thenReturn(projectCode);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userStoryService.createUserStory(code, usId, usNumber, actorName, text, priorityNumber);
        });

        assertEquals("UserStory already exist", exception.getMessage());
    }

    /**
     * Unit Test for {@link CreateUserStoryServiceImpl#addUserStory(UserStory,
     * Priority, UserStoryID, ProjectCode)}
     */
    @Test
    void addUserStory_Success() {
        List<UserStory> userStories = new ArrayList<>();

        // Conditions of addUserStory
        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getPriority()).thenReturn(priority);
        when(priority.getIndex()).thenReturn(1);

        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStory.getProjectCode()).thenReturn(new ProjectCode("PRJ10"));
        when(userStory.getUserStoryID()).thenReturn(userStoryID);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        when(userStoryRepository.save(userStory)).thenReturn(userStory);
        when(userStoryRepository.containsOfIdentity(userStoryID)).thenReturn(true);

        boolean result = userStoryService.addUserStory(userStory, new Priority(1),
                userStoryID, projectCode);

        assertTrue(result);
    }

    @Test
    void addUserStory_SamePriority() {
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory);

        UserStory userStoryDouble = mock(UserStory.class);

        // Conditions of addUserStory
        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getProjectCode()).thenReturn(projectCode);

        when(userStoryDouble.getPriority()).thenReturn(priority);
        when(priority.getIndex()).thenReturn(1);

        when(userStoryDouble.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStoryNumber.getNumber()).thenReturn("2");
        when(projectCode.getProjectCode()).thenReturn("PRJ1");
        when(userStoryID.getUserStoryID()).thenReturn("US2");

        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);


        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(userStoryNumberDouble.getNumber()).thenReturn("3");
        when(userStory.getUserStoryID()).thenReturn(userStoryIDDouble);
        when(userStoryIDDouble.getUserStoryID()).thenReturn("US3");


        Priority priority1 = new Priority(2);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        Optional<UserStory> optionalUserStoryDouble = mock(Optional.class);

        when(userStoryRepository.ofIdentity(userStoryIDDouble)).thenReturn(optionalUserStoryDouble);
        when(optionalUserStoryDouble.get()).thenReturn(userStory);
        when(userStory.getPriority()).thenReturn(priority1);

        when(userStoryRepository.save(userStory)).thenReturn(userStory);
        when(userStoryRepository.save(userStoryDouble)).thenReturn(userStoryDouble);

        when(userStoryRepository.containsOfIdentity(userStoryID)).thenReturn(true);

        boolean result = userStoryService.addUserStory(userStoryDouble, new Priority(1),
                userStoryID, projectCode);

        assertTrue(result);
    }

    @Test
    void addUserStory_ProjectDontExist() {
        List<UserStory> userStories = new ArrayList<>();

        // Conditions of addUserStory
        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getPriority()).thenReturn(priority);
        when(priority.getIndex()).thenReturn(1);

        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStory.getProjectCode()).thenReturn(new ProjectCode("PRJ10"));
        when(userStory.getUserStoryID()).thenReturn(userStoryID);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);


        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            userStoryService.addUserStory(userStory, new Priority(1),
                    userStoryID, projectCode);
        });

        assertEquals("ProjectCode does not exist!", exception.getMessage());
    }

    @Test
    void addUserStory_Unsuccessful_NullUserStory() {
        List<UserStory> userStories = new ArrayList<>();

        // Conditions of addUserStory
        when(userStoryRepository.findAll()).thenReturn(userStories);


        boolean result = userStoryService.addUserStory(null, new Priority(1),
                userStoryID, projectCode);

        assertFalse(result);
    }

    @Test
    void addUserStory_Unsuccessful_PriorityToHigh_Wrong() {
        List<UserStory> userStories = new ArrayList<>();

        // Conditions of addUserStory
        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getPriority()).thenReturn(priority);
        when(priority.getIndex()).thenReturn(2);

        boolean result = userStoryService.addUserStory(userStory, new Priority(1),
                userStoryID, projectCode);

        assertFalse(result);
    }

    @Test
    void ensureUserStoryIsNotCreated_projectDoesNotExist() {
        String projCode = "PRJ1";
        String userStoryID = "us1";
        String userStoryNumber = "1";
        String actor = "Team Member";
        String text = "text";
        String status = "planned";
        int priority = 1;

        ProjectCode projectCode = new ProjectCode(projCode);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            userStoryService.createUserStory(projCode, userStoryID, userStoryNumber, actor, text, priority);
        });
        assertEquals("Project does not exist.", exception.getMessage());
    }

    @Test
    void createUserStory_Unsuccessful_UsNumberAlreadyExistInProject() {
        //ARRANGE

        //CreateUserStory
        String code = "PRJ1";
        UserStory userStoryDouble = mock(UserStory.class);
        String usNumber = "1";
        String userStoryIDInput2 = "US2";
        String actorInput2 = "name";
        String userStoryTextInput2 = "desc";
        String userStoryStatusInput2 = "planned";
        int priorityInput2 = 2;

        ProjectCode projectCode1 = new ProjectCode(code);
        UserStoryID userStoryID2 = new UserStoryID(userStoryIDInput2);
        Name actor2 = new Name(actorInput2);
        UserStoryNumber userStoryNumber1 = new UserStoryNumber(usNumber);
        Description userStoryText2 = new Description(userStoryTextInput2);
        UserStoryStatus userStoryStatus2 = new UserStoryStatus(userStoryStatusInput2);
        Priority priority2 = new Priority(priorityInput2);


        when(projectRepository.containsOfIdentity(projectCode1)).thenReturn(true);


        when(factoryUserStory.createUserStory(userStoryID2, projectCode1,
                userStoryNumber1, actor2, userStoryText2, userStoryStatus2
                , priority2)).thenReturn(userStoryDouble);

        //UserStoryRepository with 2 US. One (UserStoryDouble) with same projectCode as the new US

        UserStory userStoryDouble2 = mock(UserStory.class);
        ProjectCode projectCodeDouble2 = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble2 = mock(UserStoryNumber.class);
        UserStoryID userStoryIDDouble2 = mock(UserStoryID.class);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStoryDouble2);
        userStories.add(userStory);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStoryDouble2.getProjectCode()).thenReturn(projectCodeDouble2);
        when(projectCodeDouble2.getProjectCode()).thenReturn("PRJ7");

        when(userStory.getProjectCode()).thenReturn(projectCode1);

        when(userStoryDouble.getPriority()).thenReturn(priority2);


        when(userStoryDouble.getUserStoryNumber()).thenReturn(userStoryNumber1);
        when(userStoryDouble.getProjectCode()).thenReturn(projectCode1);
        when(userStoryDouble.getUserStoryID()).thenReturn(userStoryID2);

        when(userStoryDouble2.getUserStoryNumber()).thenReturn(userStoryNumberDouble2);
        when(userStoryNumberDouble2.getNumber()).thenReturn("3");
        when(userStoryDouble2.getUserStoryID()).thenReturn(userStoryIDDouble2);
        when(userStoryIDDouble2.getUserStoryID()).thenReturn("US07");

        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber1);

        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userStoryService.createUserStory(code,
                    userStoryIDInput2, usNumber,
                    actorInput2, userStoryTextInput2, priorityInput2);
        });

        //ASSERT
        assertEquals("UserStory already exist", exception.getMessage());
    }
}

