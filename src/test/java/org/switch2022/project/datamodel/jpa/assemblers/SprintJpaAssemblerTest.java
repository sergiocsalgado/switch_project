package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.SprintJPA;
import org.switch2022.project.datamodel.jpa.SprintUserStoryJpa;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SprintJpaAssembler.class
)
class SprintJpaAssemblerTest {

    @MockBean
    Sprint sprint;

    @MockBean
    SprintJPA sprintJPA;

    @MockBean
    ProjectCode projectCode;

    @MockBean
    SprintID sprintID;

    @MockBean
    SprintNumber sprintNumber;

    @MockBean
    Period period;

    @MockBean
    SprintBacklog sprintBacklog;

    @MockBean
    UserStoryID userStoryID;

    @MockBean
    SprintUserStoryJpa sprintUserStoryJpa;

    @MockBean
    SprintStatus sprintStatus;
    @Test
    void toDataModel() {
        String projectCodeInput = "PRJ1";
        String sprintIDInput = "SP01";
        int sprintNumberInput = 1;
        String startDateInput = "2023-10-10";
        String endDateInput = "2024-10-10";
        String sprintStatusInput = "planned";

        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);

        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintID()).thenReturn(sprintIDInput);

        when(sprint.getSprintNumber()).thenReturn(sprintNumber);
        when(sprintNumber.getNumber()).thenReturn(sprintNumberInput);

        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        when(sprint.getPeriod()).thenReturn(period);

        when(period.getStartDate()).thenReturn(startDate);
        when(period.getEndDate()).thenReturn(endDate);
        when(startDate.toString()).thenReturn(startDateInput);
        when(endDate.toString()).thenReturn(endDateInput);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusInput);

        List<UserStoryID> userStoryIDS = Collections.singletonList(userStoryID);

        when(sprint.getScrumBoardList()).thenReturn(userStoryIDS);

        String idUserStory = "us1";
        when(userStoryID.getUserStoryID()).thenReturn(idUserStory);

        SprintJPA sprintJPA = SprintJpaAssembler.toDataModel(sprint);

        assertEquals(projectCodeInput, sprintJPA.getProjectCode());
        assertEquals(sprintIDInput, sprintJPA.getSprintID());
        assertEquals(sprintNumberInput, sprintJPA.getSprintNumber());
        assertEquals(startDateInput, sprintJPA.getStartDate());
        assertEquals(endDateInput, sprintJPA.getEndDate());
        assertEquals(sprintStatusInput, sprint.getSprintStatus().getDescription());
    }

    @Test
    void toDomain() {
        String projectCodeInput = "PRJ1";
        String sprintIDInput = "SP01";
        int sprintNumberInput = 1;
        String startDateInput = "2023-10-10";
        String endDateInput = "2024-10-10";
        String sprintBacklogInput = "SPBL1";
        String sprintStatusInput = "planned";

        when(sprintJPA.getStartDate()).thenReturn(startDateInput);
        when(sprintJPA.getEndDate()).thenReturn(endDateInput);

        when(sprintJPA.getSprintID()).thenReturn(sprintIDInput);

        when(sprintJPA.getSprintNumber()).thenReturn(sprintNumberInput);

        when(sprintJPA.getProjectCode()).thenReturn(projectCodeInput);

        List<SprintUserStoryJpa> sprintUserStoryJpas = Collections.singletonList(sprintUserStoryJpa);

        when(sprintJPA.getSprintUserStories()).thenReturn(sprintUserStoryJpas);

        when(sprintJPA.getSprintStatus()).thenReturn(sprintStatusInput);

        try (MockedStatic<SprintUserStoryJpaAssembler> assemblerDoubler = Mockito.mockStatic(SprintUserStoryJpaAssembler.class)) {
            assemblerDoubler.when(() -> SprintUserStoryJpaAssembler.toDomain(sprintUserStoryJpa)).thenReturn(userStoryID);


            Sprint sprint = SprintJpaAssembler.toDomain(sprintJPA);

            assertEquals(projectCodeInput, sprint.getProjectCode().getProjectCode());
            assertEquals(sprintIDInput, sprint.getSprintID().getSprintID());
            assertEquals(sprintNumberInput, sprint.getSprintNumber().getNumber());
            assertEquals(startDateInput, sprint.getPeriod().getStartDate().toString());
            assertEquals(endDateInput, sprint.getPeriod().getEndDate().toString());
            assertEquals(sprintBacklogInput, sprint.getSprintBacklog().getSprintBacklogID().getIdDescription());
            assertEquals(sprintStatusInput, sprint.getSprintStatus().getDescription());
        }
    }
}
