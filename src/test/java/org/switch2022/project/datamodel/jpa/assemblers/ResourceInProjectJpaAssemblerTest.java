package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.jpa.ResourceInProjectJpa;
import org.switch2022.project.model.project.ResourceInProject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceInProjectJpaAssemblerTest {
    @Test
    void assertThrowsExceptionWhenCalledThePrivateConstructor()
            throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = ResourceInProjectJpaAssembler.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }
    @Test
    void ensureGetResourceInProjectToDomain() {
        //Arrange
        String resourceInProjectID = "resource1";
        String email = "test@gmail.com";
        String role = "team member";
        String startDate = "2023-05-22";
        String endDate = "2024-05-22";
        Double budget = 500000.0;
        String currency = "EUR";
        Double allocation = 20.0;

        ResourceInProjectJpa resourceInProjectJpaDouble = mock(ResourceInProjectJpa.class);

        when(resourceInProjectJpaDouble.getResourceInProjectID()).thenReturn(resourceInProjectID);
        when(resourceInProjectJpaDouble.getEmail()).thenReturn(email);
        when(resourceInProjectJpaDouble.getRole()).thenReturn(role);
        when(resourceInProjectJpaDouble.getStartDate()).thenReturn(startDate);
        when(resourceInProjectJpaDouble.getEndDate()).thenReturn(endDate);
        when(resourceInProjectJpaDouble.getCostPerHour()).thenReturn(budget);
        when(resourceInProjectJpaDouble.getCurrency()).thenReturn(currency);
        when(resourceInProjectJpaDouble.getAllocation()).thenReturn(allocation);

        ResourceInProject result = ResourceInProjectJpaAssembler.toDomain(resourceInProjectJpaDouble);

        assertEquals(resourceInProjectID, result.getResourceInProjectID().getResourceOfProjectID());
        assertEquals(email, result.getEmail().getEmail());
        assertEquals(role, result.getRole().getDescription());
        assertEquals(startDate, result.getPeriod().getStartDate().toString());
        assertEquals(endDate, result.getPeriod().getEndDate().toString());
        assertEquals(budget, result.getCostPerHour().getValue());
        assertEquals(currency, result.getCostPerHour().getCurrency());
        assertEquals(allocation, result.getAllocation().getAllocation());
    }
}

