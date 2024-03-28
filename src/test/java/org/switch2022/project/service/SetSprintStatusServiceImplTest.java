package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.SprintStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is responsible for changing the status of the sprint from null to Open, and Open to Closed.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SetSprintStatusServiceImpl.class)
class SetSprintStatusServiceImplTest {
    @MockBean Sprint sprint;

    @MockBean
    @Qualifier("sprintJPARepository")
    Repository<SprintID, Sprint> sprintRepository;

    @Autowired
    private SetSprintStatusServiceImpl serviceUnderTest;

    @Test
    public void setSprintStatus_ensureStatusIsSetToOpen(){
        //Arrange
        String sprintStatusDescription = "planned";
        SprintStatus sprintStatus = mock(SprintStatus.class);

        String projectCodeInput = "PRJ1";
        ProjectCode projectCode = mock(ProjectCode.class);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription);

        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);

        when(sprintRepository.save(sprint)).thenReturn(sprint);

        //Act
        Sprint result = serviceUnderTest.setSprintStatus(sprintID_Input);
        //Assert
        assertEquals(sprint, result);
    }

    @Test
    public void setSprintStatus_Successful_NoOtherSprintInSameProject(){
        //Arrange
        Sprint sprintDouble = mock(Sprint.class);

        String sprintStatusDescription = "planned";
        SprintStatus sprintStatus = mock(SprintStatus.class);

        String projectCodeInput = "PRJ1";
        String otherProjectCode = "PRJ2";
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription);

        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);

        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprintDouble);
        when(sprintRepository.findAll()).thenReturn(sprintList);


        when(sprintDouble.getProjectCode()).thenReturn(projectCode2);
        when(projectCode2.getProjectCode()).thenReturn(otherProjectCode);

        when(sprintRepository.save(sprint)).thenReturn(sprint);

        //Act
        Sprint result = serviceUnderTest.setSprintStatus(sprintID_Input);

        //Assert
        assertEquals(sprint, result);
    }

    @Test
    public void setSprintStatus_Successful_NoOtherSprintInSameProjectWithStatusOpen(){
        //Arrange
        Sprint sprintDouble = mock(Sprint.class);

        String sprintStatusDescription = "planned";
        String sprintStatusDescription2 = "closed";
        SprintStatus sprintStatus = mock(SprintStatus.class);
        SprintStatus sprintStatus2 = mock(SprintStatus.class);

        String projectCodeInput = "PRJ1";
        String otherProjectCode = "PRJ2";
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription);

        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);

        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprintDouble);
        when(sprintRepository.findAll()).thenReturn(sprintList);


        when(sprintDouble.getProjectCode()).thenReturn(projectCode2);
        when(projectCode2.getProjectCode()).thenReturn(projectCodeInput);

        when(sprintDouble.getSprintStatus()).thenReturn(sprintStatus2);
        when(sprintStatus2.getDescription()).thenReturn(sprintStatusDescription2);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        //Act
        Sprint result = serviceUnderTest.setSprintStatus(sprintID_Input);

        //Assert
        assertEquals(sprint, result);
    }

    @Test
    public void setSprintStatus_Fail_AlreadyExistSprintOpenInSameProject(){
        //Arrange
        Sprint sprintDouble = mock(Sprint.class);

        String sprintStatusDescription = "planned";
        String sprintStatusDescription2 = "closed";
        SprintStatus sprintStatus = mock(SprintStatus.class);
        SprintStatus sprintStatus2 = mock(SprintStatus.class);

        String projectCodeInput = "PRJ1";
        String otherProjectCode = "PRJ2";
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription);

        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);

        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprintDouble);
        when(sprintRepository.findAll()).thenReturn(sprintList);


        when(sprintDouble.getProjectCode()).thenReturn(projectCode2);
        when(projectCode2.getProjectCode()).thenReturn(projectCodeInput);

        when(sprintDouble.getSprintStatus()).thenReturn(sprintStatus2);
        when(sprintStatus2.getDescription()).thenReturn("open");
        //when(sprintRepository.save(sprint)).thenReturn(sprint);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.setSprintStatus(sprintID_Input);
        });

        //Assert
        assertEquals("Already exist a open Sprint.", exception.getMessage());
    }

    @Test
    public void setSprintStatus_Fail_notPlannedSprint(){
        //Arrange
        String sprintStatusDescription2 = "closed";
        SprintStatus sprintStatus = mock(SprintStatus.class);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription2);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.setSprintStatus(sprintID_Input);
        });

        //Assert
        assertEquals("Status not changed.", exception.getMessage());
    }

    @Test
    public void setSprintStatus_ensureStatusIsSetToClosed(){
        //Arrange
        String sprintStatusDescription = "open";
        SprintStatus sprintStatus = mock(SprintStatus.class);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        //Act
        Sprint result = serviceUnderTest.setSprintStatus(sprintID_Input);
        //Assert
        assertEquals(sprint, result);
    }
    @Test
    public void setSprintStatus_ensureFailsWhenSprintIsClosed(){
        //Arrange
        String sprintStatusDescription = "closed";
        SprintStatus sprintStatus = mock(SprintStatus.class);
        when(sprintStatus.getDescription()).thenReturn(sprintStatusDescription);

        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.of(sprint));

        Optional<Sprint> sprintOptionalDouble = mock(Optional.class);
        when(sprintOptionalDouble.isPresent()).thenReturn(true);
        when(sprintOptionalDouble.get()).thenReturn(sprint);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.setSprintStatus(sprintID_Input);
        });

        //Assert
        assertEquals("Status not changed.", exception.getMessage());
    }
    @Test
    public void setSprintStatus_ensureFailsWhenSprintDoesNotExist(){
        //Arrange
        String sprintID_Input = "SP1";
        SprintID sprintID = new SprintID(sprintID_Input);

        when(sprintRepository.ofIdentity(sprintID)).thenReturn(Optional.empty());

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.setSprintStatus(sprintID_Input);
        });

        //Assert
        assertEquals("Sprint does not exist.", exception.getMessage());
    }
    @Test
    public void setSprintStatus_ensureFailsWhenSprintIDNotValid(){
        //Arrange
        String sprintID_Input = "";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.setSprintStatus(sprintID_Input);
        });

        //Assert
        assertEquals("Sprint ID cannot be empty", exception.getMessage());
    }
}
