package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.FactoryResourceInProject;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the DefineRoleServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DefineRoleServiceImpl.class)
class DefineRoleServiceImplTest {
    @MockBean
    Optional optionalProject;
    @MockBean
    Period projectPeriod;
    @MockBean
    Project project;
    @MockBean
    ResourceInProject resourceInProject;

    @MockBean
    FactoryResourceInProject factoryResourceInProject;
    @MockBean
    @Qualifier("projectJPARepository")
    private Repository<ProjectCode, Project> projectRepository;

    @Autowired
    private DefineRoleServiceImpl defineRoleServiceImpl;

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String,
     * Double, Double, String, String, String)} .
     */
    @Test
    void ensureDefineTeamMemberRole() {
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "team member";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID(resourceInProjID);
        Email email = new Email(emailAddress);
        Role role = new Role(roleDescription);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);
        LocalDate endDate = DateManagement.toLocalDate(roleEndDate);

        Period period = new Period(startDate, endDate);
        Cost costPerHour = new Cost(cost, currency);
        Allocation allocation = new Allocation(percentageAllocation);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        when(project.resourceIsInProject(email, startDate)).thenReturn(false);

        when(factoryResourceInProject.create(resourceInProjectID, email, role, period, costPerHour, allocation))
                .thenReturn(resourceInProject);

        when(project.addResourceInProject(resourceInProject)).thenReturn(true);


        ResourceInProject expected = resourceInProject;
        //Act
        ResourceInProject result = defineRoleServiceImpl.defineRole(
                projCode,
                resourceInProjID,
                emailAddress,
                roleDescription,
                percentageAllocation,
                cost, currency,
                roleStartDate,
                roleEndDate);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String, Double,
     * Double, String, String, String)} .
     */
    @Test
    void ensureDefineScrumMasterRole() {
        //Arrange
        String projectStartDate = "2023-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "scrum master";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID(resourceInProjID);
        Email email = new Email(emailAddress);
        Role role = new Role(roleDescription);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);
        LocalDate endDate = DateManagement.toLocalDate(roleEndDate);

        Period period = new Period(startDate, endDate);
        Cost costPerHour = new Cost(cost, currency);
        Allocation allocation = new Allocation(percentageAllocation);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(null);

        when(project.roleDoesNotExistsInProject(role, startDate)).thenReturn(true);
        when(project.resourceIsInProject(email, startDate)).thenReturn(false);

        when(factoryResourceInProject.create(resourceInProjectID, email, role, period, costPerHour, allocation))
                .thenReturn(resourceInProject);

        when(project.addResourceInProject(resourceInProject)).thenReturn(true);


        ResourceInProject expected = resourceInProject;
        //Act
        ResourceInProject result = defineRoleServiceImpl.defineRole(
                projCode,
                resourceInProjID,
                emailAddress,
                roleDescription,
                percentageAllocation,
                cost,
                currency,
                roleStartDate,
                roleEndDate);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String, Double,
     * Double, String, String, String)} .
     */
    @Test
    void ensureDoNotDefineScrumMasterRole_resourceAlreadyInProject() {
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "scrum master";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        Email email = new Email(emailAddress);
        Role role = new Role(roleDescription);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        when(project.roleDoesNotExistsInProject(role, startDate)).thenReturn(true);

        when(project.resourceIsInProject(email, startDate)).thenReturn(true);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created",exception.getMessage());
    }

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String, Double,
     * Double, String, String, String)} .
     */
    @Test
    void ensureDoNotDefineTeamMemberRole_projectDoesNotExist(){
        //Arrange
        String projCode = "PRJ1";
        String resourceInProjID="resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription  = "scrum master";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency  = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);

        when(optionalProject.isPresent()).thenReturn(false);

        //Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Project does not exists",exception.getMessage());
    }

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String, Double,
     * Double, String, String, String)}.
     */
    @Test
    void ensureDoNotDefineScrumMasterRole_roleAlreadyExistInProject(){
        //Arrange
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "scrum master";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        Email email = new Email(emailAddress);
        Role role = new Role(roleDescription);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        when(project.roleDoesNotExistsInProject(role, startDate)).thenReturn(false);

        when(project.resourceIsInProject(email, startDate)).thenReturn(true);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created",exception.getMessage());
    }

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String, Double,
     * Double, String, String, String)}.
     */
    @Test
    void ensureDoNotDefineTeamMemberRole_exceededPercentageOfAllocation(){
        //Arrange
        String projCode = "PRJ1";
        String resourceInProjID="resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription  = "team member";
        double percentageAllocation = 31.0;
        double cost = 20.0;
        String currency  = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        Email email = new Email(emailAddress);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(roleStartDate, format);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        when(project.resourceIsInProject(email,startDate)).thenReturn(false);

        List<Project> projects = List.of(project);

        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getResourceInProjectAllocation(email,startDate)).thenReturn(70.0);


        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created",exception.getMessage());
    }

    /**
     * Test for {@link DefineRoleServiceImpl#defineRole(String, String, String, String, Double,
     * Double, String, String, String)} .
     */
    @Test
    void ensureNotDefineTeamMemberRole_failAddResource() {
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "team member";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        ResourceInProjectID resourceInProjectID = new ResourceInProjectID(resourceInProjID);
        Email email = new Email(emailAddress);
        Role role = new Role(roleDescription);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);
        LocalDate endDate = DateManagement.toLocalDate(roleEndDate);

        Period period = new Period(startDate, endDate);
        Cost costPerHour = new Cost(cost, currency);
        Allocation allocation = new Allocation(percentageAllocation);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        when(project.resourceIsInProject(email, startDate)).thenReturn(false);

        when(factoryResourceInProject.create(resourceInProjectID, email, role, period, costPerHour, allocation))
                .thenReturn(resourceInProject);

        when(project.addResourceInProject(resourceInProject)).thenReturn(false);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created", exception.getMessage());
    }
    @Test
    void ensureNotDefineTeamMemberRole_InvalidProjectCode() {
        //Arrange
        String projCode = "";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "team member";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Project code cannot be empty", exception.getMessage());
    }
    @Test
    void ensureNotDefineTeamMemberRole_StartDateBeforeProjectStartDate() {
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "team member";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-01-01";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        Email email = new Email(emailAddress);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created", exception.getMessage());
    }
    @Test
    void ensureNotDefineTeamMemberRole_EndDateAfterProjectEndDate() {
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "team member";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-05-01";
        String roleEndDate = "2026-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        Email email = new Email(emailAddress);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created", exception.getMessage());
    }
    @Test
    void ensureDoNotDefineTeam_resourceAlreadyInProject() {
        //Arrange
        String projectStartDate = "2023-03-03";
        String projectEndDate = "2025-03-03";

        String projCode = "PRJ1";
        String resourceInProjID = "resource1";
        String emailAddress = "test@gmail.com";
        String roleDescription = "team member";
        double percentageAllocation = 20.0;
        double cost = 20.0;
        String currency = "EUR";
        String roleStartDate = "2023-10-09";
        String roleEndDate = "2024-10-09";

        ProjectCode projectCode = new ProjectCode(projCode);
        Email email = new Email(emailAddress);
        Role role = new Role(roleDescription);

        LocalDate startDate = DateManagement.toLocalDate(roleStartDate);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalProject);
        when(optionalProject.isPresent()).thenReturn(true);
        when(optionalProject.get()).thenReturn(project);

        when(project.getResourceInProjectAllocation(email, startDate)).thenReturn(70.0);

        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        when(project.getPeriod()).thenReturn(projectPeriod);
        when(projectPeriod.getStartDate()).thenReturn(DateManagement.toLocalDate(projectStartDate));
        when(projectPeriod.getEndDate()).thenReturn(DateManagement.toLocalDate(projectEndDate));

        when(project.roleDoesNotExistsInProject(role, startDate)).thenReturn(true);

        when(project.resourceIsInProject(email, startDate)).thenReturn(true);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            defineRoleServiceImpl.defineRole(
                    projCode,
                    resourceInProjID,
                    emailAddress,
                    roleDescription,
                    percentageAllocation,
                    cost,
                    currency,
                    roleStartDate,
                    roleEndDate);
        });
        //Assert
        assertEquals("Resource in Project not created",exception.getMessage());
    }
}