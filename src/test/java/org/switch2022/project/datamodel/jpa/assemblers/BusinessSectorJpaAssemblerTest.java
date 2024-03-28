package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.jpa.BusinessSectorJPA;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BusinessSectorJpaAssemblerTest {

    @Test
    void ensureReturnsAValidBusinessSectorGivenABusinessSectorJPA() {
        //Arrange
        String businessSectorID = "BS1";
        String description = "desc1";

        BusinessSectorJPA businessSectorJPA = new BusinessSectorJPA(businessSectorID, description);

        BusinessSectorID businessSectorID1 = new BusinessSectorID(businessSectorID);
        Description description1 = new Description(description);

        BusinessSector businessSector = new BusinessSector(businessSectorID1, description1);

        //Act
        BusinessSector result = BusinessSectorJpaAssembler.toDomain(businessSectorJPA);

        //Assert
        assertEquals(businessSector.getBusinessSectorID(), result.getBusinessSectorID());
        assertEquals(businessSector.getBusinessSectorDescription(), result.getBusinessSectorDescription());
    }

    @Test
    void ensureReturnsAValidBusinessSectorJPAGivenABusinessSector() {
        //Arrange
        String businessSectorID = "BS1";
        String description = "desc1";

        BusinessSectorJPA businessSectorJPA = new BusinessSectorJPA(businessSectorID, description);

        BusinessSectorID businessSectorID1 = new BusinessSectorID(businessSectorID);
        Description description1 = new Description(description);

        BusinessSector businessSector = new BusinessSector(businessSectorID1, description1);

        //Act
        BusinessSectorJPA result = BusinessSectorJpaAssembler.toDataModel(businessSector);

        //Assert
        assertEquals(businessSectorJPA.getBusinessSectorID(), result.getBusinessSectorID());
        assertEquals(businessSectorJPA.getBusinessSectorDescription(), result.getBusinessSectorDescription());
    }

    /**
     * Test private constructor for {@link BusinessSectorJpaAssembler}.
     */
    @Test
    void assertThrowsExceptionWhenCalledThePrivateConstructor()
            throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = BusinessSectorJpaAssembler.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }
}
