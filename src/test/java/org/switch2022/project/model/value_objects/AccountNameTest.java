package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class AccountNameTest {

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}  AccountName}.
     */
    @Test
    void ensureThrowExceptionForAccountNameNull() {
        String stringToCheck = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new AccountName(stringToCheck);
        });
        assertEquals("Account Name cannot be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     */
    @Test
    void ensureThrowExceptionForAccountNameEmpty() {
        String stringToCheck = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new AccountName(stringToCheck);
        });
        assertEquals("Account Name cannot be empty", exception.getMessage());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     */
    @Test
    void ensureThrowExceptionForAccountNameBlank() {
        String stringToCheck = " ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new AccountName(stringToCheck);
        });
        assertEquals("Account Name cannot be empty", exception.getMessage());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     */
    @Test
    void ensureThrowExceptionForAccountNameCannotHaveNumbers() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new AccountName("An1a Ribeiro");
        });
        assertEquals("Name cannot have numbers", exception.getMessage());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     */
    @Test
    void ensureThrowExceptionForAccountNameCannotBeBiggerThan40Characters() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new AccountName("Margarida Sebastiana Ferreira Sousa Silva");
        });
        assertEquals("Name cannot exceed 40 characters", exception.getMessage());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * Unit Test for {@link AccountName#sameAs(AccountName)}.
     */
    @Test
    void ensureSameAsOtherObject() {
        //Arrange
        AccountName name = new AccountName("Ana Catarina Rosendo Ribeiro");
        boolean expected = true;
        //Act
        boolean result = name.sameAs(name);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * Unit Test for {@link AccountName#getAccountName()}.
     * Unit Test for {@link AccountName#sameAs(AccountName)}.
     */
    @Test
    void ensureNotSameAsOtherObject() {
        //Arrange
        AccountName name = new AccountName("Ana Catarina Rosendo Ribeiro");
        AccountName name1 = new AccountName("Ana Caterina Rosendo Ribeiro");
        boolean expected = false;
        //Act
        boolean result = name.sameAs(name1);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * Unit Test for {@link AccountName#sameAs(AccountName)}.
     */
    @Test
    void ensureNotSameAsNull() {
        AccountName name = new AccountName("João Morais");
        AccountName name0 = null;
        assertFalse(name.sameAs(name0));
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * * Unit Test for {@link AccountName#equals(Object)}.
     */
    @Test
    void ensureObjectsAreTheSameAndNameCanBeOf40Character() {
        AccountName name = new AccountName("Catarina Sebastiana Ferreira Sousa Silva");
        assertTrue(name.equals(name));
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * * Unit Test for {@link AccountName#equals(Object)}.
     */
    @Test
    void ensureObjectIsDifferent_null() {
        AccountName name = new AccountName("Sofia Daniela Sousa Martins");
        AccountName name0 = null;
        assertNotEquals(name, name0);
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * Unit Test for {@link AccountName#getAccountName()}.
     * Unit Test for {@link AccountName#equals(Object)}.
     */
    @Test
    void ensureSameObjects() {
        AccountName name = new AccountName("sofia daniela sousa martins");
        AccountName name1 = new AccountName("sofia daniela sousa martins");
        assertEquals(name, name1);
        assertEquals(name.getAccountName(), name1.getAccountName());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * Unit Test for {@link AccountName#getAccountName()}.
     * Unit Test for {@link AccountName#equals(Object)}.
     */
    @Test
    void ensureDifferentObjects() {
        AccountName name = new AccountName("Sofia Daniela Pereira");
        AccountName name1 = new AccountName("Sofia Daniel Pereira");
        assertNotEquals(name, name1);
        assertNotEquals(name.getAccountName(), name1.getAccountName());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * * Unit Test for {@link AccountName#equals(Object)}.
     */
    @Test
    void ensureObjectIsDifferentForTheSameName() {
        AccountName name = new AccountName("Sofia Daniela Sousa Martins");
        Object object = "Sofia Daniela Sousa Martins";
        assertNotEquals(name, object);
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * * Unit Test for {@link AccountName#hashCode()}.
     */
    @Test
    void ensureNamesAreTheSameInstance() {
        AccountName name = new AccountName("Fernando Magalhães");
        AccountName name1 = new AccountName("Fernando Magalhães");
        assertEquals(name.hashCode(), name1.hashCode());
    }

    /**
     * Unit Test for {@link AccountName#AccountName(String)}  AccountName}.
     * * Unit Test for {@link AccountName#hashCode()}.
     */
    @Test
    void ensureNamesAreNotTheSameInstance() {
        AccountName name = new AccountName("Fernando Marcus");
        AccountName name1 = new AccountName("Fernando Marcos");
        assertNotEquals(name.hashCode(), name1.hashCode());
    }
}