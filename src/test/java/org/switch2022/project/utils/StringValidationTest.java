package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class StringValidationTest {

    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException,
            InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = StringValidation.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    @Test
    void checkNull_ThrowsIllegalArgumentException() {
        //Arrange
        String stringToCheck = null;
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            StringValidation.checkNull("parameter", stringToCheck);
        });

        //Assert
        assertEquals("parameter cannot be null", exception.getMessage());
    }
    @Test
    void checkNull_NotNull() {
        //Arrange
        String validString = "string";

        //Act; Assert
        assertDoesNotThrow(() -> StringValidation.checkNull("parameter", validString));
    }

    @Test
    void checkBlank_ThrowsIllegalArgumentExceptionWhenEmpty() {
        //Arrange
        String stringToCheck = "";
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            StringValidation.checkBlank("parameter", stringToCheck);
        });

        //Assert
        assertEquals("parameter cannot be empty", exception.getMessage());
    }

    @Test
    void checkBlank_ThrowsIllegalArgumentExceptionWhenBlank() {
        //Arrange
        String stringToCheck = "  ";
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {

            StringValidation.checkBlank("parameter", stringToCheck);
        });

        //Assert
        assertEquals("parameter cannot be empty", exception.getMessage());
    }
    @Test
    void checkBlank_notBlack() {
        //Arrange
        String validString = "string";

        //Act; Assert
        assertDoesNotThrow(() -> StringValidation.checkBlank("parameter", validString));
    }
}