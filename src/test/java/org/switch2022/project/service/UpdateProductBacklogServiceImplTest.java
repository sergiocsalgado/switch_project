package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.model.value_objects.UserStoryStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UpdateProductBacklogServiceImpl.class)
class UpdateProductBacklogServiceImplTest {

    @MockBean
    private UserStory userStory;

    @MockBean
    private UserStoryStatus userStoryStatus;

    @MockBean
    @Qualifier("storyJPARepository")
    private Repository<UserStoryID, UserStory> userStoryRepository;

    @MockBean
    @Qualifier("projectJPARepository")
    private Repository<ProjectCode, Project> projectRepository;

    @Autowired
    private UpdateProductBacklogServiceImpl updateProductBacklogServiceImpl;

    @Test
    void ensureExceptionIsThrownForInvalidParameters_projectCodeEmpty() {
        //Arrange
        String projectCode = "";
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            updateProductBacklogServiceImpl.updateProductBacklog(projectCode);
        });
        //Assert
        assertEquals("Project code cannot be empty", exception.getMessage());
    }

    @Test
    void ensureUpdateStatusOfRunningUserStoriesWhenSprintIsClosed() {
        //Arrange
        String projectCode = "PRJ1";
        ProjectCode prjCode = new ProjectCode(projectCode);

        when(projectRepository.containsOfIdentity(prjCode)).thenReturn(true);

        List<UserStory> userStories = Collections.singletonList(userStory);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getProjectCode()).thenReturn(prjCode);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStoryStatus.getDescription()).thenReturn("running");

        when(userStoryRepository.save(userStory)).thenReturn(userStory);

        //Act
        List<UserStory> userStoriesResult = updateProductBacklogServiceImpl.updateProductBacklog(projectCode);

        //Assert
        assertEquals(userStories, userStoriesResult);
    }

    @Test
    void ensureIfTheProjectCodeIsDifferentFromTheUsThanReturnsEmptyList() {
        //Arrange
        String projectCode = "PRJ1";
        ProjectCode prjCode = new ProjectCode(projectCode);

        when(projectRepository.containsOfIdentity(prjCode)).thenReturn(true);

        List<UserStory> userStories = Collections.singletonList(userStory);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        String otherProjectCode = "PRJ0";
        ProjectCode otherPrjCode = new ProjectCode(otherProjectCode);

        when(userStory.getProjectCode()).thenReturn(otherPrjCode);

        List<UserStory> expect = new ArrayList<>();

        //Act
        List<UserStory> result = updateProductBacklogServiceImpl.updateProductBacklog(projectCode);

        //Assert
        assertEquals(expect, result);
    }

    @Test
    void ensureStatusIsNotUpdatedIfDifferentFromRunning() {
        //Arrange
        String projectCode = "PRJ1";
        ProjectCode prjCode = new ProjectCode(projectCode);

        when(projectRepository.containsOfIdentity(prjCode)).thenReturn(true);

        List<UserStory> userStories = Collections.singletonList(userStory);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        when(userStory.getProjectCode()).thenReturn(prjCode);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStoryStatus.getDescription()).thenReturn("finished");

        //Act
        List<UserStory> userStoriesResult = updateProductBacklogServiceImpl.updateProductBacklog(projectCode);

        //Assert
        assertEquals("finished", userStoriesResult.get(0).getStatus().getDescription());
    }

    @Test
    void ensureExceptionIsThrownForProjectThatNotExists() {
        //Arrange
        String projectCode = "PRJ1";
        ProjectCode prjCode = new ProjectCode(projectCode);
        when(projectRepository.containsOfIdentity(prjCode)).thenReturn(false);
        //Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            updateProductBacklogServiceImpl.updateProductBacklog(projectCode);
        });
        //Assert
        assertEquals("Project does not exist", exception.getMessage());
    }
}