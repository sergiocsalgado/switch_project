package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.value_objects.SprintBacklogID;
import org.switch2022.project.model.value_objects.UserStoryID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link SprintBacklog}.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SprintBacklog.class)
class SprintBacklogTest {

    @MockBean
    SprintBacklog sprintBacklog;

    @MockBean
    SprintBacklogID sprintBacklogID;

    @MockBean
    List<UserStoryID> userStories;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test class for {@link SprintBacklog#SprintBacklog(SprintBacklogID sprintBacklogID)} )}.
     */
    @Test
    void ensureSprintBacklogIDCanNotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintBacklog(null);
        });
        assertEquals("SprintBacklog can not be null.", exception.getMessage());
    }

    @Test
    void SprintBacklog_shouldReturnAnEmptyList() {
        //Act
        List<UserStoryID> userStories = sprintBacklog.getSprintBacklog();

        //Assert
        assertEquals(new ArrayList<>(), userStories);

    }

    @Test
    void ensureNotAddUserStory_alreadyExist(){
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);

        sprintBacklog.addUserStory(userStoryID);

        boolean expected = false;

        //Act
        boolean result = sprintBacklog.addUserStory(userStoryID);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void addUserStory_FailBecauseIDNull() {
        //Arrange
        UserStoryID userStoryID = null;
        SprintBacklogID sprintBacklogID1 = mock(SprintBacklogID.class);
        SprintBacklog sprintBacklog = new SprintBacklog(sprintBacklogID1);
        boolean expected = false;
        boolean result = sprintBacklog.addUserStory(userStoryID);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link SprintBacklog#hashCode()}.
     */
    @Test
    void hashCode_Success() {
        //Arrange
        SprintBacklogID sprintBacklogID1 = mock(SprintBacklogID.class);
        SprintBacklog sprintBacklog1 = new SprintBacklog(sprintBacklogID1);
        SprintBacklog sprintBacklog2 = new SprintBacklog(sprintBacklogID1);
        //Assert
        assertEquals(sprintBacklog1.hashCode(), sprintBacklog2.hashCode());
    }

    @Test
    void hashCode_Fail() {
        //Arrange
        SprintBacklogID sprintBacklogID1 = mock(SprintBacklogID.class);
        SprintBacklogID sprintBacklogID2 = mock(SprintBacklogID.class);
        SprintBacklog sprintBacklog1 = new SprintBacklog(sprintBacklogID1);
        SprintBacklog sprintBacklog2 = new SprintBacklog(sprintBacklogID2);
        //Assert
        assertNotEquals(sprintBacklog1.hashCode(), sprintBacklog2.hashCode());
    }

    /**
     * Test class for {@link SprintBacklog#equals(Object)}.
     * Test class for {@link SprintBacklog#sameIDAs(Object)}.
     */
    @Test
    void ensureObjectsAreEquals() {
        //Arrange
        SprintBacklog sprintBacklog1 = new SprintBacklog(sprintBacklogID);
        SprintBacklog sprintBacklog2 = sprintBacklog1;

        //Assert
        assertEquals(sprintBacklog1, sprintBacklog2);
        assertFalse(sprintBacklog1.sameIDAs(sprintBacklog2));
    }

    @Test
    void ensureObjectsAreNotEquals() {
        //Arrange
        SprintBacklog sprintBacklog1 = new SprintBacklog(sprintBacklogID);
        SprintBacklog sprintBacklog2 = new SprintBacklog(sprintBacklogID);

        //Assert
        assertEquals(sprintBacklog1, sprintBacklog2);
        assertFalse(sprintBacklog1.sameIDAs(sprintBacklog2));
    }

    @Test
    void ensureObjectsAreNull() {
        //Arrange
        SprintBacklog sprintBacklog2 = null;

        //Act
        boolean isEquals = sprintBacklog.equals(sprintBacklog2);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void ensureObjectDifferentClass_Insucess() {
        //Arrange
        SprintBacklog sprintBacklog1 = new SprintBacklog(sprintBacklogID);
        Object o = new Object();

        //Assert
        assertFalse(sprintBacklog1.equals(o));
    }

    /**
     * Test class for {@link SprintBacklog#identity()}
     */

    @Test
    void ensureIdentity_Sucess() {
        //Arrange
        SprintBacklog sprintBacklog1 = new SprintBacklog(sprintBacklogID);
        SprintBacklog sprintBacklog2 = sprintBacklog1;

        //Assert
        assertEquals(sprintBacklog1.identity(), sprintBacklog2.identity());
    }

    /**
     * Test class for {@link SprintBacklog#identity()}
     */

    @Test
    void ensureIdentity_Insucess() {
        //Arrange
        SprintBacklog sprintBacklog2 = new SprintBacklog(sprintBacklogID);

        //Assert
        assertNotEquals(sprintBacklog.getSprintBacklogID(), sprintBacklog2.identity());
    }

    /**
     * Tests Mock for {@link SprintBacklog}
     */
    @Test
    void getMockSprintBacklogID_shouldReturnAListWithOneUserStoryDTO() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        List<UserStoryID> userStories = new ArrayList<>();
        userStories.add(userStoryID);
        when(sprintBacklog.getSprintBacklog()).thenReturn(userStories);
        userStories.add(userStoryID);
        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);

        //Act
        List<UserStoryID> userStoryIDList = sprintBacklog.getSprintBacklog();

        //Assert
        assertEquals(userStories, userStoryIDList);
    }

    /**
     * Tests Mock for {@link SprintBacklog}
     */

    @Test
    void getUserStoryByID_shouldReturnAListWithOneUserStoryDTO() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        List<UserStoryID> userStories = new ArrayList<>();
        userStories.add(userStoryID);
        when(sprintBacklog.getSprintBacklog()).thenReturn(userStories);
        userStories.add(userStoryID);

        //Act
        List<UserStoryID> userStoryIDList = sprintBacklog.getSprintBacklog();

        //Assert
        assertEquals(userStories, userStoryIDList);

    }

    /**
     * Tests Mock for {@link SprintBacklog}
     */

    @Test
    void getUserStoryByID_shouldReturnAListWithTwoUserStoryDTO() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        List<UserStoryID> userStories = mock(List.class);
        when(sprintBacklog.getSprintBacklog()).thenReturn(userStories);

        List<UserStoryDTO> userStoryDTOList = new ArrayList<>();
        userStoryDTOList.add(mock(UserStoryDTO.class));
        userStoryDTOList.add(mock(UserStoryDTO.class));
        when(userStories.get(0)).thenReturn(userStoryID);
        when(userStories.get(1)).thenReturn(userStoryID1);

        userStories.add(userStoryID);
        userStories.add(userStoryID1);

        //Act
        List<UserStoryID> userStoryIDList = sprintBacklog.getSprintBacklog();
        //Assert
        assertEquals(userStories, userStoryIDList);
    }
}