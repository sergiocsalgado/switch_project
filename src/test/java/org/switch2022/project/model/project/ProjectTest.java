package org.switch2022.project.model.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectTest {
    @Test
    void objectIsInstanceOfClass_UnitTest() {
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

        assertInstanceOf(Project.class, project);
    }

    @Test
    void objectIsNotInstanceOfClass_UnitTest() {
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

        assertNotEquals(nameDouble, project);
    }

    @Test
    void getProjectCode_UnitTest() {
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
        ProjectCode expected = projectCodeDouble;
        ProjectCode result = project.getProjectCode();
        assertEquals(expected, result);
    }

    @Test
    void getProjectName_UnitTest() {
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
        Name expected = nameDouble;
        Name result = project.getProjectName();
        assertEquals(expected, result);
    }

    @Test
    void getProjectDescription_UnitTest() {
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
        Description expected = descriptionDouble;
        Description result = project.getProjectDescription();
        assertEquals(expected, result);
    }

    @Test
    void getCustomerID_UnitTest() {
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
        CustomerID expected = customerIDDouble;
        CustomerID result = project.getCustomerID();
        assertEquals(expected, result);
    }

    @Test
    void getBusinessSector_UnitTest() {
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
        BusinessSectorID expected = businessSectorIDDouble;
        BusinessSectorID result = project.getBusinessSectorID();
        assertEquals(expected, result);
    }

    @Test
    void getTypology_UnitTest() {
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
        TypologyID expected = typologyIDDouble;
        TypologyID result = project.getTypologyID();
        assertEquals(expected, result);
    }

    @Test
    void getPeriod_UnitTest() {
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
        Period period = new Period(LocalDate.now(), LocalDate.now().plusWeeks(56));
        project.setPeriod(period);
        Period expected = period;
        Period result = project.getPeriod();
        assertEquals(expected, result);
    }

    @Test
    void getProjectStatus_UnitTest() {
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
        ProjectStatus expected = projectStatusDouble;
        ProjectStatus result = project.getProjectStatus();
        assertEquals(expected, result);
    }

    @Test
    void testGetSprintDuration() {
        // Arrange

        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);
        Period periodDouble = mock(Period.class);

        PositiveNumber sprintDurationDouble = new PositiveNumber(2);
        Project project = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);

        // Act
        PositiveNumber sprintDuration = project.getSprintDuration();

        // Assert
        Assertions.assertEquals(sprintDurationDouble, sprintDuration);
    }

    @Test
    void testGetNumberOfPlannedSprints() {
        // Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);
        Period periodDouble = mock(Period.class);

        PositiveNumber numberOfPlannedSprintsDouble = new PositiveNumber(10);
        Project project = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);

        // Act
        PositiveNumber numberOfPlannedSprints = project.getNumberOfPlannedSprints();

        // Assert
        Assertions.assertEquals(numberOfPlannedSprintsDouble, numberOfPlannedSprints);
    }

    @Test
    void testGetBudget() {
        // Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);

        Period periodDouble = mock(Period.class);

        Cost budgetDouble = new Cost(100.0,"EUR");
        Project project = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);

        // Act
        Cost budget = project.getBudget();

        // Assert
        Assertions.assertEquals(budgetDouble, budget);
    }

    @Test
    void copy_UnitTest() {
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

        Project expected = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        Project result = expected.copy();
        assertEquals(expected, result);
    }

    @Test
    void testEquals_Success_UnitTest() {
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

        Project project1 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        Project project2 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);

        boolean expected = true;

        boolean result = project1.equals(project2);

        assertEquals(expected, result);
    }

    @Test
    void testEqualsSameInstance_Success_UnitTest() {
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

        Project project1 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble, periodDouble);

        boolean expected = true;
        boolean result = project1.equals(project1);
        assertEquals(expected, result);
    }

    @Test
    void testEquals_Fail_UnitTest() {
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

        Project project1 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        Project project2 = new Project(projectCodeDouble2, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        boolean expected = false;
        boolean result = project1.equals(project2);
        assertEquals(expected, result);
    }

    @Test
    void testEquals_Null_UnitTest() {
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

        Project project1 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        Project project2 = null;
        boolean expected = false;
        boolean result = project1.equals(project2);
        assertEquals(expected, result);
    }

    @Test
    void ensureObjectsAreFromDifferentClasses() {
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

        Object o = new Object();

        boolean isEquals = project.equals(o);

        assertFalse(isEquals);
    }

    @Test
    void testHashCode_Success_UnitTest() {
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

        Project project1 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        Project project2 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);

        assertEquals(project1.hashCode(), project2.hashCode());
    }

    @Test
    void testHashCode_Fail_UnitTest() {
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

        Project project1 = new Project(projectCodeDouble, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);
        Project project2 = new Project(projectCodeDouble2, nameDouble, descriptionDouble, customerIDDouble,
                businessSectorIDDouble, typologyIDDouble, projectStatusDouble,sprintDurationDouble,
                numberOfPlannedSprintsDouble,budgetDouble,periodDouble);

        assertNotEquals(project1.hashCode(), project2.hashCode());
    }

    @Test
    void identity_UnitTest() {
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

        ProjectCode result = project.identity();
        assertEquals(projectCodeDouble, result);
    }

    @Test
    void sameIDAs_Success_UnitTest() {
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

        boolean expected = true;
        boolean result = project.sameIDAs(projectCodeDouble);
        assertEquals(expected, result);
    }

    @Test
    void sameIDAs_Fail_UnitTest() {
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

        boolean expected = false;
        boolean result = project.sameIDAs(projectCodeDouble2);
        assertEquals(expected, result);
    }

    @Test
    void sameIDAs_Null_UnitTest() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectCode projectCodeDouble2 = null;
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

        boolean expected = false;
        boolean result = project.sameIDAs(projectCodeDouble2);
        assertEquals(expected, result);
    }

    @Test
    void getResourceInProjectAllocation_Success() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);
        Email email = mock(Email.class);
        LocalDate startDate = LocalDate.now();
        Period period1 = mock(Period.class);
        Allocation allocation = mock(Allocation.class);
        LocalDate endDate = mock(LocalDate.class);


        //Define the period
        project.addResourceInProject(resourceInProject);

        when(period1.getStartDate()).thenReturn(LocalDate.now());
        when(period1.getEndDate()).thenReturn(LocalDate.now().plusWeeks(1));
        when(resourceInProject.getPeriod()).thenReturn(period1);
        when(period1.getEndDate()).thenReturn(endDate);

        when(resourceInProject.getEmail()).thenReturn(email);
        when(endDate.isAfter(startDate)).thenReturn(true);

        when(resourceInProject.getAllocation()).thenReturn(allocation);
        when(allocation.getAllocation()).thenReturn(50.0);


        double res = 50;

        //ACT
        double exp = project.getResourceInProjectAllocation(email, startDate);
        //ASSERT
        assertEquals(res, exp);
    }

    @Test
    void getResourceInProjectAllocation_Null() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);
        Email email = mock(Email.class);
        LocalDate startDate = LocalDate.now();
        Period period1 = mock(Period.class);
        Allocation allocation = mock(Allocation.class);
        LocalDate endDate = mock(LocalDate.class);


        //Define the period
        project.addResourceInProject(resourceInProject);

        when(period1.getStartDate()).thenReturn(LocalDate.now());
        when(period1.getEndDate()).thenReturn(LocalDate.now().plusWeeks(1));
        when(resourceInProject.getPeriod()).thenReturn(period1);
        when(period1.getEndDate()).thenReturn(endDate);

        when(resourceInProject.getEmail()).thenReturn(email);
        when(endDate.isAfter(startDate)).thenReturn(true);

        when(resourceInProject.getAllocation()).thenReturn(allocation);
        when(allocation.getAllocation()).thenReturn(0.0d);


        double res = 0.0d;

        //ACT
        double exp = project.getResourceInProjectAllocation(email, startDate);
        //ASSERT
        assertEquals(res, exp);

    }

    @Test
    void ensureResourceInProjectAllocationEqualsZero_emailNotFound() {
        //Arrange
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

        Email emailDouble = mock(Email.class);
        Email emailDouble2 = mock(Email.class);
        LocalDate date = LocalDate.now();



        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(date.plusDays(4));
        when(resourceInProjectDouble.getEmail()).thenReturn(emailDouble2);

        double expected = 0.0;

        //Act
        double result = project.getResourceInProjectAllocation(emailDouble, date);
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureResourceInProjectAllocationEqualsZero_resourceInProjectEndDateIsBeforeLocalDate() {
        //Arrange
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

        Email emailDouble = mock(Email.class);
        LocalDate date = LocalDate.now();


        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(date.minusDays(3));
        when(resourceInProjectDouble.getEmail()).thenReturn(emailDouble);

        double expected = 0.0;

        //Act
        double result = project.getResourceInProjectAllocation(emailDouble, date);
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureIsInProject() {
        //ARRANGE

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

        Email email = mock(Email.class);
        ResourceInProject resourceInProject = mock(ResourceInProject.class);
        Period period1 = mock(Period.class);
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate date = LocalDate.now();

        project.addResourceInProject(resourceInProject);

        when(resourceInProject.getEmail()).thenReturn(email);
        when(resourceInProject.getPeriod()).thenReturn(period1);
        when(period1.getEndDate()).thenReturn(endDate);
        when(period1.getStartDate()).thenReturn(startDate);

        boolean exp = true;

        //ACT
        boolean res = project.resourceIsInProject(email, date);

        //ASSERT
        assertEquals(exp, res);

    }

    @Test
    void ensureIsNotInProject_emailNotFound() {
        //ARRANGE
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

        Email emailDouble = mock(Email.class);
        Email emailDouble2 = mock(Email.class);
        LocalDate date = LocalDate.now();

        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        when(resourceInProjectDouble.getEmail()).thenReturn(emailDouble2);

        boolean expected = false;

        //ACT
        boolean result = project.resourceIsInProject(emailDouble, date);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureResourceIsNotInProject_resourceEndDateBeforeLocalDate() {
        //Arrange
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

        Email emailDouble = mock(Email.class);
        LocalDate date = LocalDate.now();



        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        when(resourceInProjectDouble.getEmail()).thenReturn(emailDouble);
        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(date.minusDays(3));
        when(periodDouble.getEndDate()).thenReturn(date.minusDays(1));

        boolean expected = false;

        //Act
        boolean result = project.resourceIsInProject(emailDouble, date);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureResourceIsNotInProject_resourceStartDateAfterPresentDate() {
        //Arrange
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

        Email emailDouble = mock(Email.class);
        LocalDate date = LocalDate.now();


        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        when(resourceInProjectDouble.getEmail()).thenReturn(emailDouble);
        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(date.plusDays(2));
        when(periodDouble.getEndDate()).thenReturn(date.plusDays(10));

        boolean expected = false;

        //Act
        boolean result = project.resourceIsInProject(emailDouble, date);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureResourceInProjectIsAdded() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);


        boolean exp = true;
        //ACT
        boolean res = project.addResourceInProject(resourceInProject);

        //ASSERT
        assertEquals(exp, res);
    }

    @Test
    void ensureResourceInProjectIsNotAddedTwice() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);

        project.addResourceInProject(resourceInProject);


        boolean exp = false;
        //ACT
        boolean res = project.addResourceInProject(resourceInProject);

        //ASSERT
        assertEquals(exp, res);
    }

    @Test
    void ensureResourceInProjectIsNotAddedTwice_2() {
        //ARRANGE
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


        boolean exp = false;
        //ACT
        boolean res = project.addResourceInProject(null);

        //ASSERT
        assertEquals(exp, res);
    }

    @Test
    void ensureRoleExist() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);
        Role role = mock(Role.class);
        LocalDate startDate = LocalDate.now();
        Period period1 = mock(Period.class);
        LocalDate endDate = LocalDate.now().plusWeeks(1);

        project.addResourceInProject(resourceInProject);

        when(resourceInProject.getPeriod()).thenReturn(period1);
        when(period1.getEndDate()).thenReturn(endDate);
        when(resourceInProject.getRole()).thenReturn(role);


        boolean exp = false;
        //ACT
        boolean res = project.roleDoesNotExistsInProject(role, startDate);
        //ASSERT
        assertEquals(exp, res);

    }

    @Test
    void ensureRoleNeverExisted() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);

        Role roleDouble = mock(Role.class);
        Role roleDouble2 = mock(Role.class);

        LocalDate startDate = LocalDate.now();
        Period period1 = mock(Period.class);
        LocalDate endDate = LocalDate.now().plusWeeks(1);

        project.addResourceInProject(resourceInProject);

        when(resourceInProject.getPeriod()).thenReturn(period1);
        when(period1.getEndDate()).thenReturn(endDate);
        when(resourceInProject.getRole()).thenReturn(roleDouble2);

        boolean exp = true;
        //ACT
        boolean res = project.roleDoesNotExistsInProject(roleDouble, startDate);
        //ASSERT
        assertEquals(exp, res);
    }

    @Test
    void ensureRoleNotExistAtPresentDay() {
        //ARRANGE
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

        ResourceInProject resourceInProject = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProject);

        Role roleDouble = mock(Role.class);
        LocalDate date = LocalDate.now();

        when(resourceInProject.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(date.minusDays(3));
        when(resourceInProject.getRole()).thenReturn(roleDouble);

        boolean expected = true;

        //Act
        boolean result = project.roleDoesNotExistsInProject(roleDouble, date);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetListOfResourcesInProjectDTO() {
        //Arrange
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

        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        List<ResourceInProject> resources = Arrays.asList(resourceInProjectDouble);

        LocalDate date = LocalDate.now();

        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(date.minusDays(3));
        when(periodDouble.getEndDate()).thenReturn(date.plusDays(3));

        ResourcesInProjectDTO resourcesInProjectDTO = mock(ResourcesInProjectDTO.class);

        List<ResourcesInProjectDTO> expected = Arrays.asList(resourcesInProjectDTO);

        try (MockedStatic<ResourcesInProjectMapper> mapperDouble = Mockito.mockStatic(ResourcesInProjectMapper.class)) {
            mapperDouble.when(() -> ResourcesInProjectMapper.toResourcesDTOList(resources,projectCodeDouble)).thenReturn(expected);
            //ACT
            List<ResourcesInProjectDTO> result = project.getResourcesInProjectDTO(date);

            //ASSERT
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureGetEmptyListOfResourcesInProjectDTO_AllResourcesEndDateAreBeforePresentDay() {
        //Arrange
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

        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        List<ResourceInProject> resources = Arrays.asList(resourceInProjectDouble);


        LocalDate date = LocalDate.now();

        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(date.minusDays(3));
        when(periodDouble.getEndDate()).thenReturn(date.minusDays(1));

        List<ResourcesInProjectDTO> expected = Arrays.asList();

        try (MockedStatic<ResourcesInProjectMapper> mapperDouble = Mockito.mockStatic(ResourcesInProjectMapper.class)) {
            mapperDouble.when(() -> ResourcesInProjectMapper.toResourcesDTOList(resources,projectCodeDouble)).thenReturn(expected);
            //ACT
            List<ResourcesInProjectDTO> result = project.getResourcesInProjectDTO(date);

            //ASSERT
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureGetEmptyListOfResourcesInProjectDTO_AllResourcesStartDateAreAfterPresentDay() {
        //Arrange
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

        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        project.addResourceInProject(resourceInProjectDouble);

        List<ResourceInProject> resources = Arrays.asList(resourceInProjectDouble);

        LocalDate date = LocalDate.now();

        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(date.plusDays(3));
        when(periodDouble.getEndDate()).thenReturn(date.plusDays(7));

        List<ResourcesInProjectDTO> expected = Arrays.asList();

        try (MockedStatic<ResourcesInProjectMapper> mapperDouble = Mockito.mockStatic(ResourcesInProjectMapper.class)) {
            mapperDouble.when(() -> ResourcesInProjectMapper.toResourcesDTOList(resources,projectCodeDouble)).thenReturn(expected);
            //ACT
            List<ResourcesInProjectDTO> result = project.getResourcesInProjectDTO(date);

            //ASSERT
            assertEquals(expected, result);
        }
    }
}