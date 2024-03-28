package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.AccountMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateManagementTest {

    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException,
            InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = DateManagement.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    @Test
    void toLocalDate() {
        String date = "2023-05-17";

        LocalDate expected = LocalDate.of(2023,05,17);
        LocalDate result= DateManagement.toLocalDate(date);
        assertEquals(expected,result);
    }

    @Test
    void ensureLocalDateIsNull() {
        String date = null;

        LocalDate expected = null;
        LocalDate result= DateManagement.toLocalDate(date);
        assertEquals(expected,result);
    }
}