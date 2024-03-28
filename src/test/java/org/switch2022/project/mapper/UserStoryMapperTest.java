package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link UserStoryMapper#equals(Object)}
 */

class UserStoryMapperTest {
    /**
     * Test class for {@link UserStoryMapper}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = UserStoryMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link UserStoryMapper#getUserStoriesDTO(List userStories)} ()}
     */
    @Test
    void ensureProductBacklogUserStoriesDTOAreAddedToListCorrectly_Success() {

        //Arrange
        //Creation of User Stories and add them to a list
        String projectCodeInput = "PRJ1";
        String userStoryIDInput1 = "US1";
        String userStoryTextInput1 = "text1";
        String statusInput1 = "planned";
        String numberInput1 = "1";
        String actorInput1 = "actorInput1";
        int priorityInput1 = 1;

        String userStoryIDInput2 = "US2";
        String userStoryTextInput2 = "text2";
        String statusInput2 = "running";
        String numberInput2 = "2";
        String actorInput2 = "actor3";
        int priorityInput2 = 2;

        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);

        UserStoryID userStoryIDDouble1 = mock(UserStoryID.class);
        when(userStoryIDDouble1.getUserStoryID()).thenReturn(userStoryIDInput1);
        UserStoryID userStoryIDDouble2 = mock(UserStoryID.class);
        when(userStoryIDDouble2.getUserStoryID()).thenReturn(userStoryIDInput2);

        Description userStoryTextDouble1 = mock(Description.class);
        when(userStoryTextDouble1.getDescription()).thenReturn(userStoryTextInput1);
        Description userStoryTextDouble2 = mock(Description.class);
        when(userStoryTextDouble2.getDescription()).thenReturn(userStoryTextInput2);

        UserStoryStatus statusDouble1 = mock(UserStoryStatus.class);
        when(statusDouble1.getDescription()).thenReturn(statusInput1);
        UserStoryStatus statusDouble2 = mock(UserStoryStatus.class);
        when(statusDouble2.getDescription()).thenReturn(statusInput2);

        UserStoryNumber userStoryNumber1 = mock(UserStoryNumber.class);
        when(userStoryNumber1.getNumber()).thenReturn(numberInput1);
        UserStoryNumber userStoryNumber2 = mock(UserStoryNumber.class);
        when(userStoryNumber2.getNumber()).thenReturn(numberInput2);

        Name actor1 = mock(Name.class);
        when(actor1.getValue()).thenReturn(actorInput1);
        Name actor2 = mock(Name.class);
        when(actor2.getValue()).thenReturn(actorInput2);

        Priority priority1 = mock(Priority.class);
        when(priority1.getIndex()).thenReturn(priorityInput1);
        Priority priority2 = mock(Priority.class);
        when(priority2.getIndex()).thenReturn(priorityInput2);

        UserStory us1 = mock(UserStory.class);
        when(us1.getProjectCode()).thenReturn(projectCode);
        when(us1.getUserStoryID()).thenReturn(userStoryIDDouble1);
        when(us1.getUserStoryText()).thenReturn(userStoryTextDouble1);
        when(us1.getStatus()).thenReturn(statusDouble1);
        when(us1.getUserStoryNumber()).thenReturn(userStoryNumber1);
        when(us1.getActor()).thenReturn(actor1);
        when(us1.getPriority()).thenReturn(priority1);

        UserStory us2 = mock(UserStory.class);
        when(us2.getProjectCode()).thenReturn(projectCode);
        when(us2.getUserStoryID()).thenReturn(userStoryIDDouble2);
        when(us2.getUserStoryText()).thenReturn(userStoryTextDouble2);
        when(us2.getStatus()).thenReturn(statusDouble2);
        when(us2.getUserStoryNumber()).thenReturn(userStoryNumber2);
        when(us2.getActor()).thenReturn(actor2);
        when(us2.getPriority()).thenReturn(priority2);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(us1);
        userStories.add(us2);

        //Creation list of DTO
        //System DTO mapping procedure
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setProjectCode(projectCodeInput);
        userStoryDTO1.setUserStoryID(userStoryIDInput1);
        userStoryDTO1.setUserStoryText(userStoryTextInput1);
        userStoryDTO1.setStatus(statusInput1);
        userStoryDTO1.setUserStoryNumber(numberInput1);
        userStoryDTO1.setActor(actorInput1);
        userStoryDTO1.setPriority(priorityInput1);

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setProjectCode(projectCodeInput);
        userStoryDTO2.setUserStoryID(userStoryIDInput2);
        userStoryDTO2.setUserStoryText(userStoryTextInput2);
        userStoryDTO2.setStatus(statusInput2);
        userStoryDTO2.setUserStoryNumber(numberInput2);
        userStoryDTO2.setActor(actorInput2);
        userStoryDTO2.setPriority(priorityInput2);

