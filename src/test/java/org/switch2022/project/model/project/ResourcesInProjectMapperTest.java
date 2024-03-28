package org.switch2022.project.model.project;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ResourcesInProjectMapper#toResourcesDTOList(List)} .
 */
class ResourcesInProjectMapperTest {

    /**
     * Test class for {@link ResourcesInProjectMapper}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = ResourcesInProjectMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }
    @Test
    void test() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        List<ResourceInProject> resourcesInProject = Collections.singletonList(resourceInProjectDouble);

        ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        Cost costDouble = mock(Cost.class);
        Allocation allocationDouble = mock(Allocation.class);

        String projCode = "PRJ1";
        String idResourceInProject = "resource1";
        String emailResource = "teste@gmail.com";
        String roleResource = "team member";
        String startDateResource = "2023-05-22";
        String endDateResource = "2024-05-22";
        Double costResource = 1000.0;
        String currency = "EUR";
        Double allocationResource = 30.0;

        LocalDate startDateDouble = mock(LocalDate.class);
        LocalDate endDateDouble = mock(LocalDate.class);

        when(resourceInProjectDouble.getResourceInProjectID()).thenReturn(resourceInProjectIDDouble);
        when(resourceInProjectDouble.getEmail()).thenReturn(emailDouble);
        when(resourceInProjectDouble.getRole()).thenReturn(roleDouble);
        when(resourceInProjectDouble.getPeriod()).thenReturn(periodDouble);
        when(resourceInProjectDouble.getCostPerHour()).thenReturn(costDouble);
        when(resourceInProjectDouble.getAllocation()).thenReturn(allocationDouble);

        when(projectCodeDouble.getProjectCode()).thenReturn(projCode);
        when(resourceInProjectIDDouble.getResourceOfProjectID()).thenReturn(idResourceInProject);
        when(emailDouble.getEmail()).thenReturn(emailResource);
        when(roleDouble.getDescription()).thenReturn(roleResource);
        when(periodDouble.getStartDate()).thenReturn(startDateDouble);
        when(startDateDouble.toString()).thenReturn(startDateResource);
        when(periodDouble.getEndDate()).thenReturn(endDateDouble);
        when(endDateDouble.toString()).thenReturn(endDateResource);
        when(costDouble.getValue()).thenReturn(costResource);
        when(costDouble.getCurrency()).thenReturn(currency);
        when(allocationDouble.getAllocation()).thenReturn(allocationResource);

        //Act
        List<ResourcesInProjectDTO> result = ResourcesInProjectMapper.toResourcesDTOList(resourcesInProject,projectCodeDouble);

        //Assert
        assertEquals(projCode, result.get(0).getProjectCode());
        assertEquals(idResourceInProject,result.get(0).getResourceInProjectID());
        assertEquals(emailResource,result.get(0).getEmail());
        assertEquals(roleResource,result.get(0).getRole());
        assertEquals(startDateResource,result.get(0).getStartDate());
        assertEquals(endDateResource,result.get(0).getEndDate());
        assertEquals(costResource,result.get(0).getCostPerHour());
        assertEquals(currency,result.get(0).getCurrency());
        assertEquals(allocationResource,result.get(0).getAllocation());
    }
}