package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.ProjectJPA;
import org.switch2022.project.datamodel.jpa.ResourceInProjectJpa;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectJpaAssembler.class
)
class ProjectJpaAssemblerTest {

    @MockBean
    Period period;
    @MockBean
    ProjectCode projectCodeDouble;
    @MockBean
    Name nameDouble;
    @MockBean
    Description description;
    @MockBean
    CustomerID customerID;
    @MockBean
    BusinessSectorID businessSectorID;
    @MockBean
    TypologyID typologyID;
    @MockBean
    ProjectStatus projectStatus;
    @MockBean
    PositiveNumber sprintDuration;
    @MockBean
    Cost cost;
    @MockBean
    LocalDate projStartDate;
    @MockBean
    ResourceInProjectID resourceInProjectID;
    @MockBean
    Email email;
    @MockBean
    Role role;
    @MockBean
    Allocation allocation;
    @MockBean
    ResourceInProject resourceInProject;
    @MockBean
    Project project;
    @MockBean
    ProjectJPA projectJPA;


    @Test
    void ensureGetProjectToDataModel() {
        // ARRANGE
        String projCode = "PRJ1";
        String projName = "projname";
        String projDescription = "projDescription";
        String idCustomer = "c1";
        String idBusinessSector = "bs1";
        String idTypology = "typ1";
        String projStatus = "planned";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String budget = "1000EUR";
        String startDate = "2023-10-10";
        String endDate = "2024-10-10";

        LocalDate projEndDate = mock(LocalDate.class);

        when(project.getPeriod()).thenReturn(period);
        when(period.getEndDate()).thenReturn(projEndDate);


        PositiveNumber numberOfPlannedSprints = mock(PositiveNumber.class);

        when(project.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectCodeDouble.getProjectCode()).thenReturn(projCode);

        when(project.getProjectName()).thenReturn(nameDouble);
        when(nameDouble.getValue()).thenReturn(projName);

        when(project.getProjectDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn(projDescription);

        when(project.getCustomerID()).thenReturn(customerID);
        when(customerID.getIdOfCustomer()).thenReturn(idCustomer);

        when(project.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSectorID.getId()).thenReturn(idBusinessSector);

        when(project.getTypologyID()).thenReturn(typologyID);
        when(typologyID.getIdOfTypology()).thenReturn(idTypology);

        when(project.getProjectStatus()).thenReturn(projectStatus);
        when(projectStatus.getProjectStatus()).thenReturn(projStatus);

        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(sprintDuration.getNumber()).thenReturn(durationOfSprint);

        when(project.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprints);
        when(numberOfPlannedSprints.getNumber()).thenReturn(numberOfSprints);

        when(project.getBudget()).thenReturn(cost);
        when(cost.toString()).thenReturn(budget);

        when(project.getPeriod()).thenReturn(period);
        when(period.getStartDate()).thenReturn(projStartDate);
        when(projStartDate.toString()).thenReturn(startDate);

        when(projEndDate.toString()).thenReturn(endDate);


        String idResourceInProject = "RESOURCE1";
        String resourceEmail = "teste@gmail.com";
        String resourceRole = "team member";
        String resourceStartDate = "2023-11-11";
        String resourceEndDate = "2024-09-11";
        Double resourceCost = 1000.0;
        String currency = "EUR";
        Double resourceAllocation = 20.0;


        List<ResourceInProject> resources = Collections.singletonList(resourceInProject);

        when(project.getResources()).thenReturn(resources);
        when(resourceInProject.getResourceInProjectID()).thenReturn(resourceInProjectID);
        when(resourceInProjectID.getResourceOfProjectID()).thenReturn(idResourceInProject);

        when(resourceInProject.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn(resourceEmail);

        when(resourceInProject.getRole()).thenReturn(role);
        when(role.getDescription()).thenReturn(resourceRole);

        Period periodDouble = mock(Period.class);
        LocalDate resourceStartDateDouble = mock(LocalDate.class);
        LocalDate resourceEndDateDouble = mock(LocalDate.class);

        when(resourceInProject.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(resourceStartDateDouble);
        when(resourceStartDateDouble.toString()).thenReturn(resourceStartDate);

        when(periodDouble.getEndDate()).thenReturn(resourceEndDateDouble);
        when(resourceEndDateDouble.toString()).thenReturn(resourceEndDate);

        when(resourceInProject.getCostPerHour()).thenReturn(cost);
        when(cost.getValue()).thenReturn(resourceCost);

        when(cost.getCurrency()).thenReturn(currency);

        when(resourceInProject.getAllocation()).thenReturn(allocation);
        when(allocation.getAllocation()).thenReturn(resourceAllocation);

        // ACT
        ProjectJPA projectJPA = ProjectJpaAssembler.toDataModel(project);

        assertEquals(projCode, projectJPA.getProjectCode());
        assertEquals(projName, projectJPA.getProjectName());
        assertEquals(projDescription, projectJPA.getProjectDescription());
        assertEquals(idCustomer, projectJPA.getCustomerID());
        assertEquals(idBusinessSector, projectJPA.getBusinessSectorID());
        assertEquals(idTypology, projectJPA.getTypologyID());
        assertEquals(projStatus, projectJPA.getProjectStatus());
        assertEquals(durationOfSprint, projectJPA.getSprintDuration());
        assertEquals(numberOfSprints, projectJPA.getNumberOfPlannedSprints());
        assertEquals(budget, projectJPA.getBudget());
        assertEquals(startDate, projectJPA.getStartDate());
        assertEquals(endDate, projectJPA.getEndDate());

        ResourceInProjectJpa resourceInProjectJpa = projectJPA.getResourceInProjectJpas().get(0);

        assertEquals(idResourceInProject, resourceInProjectJpa.getResourceInProjectID());
        assertEquals(resourceEmail, resourceInProjectJpa.getEmail());
        assertEquals(resourceRole, resourceInProjectJpa.getRole());
        assertEquals(resourceStartDate, resourceInProjectJpa.getStartDate());
        assertEquals(resourceEndDate, resourceInProjectJpa.getEndDate());
        assertEquals(resourceCost, resourceInProjectJpa.getCostPerHour());
        assertEquals(currency, resourceInProjectJpa.getCurrency());
        assertEquals(resourceAllocation, resourceInProjectJpa.getAllocation());
    }

    @Test
    void ensureGetProjectToDataModelWithNullEndDate() {
        // ARRANGE
        String projCode = "PRJ1";
        String projName = "projName";
        String projDescription = "projDescription";
        String idCustomer = "c1";
        String idBusinessSector = "bs1";
        String idTypology = "typ1";
        String projStatus = "planned";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String budget = "1000.0EUR";
        String startDate = "2023-10-10";

        Project project1 = new Project(
                new ProjectCode(projCode),
                new Name(projName),
                new Description(projDescription),
                new CustomerID(idCustomer),
                new BusinessSectorID(idBusinessSector),
                new TypologyID(idTypology),
                new ProjectStatus(projStatus),
                new PositiveNumber(durationOfSprint),
                new PositiveNumber(numberOfSprints),
                new Cost(1000, "EUR"),
                new Period(DateManagement.toLocalDate(startDate), null)
        );

        // ACT
        ProjectJPA projectJPA = ProjectJpaAssembler.toDataModel(project1);

        assertEquals(projCode, projectJPA.getProjectCode());
        assertEquals(projName, projectJPA.getProjectName());
        assertEquals(projDescription, projectJPA.getProjectDescription());
        assertEquals(idCustomer, projectJPA.getCustomerID());
        assertEquals(idBusinessSector, projectJPA.getBusinessSectorID());
        assertEquals(idTypology, projectJPA.getTypologyID());
        assertEquals(projStatus, projectJPA.getProjectStatus());
        assertEquals(durationOfSprint, projectJPA.getSprintDuration());
        assertEquals(numberOfSprints, projectJPA.getNumberOfPlannedSprints());
        assertEquals(budget, projectJPA.getBudget());
        assertEquals(startDate, projectJPA.getStartDate());
        assertNull(projectJPA.getEndDate());
    }

    @Test
    void ensureGetProjectToDomain() {
        // ARRANGE
        String projCode = "PRJ1";
        String projName = "projName";
        String projDescription = "projDescription";
        String idCustomer = "C1";
        String idBusinessSector = "BS1";
        String idTypology = "TYP1";
        String projStatus = "planned";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String budget = "1000.0EUR";
        String projStartDate = "2023-10-10";
        String projEndDate = "2024-10-10";

        String resourceId = "R1";
        String email = "email@email.com";
        String role = "project manager";
        String roleStartDate = "2023-10-11";
        String roleEndDate = "2023-11-11";
        double costPerHour = 20.0;
        String currency = "EUR";
        double allocation = 100.0;

        ResourceInProjectJpa resourceInProjectJpa = new ResourceInProjectJpa(
                resourceId,
                email,
                role,
                roleStartDate,
                roleEndDate,
                costPerHour,
                currency,
                allocation,
                projectJPA
        );

        List<ResourceInProjectJpa> resourcesInProjectJpa = List.of(resourceInProjectJpa);

        when(projectJPA.getBudget()).thenReturn(budget);
        when(projectJPA.getStartDate()).thenReturn(projStartDate);
        when(projectJPA.getEndDate()).thenReturn(projEndDate);
        when(projectJPA.getProjectCode()).thenReturn(projCode);
        when(projectJPA.getProjectName()).thenReturn(projName);
        when(projectJPA.getProjectDescription()).thenReturn(projDescription);
        when(projectJPA.getCustomerID()).thenReturn(idCustomer);
        when(projectJPA.getBusinessSectorID()).thenReturn(idBusinessSector);
        when(projectJPA.getTypologyID()).thenReturn(idTypology);
        when(projectJPA.getProjectStatus()).thenReturn(projStatus);
        when(projectJPA.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectJPA.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectJPA.getResourceInProjectJpas()).thenReturn(resourcesInProjectJpa);


        // ACT
        Project project = ProjectJpaAssembler.toDomain(projectJPA);

        // ASSERT
        assertEquals(projCode, project.getProjectCode().getProjectCode());
        assertEquals(projName, project.getProjectName().getValue());
        assertEquals(projDescription, project.getProjectDescription().getDescription());
        assertEquals(idCustomer, project.getCustomerID().getIdOfCustomer());
        assertEquals(idBusinessSector, project.getBusinessSectorID().getId());
        assertEquals(idTypology, project.getTypologyID().getIdOfTypology());
        assertEquals(projStatus, project.getProjectStatus().getProjectStatus());
        assertEquals(durationOfSprint, project.getSprintDuration().getNumber());
        assertEquals(numberOfSprints, project.getNumberOfPlannedSprints().getNumber());
        assertEquals(budget, project.getBudget().toString());
        assertEquals(projStartDate, project.getPeriod().getStartDate().toString());
        assertEquals(projEndDate, project.getPeriod().getEndDate().toString());

        for (int i = 0; i < resourcesInProjectJpa.size(); i++) {
            assertEquals(
                    project.getResources().get(i),
                    ResourceInProjectJpaAssembler.toDomain(resourcesInProjectJpa.get(i))
            );
        }
    }
}
