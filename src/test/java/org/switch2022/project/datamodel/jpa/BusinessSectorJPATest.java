package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorJPATest {

    @Test
    void testNoArgsConstructor() {
        BusinessSectorJPA businessSector = new BusinessSectorJPA();
        assertNotNull(businessSector);
    }

    @Test
    void ensureConstructorCreatesValidBusinessSector() {

        //Arrange
        long id = 0;
        String businessSectorID = "BS1";
        String businessSectorDescription = "desc1";

        BusinessSectorJPA businessSectorJPA = new BusinessSectorJPA(
                businessSectorID, businessSectorDescription);

        businessSectorJPA.setBusinessSectorID("BS2");
        businessSectorJPA.setBusinessSectorDescription("desc2");

        String businessSectorIDExpected = "BS2";
        String businessSectorDescriptionExpected = "desc2";

        //Assert
        assertEquals(businessSectorIDExpected,businessSectorJPA.getBusinessSectorID());
        assertEquals(businessSectorDescriptionExpected,businessSectorJPA.getBusinessSectorDescription());
    }
}
