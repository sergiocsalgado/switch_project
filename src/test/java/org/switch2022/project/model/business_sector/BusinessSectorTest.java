package org.switch2022.project.model.business_sector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessSectorTest {

    @Mock
    BusinessSectorID businessSectorID;

    @Mock
    Description businessSectorDescription;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test class for {@link BusinessSector#BusinessSector(BusinessSectorID, Description)}  BusinessSector} .
     */
    @Test
    void ensureBusinessSectorIDCanNotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSector(null, businessSectorDescription);
        });
        assertEquals("Business Sector ID can not be null", exception.getMessage());
    }

    /**
     * Test class for {@link BusinessSector#BusinessSector(BusinessSectorID, Description)}  BusinessSector} .
     */
    @Test
    void ensureBusinessSectorDescriptionCanNotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSector(businessSectorID, null);
        });
        assertEquals("Business Sector Description can not be null", exception.getMessage());
    }

    /**
     * Test class for {@link BusinessSector#verifyBusinessSectorDescription(Description)} .
     */
    @Test
    void ensureDescriptionsAreEqual() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        boolean expected = true;
        //Act
        boolean result = businessSector.verifyBusinessSectorDescription(businessSectorDescription);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link BusinessSector#verifyBusinessSectorDescription(Description)} .
     */
    @Test
    void ensureDescriptionsAreDifferent() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        boolean expected = false;
        Description businessSectorDescription0 = mock(Description.class);
        //Act
        boolean result = businessSector.verifyBusinessSectorDescription(businessSectorDescription0);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link BusinessSector#copy()} .
     */
    @Test
    void ensureGetBusinessSectorCopy() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        BusinessSector expected = businessSector;
        //Act
        BusinessSector result = businessSector.copy();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link BusinessSector#getBusinessSectorID()} .
     */
    @Test
    void ensureGetBusinessSectorID() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        BusinessSectorID expected = businessSectorID;
        //Act
        BusinessSectorID result = businessSector.getBusinessSectorID();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link BusinessSector#getBusinessSectorDescription()} .
     */
    @Test
    void ensureGetBusinessSectorDescription() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        Description expected = businessSectorDescription;
        //Act
        Description result = businessSector.getBusinessSectorDescription();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link BusinessSector#equals(Object)} .
     */
    @Test
    void ensureObjectsAreEqual() {
        //Arrange
        BusinessSector businessSector1 = new BusinessSector(businessSectorID, businessSectorDescription);
        //Act
        BusinessSector businessSector2 = businessSector1;
        //Assert
        assertEquals(businessSector1, businessSector2);
    }

    /**
     * Test class for {@link BusinessSector#equals(Object)} .
     * Test class for {@link BusinessSector#sameIDAs(Object)} .
     */
    @Test
    void ensureObjectsClassAreNotEquals() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        Object o = new Object();
        //Act
        boolean isEquals = businessSector.equals(o);
        //Assert
        assertFalse(isEquals);
        assertFalse(businessSector.sameIDAs(o));
    }

    /**
     * Test class for {@link BusinessSector#equals(Object)} .
     * Test class for {@link BusinessSector#sameIDAs(Object)} .
     */
    @Test
    void ensureObjectsAreNotEquals() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);

        BusinessSectorID businessSectorID0 = mock(BusinessSectorID.class);
        Description businessSectorDescription0 = mock(Description.class);
        BusinessSector businessSector0 = new BusinessSector(businessSectorID0, businessSectorDescription0);
        //Act
        boolean isEquals = businessSector.equals(businessSector0);
        //Assert
        assertFalse(isEquals);
        assertFalse(businessSector.sameIDAs(businessSector0));
    }

    /**
     * Test class for {@link BusinessSector#equals(Object)} .
     */
    @Test
    void ensureObjectsAreNotEqual_objectNull() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        //Act
        BusinessSector businessSector0 = null;
        //Assert
        assertNotEquals(businessSector, businessSector0);
    }

    /**
     * Test class for {@link BusinessSector#hashCode()} .
     */
    @Test
    void ensureHashCodeAreEqual() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        //Act
        BusinessSector businessSector0 = new BusinessSector(businessSectorID, businessSectorDescription);
        //Assert
        assertEquals(businessSector.hashCode(), businessSector0.hashCode());
    }

    /**
     * Test class for {@link BusinessSector#hashCode()} .
     */
    @Test
    void ensureHashCodeAreDifferent() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);

        BusinessSectorID businessSectorID0 = mock(BusinessSectorID.class);
        Description businessSectorDescription0 = mock(Description.class);
        //Act
        BusinessSector businessSector0 = new BusinessSector(businessSectorID0, businessSectorDescription0);
        //Assert
        assertNotEquals(businessSector.hashCode(), businessSector0.hashCode());
    }

    /**
     * Test class for {@link BusinessSector#identity()} .
     */
    @Test
    void ensureBusinessSectorIdentity() {
        //Arrange
        BusinessSector businessSector = new BusinessSector(businessSectorID, businessSectorDescription);
        BusinessSectorID expectedIdentity = businessSectorID;
        //Act
        BusinessSectorID resultIdentity = businessSector.identity();
        //Assert
        assertEquals(expectedIdentity, resultIdentity);
    }

    /**
     * Test class for {@link BusinessSector#equals(Object)} .
     * * Test class for {@link BusinessSector#sameIDAs(Object)} .
     */
    @Test
    void ensureSameIDAsOtherObject_differentBusinessSectorID() {
        //Arrange
        BusinessSector businessSector1 = new BusinessSector(businessSectorID, businessSectorDescription);

        BusinessSectorID businessSectorID2 = mock(BusinessSectorID.class);
        Description businessSectorDescription2 = mock(Description.class);
        BusinessSector businessSector2 = new BusinessSector(businessSectorID2, businessSectorDescription2);

        boolean expected = false;
        //Act
        boolean result = businessSector1.sameIDAs(businessSector2);
        //Assert
        assertEquals(expected, result);
    }
}