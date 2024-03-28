package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class FactorySprintImplTest {

    @Test
    void createSprint_Success_Unit() {
        FactorySprintImpl factorySprint = new FactorySprintImpl();
        Period period = mock(Period.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintID sprintID = mock(SprintID.class);
        SprintNumber sprintNumber= mock(SprintNumber.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);

        Sprint sprint = new Sprint(projectCode, sprintID,sprintNumber ,period,sprintBacklog, sprintStatus);
        Sprint expected = sprint;

        Sprint result = factorySprint.createSprint(projectCode, sprintID,sprintNumber,
                period,sprintBacklog, sprintStatus);

        assertEquals(expected, result);
    }

    @Test
    void createSprint_Fail_AlreadyExistID_Unit() {
        FactorySprintImpl factorySprint = new FactorySprintImpl();
        Period period = mock(Period.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber= mock(SprintNumber.class);
        Sprint sprint = mock(Sprint.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);
        factorySprint.createSprint(projectCode, sprintID,sprintNumber,
                period, sprintBacklog, sprintStatus);
        Sprint expected = sprint;

        Sprint result = factorySprint.createSprint(projectCode, sprintID,sprintNumber,
                period, sprintBacklog, sprintStatus);
        assertNotEquals(expected, result);
    }

}
