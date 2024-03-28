package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypologyJPATest {

    @Test
    void testNoArgsConstructor() {
        TypologyJPA typology = new TypologyJPA();

        // Assert that the typology object is not null
        Assertions.assertNotNull(typology);

        // Assert that the typologyID field is null
        Assertions.assertNull(typology.getTypologyID());

        // Assert that the typologyDescription field is null
        Assertions.assertNull(typology.getTypologyDescription());
    }

    @Test
    void ensureConstructorCreatesValidTypology() {
        //Arrange
        String typologyID = "TP1";
        String typologyDescription = "desc1";

        TypologyJPA typologyJPA = new TypologyJPA(
                typologyID, typologyDescription
        );

        //Assert
        assertEquals(typologyID, typologyJPA.getTypologyID());
        assertEquals(typologyDescription, typologyJPA.getTypologyDescription());
    }
}
