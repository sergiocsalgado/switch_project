package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.mapper.ProjectDTOMapper;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.service.interfaces.DefineRoleService;
import org.switch2022.project.service.interfaces.ListAllProjectsService;
import org.switch2022.project.service.interfaces.ListResourcesService;
import org.switch2022.project.service.interfaces.RegisterProjectService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectController.class)
class ProjectControllerTest {

    @Autowired
    ProjectController projectController;

    @MockBean
    RegisterProjectService registerProjectService;

    @MockBean
    DefineRoleService defineRoleService;

    @MockBean
    ResourcesInProjectDTO resourcesInProjectDTO;

    @MockBean
    ResourceInProject resourceInProject;

    @MockBean
    ListAllProjectsService listAllProjectsService;

    @MockBean
    ListResourcesService listResourcesService;

    @MockBean
    ProjectDTO projectDTO;

    @MockBean
    Project project;

    /**
     * Unit Test for {@link ProjectController#registerProject(ProjectDTO)}
     */
    @Test
    void ensureProjectIsRegistered() {
        //Arrange
        ResponseEntity<Object> expected = new ResponseEntity<>(projectDTO, HttpStatus.CREATED);

        String customerName = "customerName";
        String bSName = "businessSectorName";
        String typologyName = "typologyName";

        when(registerProjectService.registerProject(projectDTO)).thenReturn(project);
        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(bSName);
        when(projectDTO.getTypology()).thenReturn(typologyName);

        try (MockedStatic<ProjectDTOMapper> mapperDouble = Mockito.mockStatic(ProjectDTOMapper.class)) {
            mapperDouble.when(() -> ProjectDTOMapper.toDTOWithoutIDs(project,customerName,bSName,typologyName))
                    .thenReturn(projectDTO);

            //Act
            ResponseEntity<Object> result = projectController.registerProject(projectDTO);
            //Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Unit Test for {@link ProjectController#registerProject(ProjectDTO)}
     */
    @Test
    void ensureProjectIsNotRegistered_invaliGivenParameters() {
        //Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Invalid input parameters for creating a project");

        when(registerProjectService.registerProject(projectDTO)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = projectController.registerProject(projectDTO);
        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    /**
     * Unit Test for {@link ProjectController#registerProject(ProjectDTO)}
     */
    @Test
    void ensureProjectIsNotRegistered_customerIDNotFound() {
        //Arrange
        IllegalStateException exception = new IllegalStateException("CustomerID not found for creating a project");

        when(registerProjectService.registerProject(projectDTO)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = projectController.registerProject(projectDTO);
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    /**
     * Unit Test for {@link ProjectController#defineRole(String, ResourcesInProjectDTO)}
     */
    @Test
    void ensureDefineResourceInProject() {
        //Arrange
        String projectCodeInput = "PRJ1";
        String resourceInProjectIdInput = "resource1";
        String emailInput = "test@gmail.com";
        String roleInput = "Scrum Master";
        double allocationInput = 20.0;
        double costPerHourInput = 20.0;
        String currencyInput = "EUR";
        String startDateInput = "2023-10-09";
        String endDateInput = "2024-10-09";

        when(resourcesInProjectDTO.getResourceInProjectID()).thenReturn(resourceInProjectIdInput);
        when(resourcesInProjectDTO.getEmail()).thenReturn(emailInput);
        when(resourcesInProjectDTO.getRole()).thenReturn(roleInput);
        when(resourcesInProjectDTO.getAllocation()).thenReturn(allocationInput);
        when(resourcesInProjectDTO.getCostPerHour()).thenReturn(costPerHourInput);
        when(resourcesInProjectDTO.getCurrency()).thenReturn(currencyInput);
        when(resourcesInProjectDTO.getStartDate()).thenReturn(startDateInput);
        when(resourcesInProjectDTO.getEndDate()).thenReturn(endDateInput);

        when(defineRoleService.defineRole(projectCodeInput, resourceInProjectIdInput, emailInput,
                roleInput, allocationInput, costPerHourInput, currencyInput, startDateInput,
                endDateInput)).thenReturn(resourceInProject);

        ResponseEntity<Object> expected = new ResponseEntity<>(
                resourcesInProjectDTO, HttpStatus.CREATED);

        try (MockedStatic<ResourcesInProjectMapper> mapperDouble = Mockito.mockStatic(ResourcesInProjectMapper.class)){
            mapperDouble.when(()->ResourcesInProjectMapper.toDTO(resourceInProject)).thenReturn(resourcesInProjectDTO);

            //Act
            ResponseEntity<Object>result= projectController.defineRole(
                    projectCodeInput,resourcesInProjectDTO) ;
            //Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Unit Test for {@link ProjectController#defineRole(String, ResourcesInProjectDTO)}
     */
    @Test
    void ensureResourceInProjectIsNotCreated_invaliGivenParameters() {
        //Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Invalid input parameters to define role.");

        String projectCodeInput = "";
        String resourceInProjectIdInput = "resource1";
        String emailInput = "test@gmail.com";
        String roleInput = "Scrum Master";
        double allocationInput = 20.0;
        double costPerHourInput = 20.0;
        String currencyInput = "EUR";
        String startDateInput = "2023-10-09";
        String endDateInput = "2024-10-09";

        when(resourcesInProjectDTO.getResourceInProjectID()).thenReturn(resourceInProjectIdInput);
        when(resourcesInProjectDTO.getEmail()).thenReturn(emailInput);
        when(resourcesInProjectDTO.getRole()).thenReturn(roleInput);
        when(resourcesInProjectDTO.getAllocation()).thenReturn(allocationInput);
        when(resourcesInProjectDTO.getCostPerHour()).thenReturn(costPerHourInput);
        when(resourcesInProjectDTO.getCurrency()).thenReturn(currencyInput);
        when(resourcesInProjectDTO.getStartDate()).thenReturn(startDateInput);
        when(resourcesInProjectDTO.getEndDate()).thenReturn(endDateInput);

        when(defineRoleService.defineRole(projectCodeInput, resourceInProjectIdInput, emailInput,
                roleInput, allocationInput, costPerHourInput, currencyInput, startDateInput,
                endDateInput)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = projectController.defineRole(
                projectCodeInput,resourcesInProjectDTO) ;
        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    /**
     * Unit Test for {@link ProjectController#defineRole(String, ResourcesInProjectDTO)}
     */
    @Test
    void ensureResourceInProjectIsNotCreated_ilegalState() {
        //Arrange
        IllegalStateException exception = new IllegalStateException("Project does not exists");

        String projectCodeInput = "PRJ1";
        String resourceInProjectIdInput = "resource1";
        String emailInput = "test@gmail.com";
        String roleInput = "Scrum Master";
        double allocationInput = 20.0;
        double costPerHourInput = 20.0;
        String currencyInput = "EUR";
        String startDateInput = "2023-10-09";
        String endDateInput = "2024-10-09";

        when(resourcesInProjectDTO.getResourceInProjectID()).thenReturn(resourceInProjectIdInput);
        when(resourcesInProjectDTO.getEmail()).thenReturn(emailInput);
        when(resourcesInProjectDTO.getRole()).thenReturn(roleInput);
        when(resourcesInProjectDTO.getAllocation()).thenReturn(allocationInput);
        when(resourcesInProjectDTO.getCostPerHour()).thenReturn(costPerHourInput);
        when(resourcesInProjectDTO.getCurrency()).thenReturn(currencyInput);
        when(resourcesInProjectDTO.getStartDate()).thenReturn(startDateInput);
        when(resourcesInProjectDTO.getEndDate()).thenReturn(endDateInput);

        when(defineRoleService.defineRole(projectCodeInput, resourceInProjectIdInput, emailInput,
                roleInput, allocationInput, costPerHourInput, currencyInput, startDateInput,
                endDateInput)).thenThrow(exception);

        //Act
        ResponseEntity<Object> result = projectController.defineRole(
                projectCodeInput,resourcesInProjectDTO) ;
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

    /**
     * Unit Test for {@link ProjectController#getProjects()}
     */
    @Test
    void ensureGetListOfAllProjectsDTO() {
        //Arrange
        ProjectDTO projectDTODouble = mock(ProjectDTO.class);

        List<ProjectDTO> projectDTOS = List.of(projectDTO, projectDTODouble);

        when(listAllProjectsService.getProjects()).thenReturn(projectDTOS);

        ResponseEntity<Object> expected = new ResponseEntity<>(projectDTOS, HttpStatus.OK);

        //Act
        ResponseEntity<Object> result = projectController.getProjects();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link ProjectController#resourcesInProjectDTO(String)}
     */
    @Test
    void ensureGetListOfResourcesInProject() {
        // Arrange
        String projectCode = "PRJ1";
        List<ResourcesInProjectDTO> resources = Collections.singletonList(resourcesInProjectDTO);

        when(listResourcesService.getResourcesInProjectDTO(projectCode)).thenReturn(resources);

        // Act
        ResponseEntity<Object> result = projectController.resourcesInProjectDTO(projectCode);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(resources, result.getBody());
    }

    /**
     * Unit Test for {@link ProjectController#resourcesInProjectDTO(String)}
     */
    @Test
    void ensureDoNotGetListOfResourcesInProject_invalidGivenParmeters() {
        // Arrange
        String projectCode = " ";

        IllegalArgumentException exception =
                new IllegalArgumentException("Project code cannot be null.");

        when(listResourcesService.getResourcesInProjectDTO(projectCode)).thenThrow(exception);

        // Act
        ResponseEntity<Object> result = projectController.resourcesInProjectDTO(projectCode);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }
    @Test
    void ensureDoNotGetListOfResourcesInProject_projectDoesNotExists() {
        // Arrange
        String projectCode = "PRJ1";

        IllegalStateException exception =
                new IllegalStateException("Project does not exists");

        when(listResourcesService.getResourcesInProjectDTO(projectCode)).thenThrow(exception);

        // Act
        ResponseEntity<Object> result = projectController.resourcesInProjectDTO(projectCode);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }
}