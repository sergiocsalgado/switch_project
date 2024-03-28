package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ProjectStatus ()}.
 * PS = Project Status
 */

class ProjectStatusTest {


    /**
     * Unit Test for {@link ProjectStatus(String)}.
     * Testing exceptions in the method.
     */

    @Test
    void ensurePSCannotBeNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkNull("Project Status", stringToCheck);
        });
        assertEquals("Project Status cannot be null", exception.getMessage());
    }

    @Test
    void ensurePSCannotBeEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Project Status", stringToCheck);
        });
        assertEquals("Project Status cannot be empty", exception.getMessage());
    }

    @Test
    void ensurePSCannotBeBlank() {
        String stringToCheck = "    ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Project Status", stringToCheck);
        });
        assertEquals("Project Status cannot be empty", exception.getMessage());
    }

    @Test
    void ensurePSCanOnlyBeOneOfThe7thDescriptions() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ProjectStatus projectStatus = new ProjectStatus("pl");
        });
        assertEquals("The status of the project is not valid (check valid status for more information)", exception.getMessage());
    }

    /**
     * Test for {@link ProjectStatus#equals(Object)}
     */

    @Test
    void ensureSameInstance() {
        assertInstanceOf(ProjectStatus.class, new ProjectStatus("planned"));
    }

    @Test
    void ensureObjectsAreTheSame() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = projectStatus;
        assertEquals(projectStatus, projectStatus1);
    }

    @Test
    void equals_ensureObjectsAreEquals() {
        ProjectStatus projectStatus = new ProjectStatus("planned");
        ProjectStatus projectStatus1 = new ProjectStatus("planned");

        assertEquals(projectStatus, projectStatus1);
    }

    @Test
    void ensureObjectsAreDifferent() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = new ProjectStatus("construction");
        assertNotEquals(projectStatus, projectStatus1);
    }

    @Test
    void ensureObjectsAreDifferent_Null() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        assertNotEquals(null, projectStatus);
    }

    /**
     * Unit Test for {@link ProjectStatus#equals(Object)}
     */

    @Test
    void ensureObjectsAreTheSame_Unit() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = projectStatus;
        assertEquals(projectStatus, projectStatus1);
    }

    @Test
    void equals_ensureObjectsAreEquals_Unit() {
        ProjectStatus projectStatus = new ProjectStatus("planned");
        ProjectStatus projectStatus1 = new ProjectStatus("planned");

        assertEquals(projectStatus, projectStatus1);
    }

    @Test
    void ensureObjectsAreDifferent_Unit() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = new ProjectStatus("construction");
        assertNotEquals(projectStatus, projectStatus1);
    }

    @Test
    void ensureObjectsAreDifferent_Null_Unit() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        assertNotEquals(null, projectStatus);
    }

    /**
     * Test for {@link ProjectStatus#hashCode()}
     */

    @Test
    void testHashCode_Success() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = new ProjectStatus("inception");

        assertEquals(projectStatus.hashCode(), projectStatus1.hashCode());
    }

    @Test
    void testHashCode_Fail() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = new ProjectStatus("construction");

        assertNotEquals(projectStatus.hashCode(), projectStatus1.hashCode());
    }

    /**
     * Unit Test for {@link ProjectStatus#hashCode()}
     */

    @Test
    void testHashCode_Success_Unit() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = new ProjectStatus("inception");

        assertEquals(projectStatus.hashCode(), projectStatus1.hashCode());
    }

    @Test
    void testHashCode_Fail_Unit() {
        ProjectStatus projectStatus = new ProjectStatus("inception");
        ProjectStatus projectStatus1 = new ProjectStatus("construction");

        assertNotEquals(projectStatus.hashCode(), projectStatus1.hashCode());
    }

    /**
     * Test for {@link ProjectStatus#getProjectStatus()}.
     */
    @Test
    void getProjectStatus_ensureSamePS() {
        ProjectStatus projectStatus = new ProjectStatus("warranty");
        String expected = "warranty";
        String res = projectStatus.getProjectStatus();
        assertEquals(expected, res);
    }

    @Test
    void getProjectStatus_ensureDifferentPS() {
        ProjectStatus projectStatus = new ProjectStatus("warranty");
        String expected = "planned";
        String res = projectStatus.getProjectStatus();
        assertNotEquals(expected, res);
    }

    /**
     * Test class for {@link ProjectStatus#sameAs(ProjectStatus)}
     */
    @Test
    void sameAs_ensureISTrueIfThePSAreTheSame() {

        ProjectStatus projectStatus = new ProjectStatus("planned");
        ProjectStatus projectStatus1 = new ProjectStatus("planned");
        boolean result = projectStatus.sameAs(projectStatus1);
        assertTrue(result);
    }

    @Test
    void sameAs_ensureIsFalseWhenThePSSAreDifferent() {
        //Arrange

        ProjectStatus projectStatus = new ProjectStatus("planned");
        ProjectStatus projectStatus1 = new ProjectStatus("closed");
        boolean result = projectStatus.sameAs(projectStatus1);
        assertFalse(result);

    }

    @Test
    void twoObjectsWithDifferentValuesAreNotEqual() {
        ProjectStatus projectStatus = new ProjectStatus("planned");
        Object object = new Object();
        assertFalse(projectStatus.equals(object));
    }

    @Test
    void ensureDoNotCreateProjectStatus_emptyDescription(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectStatus("");
        });
        assertEquals("Project Status cannot be empty", exception.getMessage());
    }
    @Test
    void ensureDoNotCreateProjectStatus_nullDescription(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectStatus(null);
        });
        assertEquals("Project Status cannot be null", exception.getMessage());
    }
    @Test
    void ensureObjectsAreNotEquals_objectNull(){
        ProjectStatus projectStatus1 = new ProjectStatus("planned");
        ProjectStatus projectStatus2 = null;

        boolean expected = false;

        boolean result = projectStatus1.equals(projectStatus2);

        assertEquals(expected, result);
    }
    @Test
    void ensureObjectsNotHaveSameID_idNull(){
        ProjectStatus projectStatus1 = new ProjectStatus("planned");
        ProjectStatus projectStatus2 = null;

        boolean expected = false;

        boolean result = projectStatus1.sameAs(projectStatus2);

        assertEquals(expected, result);
    }
}