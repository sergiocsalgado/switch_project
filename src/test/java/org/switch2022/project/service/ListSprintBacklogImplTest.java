package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.UserStoryID;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListSprintBacklogImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListSprintBacklogImpl.class)
class ListSprintBacklogImplTest {

    @MockBean
    Sprint sprint;
    @MockBean
    UserStory userStory;
    @MockBean
    UserStoryID userStoryID;

    @MockBean
    @Qualifier("sprintJPARepository")
    private Repository<SprintID, Sprint> sprintRepository;
    @MockBean
    @Qualifier("storyJPARepository")
    private Repository<UserStoryID, UserStory> userStoryRepository;

    @Autowired
    private ListSprintBacklogImpl serviceUnderTest;


    /**
     * Unit Tests for {@link ListSprintBacklogImpl#getSprintBacklog(String)} .
     */
    @Test
    void getSprintBacklog_ensureTheSprintBacklogHasOneUserStory() {
        // Arrange
        String sprintIDInput = "SP1";
        SprintID sprintID = new SprintID(sprintIDInput);
        when(sprint.getSprintID()).thenReturn(sprintID);

        Optional<Sprint> optionalSprint = mock(Optional.class);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);
        when(sprintRepository.ofIdentity(sprintID)).thenReturn(optionalSprint);

        List<UserStoryID> userStoryIDS = List.of(userStoryID);
        when(sprint.getScrumBoardList()).thenReturn(userStoryIDS);


        Optional<UserStory> optionalUs = mock(Optional.class);
        when(optionalUs.isPresent()).thenReturn(true);
        when(optionalUs.get()).thenReturn(userStory);
        when(userStoryRepository.ofIdentity(userStoryID)).thenReturn(optionalUs);
        when(userStory.getUserStoryID()).thenReturn(userStoryID);

        List<UserStory> userStories = List.of(userStory);
        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);

        List<UserStoryDTO> expected = List.of(userStoryDTO);

        try (MockedStatic<UserStoryMapper> mapperDouble = Mockito.mockStatic(UserStoryMapper.class)) {
            mapperDouble.when(() -> UserStoryMapper.getUserStoriesDTO(userStories)).thenReturn(expected);

            // Act
            List<UserStoryDTO> result = serviceUnderTest.getSprintBacklog(sprintIDInput);

            // Assert
            assertEquals(expected.size(), result.size());
            assertEquals(expected, result);
        }
    }
    @Test
    void getSprintBacklog_ensureTheSprintBacklogIsEmpty() {
        // Arrange
        String sprintIDInput = "SP1";
        SprintID sprintID = new SprintID(sprintIDInput);
        when(sprint.getSprintID()).thenReturn(sprintID);

        Optional<Sprint> optionalSprint = mock(Optional.class);
        when(optionalSprint.isPresent()).thenReturn(true);
        when(optionalSprint.get()).thenReturn(sprint);
        when(sprintRepository.ofIdentity(sprintID)).thenReturn(optionalSprint);

        List<UserStoryID> userStoryIDS = List.of();
        when(sprint.getScrumBoardList()).thenReturn(userStoryIDS);

        List<UserStory> userStories = List.of();
        List<UserStoryDTO> expected = List.of();

        try (MockedStatic<UserStoryMapper> mapperDouble = Mockito.mockStatic(UserStoryMapper.class)) {
            mapperDouble.when(() -> UserStoryMapper.getUserStoriesDTO(userStories)).thenReturn(expected);

            // Act
            List<UserStoryDTO> result = serviceUnderTest.getSprintBacklog(sprintIDInput);

            // Assert
            assertEquals(0, result.size());
            assertEquals(expected, result);
        }
    }

    @Test
    void getSprintBacklog_ensureThrowIllegalArgumentExceptionWhenSprintIdGivenIsNotValid() {
        // Arrange
        String sprintIDInput = "";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.getSprintBacklog(sprintIDInput);
        });

        //Assert
        assertEquals("Sprint ID cannot be empty", exception.getMessage());
    }
    @Test
    void getSprintBacklog_ensureThrowIllegalStateExceptionWhenSprintIdGivenDoesNotExists() {
        // Arrange
        String sprintIDInput = "SP1";
        SprintID sprintID = new SprintID(sprintIDInput);
        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.empty());

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.getSprintBacklog(sprintIDInput);
        });

        //Assert
        assertEquals("Sprint does not exists.", exception.getMessage());
    }
}