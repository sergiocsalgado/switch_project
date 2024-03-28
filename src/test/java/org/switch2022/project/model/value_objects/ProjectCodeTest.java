package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeTest {

    @Test
    void ensureProjectCodeCannotBeNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectCode(stringToCheck);
        });
        assertEquals("Project code cannot be null", exception.getMessage());
    }

    @Test
    void ensureProjectCodeCannotBeEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Project Code", stringToCheck);
        });
        assertEquals("Project Code cannot be empty", exception.getMessage());
    }

    @Test
    void ensureProjectCodeCannotBeBlank() {
        String stringToCheck = "    ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            StringValidation.checkBlank("Project Code", stringToCheck);
        });
        assertEquals("Project Code cannot be empty", exception.getMessage());
    }

    @Test
    void ensureGetProjectCode() {
        //Arrange
        ProjectCode projectCode = new ProjectCode("PRJ1");

        String expected = "PRJ1";

        //Act
        String result = projectCode.getProjectCode();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureObjectsAreFromDifferentClasses() {
        //Arrange
        ProjectCode projectCode = new ProjectCode("PRJ1");

        Object o = new Object();

        //Act
        boolean isEquals = projectCode.equals(o);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void ensureNullProjectCodeIsNotEqualToNewProjectCode() {
        //Arrange
        ProjectCode projectCode = new ProjectCode("PRJ1");

        ProjectCode projectCode1 = null;

        //Act
        boolean isEquals = projectCode.equals(projectCode1);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void ensureProjectCodesAreNotEqual() {
        //Arrange
        ProjectCode projectCode = new ProjectCode("PRJ1");

        ProjectCode projectCode1 = new ProjectCode("PRJ2");

        //Act
        boolean isEquals = projectCode.equals(projectCode1);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void ensureHashCodeIsTheSame() {
        ProjectCode projectCode = new ProjectCode("PRJ1");

        ProjectCode projectCode1 = projectCode;

        assertEquals(projectCode.hashCode(), projectCode1.hashCode());
    }

    @Test
    void ensureSameAsOtherObject() {
        ProjectCode projectCode = new ProjectCode("PRJ1");
        ProjectCode projectCode1 = projectCode;

        boolean isEquals = projectCode.sameAs(projectCode1);

        assertTrue(isEquals);
    }

    @Test
    void ensureSameAsOtherObjectEqualsFalse_ObjectNull() {
        ProjectCode projectCode = new ProjectCode("PRJ1");
        ProjectCode projectCode1 = null;

        boolean isEquals = projectCode.sameAs(projectCode1);

        assertFalse(isEquals);
    }
}