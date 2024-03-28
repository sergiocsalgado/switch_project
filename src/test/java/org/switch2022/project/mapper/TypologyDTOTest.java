package org.switch2022.project.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypologyDTOTest {

    @Test
    void testGetTypologyID() {
        TypologyDTO typology = new TypologyDTO();
        typology.setTypologyID("123");
        assertEquals("123", typology.getTypologyID());
    }

    @Test
    void testSetTypologyID() {
        TypologyDTO typology = new TypologyDTO();
        typology.setTypologyID("123");
        assertEquals("123", typology.getTypologyID());
    }

    @Test
    void testGetTypologyDescription() {
        TypologyDTO typology = new TypologyDTO();
        typology.setTypologyDescription("Description");
        assertEquals("Description", typology.getTypologyDescription());
    }

    @Test
    void testSetTypologyDescription() {
        TypologyDTO typology = new TypologyDTO();
        typology.setTypologyDescription("Description");
        assertEquals("Description", typology.getTypologyDescription());
    }

    @Test
    void testEquals() {
        TypologyDTO typology1 = new TypologyDTO();
        typology1.setTypologyID("123");
        typology1.setTypologyDescription("Description");

        TypologyDTO typology2 = new TypologyDTO();
        typology2.setTypologyID("123");
        typology2.setTypologyDescription("Description");

        TypologyDTO typology3 = new TypologyDTO();
        typology3.setTypologyID("456");
        typology3.setTypologyDescription("Description");

        assertEquals(typology1, typology2);
        assertNotEquals(typology1, typology3);
    }

    @Test
    void testHashCode() {
        TypologyDTO typology1 = new TypologyDTO();
        typology1.setTypologyID("123");
        typology1.setTypologyDescription("Description");

        TypologyDTO typology2 = new TypologyDTO();
        typology2.setTypologyID("123");
        typology2.setTypologyDescription("Description");

        assertEquals(typology1.hashCode(), typology2.hashCode());
    }

    @Test
    void testHashCode_DifferentObjects() {
        TypologyDTO typology1 = new TypologyDTO();
        typology1.setTypologyID("1234");
        typology1.setTypologyDescription("Description");

        TypologyDTO typology2 = new TypologyDTO();
        typology2.setTypologyID("123");
        typology2.setTypologyDescription("Description1");

        assertNotEquals(typology1.hashCode(), typology2.hashCode());
    }

    @Test
    void testEquals_SameObject() {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setTypologyID("1");
        typologyDTO.setTypologyDescription("Test Description");

        assertEquals(typologyDTO,typologyDTO);
    }

    @Test
    void testEquals_NullObject() {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setTypologyID("1");
        typologyDTO.setTypologyDescription("Test Description");

        assertFalse(typologyDTO.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setTypologyID("1");
        typologyDTO.setTypologyDescription("Test Description");

        String otherObject = "Not a TypologyDTO";

        assertNotEquals(typologyDTO,otherObject);
    }

    @Test
    void testEquals_EqualTypologyDTO() {
        TypologyDTO typologyDTO1 = new TypologyDTO();
        typologyDTO1.setTypologyID("1");
        typologyDTO1.setTypologyDescription("Test Description");

        TypologyDTO typologyDTO2 = new TypologyDTO();
        typologyDTO2.setTypologyID("1");
        typologyDTO2.setTypologyDescription("Test Description");

        assertEquals(typologyDTO1,typologyDTO2);
        assertEquals(typologyDTO2,typologyDTO1);
    }

    @Test
    void testEquals_DifferentTypologyID() {
        TypologyDTO typologyDTO1 = new TypologyDTO();
        typologyDTO1.setTypologyID("1");
        typologyDTO1.setTypologyDescription("Test Description");

        TypologyDTO typologyDTO2 = new TypologyDTO();
        typologyDTO2.setTypologyID("2");
        typologyDTO2.setTypologyDescription("Test Description");

        assertNotEquals(typologyDTO1,typologyDTO2);
        assertNotEquals(typologyDTO2,typologyDTO1);
    }

    @Test
    void testEquals_DifferentTypologyDescription() {
        TypologyDTO typologyDTO1 = new TypologyDTO();
        typologyDTO1.setTypologyID("1");
        typologyDTO1.setTypologyDescription("Test Description");

        TypologyDTO typologyDTO2 = new TypologyDTO();
        typologyDTO2.setTypologyID("1");
        typologyDTO2.setTypologyDescription("Different Description");

        assertNotEquals(typologyDTO1,typologyDTO2);
        assertNotEquals(typologyDTO2,typologyDTO1);
    }
}
