package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintTest {

    /**
     * Test class for {@link Sprint#equals(Object)} )}.
     */
    @Test
    void equals_sameSprint_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);
        Sprint sprint1 = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint sprint2 = sprint1;

        assertEquals(sprint1, sprint2);
        assertEquals(sprint1.getProjectCode(), sprint2.getProjectCode());
        assertEquals(sprint1.getSprintID(), sprint2.getSprintID());
    }

    /**
     * Test class for {@link Sprint#equals(Object)} )}.
     */
    @Test
    void equals_equalSprint_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertEquals(sprint1, sprint2);
        assertEquals(sprint1.getProjectCode(), sprint2.getProjectCode());
        assertEquals(sprint1.getSprintID(), sprint2.getSprintID());
    }

    /**
     * Test class for {@link Sprint#equals(Object)} )}.
     */
    @Test
    void equals_differentProjectCode_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode1 = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode1, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode2, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertNotEquals(sprint1, sprint2);
        assertNotEquals(sprint1.getProjectCode(), sprint2.getProjectCode());
    }

    /**
     * Test class for {@link Sprint#equals(Object)} )}.
     */
    @Test
    void equals_differentSprintID_unit() {
        Period period = mock(Period.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode, sprintID1, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode, sprintID2, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertNotEquals(sprint1, sprint2);
        assertNotEquals(sprint1.getSprintID(), sprint2.getSprintID());
    }

    /**
     * Test class for {@link Sprint#getSprintID()}.
     */
    @Test
    void getSprintID_Success_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        SprintID expected = sprintID;
        SprintID res = sprint.getSprintID();
        assertEquals(expected, res);
    }

    @Test
    void getSprintID_Fail_unit() {
        Period period = mock(Period.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID1, sprintNumber,
                period, sprintBacklog, sprintStatus);

        SprintID expected = sprintID2;
        SprintID res = sprint.getSprintID();
        assertNotEquals(expected, res);
    }

    @Test
    void getSprintID_Success() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = new SprintID("Sprint1");
        ProjectCode projectCode = new ProjectCode("PRJ1");
        SprintNumber sprintNumber = new SprintNumber(3);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = new SprintStatus("planned");

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        SprintID expected = sprintID;
        SprintID result = sprint.getSprintID();
        assertEquals(expected, result);
    }

    @Test
    void getSprintID_Fail() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = new SprintID("SprintID1");
        SprintID sprintID1 = new SprintID("SprintID2");
        ProjectCode projectCode = new ProjectCode("PRJ1");
        SprintNumber sprintNumber = new SprintNumber(3);
        SprintStatus sprintStatus = new SprintStatus("planned");


        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        SprintID expected = sprintID1;
        SprintID result = sprint.getSprintID();
        assertNotEquals(expected, result);
    }

    /**
     * Test class for {@link Sprint#getPeriod()}.
     */
    @Test
    void getPeriod_Success_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        Period expected = period;
        Period result = sprint.getPeriod();
        assertEquals(expected, result);
    }

    @Test
    void getPeriod_Fail_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        Period expected = mock(Period.class);
        Period result = sprint.getPeriod();
        assertNotEquals(expected, result);
    }

    @Test
    void getPeriod_Success() {
        Period period = new Period(LocalDate.now(),
                LocalDate.now().plusWeeks(3));

        SprintID sprintID = new SprintID("SprintID1");
        ProjectCode projectCode = new ProjectCode("PRJ1");
        SprintNumber sprintNumber = new SprintNumber(3);
        SprintStatus sprintStatus = new SprintStatus("planned");

        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Period expected = period;
        Period result = sprint.getPeriod();
        assertEquals(expected, result);
    }

    @Test
    void getPeriod_Fail() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(3));
        SprintID sprintID = new SprintID("SprintID1");
        ProjectCode projectCode = new ProjectCode("PRJ1");
        SprintNumber sprintNumber = new SprintNumber(3);
        SprintStatus sprintStatus = new SprintStatus("planned");

        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        Period expected = new Period(LocalDate.now(),
                LocalDate.now().plusWeeks(2));

        Period result = sprint.getPeriod();
        assertNotEquals(expected, result);
    }

    /**
     * Test class for {@link Sprint#getSprintNumber()}.
     */
    @Test
    void getSprintNumber() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);
        Sprint sprint = new Sprint(projectCode, sprintID,
                sprintNumber, period, sprintBacklog, sprintStatus);

        SprintNumber expected = sprintNumber;
        SprintNumber result = sprint.getSprintNumber();
        assertEquals(expected, result);
    }

    @Test
    void addUserStoryToSprintBacklog_Success() {
        //ARRANGE
        String sprintID = "SP01";
        Period periodDouble = mock(Period.class);


        UserStoryID userStoryID = mock(UserStoryID.class);
        SprintID sprintIDDouble = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        when(sprintIDDouble.getSprintID()).thenReturn(sprintID);
        when(sprintBacklog.addUserStory(userStoryID)).thenReturn(true);

        Sprint sprint = new Sprint(projectCode, sprintIDDouble,
                sprintNumber, periodDouble, sprintBacklog, sprintStatus);

        //ACT
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryID);

        //ASSERT
        assertTrue(result);
    }

    /**
     * Test class for {@link Sprint#setSprintStatus(SprintStatus)}
     */
    @Test
    void setSprintStatus() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);
        Sprint sprint = new Sprint(projectCode, sprintID,
                sprintNumber, period, sprintBacklog, sprintStatus);

        SprintStatus sprintStatus2 = mock(SprintStatus.class);
        sprint.setSprintStatus(sprintStatus2);

        String expected = sprintStatus.getDescription();
        String result = sprint.getSprintStatus().getDescription();
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Sprint#addUserStoryToSprintBacklog(UserStoryID)}.
     */
    @Test
    void addUserStoryToSprintBacklog_AlreadyInList_Fail() {
        //ARRANGE
        String sprintID = "SP01";
        Period periodDouble = mock(Period.class);

        UserStoryID userStoryID = mock(UserStoryID.class);
        SprintID sprintIDDouble = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        when(sprintIDDouble.getSprintID()).thenReturn(sprintID);

        Sprint sprint = new Sprint(projectCode, sprintIDDouble, sprintNumber,
                periodDouble, sprintBacklog, sprintStatus);

        //ACT
        sprint.addUserStoryToSprintBacklog(userStoryID);
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryID);

        //ASSERT
        assertFalse(result);
    }

    @Test
    void assertObjectInstanceOf_Success_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        assertInstanceOf(Sprint.class, sprint);
    }

    /**
     * Test class for {@link Sprint#equals(Object)}.
     */
    @Test
    void testEquals_SameObject_Success_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        assertTrue(sprint.equals(sprint));
    }

    @Test
    void assertObjectInstanceOf_Success() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        assertInstanceOf(Sprint.class, sprint);
    }

    @Test
    void testEquals_SameObject_Success() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertTrue(sprint.equals(sprint));
    }

    @Test
    void testNull_SameObject_Success() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber, period, sprintBacklog, sprintStatus);
        assertFalse(sprint.equals(null));
    }

    @Test
    void testEquals_DifferentClass_ReturnsFalse() {
        //Arrange
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        String string = "String";

        //Act
        boolean result = sprint.equals(string);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        SprintID sprintID = mock(SprintID.class);
        Period period = mock(Period.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertEquals(sprint1.hashCode(), sprint2.hashCode());
    }

    @Test
    public void testHashCode_Fail() {
        SprintID sprintID = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        Period period = mock(Period.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode, sprintID2, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertNotEquals(sprint1.hashCode(), sprint2.hashCode());
    }

    @Test
    public void testIdentity() {
        SprintID sprintID = mock(SprintID.class);
        Period period = mock(Period.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertNotNull(sprint.identity());
    }

    @Test
    void sameIDAs_shouldReturnTrue_whenSprintIDAndProjectCodeAreEqual() {
        // Arrange
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode, sprintID, null,
                null, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode, sprintID, null,
                null, sprintBacklog, sprintStatus);


        // Act
        boolean result = sprint1.sameIDAs(sprint2);

        // Assert
        assertTrue(result);
    }

    @Test
    void sameIDAs_shouldReturnFalse_whenSprintIDIsDifferent() {
        // Arrange
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode, sprintID1, null,
                null, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode, sprintID2, null,
                null, sprintBacklog, sprintStatus);

        // Act
        boolean result = sprint1.sameIDAs(sprint2);

        // Assert
        assertFalse(result);
    }

    @Test
    void sameIDAs_False() {
        // Arrange
        SprintID sprintID = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        ProjectCode projectCode1 = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode1, sprintID, null, null, sprintBacklog, sprintStatus);
        Sprint sprint2 = new Sprint(projectCode2, sprintID2, null, null, sprintBacklog, sprintStatus);

        // Act
        boolean result = sprint1.sameIDAs(sprint2);

        // Assert
        assertFalse(result);
    }

    @Test
    void sameIDAs_False_NotInstanceOfSprint() {
        // Arrange
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode1 = mock(ProjectCode.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = new Sprint(projectCode1, sprintID, null, null, sprintBacklog, sprintStatus);


        // Act
        boolean result = sprint1.sameIDAs(projectCode1);

        // Assert
        assertFalse(result);
    }

    @Test
    void testEquals_sameSprintID_Success() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(2));
        SprintID sprintID = new SprintID("S01");
        ProjectCode projectCode = new ProjectCode("PRJ1");
        SprintNumber sprintNumber = new SprintNumber(3);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);


        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        assertTrue(sprint.getSprintID().equals(sprintID));
    }


    @Test
    void testEquals_null_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);

        Sprint sprint1 = null;
        boolean expected = false;
        boolean result = sprint.equals(sprint1);
        assertEquals(expected, result);
    }

    @Test
    void testEquals_sameSprintID_Success_unit() {
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID, sprintNumber,
                period, sprintBacklog, sprintStatus);
        assertTrue(sprint.getSprintID().equals(sprintID));
    }

    /**
     * Test class for {@link Sprint#getScrumBoardList()}.
     */
    @Test
    void getScrumBoard_Regular_Success() {
        //Arrange
        Period mock_period = mock(Period.class);
        SprintID mock_sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, mock_sprintID, sprintNumber,
                mock_period, sprintBacklog, sprintStatus);

        UserStoryID userStoryDouble1 = mock(UserStoryID.class);
        UserStoryID userStoryDouble2 = mock(UserStoryID.class);
        UserStoryID userStoryDouble3 = mock(UserStoryID.class);
        UserStoryID userStoryDouble4 = mock(UserStoryID.class);

        sprint.addUserStoryToSprintBacklog(userStoryDouble1);
        sprint.addUserStoryToSprintBacklog(userStoryDouble2);
        sprint.addUserStoryToSprintBacklog(userStoryDouble3);
        sprint.addUserStoryToSprintBacklog(userStoryDouble4);

        List<UserStoryID> expected = new ArrayList<>();
        expected.add(userStoryDouble1);
        expected.add(userStoryDouble2);
        expected.add(userStoryDouble3);
        expected.add(userStoryDouble4);

        when(sprintBacklog.getSprintBacklog()).thenReturn(expected);

        //Act
        List<UserStoryID> result = sprint.getScrumBoardList();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link Sprint#getScrumBoardList()}.
     */
    @Test
    void getScrumBoard_ensureTheListIsEmpty_Fail() {
        //Arrange
        Period mock_period = mock(Period.class);
        SprintID mock_sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);
        Sprint sprint = new Sprint(projectCode, mock_sprintID, sprintNumber,
                mock_period, sprintBacklog, sprintStatus);

        UserStoryID userStoryDouble1 = mock(UserStoryID.class);
        UserStoryID userStoryDouble2 = mock(UserStoryID.class);
        UserStoryID userStoryDouble3 = mock(UserStoryID.class);
        UserStoryID userStoryDouble4 = mock(UserStoryID.class);

        sprint.addUserStoryToSprintBacklog(userStoryDouble1);
        sprint.addUserStoryToSprintBacklog(userStoryDouble2);
        sprint.addUserStoryToSprintBacklog(userStoryDouble3);
        sprint.addUserStoryToSprintBacklog(userStoryDouble4);

        List<UserStoryID> sprintBacklogList = List.of(userStoryDouble1,
                userStoryDouble2, userStoryDouble3, userStoryDouble4);

        when(sprintBacklog.getSprintBacklog()).thenReturn(sprintBacklogList);

        List<UserStoryID> expected = new ArrayList<>();

        //Act
        List<UserStoryID> result = sprint.getScrumBoardList();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void setSprintBacklog_ensureTheSprintBacklogIsSet() {
        Period mock_period = mock(Period.class);
        SprintID mock_sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        SprintBacklog sprintBacklog = new SprintBacklog(new SprintBacklogID("SB1"));
        Sprint sprint = new Sprint(projectCode, mock_sprintID, sprintNumber,
                mock_period, sprintBacklog, sprintStatus);

        UserStoryID userStoryDouble1 = mock(UserStoryID.class);
        UserStoryID userStoryDouble2 = mock(UserStoryID.class);
        UserStoryID userStoryDouble3 = mock(UserStoryID.class);
        UserStoryID userStoryDouble4 = mock(UserStoryID.class);

        List<UserStoryID> sprintBacklogList = List.of(userStoryDouble1,
                userStoryDouble2, userStoryDouble3, userStoryDouble4);

        //Act
        sprint.setSprintBacklog(sprintBacklogList);

        List<UserStoryID> result = sprint.getSprintBacklog().getSprintBacklog();

        //Assert
        assertEquals(sprintBacklogList, result);
    }
}
