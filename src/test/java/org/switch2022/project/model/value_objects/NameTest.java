package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    /**
     * Unit test for class{@link Name}.
     */
    @Test
    void NameSuccess() {
        //Arrange
        Name name = new Name("Ana Luis");
        //Act
        //Assert
        assertInstanceOf(Name.class, name);
    }

    /**
     * Unit test for class{@link Name}.
     */
    @Test
    void NameNull() {
        String text = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(text);

        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    /**
     * Unit test for class{@link Name}.
     */
    @Test
    void NameEmpty() {
        String text = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(text);

        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    /**
     * Unit test for class{@link Name}.
     */

    @Test
    void getDescription() {
        //Arrange
        Name name = new Name("Ht6");
        //Act
        //Assert
        assertEquals("Ht6", name.getValue());
    }

    /**
     * Unit test for class{@link Name}.
     */

    @Test
    void nameEquals() {
        //Arrange
        Name name = new Name("Ht6");
        Name nameTow = name;
        //Act
        //Assert
        assertEquals(nameTow, name);
    }

    /**
     * Unit test for class{@link Name}.
     */

    @Test
    void nameEqualsTwo() {
        //Arrange
        Name name = new Name("Ht6");
        Name nameTow = new Name("Ht6");
        //Act
        //Assert
        assertEquals(nameTow, name);
    }

    /**
     * Unit test for class{@link Name}.
     */

    @Test
    void nameEqualsFail() {
        //Arrange
        Name name = new Name("Ht6");
        PositiveNumber positiveNumber = new PositiveNumber(1);
        //Act
        //Assert
        assertNotEquals(positiveNumber, name);
    }

    /**
     * Unit test for class{@link Name}.
     */

    @Test
    void nameHashCode() {
        //Arrange
        Name name = new Name("Ht6");
        Name name1 = new Name("Ht6");
        //Act
        //Assert
        assertEquals(name1.hashCode(), name.hashCode());
    }

    /**
     * Unit test for class{@link Name}.
     */
    @Test
    void nameHashCodeFail() {
        //Arrange
        Name name = new Name("Ht6");
        Name name1 = new Name("PTH");
        //Act
        //Assert
        assertNotEquals(name1.hashCode(), name.hashCode());
    }

    /**
     * Unit test for class{@link Name}.
     */
    @Test
    void nameEqualNull() {
        //Arrange
        Name name = new Name("Ht6");
        Name nameTow = null;
        //Act
        //Assert
        assertFalse(name.equals(nameTow));
    }

    /**
     * Unit test for class{@link Name}.
     */
    @Test
    public void testEqualsWithDifferentClass() {
        //Arrange
        Name name = new Name("John");
        //Act
        //Assert
        assertFalse(name.equals(new Object()));
    }
}
