package org.switch2022.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.business_sector.FactoryBusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the AddBusinessSectorServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AddBusinessSectorServiceImpl.class
)
class AddBusinessSectorServiceImplTest {

    @MockBean
    FactoryBusinessSector factoryBusinessSector;

    @MockBean
    @Qualifier("businessSectorJPARepository")
    Repository<BusinessSectorID, BusinessSector> businessSectorRepository;

    @MockBean
    BusinessSector businessSector;

    AddBusinessSectorServiceImpl addBusinessSectorServiceImpl;

    /**
     * This method is executed before each test case to set up the required dependencies
     * and initialize the BusinessSectorService instance.
     *
     * @throws Exception if an error occurs during setup.
     */
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        addBusinessSectorServiceImpl = new AddBusinessSectorServiceImpl(factoryBusinessSector, businessSectorRepository);
    }

    /**
     * Test class for {@link AddBusinessSectorServiceImpl#addBusinessSector(String, String)}
     */
    @Test
    void ensureAddsBusinessSector_true() {
        //Arrange
        String bS_ID = "bs-5";
        String bS_Description = "E-commerce";

        BusinessSectorID businessSectorID = new BusinessSectorID(bS_ID);
        Description businessSectorDescription = new Description(bS_Description);

        when(factoryBusinessSector.addBusinessSector(businessSectorID, businessSectorDescription))
                .thenReturn(businessSector);

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(businessSectorDescription);

        when(businessSectorRepository.containsOfIdentity(businessSectorID)).thenReturn(false);
        when(businessSectorRepository.save(businessSector)).thenReturn(businessSector);

        BusinessSector expected = businessSector;

        //Act
        BusinessSector result = addBusinessSectorServiceImpl.addBusinessSector(bS_ID, bS_Description);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddsBusinessSector_true_2() {
        //Arrange
        String bS_ID = "bs-5";
        String bS_Description = "E-commerce";

        BusinessSectorID businessSectorID = new BusinessSectorID(bS_ID);
        Description businessSectorDescription = new Description(bS_Description);

        BusinessSector bSDouble = mock(BusinessSector.class);
        when(bSDouble.getBusinessSectorID()).thenReturn(new BusinessSectorID("bs-2"));
        when(bSDouble.getBusinessSectorDescription()).thenReturn(new Description("description"));

        when(factoryBusinessSector.addBusinessSector(businessSectorID, businessSectorDescription))
                .thenReturn(businessSector);
        when(businessSectorRepository.containsOfIdentity(businessSectorID)).thenReturn(false);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);

        List<BusinessSector> businessSectorList = List.of(bSDouble);
        when(businessSectorRepository.findAll()).thenReturn(businessSectorList);

        when(businessSector.getBusinessSectorDescription()).thenReturn(businessSectorDescription);
        when(businessSectorRepository.save(businessSector)).thenReturn(businessSector);

        BusinessSector expected = businessSector;

        //Act
        BusinessSector result = addBusinessSectorServiceImpl.addBusinessSector(bS_ID, bS_Description);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link AddBusinessSectorServiceImpl#addBusinessSector(String, String)}
     */
    @Test
    void ensureDoesNotAddsBusinessSectorBecauseIdAlreadyExists_false() {
        //Arrange
        String bS_ID = "bs-5";
        String bS_Description = "E-commerce";

        BusinessSectorID businessSectorID = new BusinessSectorID(bS_ID);
        Description businessSectorDescription = new Description(bS_Description);

        when(factoryBusinessSector.addBusinessSector(businessSectorID, businessSectorDescription))
                .thenReturn(businessSector);

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(businessSectorDescription);

        when(businessSectorRepository.containsOfIdentity(businessSectorID)).thenReturn(true);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            addBusinessSectorServiceImpl.addBusinessSector(bS_ID, bS_Description);
        });

        //Assert
        assertEquals("Business Sector already exists", exception.getMessage());
    }

    @Test
    void ensureDoesNotAddsBusinessSectorBecauseDescriptionAlreadyExists_false() {
        //Arrange
        String bS_ID = "bs-5";
        String bS_ID2 = "bs-6";
        String bS_Description = "E-commerce";

        BusinessSectorID businessSectorID1 = new BusinessSectorID(bS_ID);
        BusinessSectorID businessSectorID2 = new BusinessSectorID(bS_ID2);
        Description businessSectorDescription = new Description(bS_Description);

        BusinessSector businessSector1 = mock(BusinessSector.class);

        when(factoryBusinessSector.addBusinessSector(businessSectorID2, businessSectorDescription))
                .thenReturn(businessSector1);

        when(businessSector1.getBusinessSectorID()).thenReturn(businessSectorID2);
        when(businessSectorRepository.containsOfIdentity(businessSectorID2)).thenReturn(false);
        when(businessSector1.getBusinessSectorDescription()).thenReturn(businessSectorDescription);

        when(businessSectorRepository.findAll()).thenReturn(List.of(businessSector));

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID1);
        when(businessSector.getBusinessSectorDescription()).thenReturn(businessSectorDescription);


        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            addBusinessSectorServiceImpl.addBusinessSector(bS_ID2, bS_Description);
        });

        //Assert
        assertEquals("Business Sector already exists", exception.getMessage());
    }

    /**
     * Test class for {@link AddBusinessSectorServiceImpl#addBusinessSector(String, String)}
     */
    @Test
    void ensureBusinessSectorIdAdded_False() {
        //Arrange
        String bS_ID = null;
        String bS_Description = "E-commerce";

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            addBusinessSectorServiceImpl.addBusinessSector(bS_ID, bS_Description);
        });

        //Assert
        assertEquals("Business Sector ID cannot be null", exception.getMessage());
    }
}