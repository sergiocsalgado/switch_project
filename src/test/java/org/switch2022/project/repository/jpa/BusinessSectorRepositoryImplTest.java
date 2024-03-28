package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.switch2022.project.datamodel.jpa.BusinessSectorJPA;
import org.switch2022.project.datamodel.jpa.assemblers.BusinessSectorJpaAssembler;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.repository.jpa.interfaces.BusinessSectorJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorRepositoryImplTest {

    @Test
    void ensureSavesAndReturnsBusinessSectorSaved_Success() {
        //Arrange
        BusinessSectorJpaRepository jpaRepository = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl repository = new BusinessSectorRepositoryImpl(jpaRepository);

        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        Description description = mock(Description.class);

        BusinessSector businessSector = new BusinessSector(businessSectorID,description);
        BusinessSectorJPA toSaveBS = BusinessSectorJpaAssembler.toDataModel(businessSector);

        when(jpaRepository.save(toSaveBS)).thenReturn(toSaveBS);

        //Act
        BusinessSector savedBS = repository.save(businessSector);

        //Assert
        assertEquals(businessSector, savedBS);
        assertNotNull(savedBS);
    }

    @Test
    void ensureReturnsListWillAllBusinessSectors() {
        //Arrange
        BusinessSectorJpaRepository jpaRepository = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl repository = new BusinessSectorRepositoryImpl(jpaRepository);

        BusinessSectorID businessSectorID1 = new BusinessSectorID("BS1");
        BusinessSectorID businessSectorID2 = new BusinessSectorID("BS2");

        Description description1 = new Description("desc1");
        Description description2 = new Description("desc2");

        BusinessSectorJPA businessSectorJPA1 = new BusinessSectorJPA("BS1", "desc1");
        BusinessSectorJPA businessSectorJPA2 = new BusinessSectorJPA("BS2", "desc2");

        List<BusinessSectorJPA> jpaBSs = List.of(businessSectorJPA1,businessSectorJPA2);

        when(jpaRepository.findAll()).thenReturn(jpaBSs);

        BusinessSector businessSector1 = new BusinessSector(businessSectorID1,description1);
        BusinessSector businessSector2 = new BusinessSector(businessSectorID2,description2);

        List<BusinessSector> expected = List.of(businessSector1, businessSector2);

        //Act
        List<BusinessSector> result = repository.findAll();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureReturnsEmptyList() {
        //Arrange
        BusinessSectorJpaRepository jpaRepository = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl repository = new BusinessSectorRepositoryImpl(jpaRepository);

        List<BusinessSectorJPA> expected = new ArrayList<>();

        when(jpaRepository.findAll()).thenReturn(expected);
        //Act
        List<BusinessSector> result = repository.findAll();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ofIdentity_Success() {
        //Arrange
        BusinessSectorJpaRepository jpaRepository = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl repository = new BusinessSectorRepositoryImpl(jpaRepository);

        String bsID = "BS1";
        String bsDesc = "desc1";

        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        when(businessSectorID.getId()).thenReturn(bsID);
        Description businessSectorDescription = mock(Description.class);
        when(businessSectorDescription.getDescription()).thenReturn(bsDesc);

        BusinessSector businessSector = mock(BusinessSector.class);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(businessSectorDescription);

        BusinessSectorJPA businessSectorJPA = mock(BusinessSectorJPA.class);
        when(businessSectorJPA.getBusinessSectorID()).thenReturn(bsID);
        when(businessSectorJPA.getBusinessSectorDescription()).thenReturn(bsDesc);

        when(jpaRepository.findByBusinessSectorID(businessSectorID.getId())).thenReturn(Optional.of(businessSectorJPA));

        //Act
        Optional<BusinessSector> optionalBusinessSector = repository.ofIdentity(businessSectorID);

        try (MockedStatic<BusinessSectorJpaAssembler> mapper = Mockito.mockStatic(BusinessSectorJpaAssembler.class)) {
            mapper.when(() -> BusinessSectorJpaAssembler.toDataModel(optionalBusinessSector.get())).thenReturn(businessSectorJPA);

            //Assert
            assertEquals(businessSectorJPA, BusinessSectorJpaAssembler.toDataModel(optionalBusinessSector.get()));
            assertTrue(optionalBusinessSector.isPresent());
            assertNotNull(optionalBusinessSector);
        }
    }

    @Test
    void ensureContainsOfIdentity_Success() {
        //Arrange
        BusinessSectorJpaRepository jpaRepository = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl repository = new BusinessSectorRepositoryImpl(jpaRepository);

        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);

        when(jpaRepository.existsByBusinessSectorID(businessSectorID.getId())).thenReturn(true);

        //Act
        boolean identity = repository.containsOfIdentity(businessSectorID);

        //Assert
        assertTrue(identity);
    }

    @Test
    void ensureContainsOfIdentity_Fail() {
        //Arrange
        BusinessSectorJpaRepository jpaRepository = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl repository = new BusinessSectorRepositoryImpl(jpaRepository);

        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);

        when(jpaRepository.existsByBusinessSectorID(businessSectorID.getId())).thenReturn(false);

        //Act
        boolean identity = repository.containsOfIdentity(businessSectorID);

        //Assert
        assertFalse(identity);
    }
    @Test
    void ensureGetEmptyOptionalOfBusinnessSector(){

        BusinessSectorJpaRepository businessSectorJpaRepositoryDouble = mock(BusinessSectorJpaRepository.class);
        BusinessSectorRepositoryImpl businessSectorRepository = new BusinessSectorRepositoryImpl(businessSectorJpaRepositoryDouble);

        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);

        String businessSector = "bs1";

        when(businessSectorID.getId()).thenReturn(businessSector);

        when(businessSectorJpaRepositoryDouble.findByBusinessSectorID(businessSector)).thenReturn(Optional.empty());
        Optional<BusinessSector> expected = Optional.empty();
        Optional<BusinessSector> result = businessSectorRepository.ofIdentity(businessSectorID);

        assertEquals(expected,result);
    }
}
