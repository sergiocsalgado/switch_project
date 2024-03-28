package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    void throwExceptionWhenDescriptionHigherThan140Char_Success() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            String text = "j".repeat(141);
            new Description(text);
        });
        assertEquals("Description cannot be more than 140 characters.", exception.getMessage());
    }

    @Test
    void throwExceptionWhenDescriptionEmpty_Success() {
        String text = " ";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
           new Description(text);

        });
        assertEquals("Description cannot be empty", exception.getMessage());
    }

    @Test
    void throwExceptionWhenDescriptionNull_Success() {
        String text = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Description(text);

        });
        assertEquals("Description cannot be null", exception.getMessage());
    }

    @Test
    void getDescription_Success() {
        Description description = new Description("desc");
        String expected = "desc";
        String result = description.getDescription();
        assertEquals(expected, result);
    }

    @Test
    void testEquals_SameObject() {
        Description description_1 = new Description("desc");
        Description description_2 = description_1;
        assertEquals(description_1, description_2);
        assertEquals(description_1.getDescription(),description_2.getDescription());
    }
    @Test
    void testEquals_SameObject_DifferentArguments() {
        Description description_1 = new Description("desc");
        Description description_2 = new Description("desc desc");
        assertNotEquals(description_1, description_2);
        assertNotEquals(description_1.getDescription(),description_2.getDescription());
    }

    @Test
    void testEquals_DifferentObjects() {
        Description description_1 = new Description("desc");
        Object differentObject = "compare";
        assertNotEquals(description_1, differentObject);
    }

    @Test
    void testEquals_Null() {
        Description description_1 = new Description("desc");
        Description description_2 = null;
        assertNotEquals(description_1, description_2);
    }

    @Test
    void testHashCode() {
        Description description_1 = new Description("desc");
        Description description_2 = description_1;
        assertEquals(description_1.hashCode(), description_2.hashCode());
    }

    @Test
    void sameAs_Success() {
       Description description=new Description("desc") ;
        boolean expected = true;
        boolean result = description.sameAs(description);
        assertEquals(expected, result);
    }

    @Test
    void sameAs_Fail() {
        Description description=new Description("desc") ;
        Description description2=new Description("desc2") ;
        description2.getDescription();
        boolean expected = false;
        boolean result = description.sameAs(description2);
        assertEquals(expected, result);
    }

    @Test
    void sameAs_Null() {
        Description description= new Description("desc") ;
        Description description2=null ;
        assertFalse(description.sameAs(description2));
    }

}