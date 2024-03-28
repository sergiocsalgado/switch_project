package org.switch2022.project.model.project;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ResourceInProject}.
 */
class ResourceInProjectTest {

    /**
     * Test that checks {@link ResourceInProject#ResourceInProject(ResourceInProjectID, Email, Role, Period,
     * Cost, Allocation)} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void throwExceptionWithNullArguments_Success() {
        //Arrange
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ResourceInProject(null, null, null,
                    null, null, null);
        });

        //Assert
        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals("Arguments cannot be null", exception.getMessage());

    }

    /**
     * Test that checks {@link ResourceInProject#ResourceInProject(ResourceInProjectID, Email, Role, Period,
     * Cost, Allocation)} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void throwExceptionWithEmailNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

        ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        Cost costDouble = mock(Cost.class);
        Allocation allocationDouble = mock(Allocation.class);

            new ResourceInProject(resourceInProjectIDDouble, null, roleDouble,
                    periodDouble, costDouble, allocationDouble);
        });

        assertEquals("Arguments cannot be null", exception.getMessage());
    }

    /**
     * Test that checks {@link ResourceInProject#ResourceInProject(ResourceInProjectID, Email, Role, Period,
     * Cost, Allocation)} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void throwExceptionWithRoleNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            Email email = mock(Email.class);
            ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
            Period periodDouble = mock(Period.class);
            Cost costDouble = mock(Cost.class);
            Allocation allocationDouble = mock(Allocation.class);

            new ResourceInProject(resourceInProjectIDDouble, email, null,
                    periodDouble, costDouble, allocationDouble);
        });

        assertEquals("Arguments cannot be null", exception.getMessage());
    }

    /**
     * Test that checks {@link ResourceInProject#ResourceInProject(ResourceInProjectID, Email, Role, Period,
     * Cost, Allocation)} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void throwExceptionWithPeriodNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            Email email = mock(Email.class);
            ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
            Role roleDouble = mock(Role.class);
            Cost costDouble = mock(Cost.class);
            Allocation allocationDouble = mock(Allocation.class);

            new ResourceInProject(resourceInProjectIDDouble, email, roleDouble,
                    null, costDouble, allocationDouble);
        });

        assertEquals("Arguments cannot be null", exception.getMessage());
    }

    /**
     * Test that checks {@link ResourceInProject#ResourceInProject(ResourceInProjectID, Email, Role, Period,
     * Cost, Allocation)} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void throwExceptionWithCostNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            Email email = mock(Email.class);
            ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
            Role roleDouble = mock(Role.class);
            Period periodDouble = mock(Period.class);
            Allocation allocationDouble = mock(Allocation.class);

            new ResourceInProject(resourceInProjectIDDouble, email, roleDouble,
                    periodDouble, null, allocationDouble);
        });

        assertEquals("Arguments cannot be null", exception.getMessage());
    }

    /**
     * Test that checks {@link ResourceInProject#ResourceInProject(ResourceInProjectID, Email, Role, Period,
     * Cost, Allocation)} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void throwExceptionWithAllocationNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            Email email = mock(Email.class);
            ResourceInProjectID resourceInProjectIDDouble = mock(ResourceInProjectID.class);
            Role roleDouble = mock(Role.class);
            Period periodDouble = mock(Period.class);
            Cost costDouble = mock(Cost.class);
            Allocation allocationDouble = mock(Allocation.class);

            new ResourceInProject(resourceInProjectIDDouble, email, roleDouble,
                    periodDouble, costDouble, null);
        });

        assertEquals("Arguments cannot be null", exception.getMessage());
    }

    /**
     * Test that checks {@link ResourceInProject} object created with valid parameters
     * is an instance of the ResourceInProject class.
     */
    @Test
    void sameInstance_Regular_Success() {
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);

