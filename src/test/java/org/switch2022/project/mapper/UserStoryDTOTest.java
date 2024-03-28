package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryDTOTest {

    @Test
    void ensureGetUserStoryIDReturnsCorrectUserStoryID() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryID("US3");
        String expected = "US3";

        //Act
        String result = userStoryDTO.getUserStoryID();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetUserStoryIDReturnsWrongUserStoryID() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryID("US33");
        String expected = "US3";

        //Act
        String result = userStoryDTO.getUserStoryID();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureSetUserStoryIDReturnsUserStoryIDSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryID("US2");
        userStoryDTO.setUserStoryID("US23");
        String expected = "US23";

        //Act
        String result = userStoryDTO.getUserStoryID();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSetUserStoryIDDoesNotReturnsUserStoryIDSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryID("US2");
        userStoryDTO.setUserStoryID("US23");
        String expected = "US2";

        //Act
        String result = userStoryDTO.getUserStoryID();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureGetUserStoryTextReturnsCorrectUserStoryText() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryText("create");
        String expected = "create";

        //Act
        String result = userStoryDTO.getUserStoryText();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetUserStoryTextReturnsWrongUserStoryText() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryText("create");
        String expected = "add";

        //Act
        String result = userStoryDTO.getUserStoryText();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureSetUserStoryTextReturnsUserStoryTextSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryText("create");
        userStoryDTO.setUserStoryText("add");
        String expected = "add";

        //Act
        String result = userStoryDTO.getUserStoryText();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSetUserStoryTextDoesNotReturnsUserStoryTextSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryText("create");
        userStoryDTO.setUserStoryText("add");
        String expected = "create";

        //Act
        String result = userStoryDTO.getUserStoryText();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureGetStatusReturnsCorrectStatus() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("Planned");
        String expected = "Planned";

        //Act
        String result = userStoryDTO.getStatus();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetStatusReturnsWrongStatus() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("Planned");
        String expected = "Running";

        //Act
        String result = userStoryDTO.getStatus();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureSetStatusReturnsStatusSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("Planned");
        userStoryDTO.setStatus("Running");
        String expected = "Running";

        //Act
        String result = userStoryDTO.getStatus();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSetStatusDoesNotReturnsStatusSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("Planned");
        userStoryDTO.setStatus("Running");
        String expected = "Planned";

        //Act
        String result = userStoryDTO.getStatus();

        //Assert
        assertNotEquals(expected, result);
    }
    @Test
    void ensureSetProjectCodeReturnsProjectCodeSet() {
        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setProjectCode("PRJ1");
        String expected = "PRJ1";

        //Act
        String result = userStoryDTO.getProjectCode();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void returnFalseDifferentUserStoryID() {
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setUserStoryID("US1");
        userStoryDTO1.setStatus("planned");
        userStoryDTO1.setUserStoryText("delete");

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setUserStoryID("US2");
        userStoryDTO2.setStatus("planned");
        userStoryDTO2.setUserStoryText("delete");

        boolean isEquals = userStoryDTO1.equals(userStoryDTO2);
        assertFalse(isEquals);
    }

    @Test
    void returnFalseDifferentUserStoryText() {
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setUserStoryID("US1");
        userStoryDTO1.setStatus("planned");
        userStoryDTO1.setUserStoryText("delete");

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setUserStoryID("US1");
        userStoryDTO2.setStatus("planned");
        userStoryDTO2.setUserStoryText("modify");

        boolean isEquals = userStoryDTO1.equals(userStoryDTO2);
        assertFalse(isEquals);
    }

    @Test
    void returnFalseDifferentUserStoryStatus() {
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setUserStoryID("US1");
        userStoryDTO1.setStatus("planned");
        userStoryDTO1.setUserStoryText("delete");

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setUserStoryID("US1");
        userStoryDTO2.setStatus("running");
        userStoryDTO2.setUserStoryText("delete");

        boolean isEquals = userStoryDTO1.equals(userStoryDTO2);
        assertFalse(isEquals);
    }

    @Test
    void returnTrueEqualsWithSameObject() {
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        boolean isEquals = userStoryDTO.equals(userStoryDTO);
        assertTrue(isEquals);
    }

    @Test
    void returnTrueWithDifferentObjectsButSameAttributes() {
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setUserStoryID("US1");
        userStoryDTO1.setStatus("planned");
        userStoryDTO1.setUserStoryText("delete");

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setUserStoryID("US1");
        userStoryDTO2.setStatus("planned");
        userStoryDTO2.setUserStoryText("delete");

        boolean isEquals = userStoryDTO1.getStatus().equals(userStoryDTO2.getStatus())
                && userStoryDTO1.getUserStoryID().equals(userStoryDTO2.getUserStoryID())
                && userStoryDTO1.getUserStoryText().equals(userStoryDTO2.getUserStoryText());
        assertTrue(isEquals);
    }

    @Test
    void returnFalseDifferentClasses() {
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        Object object = new Object();
        boolean isEquals = userStoryDTO.equals(object);
        assertFalse(isEquals);
    }

    @Test
    void returnFalseNullObject() {
        UserStoryDTO userStoryDTO = null;
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        boolean isEquals = userStoryDTO1.equals(userStoryDTO);
        assertFalse(isEquals);
    }


    @Test
    void HashCode_Equal() {
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setUserStoryID("US1");
        userStoryDTO1.setStatus("planned");
        userStoryDTO1.setUserStoryText("delete");

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setUserStoryID("US1");
        userStoryDTO2.setStatus("planned");
        userStoryDTO2.setUserStoryText("delete");

        assertEquals(userStoryDTO1.hashCode(), userStoryDTO2.hashCode());
    }

    @Test
    void HashCode_Different() {
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.setUserStoryID("US1");
        userStoryDTO1.setStatus("planned");
        userStoryDTO1.setUserStoryText("delete");

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.setUserStoryID("US1");
        userStoryDTO2.setStatus("running");
        userStoryDTO2.setUserStoryText("delete");

        assertNotEquals(userStoryDTO1.hashCode(), userStoryDTO2.hashCode());
    }
}
