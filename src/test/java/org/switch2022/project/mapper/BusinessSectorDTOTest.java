package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorDTOTest {

    /**
     * Test class for {@link BusinessSectorDTO#setBusinessSectorID(String)}.
     * Test class for {@link BusinessSectorDTO#getBusinessSectorID()}.
     */
    @Test
    void ensureGetBusinessSectorIDReturnsSetBusinessSectorID() {
        //Arrange
        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        businessSectorDTO.setBusinessSectorID("bs-1");
        businessSectorDTO.setBusinessSectorID("bs-2");
        String expected = "bs-2";
        //Act
        String result = businessSectorDTO.getBusinessSectorID();
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Test class for {@link BusinessSectorDTO#setDescription(String)}.
     * Test class for {@link BusinessSectorDTO#getDescription()}.
     */
    @Test
    void ensureGetDescriptionReturnsSetDesvription() {
        //Arrange
        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        businessSectorDTO.setDescription("E-commerce");
        businessSectorDTO.setDescription("Finance");
        String expected = "Finance";
        //Act
        String result = businessSectorDTO.getDescription();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link BusinessSectorDTO#equals(Object)}.
     */
    @Test
    void ensureObjectNotEqualsNull() {
        BusinessSectorDTO businessSectorDTO1 = null;
        BusinessSectorDTO businessSectorDTO2 = new BusinessSectorDTO();
        assertNotEquals(businessSectorDTO2,businessSectorDTO1);
    }

    /**
     * Test class for {@link BusinessSectorDTO#equals(Object)}.
     */
    @Test
    void ensureDifferentObjects() {
        //Arrange
        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        //Act
        Object o = new Object();
        //Assert
        assertNotEquals(businessSectorDTO, o);
    }

    /**
     * Test class for {@link BusinessSectorDTO#equals(Object)}.
     */
    @Test
    void ensureObjectsAreEqual() {
        //Arrange
        BusinessSectorDTO businessSectorDTO1 = new BusinessSectorDTO();
        //Act
        BusinessSectorDTO businessSectorDTO2 = businessSectorDTO1;
        //Assert
        assertEquals(businessSectorDTO1, businessSectorDTO2);
    }

    /**
     * Test class for {@link BusinessSectorDTO#hashCode()}.
     * Test class for {@link BusinessSectorDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectHaveEqualHashCode() {
        //Arrange
        String businessSectorID = "bs-1";
        String description = "E-commerce";

        BusinessSectorDTO businessSectorDTO1 = new BusinessSectorDTO();
        businessSectorDTO1.setBusinessSectorID(businessSectorID);
        businessSectorDTO1.setDescription(description);

        //Act
        BusinessSectorDTO businessSectorDTO2 = new BusinessSectorDTO();
        businessSectorDTO2.setBusinessSectorID(businessSectorID);
        businessSectorDTO2.setDescription(description);

        //Assert
        assertEquals(businessSectorDTO1.hashCode(), businessSectorDTO2.hashCode());
        assertEquals(businessSectorDTO1, businessSectorDTO2);
    }

    /**
     * Test class for {@link BusinessSectorDTO#hashCode()}.
     * Test class for {@link BusinessSectorDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectDontHaveEqualHashCode_differentBusinessSectorID() {
        //Arrange
        String businessSectorID1 = "bs-1";
        String description1 = "E-commerce";

        BusinessSectorDTO businessSectorDTO1 = new BusinessSectorDTO();
        businessSectorDTO1.setBusinessSectorID(businessSectorID1);
        businessSectorDTO1.setDescription(description1);

        //Act
        String businessSectorID2 = "bs-2";
        String description2 = "E-commerce";

        BusinessSectorDTO businessSectorDTO2 = new BusinessSectorDTO();
        businessSectorDTO2.setBusinessSectorID(businessSectorID2);
        businessSectorDTO2.setDescription(description2);

        //Assert
        assertNotEquals(businessSectorDTO1.hashCode(), businessSectorDTO2.hashCode());
        assertNotEquals(businessSectorDTO1, businessSectorDTO2);
    }

    /**
     * Test class for {@link BusinessSectorDTO#hashCode()}.
     * Test class for {@link BusinessSectorDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectDontHaveEqualHashCode_differentDescription() {
        //Arrange
        String businessSectorID1 = "bs-1";
        String description1 = "E-commerce";

        BusinessSectorDTO businessSectorDTO1 = new BusinessSectorDTO();
        businessSectorDTO1.setBusinessSectorID(businessSectorID1);
        businessSectorDTO1.setDescription(description1);

        //Act
        String businessSectorID2 = "bs-1";
        String description2 = "Finance";

        BusinessSectorDTO businessSectorDTO2 = new BusinessSectorDTO();
        businessSectorDTO2.setBusinessSectorID(businessSectorID2);
        businessSectorDTO2.setDescription(description2);

        //Assert
        assertNotEquals(businessSectorDTO1.hashCode(), businessSectorDTO2.hashCode());
        assertNotEquals(businessSectorDTO1, businessSectorDTO2);
    }
}