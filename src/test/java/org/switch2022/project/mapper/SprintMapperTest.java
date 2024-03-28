package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintMapperTest {

    /**
     * Test class for {@link SprintMapper private constructor}.
     */

    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = SprintMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    @Test
    void listSprintDTO_success() {
        String sprintID_input = "sprint";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        LocalDate startDate_inputToLocalDate = DateManagement.toLocalDate(startDate_input);
        String endDate_input = "2023-06-17";
        LocalDate endDate_inputToLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "prj1";
        String sprintStatusInput = "planned";

        String sprintID2_input = "sprint2";
        int sprintNumber2_input = 2;
        String startDate2_input = "2023-05-25";
        LocalDate startDate_inputToLocalDate2 = DateManagement.toLocalDate(startDate2_input);
        String endDate2_input = "2023-06-25";
        LocalDate endDate_inputToLocalDate2 = DateManagement.toLocalDate(endDate2_input);
        String projectCode2_input = "prj2";
        String sprintStatusInput2 = "planned";

        SprintID sprintID = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        when(sprintID.getSprintID()).thenReturn(sprintID_input);
        when(sprintID2.getSprintID()).thenReturn(sprintID2_input);

        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintNumber sprintNumber2 = mock(SprintNumber.class);
        when(sprintNumber.getNumber()).thenReturn(sprintNumber_input);
        when(sprintNumber2.getNumber()).thenReturn(sprintNumber2_input);

        Period period = mock(Period.class);
        Period period2 = mock(Period.class);
        when(period.getStartDate()).thenReturn(startDate_inputToLocalDate);

        when(period2.getStartDate()).thenReturn(startDate_inputToLocalDate2);
        when(period.getEndDate()).thenReturn(endDate_inputToLocalDate);
        when(period2.getEndDate()).thenReturn(endDate_inputToLocalDate2);

        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);
        when(projectCode.getProjectCode()).thenReturn(projectCode_input);
        when(projectCode2.getProjectCode()).thenReturn(projectCode2_input);

        SprintStatus sprintStatus = mock(SprintStatus.class);
        SprintStatus sprintStatus2 = mock(SprintStatus.class);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusInput);
        when(sprintStatus2.getDescription()).thenReturn(sprintStatusInput2);

        Sprint sprint1 = mock(Sprint.class);
        when(sprint1.getSprintNumber()).thenReturn(sprintNumber);
        when(sprint1.getSprintID()).thenReturn(sprintID);
        when(sprint1.getPeriod()).thenReturn(period);
        when(sprint1.getProjectCode()).thenReturn(projectCode);
        when(sprint1.getSprintStatus()).thenReturn(sprintStatus);

        Sprint sprint2 = mock(Sprint.class);
        when(sprint2.getSprintNumber()).thenReturn(sprintNumber2);
        when(sprint2.getSprintID()).thenReturn(sprintID2);
        when(sprint2.getPeriod()).thenReturn(period2);
        when(sprint2.getProjectCode()).thenReturn(projectCode2);
        when(sprint2.getSprintStatus()).thenReturn(sprintStatus2);

        List<Sprint> sprints = new ArrayList<>();
        sprints.add(sprint1);
        sprints.add(sprint2);

        SprintDTO sprintDTO1 = new SprintDTO();
        sprintDTO1.setSprintID(sprintID_input);
        sprintDTO1.setSprintNumber(sprintNumber_input);
        sprintDTO1.setStartDate(startDate_input);
        sprintDTO1.setEndDate(endDate_input);
        sprintDTO1.setProjectCode(projectCode_input);
        sprintDTO1.setSprintStatus(sprintStatusInput);

        SprintDTO sprintDTO2 = new SprintDTO();
        sprintDTO2.setSprintID(sprintID2_input);
        sprintDTO2.setSprintNumber(sprintNumber2_input);
        sprintDTO2.setStartDate(startDate2_input);
        sprintDTO2.setEndDate(endDate2_input);
        sprintDTO2.setProjectCode(projectCode2_input);
        sprintDTO2.setSprintStatus(sprintStatusInput2);

        List<SprintDTO> expected = new ArrayList<>();
        expected.add(sprintDTO1);
        expected.add(sprintDTO2);
        List<SprintDTO> result = SprintMapper.listSprintDTO(sprints);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSprintID(), result.get(i).getSprintID());
            assertEquals(expected.get(i).getSprintNumber(), result.get(i).getSprintNumber());
            assertEquals(expected.get(i).getStartDate(), result.get(i).getStartDate());
            assertEquals(expected.get(i).getEndDate(), result.get(i).getEndDate());
            assertEquals(expected.get(i).getProjectCode(), result.get(i).getProjectCode());
            assertEquals(expected.get(i).getSprintStatus(), result.get(i).getSprintStatus());
        }

        assertEquals(expected, result);
        assertEquals(expected.size(), result.size());
    }

    @Test
    void toDTO_success() {
        String sprintID_input = "sprint";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        LocalDate startDate_inputToLocalDate = DateManagement.toLocalDate(startDate_input);
        String endDate_input = "2023-06-17";
        LocalDate endDate_inputToLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "prj1";
        String sprintStatus_input = "planned";

        SprintID sprintID = mock(SprintID.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = mock(Sprint.class);
        when(sprint1.getSprintNumber()).thenReturn(sprintNumber);
        when(sprintNumber.getNumber()).thenReturn(sprintNumber_input);

        when(sprint1.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintID()).thenReturn(sprintID_input);

        when(sprint1.getPeriod()).thenReturn(period);
        when(period.getStartDate()).thenReturn(startDate_inputToLocalDate);
        when(period.getEndDate()).thenReturn(endDate_inputToLocalDate);

        when(sprint1.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCode_input);

        when(sprint1.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatus_input);

        SprintDTO expected = mock(SprintDTO.class);
        when(expected.getSprintNumber()).thenReturn(sprintNumber_input);
        when(expected.getSprintID()).thenReturn(sprintID_input);
        when(expected.getStartDate()).thenReturn(startDate_input);
        when(expected.getEndDate()).thenReturn(endDate_input);
        when(expected.getProjectCode()).thenReturn(projectCode_input);
        when(expected.getSprintStatus()).thenReturn(sprintStatus_input);

        SprintDTO result = SprintMapper.toDTO(sprint1);
        assertEquals(expected.getSprintNumber(), result.getSprintNumber());
        assertEquals(expected.getSprintID(), result.getSprintID());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getEndDate(), result.getEndDate());
        assertEquals(expected.getProjectCode(), result.getProjectCode());
        assertEquals(expected.getSprintStatus(), result.getSprintStatus());
    }

    @Test
    void toDTOUpdatedStatus_success() {
        String sprintStatus_input = "Open";

        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint1 = mock(Sprint.class);
        when(sprint1.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatus_input);

        SprintDTO expected = mock(SprintDTO.class);
        when(expected.getSprintStatus()).thenReturn(sprintStatus_input);

        SprintDTO result = SprintMapper.toDTOUpdatedStatus(sprint1);
        assertEquals(expected.getSprintStatus(), result.getSprintStatus());
    }
}
