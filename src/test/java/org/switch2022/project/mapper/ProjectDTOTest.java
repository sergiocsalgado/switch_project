package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@link ProjectDTO}
 */
class ProjectDTOTest {

    @Test
    void ensureProjectsDTOAreEqual() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode("PRJ1");
        projectDTO.setName("E-commerce platform");
        projectDTO.setDescription("Update layout of website");
        projectDTO.setCustomer("c1");
        projectDTO.setBusinessSector("bs1");
        projectDTO.setTypology("t1");
        projectDTO.setStartDate("2023-05-25");
        projectDTO.setEndDate("2024-05-22");
        projectDTO.setStatus("planned");
        projectDTO.setSprintDuration(3);
        projectDTO.setNumberOfPlannedSprints(15);
        projectDTO.setBudget("1000€");

        ProjectDTO projectDTO1 = new ProjectDTO();

        projectDTO1.setProjectCode(projectDTO.getProjectCode());
        projectDTO1.setName(projectDTO.getName());
        projectDTO1.setDescription(projectDTO.getDescription());
        projectDTO1.setCustomer(projectDTO.getCustomer());
        projectDTO1.setBusinessSector(projectDTO.getBusinessSector());
        projectDTO1.setTypology(projectDTO.getTypology());
        projectDTO1.setStartDate(projectDTO.getStartDate());
        projectDTO1.setEndDate(projectDTO.getEndDate());
        projectDTO1.setStatus(projectDTO.getStatus());
        projectDTO1.setSprintDuration(projectDTO.getSprintDuration());
        projectDTO1.setNumberOfPlannedSprints(projectDTO.getNumberOfPlannedSprints());
        projectDTO1.setBudget(projectDTO.getBudget());
        // Act
        boolean isEqual= projectDTO.equals(projectDTO1);
        // Assert
        assertTrue(isEqual);
    }

    @Test
    void ensureProjectsDTOAreNotEqual_projectDTONull() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode("PRJ1");
        projectDTO.setName("E-commerce platform");
        projectDTO.setDescription("Update layout of website");
        projectDTO.setCustomer("c1");
        projectDTO.setBusinessSector("bs1");
        projectDTO.setTypology("t1");
        projectDTO.setStartDate("2023-05-25");
        projectDTO.setEndDate("2024-05-22");
        projectDTO.setStatus("planned");
        projectDTO.setSprintDuration(3);
        projectDTO.setNumberOfPlannedSprints(15);
        projectDTO.setBudget("1000€");
        // Act
        ProjectDTO projectDTO1 = null;
        // Assert
        assertNotEquals(projectDTO, projectDTO1);
    }

    @Test
    void ensureProjectDTOAndProfileAreNotSameInstance() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode("PRJ1");
        projectDTO.setName("E-commerce platform");
        projectDTO.setDescription("Update layout of website");
        projectDTO.setCustomer("c1");
        projectDTO.setBusinessSector("bs1");
        projectDTO.setTypology("t1");
        projectDTO.setStartDate("2023-05-25");
        projectDTO.setEndDate("2024-05-22");
        projectDTO.setStatus("planned");
        projectDTO.setSprintDuration(3);
        projectDTO.setNumberOfPlannedSprints(15);
        projectDTO.setBudget("1000€");
        // Act
        Profile profileDouble = mock(Profile.class);
        // Assert
        assertNotEquals(projectDTO, profileDouble);
    }

    @Test
    void ensureProjectsDTOAreEqual_success() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode("PRJ1");
        projectDTO.setName("E-commerce platform");
        projectDTO.setDescription("Update layout of website");
        projectDTO.setCustomer("c1");
        projectDTO.setBusinessSector("bs1");
        projectDTO.setTypology("t1");
        projectDTO.setStartDate("2023-05-25");
        projectDTO.setEndDate("2024-05-22");
        projectDTO.setStatus("planned");
        projectDTO.setSprintDuration(3);
        projectDTO.setNumberOfPlannedSprints(15);
        projectDTO.setBudget("1000€");
        // Act
        ProjectDTO projectDTO1 = projectDTO;
        // Assert
        assertEquals(projectDTO, projectDTO1);
        assertEquals(projectDTO.hashCode(),projectDTO1.hashCode());
    }
    @Test
    void ensureProjectsDTOAreNotEqual_differentProjectCode() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode("PRJ1");
        projectDTO.setName("E-commerce platform");
        projectDTO.setDescription("Update layout of website");
        projectDTO.setCustomer("c1");
        projectDTO.setBusinessSector("bs1");
        projectDTO.setTypology("t1");
        projectDTO.setStartDate("2023-05-25");
        projectDTO.setEndDate("2024-05-22");
        projectDTO.setStatus("planned");
        projectDTO.setSprintDuration(3);
        projectDTO.setNumberOfPlannedSprints(15);
        projectDTO.setBudget("1000€");

        ProjectDTO projectDTO1 = new ProjectDTO();

        projectDTO1.setProjectCode("PRJ2");
        projectDTO1.setName(projectDTO.getName());
        projectDTO1.setDescription(projectDTO.getDescription());
        projectDTO1.setCustomer(projectDTO.getCustomer());
        projectDTO1.setBusinessSector(projectDTO.getBusinessSector());
        projectDTO1.setTypology(projectDTO.getTypology());
        projectDTO1.setStartDate(projectDTO.getStartDate());
        projectDTO1.setEndDate(projectDTO.getEndDate());
        projectDTO1.setStatus(projectDTO.getStatus());
        projectDTO1.setSprintDuration(projectDTO.getSprintDuration());
        projectDTO1.setNumberOfPlannedSprints(projectDTO.getNumberOfPlannedSprints());
        projectDTO1.setBudget(projectDTO.getBudget());
        // Act
        boolean isEqual= projectDTO.equals(projectDTO1);
        // Assert
        assertFalse(isEqual);
        assertNotEquals(projectDTO.hashCode(),projectDTO1.hashCode());
    }


    @Test
    void ensureGetSprintDuration() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setSprintDuration(3);
        // Act
        int expected = 0;

        int result = projectDTO.getSprintDuration();
        // Assert
        assertNotEquals(expected,result);
    }

    @Test
    void ensureGetNumberOfPlannedSprints() {
        // Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setNumberOfPlannedSprints(15);
        // Act
        int expected = 0;

        int result = projectDTO.getNumberOfPlannedSprints();
        // Assert
        assertNotEquals(expected,result);
    }

    @Test
    void ensureProjectsDTOAreNotEqual_differentProjectName() {
        //Arrange
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode("PRJ1");
        projectDTO.setName("E-commerce platform");
        projectDTO.setDescription("Update layout of website");
        projectDTO.setCustomer("c1");
        projectDTO.setBusinessSector("bs1");
        projectDTO.setTypology("t1");
        projectDTO.setStartDate("2023-05-25");
        projectDTO.setEndDate("2024-05-22");
        projectDTO.setStatus("planned");
        projectDTO.setSprintDuration(3);
        projectDTO.setNumberOfPlannedSprints(15);
        projectDTO.setBudget("1000€");

        ProjectDTO projectDTO1 = new ProjectDTO();

        projectDTO1.setProjectCode(projectDTO.getProjectCode());
        projectDTO1.setName("E-commerce platform");
        projectDTO1.setDescription(projectDTO.getDescription());
        projectDTO1.setCustomer(projectDTO.getCustomer());
        projectDTO1.setBusinessSector(projectDTO.getBusinessSector());
        projectDTO1.setTypology(projectDTO.getTypology());
        projectDTO1.setStartDate(projectDTO.getStartDate());
        projectDTO1.setEndDate(projectDTO.getEndDate());
        projectDTO1.setStatus(projectDTO.getStatus());
        projectDTO1.setSprintDuration(projectDTO.getSprintDuration());
        projectDTO1.setNumberOfPlannedSprints(projectDTO.getNumberOfPlannedSprints());
        projectDTO1.setBudget(projectDTO.getBudget());

        //Act + Assert
        assertEquals(projectDTO, projectDTO1);
        assertEquals(projectDTO.getProjectCode(), projectDTO1.getProjectCode());
        assertEquals(projectDTO.getName(),projectDTO1.getName());
        assertEquals(projectDTO.getDescription(),projectDTO1.getDescription());
        assertEquals(projectDTO.getCustomer(),projectDTO1.getCustomer());
        assertEquals(projectDTO.getBusinessSector(),projectDTO1.getBusinessSector());
        assertEquals(projectDTO.getTypology(),projectDTO1.getTypology());
        assertEquals(projectDTO.getStartDate(),projectDTO1.getStartDate());
        assertEquals(projectDTO.getEndDate(),projectDTO1.getEndDate());
        assertEquals(projectDTO.getStatus(),projectDTO1.getStatus());
        assertEquals(projectDTO.getSprintDuration(),projectDTO1.getSprintDuration());
        assertEquals(projectDTO.getNumberOfPlannedSprints(),projectDTO1.getNumberOfPlannedSprints());
        assertEquals(projectDTO.getBudget(),projectDTO1.getBudget());
        assertEquals(projectDTO.hashCode(),projectDTO1.hashCode());
    }

    @Test
    public void testEquals_SameInstance_ShouldReturnTrue() {
        ProjectDTO projectDTO = new ProjectDTO();
        assertTrue(projectDTO.equals(projectDTO));
    }

    @Test
    public void testEquals_NullObject_ShouldReturnFalse() {
        ProjectDTO projectDTO = new ProjectDTO();
        assertFalse(projectDTO.equals(null));
    }

    @Test
    public void testEquals_DifferentClass_ShouldReturnFalse() {
        ProjectDTO projectDTO = new ProjectDTO();
        String other = "Not a ProjectDTO";
        assertFalse(projectDTO.equals(other));
    }

    @Test
    void testEquals_DifferentName_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name1");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name2");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentDescription_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description1");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description2");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentCustomer_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer1");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer2");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentBusinessSector_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS2");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentTypology_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("1");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("2");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentStartDate_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("TYP1");
        projectDTO1.setStartDate("2023-05-25");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("TYP1");
        projectDTO2.setStartDate("2024-05-26");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentEndDate_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("Typology1");
        projectDTO1.setStartDate("2023-05-25");
        projectDTO1.setEndDate("2024-05-25");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("Typology1");
        projectDTO2.setStartDate("2023-05-25");
        projectDTO2.setEndDate("2024-05-26");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentStatus_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("Typology1");
        projectDTO1.setStartDate("2023-05-25");
        projectDTO1.setEndDate("2024-05-25");
        projectDTO1.setStatus("planned");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("Typology1");
        projectDTO2.setStartDate("2023-05-25");
        projectDTO2.setEndDate("2024-05-25");
        projectDTO2.setStatus("blocked");

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentSprintDuration_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("Typology1");
        projectDTO1.setStartDate("2023-05-25");
        projectDTO1.setEndDate("2024-05-25");
        projectDTO1.setStatus("planned");
        projectDTO1.setSprintDuration(3);

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("Typology1");
        projectDTO2.setStartDate("2023-05-25");
        projectDTO2.setEndDate("2024-05-25");
        projectDTO2.setStatus("planned");
        projectDTO2.setSprintDuration(6);

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentNumberOfPlannedSprints_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("Typology1");
        projectDTO1.setStartDate("2023-05-25");
        projectDTO1.setEndDate("2024-05-25");
        projectDTO1.setStatus("planned");
        projectDTO1.setSprintDuration(3);
        projectDTO1.setNumberOfPlannedSprints(15);

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("Typology1");
        projectDTO2.setStartDate("2023-05-25");
        projectDTO2.setEndDate("2024-05-25");
        projectDTO2.setStatus("planned");
        projectDTO2.setSprintDuration(3);
        projectDTO2.setNumberOfPlannedSprints(20);

        assertNotEquals(projectDTO1, projectDTO2);
    }

    @Test
    void testEquals_DifferentBudget_ShouldReturnFalse() {
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setProjectCode("PRJ1");
        projectDTO1.setName("Name");
        projectDTO1.setDescription("Description");
        projectDTO1.setCustomer("Customer");
        projectDTO1.setBusinessSector("BS1");
        projectDTO1.setTypology("Typology1");
        projectDTO1.setStartDate("2023-05-25");
        projectDTO1.setEndDate("2024-05-25");
        projectDTO1.setStatus("planned");
        projectDTO1.setSprintDuration(3);
        projectDTO1.setNumberOfPlannedSprints(15);
        projectDTO1.setBudget("1000€");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectCode("PRJ1");
        projectDTO2.setName("Name");
        projectDTO2.setDescription("Description");
        projectDTO2.setCustomer("Customer");
        projectDTO2.setBusinessSector("BS1");
        projectDTO2.setTypology("Typology1");
        projectDTO2.setStartDate("2023-05-25");
        projectDTO2.setEndDate("2024-05-25");
        projectDTO2.setStatus("planned");
        projectDTO2.setSprintDuration(3);
        projectDTO2.setNumberOfPlannedSprints(15);
        projectDTO2.setBudget("1500€");

        assertNotEquals(projectDTO1, projectDTO2);
    }

}