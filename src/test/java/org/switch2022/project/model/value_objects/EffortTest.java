package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffortTest {
    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    void effortSuccess() {
        //Arrange
        //Act
        //Assert
        assertInstanceOf(Effort.class, new Effort(1));
        assertInstanceOf(Effort.class, new Effort(2));
        assertInstanceOf(Effort.class, new Effort(3));
        assertInstanceOf(Effort.class, new Effort(5));
        assertInstanceOf(Effort.class, new Effort(8));
        assertInstanceOf(Effort.class, new Effort(13));
        assertInstanceOf(Effort.class, new Effort(20));
        assertInstanceOf(Effort.class, new Effort(40));
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    void effortNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Effort(10);
        });
        assertEquals("This Story Points is not valid!", exception.getMessage());
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    void effortNull4() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Effort(4);
        });
        assertEquals("This Story Points is not valid!", exception.getMessage());
    }

    /**
     * Unit test for class{@link Effort}.
     */

    @Test
    void getStoryPoints() {
        //Arrange
        Effort effort = new Effort(0);
        //Act
        //Assert
        assertEquals(0, effort.getStoryPoints());

    }

    /**
     * Unit test for class{@link Effort}.
     */

    @Test
    void getStoryPointsFail() {
        //Arrange
        Effort effort = new Effort(3);
        //Act
        //Assert
        assertNotEquals(5, effort.getStoryPoints());

    }

    /**
     * Unit test for class{@link Effort}.
     */

    @Test
    void effortEquals() {
        //Arrange
        Effort effort = new Effort(2);
        Effort effortTow = effort;
        //Act
        //Assert
        assertEquals(effortTow, effort);
    }

    /**
     * Unit test for class{@link Effort}.
     */

    @Test
    void effortEqualsTwo() {
        //Arrange
        Effort effort = new Effort(2);
        Effort effortTow = new Effort(2);
        //Act
        //Assert
        assertEquals(effortTow, effort);
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    void effortEqualsFail() {
        //Arrange
        Effort effort = new Effort(2);
        PositiveNumber positiveNumber = new PositiveNumber(1);
        //Act
        //Assert
        assertNotEquals(positiveNumber, effort);
    }

    /**
     * Unit test for class{@link Effort}.
     */

    @Test
    void effortHashCode() {
        //Arrange
        Effort effort = new Effort(5);
        Effort effortTow = new Effort(5);
        //Act
        //Assert
        assertEquals(effortTow.hashCode(), effort.hashCode());
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    public void testSameAs() {
        //Arrange
        Effort effort = new Effort(5);
        //Act
        //Assert
        assertTrue(effort.sameAs(new Effort(5)));
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    public void testSameAsFail() {
        //Arrange
        Effort effort = new Effort(5);
        //Act
        //Assert
        assertFalse(effort.sameAs(new Effort(2)));
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    void effortHashCodeFail() {
        //Arrange
        Effort effort = new Effort(2);
        Effort effortTow = new Effort(1);
        //Act
        //Assert
        assertNotEquals(effortTow.hashCode(), effort.hashCode());
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    void effortEqualNull() {
        //Arrange
        Effort effort = new Effort(3);
        Effort effortTow = null;
        //Act
        //Assert
        assertFalse(effort.equals(effortTow));
    }

    /**
     * Unit test for class{@link Effort}.
     */
    @Test
    public void testEqualsWithDifferentClass() {
        //Arrange
        Effort effort = new Effort(3);
        //Act
        //Assert
        assertFalse(effort.equals(new Object()));
    }

}