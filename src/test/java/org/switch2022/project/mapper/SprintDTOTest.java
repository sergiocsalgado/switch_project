package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Period;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.SprintNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintDTOTest {

    @Test
    void getSprintID() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID("sprint1");
        String expected = "sprint1";
        String result = sprintDTO.getSprintID();
        assertEquals(expected, result);
    }

    @Test
    void setSprintID() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID("sprint1");
        sprintDTO.setSprintID("sprint2");
        String expected = "sprint2";
        String result = sprintDTO.getSprintID();
        assertEquals(expected, result);
    }

    @Test
    void getSprintNumber() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintNumber(1);
        int expected = 1;
        int result = sprintDTO.getSprintNumber();
        assertEquals(expected, result);
    }

    @Test
    void setSprintNumber() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintNumber(1);
        sprintDTO.setSprintNumber(2);
        int expected = 2;
        int result = sprintDTO.getSprintNumber();
        assertEquals(expected, result);
    }

    @Test
    void getStartDate() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setStartDate("2023-05-17");
        String expected = "2023-05-17";
        String result = sprintDTO.getStartDate();
        assertEquals(expected, result);
    }

    @Test
    void setStartDate() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setStartDate("2023-05-17");
        sprintDTO.setStartDate("2023-05-21");
        String expected = "2023-05-21";
        String result = sprintDTO.getStartDate();
        assertEquals(expected, result);
    }

    @Test
    void getEndDate() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setEndDate("2023-05-17");
        String expected = "2023-05-17";
        String result = sprintDTO.getEndDate();
        assertEquals(expected, result);
    }

    @Test
    void setEndDate() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setEndDate("2023-05-17");
        sprintDTO.setEndDate("2023-05-19");
        String expected = "2023-05-19";
        String result = sprintDTO.getEndDate();
        assertEquals(expected, result);
    }

    @Test
    void getProjectCode() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("PRJ1");

        String expected = "PRJ1";
        String result = sprintDTO.getProjectCode();
        assertEquals(expected, result);
    }

    @Test
    void setProjectCode() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("PRJ1");
        sprintDTO.setProjectCode("PRJ2");
        String expected = "PRJ2";
        String result = sprintDTO.getProjectCode();
        assertEquals(expected, result);
    }

    @Test
    void testHashCode_objectsAreTheSame() {
        SprintDTO sprintDTO = new SprintDTO();
        SprintDTO sprintDTO2 = new SprintDTO();
        assertEquals(sprintDTO.hashCode(), sprintDTO2.hashCode());
    }

    @Test
    void testHashCode_AttributesOfObjectHaveEqualHashCode() {
        String sprintID = "sprint";
        int sprintNumber = 1;
        String startDate = "2023-05-17";
        String endDate = "2023-06-17";
        String projectCode = "prj1";

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID(sprintID);
        sprintDTO.setSprintNumber(sprintNumber);
        sprintDTO.setStartDate(startDate);
        sprintDTO.setEndDate(endDate);
        sprintDTO.setProjectCode(projectCode);
        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID(sprintID);
        sprintDTO2.setSprintNumber(sprintNumber);
        sprintDTO2.setStartDate(startDate);
        sprintDTO2.setEndDate(endDate);
        sprintDTO2.setProjectCode(projectCode);

        assertEquals(sprintDTO.hashCode(), sprintDTO2.hashCode());
        assertEquals(sprintDTO, sprintDTO2);
        assertEquals(sprintDTO,sprintDTO2);
    }

    @Test
    void testHashCode_AttributesOfObjectDoesntHaveEqualHashCode() {
        String sprintID = "sprint";
        int sprintNumber = 1;
        String startDate = "2023-05-17";
        String endDate = "2023-06-17";
        String projectCode = "prj1";

        String sprintID2 = "sprint2";
        int sprintNumber2 = 2;
        String startDate2 = "2023-05-18";
        String endDate2 = "2023-06-18";
        String projectCode2 = "prj2";

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID(sprintID);
        sprintDTO.setSprintNumber(sprintNumber);
        sprintDTO.setStartDate(startDate);
        sprintDTO.setEndDate(endDate);
        sprintDTO.setProjectCode(projectCode);
        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID(sprintID2);
        sprintDTO2.setSprintNumber(sprintNumber2);
        sprintDTO2.setStartDate(startDate2);
        sprintDTO2.setEndDate(endDate2);
        sprintDTO2.setProjectCode(projectCode2);

        assertNotEquals(sprintDTO.hashCode(), sprintDTO2.hashCode());
        assertNotEquals(sprintDTO, sprintDTO2);
        assertNotEquals(sprintDTO,sprintDTO2);
    }

    @Test
    void testHashCode_DifferentStartDate() {
        String sprintID = "sprint";
        int sprintNumber = 1;
        String startDate = "2000-05-17";
        String endDate = "2023-06-17";
        String projectCode = "prj1";

        String sprintID2 = "sprint";
        int sprintNumber2 = 1;
        String startDate2 = "2023-05-17";
        String endDate2 = "2023-06-17";
        String projectCode2 = "prj1";

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID(sprintID);
        sprintDTO.setSprintNumber(sprintNumber);
        sprintDTO.setStartDate(startDate);
        sprintDTO.setEndDate(endDate);
        sprintDTO.setProjectCode(projectCode);
        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID(sprintID2);
        sprintDTO2.setSprintNumber(sprintNumber2);
        sprintDTO2.setStartDate(startDate2);
        sprintDTO2.setEndDate(endDate2);
        sprintDTO2.setProjectCode(projectCode2);

        assertNotEquals(sprintDTO.hashCode(), sprintDTO2.hashCode());
        assertNotEquals(sprintDTO, sprintDTO2);
    }

    @Test
    void testHashCode_DifferentEndDate() {
        String sprintID = "sprint";
        int sprintNumber = 1;
        String startDate = "2023-05-17";
        String endDate = "2000-06-17";
        String projectCode = "prj1";

        String sprintID2 = "sprint";
        int sprintNumber2 = 1;
        String startDate2 = "2023-05-17";
        String endDate2 = "2023-06-17";
        String projectCode2 = "prj1";

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID(sprintID);
        sprintDTO.setSprintNumber(sprintNumber);
        sprintDTO.setStartDate(startDate);
        sprintDTO.setEndDate(endDate);
        sprintDTO.setProjectCode(projectCode);
        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID(sprintID2);
        sprintDTO2.setSprintNumber(sprintNumber2);
        sprintDTO2.setStartDate(startDate2);
        sprintDTO2.setEndDate(endDate2);
        sprintDTO2.setProjectCode(projectCode2);

        assertNotEquals(sprintDTO.hashCode(), sprintDTO2.hashCode());
        assertNotEquals(sprintDTO, sprintDTO2);
    }

    @Test
    void testHashCode_DifferentProjectCode() {
        String sprintID = "sprint";
        int sprintNumber = 1;
        String startDate = "2023-05-17";
        String endDate = "2023-06-17";
        String projectCode = "prj1";

        String sprintID2 = "sprint";
        int sprintNumber2 = 1;
        String startDate2 = "2023-05-17";
        String endDate2 = "2023-06-17";
        String projectCode2 = "prj1000";

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID(sprintID);
        sprintDTO.setSprintNumber(sprintNumber);
        sprintDTO.setStartDate(startDate);
        sprintDTO.setEndDate(endDate);
        sprintDTO.setProjectCode(projectCode);
        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID(sprintID2);
        sprintDTO2.setSprintNumber(sprintNumber2);
        sprintDTO2.setStartDate(startDate2);
        sprintDTO2.setEndDate(endDate2);
        sprintDTO2.setProjectCode(projectCode2);

        assertNotEquals(sprintDTO.hashCode(), sprintDTO2.hashCode());
        assertNotEquals(sprintDTO, sprintDTO2);
    }

    @Test
    void sameInstance_Success() {
        SprintDTO sprintDTO = new SprintDTO();
        String sprintID2 = "sprint2";
        int sprintNumber2 = 2;
        String startDate2 = "2023-05-17";
        String endDate2 = "2023-06-17";
        String projectCode2 = "prj2";

        sprintDTO.setSprintID(sprintID2);
        sprintDTO.setSprintNumber(sprintNumber2);
        sprintDTO.setStartDate(startDate2);
        sprintDTO.setEndDate(endDate2);
        sprintDTO.setProjectCode(projectCode2);
        assertInstanceOf(SprintDTO.class, sprintDTO);
    }

    @Test
    void sameInstance_Fail() {
        Profile profile = mock(Profile.class);

        SprintDTO sprintDTO = new SprintDTO();
        String sprintID2 = "sprint2";
        int sprintNumber2 = 2;
        String startDate2 = "2023-05-17";
        String endDate2 = "2023-06-17";
        String projectCode2 = "prj2";

        sprintDTO.setSprintID(sprintID2);
        sprintDTO.setSprintNumber(sprintNumber2);
        sprintDTO.setStartDate(startDate2);
        sprintDTO.setEndDate(endDate2);
        sprintDTO.setProjectCode(projectCode2);

        assertNotEquals(profile, sprintDTO);
    }

    @Test
    void equals_Null() {
        SprintDTO sprintDTO = null;
        SprintDTO sprintDTO2 = new SprintDTO();

        assertFalse(sprintDTO2.equals(sprintDTO));
    }

    @Test
    void equals_Sucess() {
        SprintDTO sprintDTO = new SprintDTO();
        SprintID sprintID = new SprintID("sprint1");
        SprintNumber sprintNumber = new SprintNumber(1);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(3));
        ProjectCode projectCode = new ProjectCode("PRJ1");

        sprintDTO.setSprintID(sprintID.getSprintID());
        sprintDTO.setSprintNumber(sprintNumber.getNumber());
        sprintDTO.setStartDate(period.getStartDate().toString());
        sprintDTO.setEndDate(period.getEndDate().toString());
        sprintDTO.setProjectCode(projectCode.getProjectCode());

        SprintDTO sprintDTO2 = sprintDTO;

        assertEquals(sprintDTO, sprintDTO2);

    }

    @Test
    void equals_Fail() {
        SprintDTO sprintDTO = new SprintDTO();
        SprintID sprintID = new SprintID("sprint1");
        SprintNumber sprintNumber = new SprintNumber(1);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(3));
        ProjectCode projectCode = new ProjectCode("PRJ1");

        sprintDTO.setSprintID(sprintID.getSprintID());
        sprintDTO.setSprintNumber(sprintNumber.getNumber());
        sprintDTO.setStartDate(period.getStartDate().toString());
        sprintDTO.setEndDate(period.getEndDate().toString());
        sprintDTO.setProjectCode(projectCode.getProjectCode());

        SprintDTO sprintDTO2 = new SprintDTO();
        SprintID sprintID2 = new SprintID("sprint2");
        SprintNumber sprintNumber2 = new SprintNumber(2);
        Period period2 = new Period(LocalDate.now().plusDays(1), LocalDate.now().plusWeeks(4));
        ProjectCode projectCode2 = new ProjectCode("PRJ2");

        sprintDTO2.setSprintID(sprintID2.getSprintID());
        sprintDTO2.setSprintNumber(sprintNumber2.getNumber());
        sprintDTO2.setStartDate(period2.getStartDate().toString());
        sprintDTO2.setEndDate(period2.getEndDate().toString());
        sprintDTO2.setProjectCode(projectCode2.getProjectCode());

        assertNotEquals(sprintDTO, sprintDTO2);
        assertFalse(sprintDTO.equals(sprintDTO2));
    }

    @Test
    void equals_Fail_twoDifferentObjects() {
        SprintDTO sprintDTO = new SprintDTO();
        SprintID sprintID = new SprintID("sprint1");
        SprintNumber sprintNumber = new SprintNumber(1);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(3));
        ProjectCode projectCode = new ProjectCode("PRJ1");

        sprintDTO.setSprintID(sprintID.getSprintID());
        sprintDTO.setSprintNumber(sprintNumber.getNumber());
        sprintDTO.setStartDate(period.getStartDate().toString());
        sprintDTO.setEndDate(period.getEndDate().toString());
        sprintDTO.setProjectCode(projectCode.getProjectCode());

        Object obj = new Object();

        assertNotEquals(sprintDTO, obj);
        assertFalse(sprintDTO.equals(obj));
    }

    @Test
    void testEquals_SameObject_ReturnsTrue() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID("S001");
        sprintDTO.setStartDate("2023-01-01");
        sprintDTO.setEndDate("2023-01-07");
        sprintDTO.setProjectCode("P001");

        assertTrue(sprintDTO.equals(sprintDTO));
    }

    @Test
    void testEquals_EqualObjects_ReturnsTrue() {
        SprintDTO sprintDTO1 = new SprintDTO();
        sprintDTO1.setSprintID("S001");
        sprintDTO1.setStartDate("2023-01-01");
        sprintDTO1.setEndDate("2023-01-07");
        sprintDTO1.setProjectCode("P001");

        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID("S001");
        sprintDTO2.setStartDate("2023-01-01");
        sprintDTO2.setEndDate("2023-01-07");
        sprintDTO2.setProjectCode("P001");

        assertEquals(sprintDTO1,sprintDTO2);
        assertEquals(sprintDTO2,sprintDTO1);
    }

    @Test
    void testEquals_NullObject_ReturnsFalse() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID("S001");
        sprintDTO.setStartDate("2023-01-01");
        sprintDTO.setEndDate("2023-01-07");
        sprintDTO.setProjectCode("P001");

        assertFalse(sprintDTO.equals(null));
    }

    @Test
    void testEquals_DifferentClass_ReturnsFalse() {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintID("S001");
        sprintDTO.setStartDate("2023-01-01");
        sprintDTO.setEndDate("2023-01-07");
        sprintDTO.setProjectCode("P001");

        String otherObject = "not a SprintDTO";

        assertNotEquals(sprintDTO,otherObject);
    }

    @Test
    void testEquals_DifferentSprintID_ReturnsFalse() {
        SprintDTO sprintDTO1 = new SprintDTO();
        sprintDTO1.setSprintID("S001");
        sprintDTO1.setStartDate("2023-01-01");
        sprintDTO1.setEndDate("2023-01-07");
        sprintDTO1.setProjectCode("P001");

        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID("S002");
        sprintDTO2.setStartDate("2023-01-01");
        sprintDTO2.setEndDate("2023-01-07");
        sprintDTO2.setProjectCode("P001");

        assertNotEquals(sprintDTO1,sprintDTO2);
        assertNotEquals(sprintDTO2,sprintDTO1);
    }
}
