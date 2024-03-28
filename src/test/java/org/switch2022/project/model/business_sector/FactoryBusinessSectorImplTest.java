package org.switch2022.project.model.business_sector;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class FactoryBusinessSectorImplTest {

    /**
     * Test class for {@link FactoryBusinessSectorImpl#addBusinessSector(BusinessSectorID, Description)}.
     */
    @Test
    void ensureAddsBusinessSector_Success() {
        //Arrange
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        Description businessSectorDescription = mock(Description.class);

        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        FactoryBusinessSector factoryBusinessSector = new FactoryBusinessSectorImpl();
        BusinessSector expected = businessSector;
        //Act
        BusinessSector result = factoryBusinessSector.addBusinessSector(businessSectorID, businessSectorDescription);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link FactoryBusinessSectorImpl#addBusinessSector(BusinessSectorID, Description)}.
     */
    @Test
    void ensureAddsOtherBusinessSector_Fail() {
        //Arrange
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        BusinessSectorID businessSectorID0 = mock(BusinessSectorID.class);
        Description businessSectorDescription = mock(Description.class);

        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        FactoryBusinessSector factoryBusinessSector = new FactoryBusinessSectorImpl();
        BusinessSector expected = businessSector;
        //Act
        BusinessSector result = factoryBusinessSector.addBusinessSector(businessSectorID0, businessSectorDescription);
        //Assert
        assertNotEquals(expected, result);
    }
}