package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectJPATest {

    @Test
    void ensureConstructorCreatesValidProject() {
        String projectCode = "PRJ1";
        String projectName = "projName";
        String projectDescription = "description";
        String customerID = "c1";
        String businessSectorID = "bs1";
        String typologyID = "typ1";
        String projectStatus = "planned";
        int sprintDuration = 3;
        int numberOfPlannedSprints = 15;
        String budget = "5000EUR";
        String startDate = "2023-10-10";
        String endDate = "2024-10-10";

        ProjectJPA project = new ProjectJPA(projectCode, projectName, projectDescription,
                customerID, businessSectorID, typologyID, projectStatus, sprintDuration,
                numberOfPlannedSprints, budget, startDate, endDate);

        String newEndDate = "2024-12-12";
        project.setEndDate(newEndDate);

        assertEquals(projectCode, project.getProjectCode());
        assertEquals(projectName, project.getProjectName());
        assertEquals(projectDescription, project.getProjectDescription());
        assertEquals(customerID, project.getCustomerID());
        assertEquals(businessSectorID, project.getBusinessSectorID());
        assertEquals(typologyID, project.getTypologyID());
        assertEquals(projectStatus, project.getProjectStatus());
        assertEquals(sprintDuration, project.getSprintDuration());
        assertEquals(numberOfPlannedSprints, project.getNumberOfPlannedSprints());
        assertEquals(budget, project.getBudget());
        assertEquals(startDate, project.getStartDate());
        assertEquals(newEndDate, project.getEndDate());
    }

    @Test
    void setResourceInProjectJpas_ensureTheResourcesAreSet() {
        // Arrange
        String projectCode = "PRJ1";
        String projectName = "projName";
        String projectDescription = "description";
        String customerID = "c1";
        String businessSectorID = "bs1";
        String typologyID = "typ1";
        String projectStatus = "planned";
        int sprintDuration = 3;
        int numberOfPlannedSprints = 15;
        String budget = "5000EUR";
        String startDate = "2023-10-10";
        String endDate = "2024-10-10";

        ProjectJPA project = new ProjectJPA(projectCode, projectName, projectDescription,
                customerID, businessSectorID, typologyID, projectStatus, sprintDuration,
                numberOfPlannedSprints, budget, startDate, endDate);

        List<ResourceInProjectJpa> resourceInProjectJpas = List.of(
                new ResourceInProjectJpa(
                        "RS01",
                        "email@email.com",
                        "team member",
                        "2022-05-22",
                        "2022-06-10",
                        100.0,
                        "EUR",
                        100.0,
                        project
                )
        );

        // Act
        project.setResourceInProjectJpas(resourceInProjectJpas);

        // Assert
        assertEquals(resourceInProjectJpas, project.getResourceInProjectJpas());

    }
}
