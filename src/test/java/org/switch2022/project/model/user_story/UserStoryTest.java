package org.switch2022.project.model.user_story;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryTest {

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority, Effort)}  UserStory
     */
    @Test
    public void ensureThrowExceptionForProjectCodeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Name actorDouble = mock(Name.class);
            Description userStoryTextDouble = mock(Description.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(userStoryIDDouble, null, userStoryNumberDouble,
                    actorDouble, userStoryTextDouble, statusDouble, priorityDouble);
        });
        assertEquals("Project Code can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority)}
     */
    @Test
    public void ensureThrowExceptionForUserStoryIDNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Name actorDouble = mock(Name.class);
            Description userStoryTextDouble = mock(Description.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(null, projectCodeDouble,
                    userStoryNumberDouble, actorDouble, userStoryTextDouble,
                    statusDouble, priorityDouble);
        });
        assertEquals("User Story ID can't be null", exception.getMessage());
    }

    @Test
    public void ensureThrowExceptionForUserStoryNumberNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            Name actorDouble = mock(Name.class);
            Description userStoryTextDouble = mock(Description.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(userStoryIDDouble, projectCodeDouble,
                    null, actorDouble, userStoryTextDouble,
                    statusDouble, priorityDouble);
        });
        assertEquals("User Story Number can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority)}  UserStory
     */
    @Test
    public void ensureThrowExceptionForActorNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Description userStoryTextDouble = mock(Description.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(userStoryIDDouble, projectCodeDouble, userStoryNumberDouble,
                    null, userStoryTextDouble, statusDouble, priorityDouble);
        });
        assertEquals("Actor can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority, Effort)}  UserStory
     */
    @Test
    public void ensureThrowExceptionForUserStoryTextNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Name actorDouble = mock(Name.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(userStoryIDDouble, projectCodeDouble,
                    userStoryNumberDouble, actorDouble, null,
                    statusDouble, priorityDouble);
        });
        assertEquals("User Story Text can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority)}  UserStory
     */
    @Test
    public void ensureThrowExceptionForStatusNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Name actorDouble = mock(Name.class);
            Description userStoryTextDouble = mock(Description.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(userStoryIDDouble, projectCodeDouble,
                    userStoryNumberDouble, actorDouble, userStoryTextDouble,
                    null, priorityDouble);
        });
        assertEquals("Status can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority)}  UserStory
     */
    @Test
    public void ensureThrowExceptionForPriorityNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Name actorDouble = mock(Name.class);
            Description userStoryTextDouble = mock(Description.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);

            new UserStory(userStoryIDDouble, projectCodeDouble,
                    userStoryNumberDouble, actorDouble, userStoryTextDouble,
                    statusDouble, null);
        });
        assertEquals("Priority can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#UserStory(UserStoryID, ProjectCode,
     * UserStoryNumber, Name, Description, UserStoryStatus, Priority)}  UserStory
     */
    @Test
    public void ensureThrowExceptionForEffortNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            ProjectCode projectCodeDouble = mock(ProjectCode.class);
            UserStoryID userStoryIDDouble = mock(UserStoryID.class);
            UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
            Name actorDouble = mock(Name.class);
            Description userStoryTextDouble = mock(Description.class);
            UserStoryStatus statusDouble = mock(UserStoryStatus.class);
            Priority priorityDouble = mock(Priority.class);

            new UserStory(userStoryIDDouble, projectCodeDouble,
                    userStoryNumberDouble, actorDouble, userStoryTextDouble,
                    statusDouble, priorityDouble, null);
        });
        assertEquals("Effort can't be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link UserStory#getProjectCode()}.
     */
    @Test
    public void getProjectCodeEquals() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);
        ProjectCode expected = projectCodeDouble;

        //Act
        ProjectCode result = userStory.getProjectCode();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#getUserStoryNumber()}.
     */

    @Test
    void getUserStoryNumberEquals() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);
        UserStoryNumber expected = userStoryNumberDouble;

        //Act
        UserStoryNumber result = userStory.getUserStoryNumber();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#getUserStoryID()}.
     */
    @Test
    public void getUserStoryIDEquals() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStoryID expected = userStoryIDDouble;

        //Act
        UserStoryID result = userStory.getUserStoryID();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#getActor()}.
     */
    @Test
    public void getActorEquals() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        Name expected = actorDouble;
        //Act
        Name result = userStory.getActor();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#getUserStoryText()}.
     */
    @Test
    public void getUserStoryTextEquals() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        Description expected = userStoryTextDouble;
        //Act
        Description result = userStory.getUserStoryText();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#getStatus()}.
     */
    @Test
    public void getStatus() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStoryStatus expected = statusDouble;
        //Act
        UserStoryStatus result = userStory.getStatus();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#getPriority()}.
     */
    @Test
    public void getPriority() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        Priority expect = priorityDouble;
        //Act
        Priority result = userStory.getPriority();
        //Assert
        assertEquals(expect, result);
    }

    /**
     * Unit Test for {@link UserStory#getEffort()}.
     */
    @Test
    public void getEffort() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);
        Effort effortDouble = mock(Effort.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble, effortDouble);
        Effort expect = effortDouble;
        //Act
        Effort result = userStory.getEffort();
        //Assert
        assertEquals(expect, result);
    }

    /**
     * Unit Test for {@link UserStory#setEffort(Effort)}.
     */

    @Test
    void setEffort() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);
        Effort effortDouble = mock(Effort.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble, effortDouble);

        Effort expected = effortDouble;
        //Act
        userStory.setEffort(effortDouble);
        Effort result = userStory.getEffort();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#setPriority(Priority)}.
     */

    @Test
    void setPriority() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        Priority expected = priorityDouble;
        //Act
        userStory.setPriority(priorityDouble);
        Priority result = userStory.getPriority();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#setStatus(UserStoryStatus)}.
     */

    @Test
    void setStatus() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStoryStatus expected = statusDouble;
        //Act
        userStory.setStatus(statusDouble);
        UserStoryStatus result = userStory.getStatus();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#copy()}.
     */
    @Test
    public void ensureGetUserStoryCopy() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStory expected = userStory;

        //Act
        UserStory result = userStory.copy();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#equals(Object)}.
     */
    @Test
    void testEquals_NullObject_ReturnsFalse() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        Object o = null;

        boolean isEquals = userStory.equals(o);

        assertFalse(isEquals);
    }


    /**
     * Unit Test for {@link UserStory#equals(Object)}.
     */
    @Test
    public void returnFalseDifferentClasses() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        Object o = new Object();

        boolean isEquals = userStory.equals(o);

        assertFalse(isEquals);
    }

    /**
     * Unit Test for {@link UserStory#equals(Object)}.
     */
    @Test
    public void returnTrueDifferentClasses() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        boolean isEquals = userStory.equals(userStory);

        assertTrue(isEquals);
    }

    /**
     * Unit Test for {@link UserStory#equals(Object)}.
     */
    @Test
    public void returnFalseBecauseUserStoryDoubleIsNull() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStory userStoryDouble = mock(UserStory.class);

        boolean isEquals = userStory.equals(userStoryDouble);

        assertFalse(isEquals);
    }

    /**
     * Unit Test for {@link UserStory#hashCode()}.
     */
    @Test
    public void testHashCode() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory1 = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStory userStory2 = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        assertEquals(userStory1.hashCode(), userStory2.hashCode());
    }

    @Test
    public void testHashCodeZero() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory1 = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStory userStory2 = new UserStory(userStoryIDDouble, projectCodeDouble,

                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        assertEquals(userStory1.hashCode(), userStory2.hashCode());

    }

    /**
     * Unit Test for {@link UserStory#identity()}.
     */
    @Test
    public void testIdentity() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStoryID expected = userStoryIDDouble;
        //Act
        UserStoryID result = userStory.identity();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link UserStory#sameIDAs(Object)}.
     */
    @Test
    public void testSameIdAsTrue() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStory userStory1 = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);
        assertTrue(userStory.sameIDAs(userStory1));
    }

    /**
     * Unit Test for {@link UserStory#sameIDAs(Object)}.
     */
    @Test
    public void testSameIdAsFalse() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);
        UserStoryID userStoryIDDouble1 = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        Name actorDouble = mock(Name.class);
        Description userStoryTextDouble = mock(Description.class);
        Description userStoryTextDouble1 = mock(Description.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        UserStoryStatus statusDouble1 = mock(UserStoryStatus.class);
        Priority priorityDouble = mock(Priority.class);
        Priority priorityDouble1 = mock(Priority.class);

        UserStory userStory = new UserStory(userStoryIDDouble, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble,
                statusDouble, priorityDouble);

        UserStory userStory1 = new UserStory(userStoryIDDouble1, projectCodeDouble,
                userStoryNumberDouble, actorDouble, userStoryTextDouble1,
                statusDouble1, priorityDouble1);
        assertFalse(userStory.sameIDAs(userStory1));
    }
}
