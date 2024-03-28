package org.switch2022.project.model.user_story;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class FactoryUserStoryImplTest {

    /**
     * Test class for {@link FactoryUserStoryImpl#createUserStory(ProjectCode,
     * UserStoryNumber, UserStoryID, Name, Description, UserStoryStatus, Priority)}.
     */
    @Test
    void ensureCreatesUserStory_Success() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Name name = mock(Name.class);
        Description description = mock(Description.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        Priority priority = mock(Priority.class);

        UserStory us = new UserStory(userStoryID, projectCode, userStoryNumber,
                name, description, userStoryStatus, priority);
        FactoryUserStory factoryUserStory = new FactoryUserStoryImpl();

        UserStory expected = us;

        //Act
        UserStory result = factoryUserStory.createUserStory(userStoryID,
                projectCode, userStoryNumber, name, description, userStoryStatus,
                priority);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureCreatesUserStory_Fail() {
        //Arrange
        ProjectCode projectCode1 = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        Name name = mock(Name.class);
        Description description = mock(Description.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        Priority priority = mock(Priority.class);

        UserStory us = new UserStory(userStoryID1, projectCode1, userStoryNumber,
                name, description, userStoryStatus, priority);
        FactoryUserStory factoryUserStory = new FactoryUserStoryImpl();

        UserStory expected = us;

        //Act
        UserStory result = factoryUserStory.createUserStory(userStoryID2,
                projectCode2, userStoryNumber, name, description, userStoryStatus,
                priority);

        //Assert
        assertNotEquals(expected, result);

    }
}
