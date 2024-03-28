package org.switch2022.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.sprint.FactorySprint;
import org.switch2022.project.model.sprint.FactorySprintBacklog;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateSprintServiceImpl.class)
class CreateSprintServiceImplTest {
    @MockBean
    Sprint sprint;
    @MockBean
    SprintNumber sprintNumber;
    @MockBean
    SprintID sprintID;
    @MockBean
    SprintBacklogID sprintBacklogID;
    @MockBean
    SprintBacklog sprintBacklog;
    @MockBean
    Period period;
    @MockBean
    Project project;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    UserStoryID userStoryID;
    @MockBean
    UserStory userStory;
    @MockBean
    FactorySprint factorySprint;
    @MockBean
    FactorySprintBacklog factorySprintBacklog;
    @MockBean
    @Qualifier("sprintJPARepository")
    Repository<SprintID, Sprint> sprintRepository;
    @MockBean
    @Qualifier("projectJPARepository")
    Repository<ProjectCode, Project> projectRepository;
    @MockBean
    Optional optionalProject;
    @MockBean
    SprintStatus sprintStatus;

    private CreateSprintServiceImpl sprintServiceImpl;

    /**
     * This method is executed before each test case to set up the required dependencies
     * and initialize the HumanResourceService instance.
     *
     * @throws Exception if an error occurs during setup.
     */
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        sprintServiceImpl = new CreateSprintServiceImpl(
                factorySprint,
                factorySprintBacklog,
                sprintRepository,
                projectRepository);
    }

    /**
     * Test class for {@link CreateSprintServiceImpl#createAndSaveSprint(String, int, String, String, String)}
     */
    @Test
    void createAndSaveSprintWithValidAttributes_Success() {
        //Arrange
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        LocalDate startDate_inputToLocalDate = DateManagement.toLocalDate(startDate_input);
        String endDate_input = "2023-06-17";
        LocalDate endDate_inputToLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "PRJ1";
        String sprintBacklogNumber = "SB1";
        String sprintStatusInput = "planned";

        ProjectCode projectCode1 = new ProjectCode(projectCode_input);
        Period period1 = new Period(startDate_inputToLocalDate, endDate_inputToLocalDate);
        SprintID sprintID1 = new SprintID(sprintID_input);
        SprintNumber sprintNumber1 = new SprintNumber(sprintNumber_input);
        SprintBacklogID sprintBacklogID1 = new SprintBacklogID(sprintBacklogNumber);
        SprintStatus sprintStatus1 = new SprintStatus(sprintStatusInput);

        Optional<Project> optionalProj = mock(Optional.class);
        when(projectRepository.ofIdentity(projectCode1)).thenReturn(optionalProj);
        when(optionalProj.isPresent()).thenReturn(true);
        when(optionalProj.get()).thenReturn(project);

        when(factorySprintBacklog.create(sprintBacklogID1)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode1, sprintID1, sprintNumber1, period1,
                sprintBacklog, sprintStatus1)).thenReturn(sprint);

        when(sprint.getSprintID()).thenReturn(sprintID1);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber1);
        when(sprint.getPeriod()).thenReturn(period1);
        when(sprint.getProjectCode()).thenReturn(projectCode1);
        when(sprintRepository.containsOfIdentity(sprintID1)).thenReturn(false);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        Sprint expected = sprint;
        //Act
        Sprint result = sprintServiceImpl.createAndSaveSprint(sprintID_input, sprintNumber_input,
                startDate_input, endDate_input, projectCode_input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void createAndSaveSprintWithValidAttributes_Fail_ProjectDoesntExist() {
        //Arrange
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        String endDate_input = "2023-06-17";
        String projectCode_input = "PRJ1";

        ProjectCode projectCode1 = new ProjectCode(projectCode_input);

        Optional<Project> optionalProj = mock(Optional.class);
        when(projectRepository.ofIdentity(projectCode1)).thenReturn(optionalProj);
        when(optionalProj.isPresent()).thenReturn(false);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            sprintServiceImpl.createAndSaveSprint(sprintID_input, sprintNumber_input,
                    startDate_input, endDate_input, projectCode_input);
        });
        //Assert
        assertEquals("Project doesn't exist", exception.getMessage());

    }

    @Test
    void createAndSaveSprintWithValidAttributes_Fail_sprintAlreadyExists() {
        //Arrange
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        LocalDate startDate_inputToLocalDate = DateManagement.toLocalDate(startDate_input);
        String endDate_input = "2023-06-17";
        LocalDate endDate_inputToLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "PRJ1";
        String sprintBacklogNumber = "SB1";
        String sprintStatusInput = "planned";

        ProjectCode projectCode1 = new ProjectCode(projectCode_input);
        Period period1 = new Period(startDate_inputToLocalDate, endDate_inputToLocalDate);
        SprintID sprintID1 = new SprintID(sprintID_input);
        SprintNumber sprintNumber1 = new SprintNumber(sprintNumber_input);
        SprintBacklogID sprintBacklogID1 = new SprintBacklogID(sprintBacklogNumber);
        SprintStatus sprintStatus1 = new SprintStatus(sprintStatusInput);

        Optional<Project> optionalProj = mock(Optional.class);
        when(projectRepository.ofIdentity(projectCode1)).thenReturn(optionalProj);
        when(optionalProj.isPresent()).thenReturn(true);
        when(optionalProj.get()).thenReturn(project);

        when(factorySprintBacklog.create(sprintBacklogID1)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode1, sprintID1, sprintNumber1,
                period1, sprintBacklog, sprintStatus1)).thenReturn(sprint);

        when(sprint.getSprintID()).thenReturn(sprintID1);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber1);
        when(sprint.getPeriod()).thenReturn(period1);
        when(sprint.getProjectCode()).thenReturn(projectCode1);
        when(sprintRepository.containsOfIdentity(sprintID1)).thenReturn(true);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            sprintServiceImpl.createAndSaveSprint(sprintID_input, sprintNumber_input,
                    startDate_input, endDate_input, projectCode_input);
        });
        //Assert
        assertEquals("Sprint already exists", exception.getMessage());
    }


    @Test
    void createAndSaveSprintWithInvalidAttributes_Fail_InvalidDates() {
        // Arrange
        // Sprint to compare
        String sprintID_input2 = "SP03";
        int sprintNumber_input2 = 3;
        String startDate_input2 = "2023-05-17";
        LocalDate startDate_inputToLocalDate2 = DateManagement.toLocalDate(startDate_input2);
        String endDate_input2 = "2023-06-17";
        LocalDate endDate_inputToLocalDate2 = DateManagement.toLocalDate(endDate_input2);
        String projectCode_input = "PRJ1";
        String sprintBacklogNumber1 = "SB1";
        String sprintStatusInput = "planned";

        ProjectCode projectCode1 = new ProjectCode(projectCode_input);
        Period period2 = new Period(startDate_inputToLocalDate2, endDate_inputToLocalDate2);
        SprintID sprintID2 = new SprintID(sprintID_input2);
        SprintNumber sprintNumber2 = new SprintNumber(sprintNumber_input2);
        SprintBacklogID sprintBacklogID1 = new SprintBacklogID(sprintBacklogNumber1);
        SprintStatus sprintStatus1 = new SprintStatus(sprintStatusInput);


        Optional<Project> optionalProj = mock(Optional.class);
        when(projectRepository.ofIdentity(projectCode1)).thenReturn(optionalProj);
        when(optionalProj.isPresent()).thenReturn(true);
        when(optionalProj.get()).thenReturn(project);

        when(factorySprintBacklog.create(sprintBacklogID1)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode1, sprintID2, sprintNumber2, period2, sprintBacklog, sprintStatus1))
                .thenReturn(sprint);

        when(sprint.getSprintID()).thenReturn(sprintID2);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber2);
        when(sprint.getPeriod()).thenReturn(period2);
        when(sprint.getProjectCode()).thenReturn(projectCode1);
        when(sprintRepository.containsOfIdentity(sprintID2)).thenReturn(false);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        sprintServiceImpl.createAndSaveSprint(
                sprintID_input2, sprintNumber_input2, startDate_input2, endDate_input2, projectCode_input
        );

        // Sprint creation
        Sprint sprint1 = mock(Sprint.class);
        String sprintID_input = "SP04";
        int sprintNumber_input = 4;
        String startDate_input = "2023-06-01";
        LocalDate startDate_inputToLocalDate = DateManagement.toLocalDate(startDate_input);
        String endDate_input = "2023-07-01";
        LocalDate endDate_inputToLocalDate = DateManagement.toLocalDate(endDate_input);
        String sprintBacklogNumber2 = "2";
        String sprintStatusInput2 = "planned";

        Period period1 = new Period(startDate_inputToLocalDate, endDate_inputToLocalDate);
        SprintID sprintID1 = new SprintID(sprintID_input);
        SprintNumber sprintNumber1 = new SprintNumber(sprintNumber_input);
        SprintBacklogID sprintBacklogID2 = new SprintBacklogID(sprintBacklogNumber2);
        SprintStatus sprintStatus2 = new SprintStatus(sprintStatusInput2);


        when(factorySprintBacklog.create(sprintBacklogID2)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode1, sprintID1, sprintNumber1, period1, sprintBacklog, sprintStatus2))
                .thenReturn(sprint1);

        when(sprint1.getSprintID()).thenReturn(sprintID1);
        when(sprint1.getSprintNumber()).thenReturn(sprintNumber1);
        when(sprint1.getPeriod()).thenReturn(period1);
        when(sprint1.getProjectCode()).thenReturn(projectCode1);
        when(sprintRepository.containsOfIdentity(sprintID1)).thenReturn(false);
        when(sprintRepository.save(sprint1)).thenReturn(sprint1);

        when(sprintRepository.findAll()).thenReturn(List.of(sprint));

        // Act and Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            sprintServiceImpl.createAndSaveSprint(
                    sprintID_input, sprintNumber_input, startDate_input, endDate_input, projectCode_input
            );
        });

        // Assert
        assertEquals("Dates are not valid", exception.getMessage());
    }


    @Test
    void createAndSaveSprintWithInvalidAttributes_Unsuccessful_InvalidParameters() {
        //Arrange
        String sprintID_input = " ";
        int sprintNumber_input = 2;
        String startDate_input = "2023-05-17";

        String endDate_input = "2023-06-17";

        String projectCode_input = "PRJ7";

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            sprintServiceImpl.createAndSaveSprint(sprintID_input, sprintNumber_input,
                    startDate_input, endDate_input, projectCode_input);
        });

        //Assert
        assertEquals("Sprint ID cannot be empty", exception.getMessage());
    }

    @Test
    void getSprintByID() {
        Optional<Sprint> expected = Optional.ofNullable(sprint);
        when(sprintRepository.ofIdentity(sprintID)).thenReturn(expected);
        Optional<Sprint> result = sprintServiceImpl.getSprintById(sprintID);
        assertEquals(expected, result);
    }

    @Test
    void ensureCreateAndSaveSprint_noSprintOnGoingAtSameTime() {
        //Arrange
        String idSprint = "SP01";
        int numberOfSprint = 1;
        int numberOfSprint2 = 2;
        String sprintStartDate = "2023-05-17";
        String sprintEndDate = "2023-06-17";
        String codeOfProject = "PRJ1";
        String sprintStatusInput = "planned";

        SprintID sprintID = new SprintID(idSprint);
        SprintNumber sprintNumber = new SprintNumber(numberOfSprint);
        SprintNumber sprintNumber2 = new SprintNumber(numberOfSprint2);
        LocalDate startDate = DateManagement.toLocalDate(sprintStartDate);
        LocalDate endDate = DateManagement.toLocalDate(sprintEndDate);
        String date = "2023-05-16";
        LocalDate endDate2 = DateManagement.toLocalDate(date);
        Period period2 = new Period(startDate, endDate);
        ProjectCode projectCode = new ProjectCode(codeOfProject);
        SprintBacklogID sprintBacklogID = new SprintBacklogID("SB1");
        SprintStatus sprintStatus1 = new SprintStatus(sprintStatusInput);

        Sprint sprintDouble = mock(Sprint.class);
        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);

        when(factorySprintBacklog.create(sprintBacklogID)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode, sprintID, sprintNumber,
                period2, sprintBacklog, sprintStatus1)).thenReturn(sprint);

        when(sprintRepository.containsOfIdentity(sprintID)).thenReturn(false);
        when(sprint.getSprintID()).thenReturn(sprintID);

        List<Sprint> sprints = Collections.singletonList(sprintDouble);

        when(sprintRepository.findAll()).thenReturn(sprints);

        when(sprintDouble.getProjectCode()).thenReturn(projectCode);
        when(sprintDouble.getSprintNumber()).thenReturn(sprintNumber2);
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber);

        when(sprint.getPeriod()).thenReturn(period2);
        when(sprintDouble.getPeriod()).thenReturn(period);
        when(period.getEndDate()).thenReturn(endDate2);

        when(sprintRepository.save(sprint)).thenReturn(sprint);

        Sprint expected = sprint;

        //Act
        Sprint result = sprintServiceImpl.createAndSaveSprint(idSprint, numberOfSprint, sprintStartDate, sprintEndDate, codeOfProject);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void sprintIsNotCreatedBecauseSprintNumberExistsInAProject_Fail() {
        // Arrange
        // First sprint
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        LocalDate startDate_inputToLocalDate = DateManagement.toLocalDate(startDate_input);
        String endDate_input = "2023-06-17";
        LocalDate endDate_inputToLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "PRJ1";
        String sprintBacklogNumber = "SB1";
        String sprintStatusInput = "planned";

        ProjectCode projectCode1 = new ProjectCode(projectCode_input);
        Period period1 = new Period(startDate_inputToLocalDate, endDate_inputToLocalDate);
        SprintID sprintID1 = new SprintID(sprintID_input);
        SprintNumber sprintNumber1 = new SprintNumber(sprintNumber_input);
        SprintBacklogID sprintBacklogID1 = new SprintBacklogID(sprintBacklogNumber);
        SprintStatus sprintStatus1 = new SprintStatus(sprintStatusInput);

        Optional<Project> optionalProj = Optional.of(project);
        when(projectRepository.ofIdentity(projectCode1)).thenReturn(optionalProj);
        when(factorySprintBacklog.create(sprintBacklogID1)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode1, sprintID1, sprintNumber1, period1, sprintBacklog, sprintStatus1)).thenReturn(sprint);
        when(sprint.getSprintID()).thenReturn(sprintID1);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber1);
        when(sprint.getPeriod()).thenReturn(period1);
        when(sprint.getProjectCode()).thenReturn(projectCode1);
        when(sprintRepository.containsOfIdentity(sprintID1)).thenReturn(false);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        // Second sprint with same sprint number
        String sprintID_input2 = "SP02";
        String startDate_input2 = "2023-07-17";
        LocalDate startDate_inputToLocalDate2 = DateManagement.toLocalDate(startDate_input2);
        String endDate_input2 = "2023-08-17";
        LocalDate endDate_inputToLocalDate2 = DateManagement.toLocalDate(endDate_input2);
        String sprintBacklogNumber2 = "SB2";
        String sprintStatusInput2 = "planned";

        Period period2 = new Period(startDate_inputToLocalDate2, endDate_inputToLocalDate2);
        SprintID sprintID2 = new SprintID(sprintID_input2);
        SprintBacklogID sprintBacklogID2 = new SprintBacklogID(sprintBacklogNumber2);
        SprintStatus sprintStatus2 = new SprintStatus(sprintStatusInput2);


        when(factorySprintBacklog.create(sprintBacklogID2)).thenReturn(sprintBacklog);
        when(factorySprint.createSprint(projectCode1, sprintID2, sprintNumber1, period2, sprintBacklog, sprintStatus2)).thenReturn(sprint);
        when(sprint.getSprintID()).thenReturn(sprintID2);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber1);
        when(sprint.getPeriod()).thenReturn(period2);
        when(sprint.getProjectCode()).thenReturn(projectCode1);
        when(sprintRepository.containsOfIdentity(sprintID2)).thenReturn(false);

        sprintServiceImpl.createAndSaveSprint(sprintID_input, sprintNumber_input,
                startDate_input, endDate_input, projectCode_input);

        List<Sprint> sprints = new ArrayList<>();
        sprints.add(sprint);

        when(sprintRepository.findAll()).thenReturn(sprints);


        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            sprintServiceImpl.createAndSaveSprint(sprintID_input2, sprintNumber_input,
                    startDate_input2, endDate_input2, projectCode_input);
        });

        // Assert
        assertEquals("Sprint already exists", exception.getMessage());
    }

}
