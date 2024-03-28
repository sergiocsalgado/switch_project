package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorMapperTest {

    /**
     * Test class for {@link BusinessSectorMapper private constructor}.
     */

    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = BusinessSectorMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link BusinessSectorMapper#listBusinessSectorDTO(List)}.
     */
    @Test
    void ensureListBusinessSectorDTO_success() {
        //Arrange
        String businessSectorID1 = "bs-1";
        String description1 = "E-commerce";

        String businessSectorID2 = "bs-2";
        String description2 = "Finance";

        BusinessSectorID businessSectorID_1 = mock(BusinessSectorID.class);
        BusinessSectorID businessSectorID_2 = mock(BusinessSectorID.class);
        when(businessSectorID_1.getId()).thenReturn(businessSectorID1);
        when(businessSectorID_2.getId()).thenReturn(businessSectorID2);

        Description description_1 = mock(Description.class);
        Description description_2 = mock(Description.class);
        when(description_1.getDescription()).thenReturn(description1);
        when(description_2.getDescription()).thenReturn(description2);

        BusinessSector businessSector1 = mock(BusinessSector.class);
        when(businessSector1.getBusinessSectorID()).thenReturn(businessSectorID_1);
        when(businessSector1.getBusinessSectorDescription()).thenReturn(description_1);

        BusinessSector businessSector2 = mock(BusinessSector.class);
        when(businessSector2.getBusinessSectorID()).thenReturn(businessSectorID_2);
        when(businessSector2.getBusinessSectorDescription()).thenReturn(description_2);

        List<BusinessSector> businessSectors = new ArrayList<>();
        businessSectors.add(businessSector1);
        businessSectors.add(businessSector2);

        BusinessSectorDTO businessSectorDTO1 = new BusinessSectorDTO();
        businessSectorDTO1.setBusinessSectorID(businessSectorID1);
        businessSectorDTO1.setDescription(description1);

        BusinessSectorDTO businessSectorDTO2 = new BusinessSectorDTO();
        businessSectorDTO2.setBusinessSectorID(businessSectorID2);
        businessSectorDTO2.setDescription(description2);

        List<BusinessSectorDTO> expected = new ArrayList<>();
        expected.add(businessSectorDTO1);
        expected.add(businessSectorDTO2);

        //Act
        List<BusinessSectorDTO> result = BusinessSectorMapper.listBusinessSectorDTO(businessSectors);

        //Assert
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getBusinessSectorID(), result.get(i).getBusinessSectorID());
            assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
        assertEquals(expected, result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test class for {@link BusinessSectorMapper#toDTO(BusinessSector)}.
     */
    @Test
    void ensureToDTO_success() {
        //Arrange
        String businessSectorID0 = "bs-1";
        String description0 = "E-commerce";

        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        Description description = mock(Description.class);
        BusinessSector businessSector =mock(BusinessSector.class);

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSectorID.getId()).thenReturn(businessSectorID0);

        when(businessSector.getBusinessSectorDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn(description0);

        BusinessSectorDTO expected = mock(BusinessSectorDTO.class);
        when(expected.getBusinessSectorID()).thenReturn(businessSectorID0);
        when(expected.getDescription()).thenReturn(description0);

        //Act
        BusinessSectorDTO result = BusinessSectorMapper.toDTO(businessSector);

        //Assert
        assertEquals(expected.getBusinessSectorID(), result.getBusinessSectorID());
        assertEquals(expected.getDescription(), result.getDescription());
    }
}