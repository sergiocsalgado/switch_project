package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsNull() {
        String text = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email(text);

        });
        assertEquals("Email cannot be null", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsEmpty() {
        String text = " ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email(text);

        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsNotValid_DoesNotContainAtSign() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email("anaribeiro.com");
        });
        assertEquals("Email is not valid", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsNotValid_DoesNotContainAnyPointAfterAtSign() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email("ana.ribeiro@com");
        });
        assertEquals("Email is not valid", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsNotValid_ContainsTwoPoints() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email("anaribeiro@isep.ipp..pt");
        });
        assertEquals("Email is not valid", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsNotValid_ContainsTwoAtSighs() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email("ana@ribeiro@isep.ipp.pt");
        });
        assertEquals("Email is not valid", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     */
    @Test
    void ensureThrowExceptionForEmailIsNotValid_ContainsSpaces() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Email("ana ribeiro@email.com");
        });
        assertEquals("Email is not valid", exception.getMessage());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#sameAs(Object)}.
     */
    @Test
    void ensureSameAsOtherObject() {
        //Arrange
        Email email = new Email("anacrribeiro@outlook.com");
        boolean expected = true;
        //Act
        boolean result = email.sameAs(email);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#getEmail()}.
     * Unit Test for {@link Email#sameAs(Object)}.
     */
    @Test
    void ensureNotSameAsOtherObject() {
        //Arrange
        Email email = new Email("anacrribeiro@outlook.com");
        Email email1 = new Email("anacribeiro@outlook.com");
        boolean expected = false;
        //Act
        boolean result = email.sameAs(email1);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#sameAs(Object)}.
     */
    @Test
    void ensureNotSameAsNull() {
        Email email = new Email("anacrribeiro@outlook.com");
        Email email0 = null;
        assertFalse(email.sameAs(email0));
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#equals(Object)}.
     */
    @Test
    void ensureObjectsAreTheSame() {
        Email email = new Email("filipe_martins@gmail.com");
        assertTrue(email.equals(email));
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#equals(Object)}.
     */
    @Test
    void ensureObjectIsDifferent_null() {
        Email email = new Email("filipe_martins@gmail.com");
        Email email0 = null;
        assertNotEquals(email, email0);
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#getEmail()}
     * Unit Test for {@link Email#equals(Object)}.
     */
    @Test
    void ensureSameObjects() {
        Email email = new Email("filipe_mateus@gmail.com");
        Email email1 = new Email("filipe_mateus@gmail.com");
        assertEquals(email, email1);
        assertEquals(email.getEmail(), email1.getEmail());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#getEmail()}
     * Unit Test for {@link Email#equals(Object)}.
     */
    @Test
    void ensureDifferentObjects() {
        Email email = new Email("filipe_martins@gmail.com");
        Email email1 = new Email("filipe_mateus@gmail.com");
        assertNotEquals(email, email1);
        assertNotEquals(email.getEmail(), email1.getEmail());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#equals(Object)}.
     */
    @Test
    void ensureObjectIsDifferentForTheSameEmail() {
        Email email = new Email("filipe_martins@gmail.com");
        Object object = "filipe_martins@gmail.com";
        assertNotEquals(email, object);
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#hashCode()}.
     */
    @Test
    void ensureEmailsAreTheSameInstance() {
        Email email = new Email("filipe_martins@gmail.com");
        Email email1 = new Email("filipe_martins@gmail.com");
        assertEquals(email.hashCode(), email1.hashCode());
    }

    /**
     * Unit Test for {@link Email#Email(String)}  Email}.
     * Unit Test for {@link Email#hashCode()}.
     */
    @Test
    void ensureEmailsAreNotTheSameInstance() {
        Email email = new Email("filipe_martins@outlok.com");
        Email email1 = new Email("Filipe_martins@outlook.com");
        assertNotEquals(email.hashCode(), email1.hashCode());
    }
}