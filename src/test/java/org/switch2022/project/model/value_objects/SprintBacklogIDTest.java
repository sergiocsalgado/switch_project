package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogIDTest {

    private SprintBacklogID SprintBacklogID1;
    private SprintBacklogID SprintBacklogID2;

    /**
     * Test class for {@link SprintBacklogID#SprintBacklogID(String)}  SprintBacklogID} ()}.
     */
    @Test
    void ensureThrowExceptionForSprintBacklogIDNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintBacklogID(stringToCheck);
        });
        assertEquals("SprintBacklog ID cannot be null", exception.getMessage());
    }

    /**
     * Test class for {@link SprintBacklogID#SprintBacklogID(String)}  SprintBacklogID} ()}.
     */
    @Test
    void ensureThrowExceptionForSprintBacklogIDEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new SprintBacklogID(stringToCheck);
        });
        assertEquals("SprintBacklog ID cannot be empty", exception.getMessage());
    }

    /**
     * Test class for {@link SprintBacklogID#getIdDescription()} ()}.
     */
    @Test
    void ensureGetsSprintBacklog_Success() {
        //Arrange
        SprintBacklogID sprintBacklogID = new SprintBacklogID("SB3");

        String expected = "SB3";

        //Act
        String result = sprintBacklogID.getIdDescription();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link SprintBacklogID#hashCode()}.
     * Test class for {@link SprintBacklogID#equals(Object)}.
     */
    @Test
    void testEqualsAndHashCodeOfSameValueObjects() {
        //Arrange and Act
        SprintBacklogID1 = new SprintBacklogID("SB1");
        SprintBacklogID2 = SprintBacklogID1;

        //Assert
        assertEquals(SprintBacklogID1, SprintBacklogID2);
        assertEquals(SprintBacklogID1.hashCode(), SprintBacklogID2.hashCode());
        assertEquals(SprintBacklogID1.getIdDescription(), SprintBacklogID2.getIdDescription());
    }


    @Test
    void testEqualsAndHashCodeOfDifferentValueObjects() {
        //Arrange and Act
        SprintBacklogID1 = new SprintBacklogID("SB1");
        SprintBacklogID2 = new SprintBacklogID("SB2");

        //Assert
        assertNotEquals(SprintBacklogID1, SprintBacklogID2);
        assertNotEquals(SprintBacklogID1.hashCode(), SprintBacklogID2.hashCode());
        assertNotEquals(SprintBacklogID1.getIdDescription(), SprintBacklogID2.getIdDescription());
    }

    @Test
    void testEquals_ObjectDifferentClass_Insucess() {
        SprintBacklogID1 = new SprintBacklogID("SB1");

        Object o = new Object();
        assertFalse(SprintBacklogID1.equals(o));
    }

    /**
     * Test class for {@link SprintBacklogID#sameAs(Object)}.
     */
    @Test
    void ensureIsTheSameSprintBacklogID_Sucess() {
        //Arrange
        SprintBacklogID1 = new SprintBacklogID("SB1");
        SprintBacklogID2 = SprintBacklogID1;

        //Act
        boolean result = SprintBacklogID2.sameAs(SprintBacklogID1);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureIsTheSameSprintBacklogID_Insucess() {
        //Arrange
        SprintBacklogID1 = new SprintBacklogID("SB1");
        SprintBacklogID2 = new SprintBacklogID("SB2");

        //Act
        boolean result = SprintBacklogID2.sameAs(SprintBacklogID1);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureIsTheSameSprintBacklogID_Null() {
        //Arrange
        SprintBacklogID1 = new SprintBacklogID("SB1");
        SprintBacklogID2 = null;

        //Act
        boolean result = SprintBacklogID1.sameAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureObjectsAreNotEquals_objectNull() {
        //Arrange
        SprintBacklogID1 = new SprintBacklogID("sb1");
        SprintBacklogID2 = null;

        boolean expected = false;

        //Act
        boolean result = SprintBacklogID1.equals(SprintBacklogID2);

        //Assert
        assertEquals(expected, result);
    }
}