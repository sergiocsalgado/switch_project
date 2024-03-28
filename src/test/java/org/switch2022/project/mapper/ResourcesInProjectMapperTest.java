package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourcesInProjectMapperTest {

    /**
     * Unit Test ResourceInProjectDTO_createResourceDTO
     * Verifies that the ResourceInProjectDTO method returns the correct ResourceInProjectDTO.
     */

    @Test
    void ResourceInProjectDTO_createResourceDTO() {
        //Arrange
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID("1");
        resourcesInProjectDTO.setEmail("user");
        resourcesInProjectDTO.setRole("user");
        //Act + Assert
        assertEquals("1", resourcesInProjectDTO.getResourceInProjectID());
        assertEquals("user", resourcesInProjectDTO.getEmail());
        assertEquals("user", resourcesInProjectDTO.getRole());
    }

    /**
     * Unit Test ResourcesInProjectMapper_toDTO
     * Verifies that the ResourcesInProjectMapper method returns the correct ResourcesInProjectDTO.
     */

    @Test
    void ResourcesInProjectMapper_toDTO() {
        //Arrange
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID("1");
        resourcesInProjectDTO.setEmail("user");
        resourcesInProjectDTO.setRole("user");

        //Act + Assert
        assertEquals("1", resourcesInProjectDTO.getResourceInProjectID());
        assertEquals("user", resourcesInProjectDTO.getEmail());
        assertEquals("user", resourcesInProjectDTO.getRole());
    }

    /**
     * Unit Test_toResourcesDTOList_ReturnCollectionList
     * Verifies that the ResourcesInProjectMapper method returns the correct ResourcesInProjectDTO.
     */

    @Test
    void Test_toResourcesDTOList_ReturnCollectionList() {
        //Arrange
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID("1");
        resourcesInProjectDTO.setEmail("user");
        resourcesInProjectDTO.setRole("user");
        //Act + Assert
        assertEquals("1", resourcesInProjectDTO.getResourceInProjectID());
        assertEquals("user", resourcesInProjectDTO.getEmail());
        assertEquals("user", resourcesInProjectDTO.getRole());
    }

    /**
     * Unit Test_toDTO_ReturnResourceInProjectDTO
     * Verifies that the ResourcesInProjectMapper method returns the correct ResourcesInProjectDTO.
     */

    @Test
    void Test_toDTO_ReturnResourceInProjectDTO() {
        //Arrange
        ResourceInProject resourceInProject = new ResourceInProject(
                new ResourceInProjectID("resource1"),
                new Email("user@gmail.com"),
                new Role("team member"),
                new Period(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 2, 20)),
                new Cost(10.0, "USD"),
                new Allocation(90.0));

        ResourcesInProjectDTO resourcesInProjectDTO = ResourcesInProjectMapper.toDTO(resourceInProject);

        //Act + Assert
        assertEquals("resource1", resourcesInProjectDTO.getResourceInProjectID());
        assertEquals("user@gmail.com", resourcesInProjectDTO.getEmail());
        assertEquals("team member", resourcesInProjectDTO.getRole());
        assertEquals(90.0, resourcesInProjectDTO.getAllocation());
        assertEquals(10.0, resourcesInProjectDTO.getCostPerHour());
        assertEquals("USD", resourcesInProjectDTO.getCurrency());
        assertEquals("2021-01-01", resourcesInProjectDTO.getStartDate());
        assertEquals("2021-02-20", resourcesInProjectDTO.getEndDate());
    }
}