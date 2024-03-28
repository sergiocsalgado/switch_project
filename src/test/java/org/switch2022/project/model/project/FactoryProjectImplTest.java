package org.switch2022.project.model.project;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class FactoryProjectImplTest {

    @Test
    void createProject_Success_Unit() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);
        Period periodDouble = mock(Period.class);
        Project project = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        FactoryProjectImpl factoryProject = new FactoryProjectImpl();
        Project expected = project;
        Project result = factoryProject.createProject(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        assertEquals(expected, result);
    }

    @Test
    void createProject_Fail_Unit() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectCode projectCodeDouble2 = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);
        Period periodDouble = mock(Period.class);
        Project project = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        FactoryProjectImpl factoryProject = new FactoryProjectImpl();
        Project expected = project;
        Project result = factoryProject.createProject(projectCodeDouble2, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        assertNotEquals(expected, result);
    }
}