package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.AccountMapper;
import org.switch2022.project.mapper.SprintDTO;
import org.switch2022.project.mapper.SprintMapper;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.ListSprintBacklogImpl;
import org.switch2022.project.service.interfaces.ListSprintsInProjectService;
import org.switch2022.project.service.interfaces.SetSprintStatusService;
import org.switch2022.project.service.interfaces.SprintService;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SprintController.class)
class SprintControllerTest {
    @MockBean
    Sprint sprint;
    @MockBean
    SprintID sprintID;
    @MockBean
    SprintNumber sprintNumber;
    @MockBean
    Period period;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    private SprintStatus sprintStatus;
    @MockBean
    private SprintDTO sprintDTO;

    @MockBean
    private SprintService sprintService;
    @MockBean
    private ListSprintsInProjectService listSprintsInProjectService;
    @MockBean
    private ListSprintBacklogImpl listSprintBacklog;
    @MockBean
    private SetSprintStatusService setSprintStatusService;

    @Autowired
    SprintController controllerUnderTest;

    /**
     * Unit Test for {@link SprintController#getSprintsInProject(String)}
     */
    @Test
    void createSprint_Success() {
        //Arrange
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        String endDate_input = "2023-06-17";
        LocalDate startDate_InputTolLocalDate = DateManagement.toLocalDate(startDate_input);
        LocalDate endDate_InputTolLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "PRJ1";
        String status = "planned";

        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber);
        when(sprint.getPeriod()).thenReturn(period);
        when(sprint.getProjectCode()).thenReturn(projectCode);

        when(sprintID.getSprintID()).thenReturn(sprintID_input);
        when(sprintNumber.getNumber()).thenReturn(sprintNumber_input);
        when(period.getStartDate()).thenReturn(startDate_InputTolLocalDate);
        when(period.getEndDate()).thenReturn(endDate_InputTolLocalDate);
        when(projectCode.getProjectCode()).thenReturn(projectCode_input);
        when(sprintStatus.getDescription()).thenReturn(sprintID_input);

        when(sprintService.createAndSaveSprint(sprintID_input, sprintNumber_input, startDate_input,
                endDate_input, projectCode_input)).thenReturn(sprint);

        SprintDTO sprintIDTO = new SprintDTO();
        sprintIDTO.setSprintID(sprintID_input);
        sprintIDTO.setSprintNumber(sprintNumber_input);
        sprintIDTO.setStartDate(startDate_input);
        sprintIDTO.setEndDate(endDate_input);
        sprintIDTO.setProjectCode(projectCode_input);
        sprintIDTO.setSprintStatus(status);

        ResponseEntity<Object> expected = new ResponseEntity<>(sprintIDTO, HttpStatus.CREATED);

