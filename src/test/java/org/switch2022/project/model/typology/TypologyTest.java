package org.switch2022.project.model.typology;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import static org.junit.jupiter.api.Assertions.*;

class TypologyTest {

    @Test
    void testTypologyCreation() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription = new Description("Fixed Cost");

        Typology typology = new Typology(typologyID, typologyDescription);

        assertEquals(typologyID, typology.getTypologyID());
    }

    @Test
    void testTypologyCreation_NullID() {
        Description typologyDescription = new Description("Fixed Cost");

        assertThrows(IllegalArgumentException.class, () -> {
            new Typology(null, typologyDescription);
        });
    }

    @Test
    void testTypologyCreation_NullDescription() {
        TypologyID typologyID = new TypologyID("1");

        assertThrows(IllegalArgumentException.class, () -> {
            new Typology(typologyID, null);
        });
    }

    @Test
    void testTypologyCopy() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription = new Description("Fixed Cost");
        Typology typology = new Typology(typologyID, typologyDescription);

        Typology copy = typology.copy();

        assertNotSame(typology, copy);
        assertEquals(typology.getTypologyID(), copy.getTypologyID());
        assertEquals(typology.getDescription(), copy.getDescription());
    }

    @Test
    void testTypologyEquality() {
        TypologyID typologyID1 = new TypologyID("1");
        Description typologyDescription1 = new Description("Time and Materials");
        Typology typology1 = new Typology(typologyID1, typologyDescription1);

        TypologyID typologyID2 = new TypologyID("2");
        Description typologyDescription2 = new Description("Fixed Cost");
        Typology typology2 = new Typology(typologyID2, typologyDescription2);

        TypologyID typologyID3 = new TypologyID("1");
        Description typologyDescription3 = new Description("Time and Materials");
        Typology typology3 = new Typology(typologyID3, typologyDescription3);

        assertEquals(typology1, typology3);
        assertNotEquals(typology1, typology2);
        assertNotEquals(typology2, typology3);
    }

    @Test
    void testSameIDAs_NotTypologyInstance() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription = new Description("Fixed Cost");
        Typology typology = new Typology(typologyID, typologyDescription);

        boolean result = typology.sameIDAs("Not a Typology object");

        assertFalse(result);
    }

    @Test
    void testIdentity() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription =new Description("Fixed Cost");
        Typology typology = new Typology(typologyID, typologyDescription);

        TypologyID result = typology.identity();

        assertEquals(typologyID, result);
    }

    @Test
    void testHashCode_EqualObjects() {
        TypologyID typologyID1 = new TypologyID("1");
        Description typologyDescription1 = new Description("Fixed Cost");
        Typology typology1 = new Typology(typologyID1, typologyDescription1);

        TypologyID typologyID2 = new TypologyID("1");
        Description typologyDescription2 =new Description("Fixed Cost");
        Typology typology2 = new Typology(typologyID2, typologyDescription2);

        int hashCode1 = typology1.hashCode();
        int hashCode2 = typology2.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void testEquals_SameObject() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription = new Description("Fixed Cost");
        Typology typology = new Typology(typologyID, typologyDescription);

        boolean result = typology.equals(typology);

        assertTrue(result);
    }

    @Test
    void testEquals_NullObject() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription = new Description("Fixed Cost");
        Typology typology = new Typology(typologyID, typologyDescription);

        boolean result = typology.equals(null);

        assertFalse(result);
    }
}