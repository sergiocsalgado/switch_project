package org.switch2022.project.model.project;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FactoryResourceInProjectImplTest {

    FactoryResourceInProjectImpl factory = new FactoryResourceInProjectImpl();

    @Test
    void ensureObjectCreatedIsAnInstanceOfResourceInProject() {
        //Arrange
        ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        Cost costDouble = mock(Cost.class);
        Allocation allocationDouble = mock(Allocation.class);

        //Act
        ResourceInProject resource = factory.create(
                resourceInProjectIDDouble,
                emailDouble,
                roleDouble,
                periodDouble,
                costDouble,
                allocationDouble);

        //Assert
        assertInstanceOf(ResourceInProject.class, resource);
    }

    @Test
    void ensureObjectIsNotCreatedWithNullValues() {
        //Arrange
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.create(
                    null,
                    new Email("email@isep.com"),
                    new Role("project manager"),
                    new Period(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                    new Cost(20.0, "EUR"),
                    new Allocation(100.0));
        });

        //Assert
        assertInstanceOf(IllegalArgumentException.class, exception);
        assertEquals("Arguments cannot be null", exception.getMessage());
    }

    @Test
    void ensureObjectIsNotCreatedNegativeCost() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.create(
                    new ResourceInProjectID("resource1"),
                    new Email("email@isep.com"),
                    new Role("project manager"),
                    new Period(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                    new Cost(-20.0, "EUR"),
                   new  Allocation(100.0));
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("invalid cost value", exception.getMessage());
    }

    @Test
    void ensureObjectIsNotCreatedGraterThan100() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.create(
                    new ResourceInProjectID("resource1"),
                    new Email("email@isep.com"),
                    new Role("project manager"),
                    new Period(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                    new Cost(20.0, "EUR"),
                    new Allocation(444));
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Allocation value must be between 0 and 100", exception.getMessage());
    }
}