        try (MockedStatic<SprintMapper> mapperDouble = Mockito.mockStatic(SprintMapper.class)) {
            mapperDouble.when(() -> SprintMapper.toDTO(sprint)).thenReturn(sprintIDTO);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.createSprint(projectCode_input, sprintIDTO);

            //Assert
            assertEquals(expected, result);
            assertEquals(HttpStatus.CREATED, result.getStatusCode());
        }
    }

    @Test
    void createSprint_FailBecauseProjectCodeDoesntExist() {
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        String endDate_input = "2023-06-17";
        String projectCode_input = " ";

        SprintDTO sprintIDTO = new SprintDTO();
        sprintIDTO.setSprintID(sprintID_input);
        sprintIDTO.setSprintNumber(sprintNumber_input);
        sprintIDTO.setStartDate(startDate_input);
        sprintIDTO.setEndDate(endDate_input);
        sprintIDTO.setProjectCode(projectCode_input);

        IllegalStateException exception =
                new IllegalStateException("Project code not found, cannot create a sprint.");
        when(sprintService.createAndSaveSprint(sprintID_input,
                sprintNumber_input,
                startDate_input, endDate_input,
                projectCode_input))
                .thenThrow(exception);

        ResponseEntity<Object> result = controllerUnderTest.createSprint(projectCode_input, sprintIDTO);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }

    @Test
    void createSprint_FailBecauseInvalidInputs() {
        String sprintID_input = " ";
        int sprintNumber_input = 1;
        String startDate_input = "20222-05-17";
        String endDate_input = "202222-06-17";
        String projectCode_input = " ";

        SprintDTO sprintIDTO = new SprintDTO();
        sprintIDTO.setSprintID(sprintID_input);
        sprintIDTO.setSprintNumber(sprintNumber_input);
        sprintIDTO.setStartDate(startDate_input);
        sprintIDTO.setEndDate(endDate_input);
        sprintIDTO.setProjectCode(projectCode_input);

        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid input parameters to create a sprint.");

        when(sprintService.createAndSaveSprint(sprintID_input,
                sprintNumber_input,
                startDate_input, endDate_input,
                projectCode_input))
                .thenThrow(exception);

        ResponseEntity<Object> result = controllerUnderTest.createSprint(projectCode_input, sprintIDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }

    /**
     * Unit Test for {@link SprintController#getSprintsInProject(String)}
     */
    @Test
    public void getSprintsInProject() {
        // Arrange
        String projectCode_input = "PRJ1";

        SprintDTO sprintDTO = mock(SprintDTO.class);
        List<SprintDTO> sprintsDTO = List.of(sprintDTO);
        when(listSprintsInProjectService.getSprintsInProject(projectCode_input)).thenReturn(sprintsDTO);

        ResponseEntity<Object> expected = new ResponseEntity<>(sprintsDTO, HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = controllerUnderTest.getSprintsInProject(projectCode_input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link SprintController#getSprintBacklog(String)}
     */
    @Test
    public void ensureGetsSprintBacklog_Success() {
        //Arrange
        UserStoryDTO uSDTO1 = mock(UserStoryDTO.class);
        UserStoryDTO uSDTO2 = mock(UserStoryDTO.class);

        String sprintID1 = "SP1";

        List<UserStoryDTO> userStoryDTOS = List.of(uSDTO1, uSDTO2);

        when(sprintID.getSprintID()).thenReturn(sprintID1);
        when(listSprintBacklog.getSprintBacklog(sprintID1)).thenReturn(userStoryDTOS);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoryDTOS, HttpStatus.OK);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.getSprintBacklog(sprintID1);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link SprintController#setSprintStatus(SprintDTO)}
     */
    @Test
    public void setSprintStatus_Successful(){
        //ARRANGE
        String sprintIDInput = "SP1";
        ResponseEntity<Object> expected = new ResponseEntity<>(sprintDTO, HttpStatus.OK);

        when(sprintDTO.getSprintID()).thenReturn(sprintIDInput);
        when(setSprintStatusService.setSprintStatus(sprintIDInput)).thenReturn(sprint);

        try (MockedStatic<SprintMapper> mapperDouble = Mockito.mockStatic(SprintMapper.class)) {
            mapperDouble.when(() -> SprintMapper.toDTOUpdatedStatus(sprint)).thenReturn(sprintDTO);

            //ACT
            ResponseEntity<Object> result = controllerUnderTest.setSprintStatus(sprintDTO);
            //ASSERT
            assertEquals(expected, result);
        }
    }

    @Test
    public void setSprintStatus_Unsuccessful_WrongStatusOnSprint(){
        //Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Status not changed.");
        String sprintIDInput = "SP1";

        when(sprintDTO.getSprintID()).thenReturn(sprintIDInput);
        when(setSprintStatusService.setSprintStatus(sprintIDInput)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.setSprintStatus(sprintDTO);
        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    @Test
    public void setSprintStatus_Unsuccessful_WrongID(){
        //Arrange
        IllegalStateException exception = new IllegalStateException("Sprint does not exist.");
        String sprintIDInput = "SP1";

        when(sprintDTO.getSprintID()).thenReturn(sprintIDInput);
        when(setSprintStatusService.setSprintStatus(sprintIDInput)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.setSprintStatus(sprintDTO);
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}

