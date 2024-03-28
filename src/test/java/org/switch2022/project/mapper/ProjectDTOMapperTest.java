package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.value_objects.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ProjectDTOMapper}
 */
class ProjectDTOMapperTest {

    /**
     * Test class for {@link ProjectDTOMapper}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = ProjectDTOMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    @Test
    void ensureGetProjectDTO(){
        //Arrange
        Project projectDouble = mock(Project.class);

        String code ="PRJ1";
        String name = "E-commerce platform";
        String description = "Update layout of website";
        String customerID = "c1";
        String businessSectorID = "bs1";
        String typologyID = "t1";
        String startDate = "2023-05-25";
        String endDate = "2024-05-22";
        String status = "planned";
        int sprintDuration = 3;
        int numberOfPlannedSprints = 15;
        String budget = "1000€";

        ProjectCode codeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        Period periodDouble = mock(Period.class);
        LocalDate startDateDouble = mock(LocalDate.class);
        LocalDate endDateDouble = mock(LocalDate.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);


        when(projectDouble.getProjectCode()).thenReturn(codeDouble);
        when(codeDouble.getProjectCode()).thenReturn(code);

        when(projectDouble.getProjectName()).thenReturn(nameDouble);
        when(nameDouble.getValue()).thenReturn(name);

        when(projectDouble.getProjectDescription()).thenReturn(descriptionDouble);
        when(descriptionDouble.getDescription()).thenReturn(description);

        when(projectDouble.getCustomerID()).thenReturn(customerIDDouble);
        when(customerIDDouble.getIdOfCustomer()).thenReturn(customerID);

        when(projectDouble.getBusinessSectorID()).thenReturn(businessSectorIDDouble);
        when(businessSectorIDDouble.getId()).thenReturn(businessSectorID);

        when(projectDouble.getTypologyID()).thenReturn(typologyIDDouble);
        when(typologyIDDouble.getIdOfTypology()).thenReturn(typologyID);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(startDateDouble);
        when(startDateDouble.toString()).thenReturn(startDate);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(endDateDouble);
        when(endDateDouble.toString()).thenReturn(endDate);

        when(projectDouble.getProjectStatus()).thenReturn(projectStatusDouble);
        when(projectStatusDouble.getProjectStatus()).thenReturn(status);

        when(projectDouble.getSprintDuration()).thenReturn(sprintDurationDouble);
        when(sprintDurationDouble.getNumber()).thenReturn(sprintDuration);

        when(projectDouble.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprintsDouble);
        when(numberOfPlannedSprintsDouble.getNumber()).thenReturn(numberOfPlannedSprints);

        when(projectDouble.getBudget()).thenReturn(budgetDouble);
        when(budgetDouble.toString()).thenReturn(budget);


        ProjectDTO expected = mock(ProjectDTO.class);
        when(expected.getProjectCode()).thenReturn(code);
        when(expected.getName()).thenReturn(name);
        when(expected.getDescription()).thenReturn(description);
        when(expected.getCustomer()).thenReturn(customerID);
        when(expected.getBusinessSector()).thenReturn(businessSectorID);
        when(expected.getTypology()).thenReturn(typologyID);
        when(expected.getStartDate()).thenReturn(startDate);
        when(expected.getEndDate()).thenReturn(endDate);
        when(expected.getStatus()).thenReturn(status);
        when(expected.getSprintDuration()).thenReturn(sprintDuration);
        when(expected.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprints);
        when(expected.getBudget()).thenReturn(budget);

        //Act
        ProjectDTO result = ProjectDTOMapper.toDTO(projectDouble);

        //Assert
        assertEquals(expected.getProjectCode(),result.getProjectCode());
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.getDescription(),result.getDescription());
        assertEquals(expected.getCustomer(),result.getCustomer());
        assertEquals(expected.getBusinessSector(),result.getBusinessSector());
        assertEquals(expected.getTypology(),result.getTypology());
        assertEquals(expected.getStartDate(),result.getStartDate());
        assertEquals(expected.getEndDate(),result.getEndDate());
        assertEquals(expected.getStatus(),result.getStatus());
        assertEquals(expected.getSprintDuration(),result.getSprintDuration());
        assertEquals(expected.getNumberOfPlannedSprints(),result.getNumberOfPlannedSprints());
        assertEquals(expected.getBudget(),result.getBudget());
    }

    @Test
    void ensureGetProjectDTOWithNullEndDate(){
        //Arrange
        Project projectDouble = mock(Project.class);

        String code ="PRJ1";
        String name = "E-commerce platform";
        String description = "Update layout of website";
        String customerID = "c1";
        String businessSectorID = "bs1";
        String typologyID = "t1";
        String startDate = "2023-05-25";
        String endDate = null;
        String status = "planned";
        int sprintDuration = 3;
        int numberOfPlannedSprints = 15;
        String budget = "1000€";

        ProjectCode codeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        CustomerID customerIDDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        Period periodDouble = mock(Period.class);
        LocalDate startDateDouble = mock(LocalDate.class);
        LocalDate endDateDouble = mock(LocalDate.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);


        when(projectDouble.getProjectCode()).thenReturn(codeDouble);
        when(codeDouble.getProjectCode()).thenReturn(code);

        when(projectDouble.getProjectName()).thenReturn(nameDouble);
        when(nameDouble.getValue()).thenReturn(name);

        when(projectDouble.getProjectDescription()).thenReturn(descriptionDouble);
        when(descriptionDouble.getDescription()).thenReturn(description);

        when(projectDouble.getCustomerID()).thenReturn(customerIDDouble);
        when(customerIDDouble.getIdOfCustomer()).thenReturn(customerID);

        when(projectDouble.getBusinessSectorID()).thenReturn(businessSectorIDDouble);
        when(businessSectorIDDouble.getId()).thenReturn(businessSectorID);

        when(projectDouble.getTypologyID()).thenReturn(typologyIDDouble);
        when(typologyIDDouble.getIdOfTypology()).thenReturn(typologyID);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(startDateDouble);
        when(startDateDouble.toString()).thenReturn(startDate);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(endDateDouble);
        when(endDateDouble.toString()).thenReturn(endDate);

        when(projectDouble.getProjectStatus()).thenReturn(projectStatusDouble);
        when(projectStatusDouble.getProjectStatus()).thenReturn(status);

        when(projectDouble.getSprintDuration()).thenReturn(sprintDurationDouble);
        when(sprintDurationDouble.getNumber()).thenReturn(sprintDuration);

        when(projectDouble.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprintsDouble);
        when(numberOfPlannedSprintsDouble.getNumber()).thenReturn(numberOfPlannedSprints);

        when(projectDouble.getBudget()).thenReturn(budgetDouble);
        when(budgetDouble.toString()).thenReturn(budget);


        ProjectDTO expected = mock(ProjectDTO.class);
        when(expected.getProjectCode()).thenReturn(code);
        when(expected.getName()).thenReturn(name);
        when(expected.getDescription()).thenReturn(description);
        when(expected.getCustomer()).thenReturn(customerID);
        when(expected.getBusinessSector()).thenReturn(businessSectorID);
        when(expected.getTypology()).thenReturn(typologyID);
        when(expected.getStartDate()).thenReturn(startDate);
        when(expected.getEndDate()).thenReturn(endDate);
        when(expected.getStatus()).thenReturn(status);
        when(expected.getSprintDuration()).thenReturn(sprintDuration);
        when(expected.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprints);
        when(expected.getBudget()).thenReturn(budget);

        //Act
        ProjectDTO result = ProjectDTOMapper.toDTO(projectDouble);

        //Assert
        assertEquals(expected.getProjectCode(),result.getProjectCode());
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.getDescription(),result.getDescription());
        assertEquals(expected.getCustomer(),result.getCustomer());
        assertEquals(expected.getBusinessSector(),result.getBusinessSector());
        assertEquals(expected.getTypology(),result.getTypology());
        assertEquals(expected.getStartDate(),result.getStartDate());
        assertEquals(expected.getEndDate(),result.getEndDate());
        assertEquals(expected.getStatus(),result.getStatus());
        assertEquals(expected.getSprintDuration(),result.getSprintDuration());
        assertEquals(expected.getNumberOfPlannedSprints(),result.getNumberOfPlannedSprints());
        assertEquals(expected.getBudget(),result.getBudget());
    }

    /**
     * Test class for {@link ProjectDTOMapper#toDTOWithoutIDs(Project, String, String, String)} .
     */
    @Test
    void toDTOWithoutIDs_ensureIsReturnedAValidProjectDTOWithEndDate() {
        //Arrange
        Project projectDouble = mock(Project.class);

        String code ="PRJ1";
        String name = "E-commerce platform";
        String description = "Update layout of website";
        String startDate = "2023-05-25";
        String endDate = "2023-06-25";
        String status = "planned";
        int sprintDuration = 3;
        int numberOfPlannedSprints = 15;
        String budget = "10000€";

        String customerName = "customer";
        String businessDescription = "business";
        String typologyDescription = "typology";

        ProjectCode codeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        Period periodDouble = mock(Period.class);
        LocalDate startDateDouble = mock(LocalDate.class);
        LocalDate endDateDouble = mock(LocalDate.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);


        when(projectDouble.getProjectCode()).thenReturn(codeDouble);
        when(codeDouble.getProjectCode()).thenReturn(code);

        when(projectDouble.getProjectName()).thenReturn(nameDouble);
        when(nameDouble.getValue()).thenReturn(name);

        when(projectDouble.getProjectDescription()).thenReturn(descriptionDouble);
        when(descriptionDouble.getDescription()).thenReturn(description);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(startDateDouble);
        when(startDateDouble.toString()).thenReturn(startDate);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(endDateDouble);
        when(endDateDouble.toString()).thenReturn(endDate);

        when(projectDouble.getProjectStatus()).thenReturn(projectStatusDouble);
        when(projectStatusDouble.getProjectStatus()).thenReturn(status);

        when(projectDouble.getSprintDuration()).thenReturn(sprintDurationDouble);
        when(sprintDurationDouble.getNumber()).thenReturn(sprintDuration);

        when(projectDouble.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprintsDouble);
        when(numberOfPlannedSprintsDouble.getNumber()).thenReturn(numberOfPlannedSprints);

        when(projectDouble.getBudget()).thenReturn(budgetDouble);
        when(budgetDouble.toString()).thenReturn(budget);

        ProjectDTO expected = mock(ProjectDTO.class);
        when(expected.getProjectCode()).thenReturn(code);
        when(expected.getName()).thenReturn(name);
        when(expected.getDescription()).thenReturn(description);
        when(expected.getCustomer()).thenReturn(customerName);
        when(expected.getBusinessSector()).thenReturn(businessDescription);
        when(expected.getTypology()).thenReturn(typologyDescription);
        when(expected.getStartDate()).thenReturn(startDate);
        when(expected.getEndDate()).thenReturn(endDate);
        when(expected.getStatus()).thenReturn(status);
        when(expected.getSprintDuration()).thenReturn(sprintDuration);
        when(expected.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprints);
        when(expected.getBudget()).thenReturn(budget);

        //Act
        ProjectDTO result = ProjectDTOMapper.toDTOWithoutIDs(
                projectDouble,
                customerName,
                businessDescription,
                typologyDescription);

        //Assert
        assertEquals(expected.getProjectCode(),result.getProjectCode());
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.getDescription(),result.getDescription());
        assertEquals(expected.getCustomer(),result.getCustomer());
        assertEquals(expected.getBusinessSector(),result.getBusinessSector());
        assertEquals(expected.getTypology(),result.getTypology());
        assertEquals(expected.getStartDate(),result.getStartDate());
        assertEquals(expected.getEndDate(),result.getEndDate());
        assertEquals(expected.getStatus(),result.getStatus());
        assertEquals(expected.getSprintDuration(),result.getSprintDuration());
        assertEquals(expected.getNumberOfPlannedSprints(),result.getNumberOfPlannedSprints());
        assertEquals(expected.getBudget(),result.getBudget());
    }
    @Test
    void toDTOWithoutIDs_ensureIsReturnedAValidProjectDTOWithNullEndDate() {
        //Arrange
        Project projectDouble = mock(Project.class);

        String code ="PRJ1";
        String name = "E-commerce platform";
        String description = "Update layout of website";
        String startDate = "2023-05-25";
        String endDate = null;
        String status = "planned";
        int sprintDuration = 3;
        int numberOfPlannedSprints = 15;
        String budget = "10000€";

        String customerName = "customer";
        String businessDescription = "business";
        String typologyDescription = "typology";

        ProjectCode codeDouble = mock(ProjectCode.class);
        Name nameDouble = mock(Name.class);
        Description descriptionDouble = mock(Description.class);
        Period periodDouble = mock(Period.class);
        LocalDate startDateDouble = mock(LocalDate.class);
        LocalDate endDateDouble = mock(LocalDate.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        PositiveNumber sprintDurationDouble = mock(PositiveNumber.class);
        PositiveNumber numberOfPlannedSprintsDouble = mock(PositiveNumber.class);
        Cost budgetDouble = mock(Cost.class);


        when(projectDouble.getProjectCode()).thenReturn(codeDouble);
        when(codeDouble.getProjectCode()).thenReturn(code);

        when(projectDouble.getProjectName()).thenReturn(nameDouble);
        when(nameDouble.getValue()).thenReturn(name);

        when(projectDouble.getProjectDescription()).thenReturn(descriptionDouble);
        when(descriptionDouble.getDescription()).thenReturn(description);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getStartDate()).thenReturn(startDateDouble);
        when(startDateDouble.toString()).thenReturn(startDate);

        when(projectDouble.getPeriod()).thenReturn(periodDouble);
        when(periodDouble.getEndDate()).thenReturn(endDateDouble);
        when(endDateDouble.toString()).thenReturn(endDate);

        when(projectDouble.getProjectStatus()).thenReturn(projectStatusDouble);
        when(projectStatusDouble.getProjectStatus()).thenReturn(status);

        when(projectDouble.getSprintDuration()).thenReturn(sprintDurationDouble);
        when(sprintDurationDouble.getNumber()).thenReturn(sprintDuration);

        when(projectDouble.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprintsDouble);
        when(numberOfPlannedSprintsDouble.getNumber()).thenReturn(numberOfPlannedSprints);

        when(projectDouble.getBudget()).thenReturn(budgetDouble);
        when(budgetDouble.toString()).thenReturn(budget);

        ProjectDTO expected = mock(ProjectDTO.class);
        when(expected.getProjectCode()).thenReturn(code);
        when(expected.getName()).thenReturn(name);
        when(expected.getDescription()).thenReturn(description);
        when(expected.getCustomer()).thenReturn(customerName);
        when(expected.getBusinessSector()).thenReturn(businessDescription);
        when(expected.getTypology()).thenReturn(typologyDescription);
        when(expected.getStartDate()).thenReturn(startDate);
        when(expected.getEndDate()).thenReturn(endDate);
        when(expected.getStatus()).thenReturn(status);
        when(expected.getSprintDuration()).thenReturn(sprintDuration);
        when(expected.getNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprints);
        when(expected.getBudget()).thenReturn(budget);

        //Act
        ProjectDTO result = ProjectDTOMapper.toDTOWithoutIDs(
                projectDouble,
                customerName,
                businessDescription,
                typologyDescription);

        //Assert
        assertEquals(expected.getProjectCode(),result.getProjectCode());
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.getDescription(),result.getDescription());
        assertEquals(expected.getCustomer(),result.getCustomer());
        assertEquals(expected.getBusinessSector(),result.getBusinessSector());
        assertEquals(expected.getTypology(),result.getTypology());
        assertEquals(expected.getStartDate(),result.getStartDate());
        assertEquals(expected.getEndDate(),result.getEndDate());
        assertEquals(expected.getStatus(),result.getStatus());
        assertEquals(expected.getSprintDuration(),result.getSprintDuration());
        assertEquals(expected.getNumberOfPlannedSprints(),result.getNumberOfPlannedSprints());
        assertEquals(expected.getBudget(),result.getBudget());
    }
}