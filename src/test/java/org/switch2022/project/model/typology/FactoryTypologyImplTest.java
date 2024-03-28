package org.switch2022.project.model.typology;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FactoryTypologyImplTest {

    @Test
    void testCreateTypology() {
        TypologyID typologyID = new TypologyID("1");
        Description typologyDescription = new Description("Fixed Cost");
        FactoryTypologyImpl factory = new FactoryTypologyImpl();

        Typology typology = factory.createTypology(typologyID, typologyDescription);

        assertNotNull(typology);
        assertEquals(typologyID, typology.getTypologyID());
    }

}