        List<UserStoryDTO> expected = new ArrayList<>();
        expected.add(userStoryDTO1);
        expected.add(userStoryDTO2);

        //Act
        List<UserStoryDTO> result = UserStoryMapper.getUserStoriesDTO(userStories);

        //Assert
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getProjectCode(), result.get(i).getProjectCode());
            assertEquals(expected.get(i).getUserStoryID(), result.get(i).getUserStoryID());
            assertEquals(expected.get(i).getUserStoryText(), result.get(i).getUserStoryText());
            assertEquals(expected.get(i).getStatus(), result.get(i).getStatus());
            assertEquals(expected.get(i).getUserStoryNumber(), result.get(i).getUserStoryNumber());
            assertEquals(expected.get(i).getActor(), result.get(i).getActor());
            assertEquals(expected.get(i).getPriority(), result.get(i).getPriority());
        }

        assertEquals(expected, result);
        assertEquals(expected.size(), result.size());
    }

    @Test
    void ensureProductBacklogIsAnEmptyList_Success() {

        //Arrange
        //Creation of User Stories and add them to a list
        UserStory us1 = mock(UserStory.class);
        ProjectCode projectCodeDouble1 = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble1 = mock(UserStoryID.class);
        Name actorDouble1 = mock(Name.class);
        Description userStoryTextDouble1 = mock(Description.class);
        UserStoryStatus statusDouble1 = mock(UserStoryStatus.class);
        Priority priorityDouble1 = mock(Priority.class);

        when(us1.getProjectCode()).thenReturn(projectCodeDouble1);
        when(us1.getUserStoryID()).thenReturn(userStoryIDDouble1);
        when(us1.getActor()).thenReturn(actorDouble1);
        when(us1.getUserStoryText()).thenReturn(userStoryTextDouble1);
        when(us1.getStatus()).thenReturn(statusDouble1);
        when(us1.getPriority()).thenReturn(priorityDouble1);

        UserStory us2 = mock(UserStory.class);
        ProjectCode projectCodeDouble2 = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble2 = mock(UserStoryID.class);
        Name actorDouble2 = mock(Name.class);
        Description userStoryTextDouble2 = mock(Description.class);
        UserStoryStatus statusDouble2 = mock(UserStoryStatus.class);
        Priority priorityDouble2 = mock(Priority.class);

        when(us1.getProjectCode()).thenReturn(projectCodeDouble2);
        when(us1.getUserStoryID()).thenReturn(userStoryIDDouble2);
        when(us1.getActor()).thenReturn(actorDouble2);
        when(us1.getUserStoryText()).thenReturn(userStoryTextDouble2);
        when(us1.getStatus()).thenReturn(statusDouble2);
        when(us1.getPriority()).thenReturn(priorityDouble2);

        List<UserStory> userStories = new ArrayList<>();

        //Creation list of DTO
        //System DTO mapping procedure
        List<UserStoryDTO> expected = new ArrayList<>();

        //Act
        List<UserStoryDTO> result = UserStoryMapper.getUserStoriesDTO(userStories);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureDTOIsCreatedAndAddedToListCorrectly_Success() {
        //Arrange
        String projectCode = "PRJ1";
        String userStoryIDInput1 = "US1";
        String numberInput1 = "1";
        String nameInput1 = "name1";
        String userStoryTextInput1 = "text1";
        String statusInput1 = "planned";
        int priorityInput1 = 1;

        UserStory us1 = new UserStory(
                new UserStoryID(userStoryIDInput1),
                new ProjectCode(projectCode),
                new UserStoryNumber(numberInput1),
                new Name(nameInput1),
                new Description(userStoryTextInput1),
                new UserStoryStatus(statusInput1),
                new Priority(priorityInput1));

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryID(userStoryIDInput1);
        userStoryDTO.setProjectCode(projectCode);
        userStoryDTO.setUserStoryText(userStoryTextInput1);
        userStoryDTO.setStatus(statusInput1);
        userStoryDTO.setUserStoryNumber(numberInput1);
        userStoryDTO.setActor(nameInput1);
        userStoryDTO.setPriority(priorityInput1);

        //Act
        UserStoryDTO result = UserStoryMapper.toDTO(us1);

        //Assert
        assertEquals(userStoryDTO, result);
        assertEquals(userStoryDTO.getUserStoryID(), result.getUserStoryID());
        assertEquals(userStoryDTO.getProjectCode(), result.getProjectCode());
        assertEquals(userStoryDTO.getUserStoryNumber(), result.getUserStoryNumber());
        assertEquals(userStoryDTO.getActor(), result.getActor());
        assertEquals(userStoryDTO.getUserStoryText(), result.getUserStoryText());
        assertEquals(userStoryDTO.getStatus(), result.getStatus());
        assertEquals(userStoryDTO.getPriority(), result.getPriority());
    }
}