        assertInstanceOf(ResourceInProject.class, resourceInProject);
    }

    /**
     * Test methods for {@link ResourceInProject#getResourceInProjectID()}
     */
    @Test
    void getResourceInProjectID_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        when(resourceInProjectID.getResourceOfProjectID()).thenReturn("Resource1");
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        String expected = "Resource1";

        //Act
        String result = resourceInProject.getResourceInProjectID().getResourceOfProjectID();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getResourceInProjectID_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        when(resourceInProjectID.getResourceOfProjectID()).thenReturn("Resource1");
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        String expected = "Resource7";

        //Act
        String result = resourceInProject.getResourceInProjectID().getResourceOfProjectID();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void getEmail_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        when(email.getEmail()).thenReturn("email12@mail.com");
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        String expected = "email12@mail.com";

        //Act
        String result = resourceInProject.getEmail().getEmail();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test methods for {@link ResourceInProject#getEmail()}
     */
    @Test
    void getEmail_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        when(email.getEmail()).thenReturn("email12@mail.com");
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        String expected = "wrong@mail.com";

        //Act
        String result = resourceInProject.getEmail().getEmail();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test methods for {@link ResourceInProject#getRole()}
     */
    @Test
    void getRole_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        when(role.getDescription()).thenReturn("product owner");
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        String expected = "product owner";

        //Act
        String result = resourceInProject.getRole().getDescription();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getRole_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        when(role.getDescription()).thenReturn("product owner");
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        String expected = "productOwner";

        //Act
        String result = resourceInProject.getRole().getDescription();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test methods for {@link ResourceInProject#getPeriod()}
     */
    @Test
    void getPeriod_StartDate_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        when(period.getStartDate()).thenReturn(LocalDate.now());
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        LocalDate expected = LocalDate.now();

        //Act
        LocalDate result = resourceInProject.getPeriod().getStartDate();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getPeriod_StartDate_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        when(period.getStartDate()).thenReturn(LocalDate.now());
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        LocalDate expected = LocalDate.now();

        //Act
        LocalDate result = resourceInProject.getPeriod().getStartDate().plusDays(1);

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void getPeriod_EndDate_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        when(period.getEndDate()).thenReturn(LocalDate.now().plusDays(2));
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        LocalDate expected = LocalDate.now().plusDays(2);

        //Act
        LocalDate result = resourceInProject.getPeriod().getEndDate();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getPeriod_EndDate_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        when(period.getEndDate()).thenReturn(LocalDate.now().plusDays(2));
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        LocalDate expected = LocalDate.now();

        //Act
        LocalDate result = resourceInProject.getPeriod().getEndDate().plusDays(4);

        //Assert
        assertNotEquals(expected, result);
    }


    /**
     * Test methods for {@link ResourceInProject#getCostPerHour()}
     */
    @Test
    void getCostPerHour_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        when(cost.getValue()).thenReturn(10.0);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        double expected = 10.0;

        //Act
        double result = resourceInProject.getCostPerHour().getValue();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getCostPerHour_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        when(cost.getValue()).thenReturn(10.0);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        double expected = 20.0;

        //Act
        double result = resourceInProject.getCostPerHour().getValue();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test methods for {@link ResourceInProject#getAllocation()}
     */
    @Test
    void getAllocation_Regular_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);
        when(allocation.getAllocation()).thenReturn(20.0);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        double expected = 20.0;

        //Act
        double result = resourceInProject.getAllocation().getAllocation();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getAllocation_Regular_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);
        when(allocation.getAllocation()).thenReturn(20.0);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);
        double expected = 25.0;

        //Act
        double result = resourceInProject.getAllocation().getAllocation();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test methods for {@link ResourceInProject#copy()}
     */
    @Test
    void ensureReturnsACopy_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);

        //Act
        ResourceInProject compare = resourceInProject.copy();

        //Assert
        assertEquals(resourceInProject, compare);
    }

    /**
     * Test methods for {@link ResourceInProject#equals(Object)}
     * Test methods for {@link ResourceInProject#hashCode()}
     */
    @Test
    void equals_SameObject_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);

        //Act
        ResourceInProject compare = resourceInProject;

        //Assert
        assertEquals(resourceInProject, compare);
        assertEquals(resourceInProject.hashCode(), compare.hashCode());
        assertEquals(resourceInProject.getResourceInProjectID(),compare.getResourceInProjectID());
    }

    @Test
    void equals_DifferentAccount_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID1 = mock(ResourceInProjectID.class);
        ResourceInProjectID resourceInProjectID2 = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID1, email, role,
                period, cost, allocation);

        //Act
        ResourceInProject compare = new ResourceInProject(resourceInProjectID2, email, role,
                period, cost, allocation);

        //Assert
        assertNotEquals(resourceInProject, compare);
        assertNotEquals(resourceInProject.hashCode(), compare.hashCode());
        assertNotEquals(resourceInProject.getResourceInProjectID(),compare.getResourceInProjectID());
    }

    @Test
    void equals_differentInstance_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);

        //Act
        ProfileID profileIDNumber =  new ProfileID("1");
        Description description = new Description("User");

        Profile profile = new Profile(profileIDNumber, description);
        //Assert
        assertNotEquals(resourceInProject, profile);
    }

    @Test
    void equals_nullObject_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID1 = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID1, email, role,
                period, cost, allocation);

        //Act
        ResourceInProject compare = null;

        //Assert
        assertNotEquals(resourceInProject, compare);
    }

    /**
     * Test methods for {@link ResourceInProject#identity()}
     */
    @Test
    void ensureSameIdentity_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID, email, role,
                period, cost, allocation);

        //Act
        ResourceInProjectID identity = resourceInProject.identity();

        //Assert
        assertEquals(resourceInProjectID, identity);
    }

    /*@Test
    void ensureSameIDAsOtherObject_Success() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID1 = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID1, email, role,
                period, cost, allocation);
        ResourceInProject compare = new ResourceInProject(resourceInProjectID1, email, role,
                period, cost, allocation);

        boolean expected = true;

        //Act
        boolean result = resourceInProject.sameIDAs(compare);

        //Assert
        assertEquals(expected,result);
    }*/

    @Test
    void ensureIsNotSameValueAsOtherObject_Fail() {
        //Arrange
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID1 = mock(ResourceInProjectID.class);
        ResourceInProjectID resourceInProjectID2 = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID1, email, role,
                period, cost, allocation);

        ResourceInProject compare = new ResourceInProject(resourceInProjectID2, email, role,
                period, cost, allocation);

        boolean expected = false;

        //Act
        boolean result = resourceInProject.sameIDAs(compare);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureIsNotSameValueAsOtherObject_Null() {
        //Arrange
        //Act
        Email email = mock(Email.class);
        ResourceInProjectID resourceInProjectID1 = mock(ResourceInProjectID.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        Cost cost = mock(Cost.class);
        Allocation allocation = mock(Allocation.class);

        ResourceInProject resourceInProject = new ResourceInProject(resourceInProjectID1, email, role,
                period, cost, allocation);

        ResourceInProject compare = null;

        //Assert
        assertFalse(resourceInProject.sameIDAs(compare));
    }
}