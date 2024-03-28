package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

class ResourceInProjectJpaTest {

    @Test
    void shouldGetResourceInProjectProperties() {
        // Arrange
        String resourceInProjectID = "resource1";
        String email = "user@gmail.com";
        String role = "team member";
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";
        double costPerHour = 10.0;
        String currency = "EUR";
        double allocation = 0.8;
        ProjectJPA projectJPA = mock(ProjectJPA.class);

        // Act
        ResourceInProjectJpa resource = new ResourceInProjectJpa(
                resourceInProjectID,
                email,
                role,
                startDate,
                endDate,
                costPerHour,
                currency,
                allocation,
                projectJPA
        );

        // Assert
        assertEquals(resourceInProjectID, resource.getResourceInProjectID());
        assertEquals(email, resource.getEmail());
        assertEquals(role, resource.getRole());
        assertEquals(startDate, resource.getStartDate());
        assertEquals(endDate, resource.getEndDate());
        assertEquals(costPerHour, resource.getCostPerHour());
        assertEquals(currency, resource.getCurrency());
        assertEquals(allocation, resource.getAllocation());
    }

    @Test
    void constructor_shouldCreateAValidResourceInProjectJpa() {
        // Arrange
        // Act
        ResourceInProjectJpa resourceInProjectJpa = new ResourceInProjectJpa();

        // Assert
        assertInstanceOf(ResourceInProjectJpa.class, resourceInProjectJpa);
    }

}
