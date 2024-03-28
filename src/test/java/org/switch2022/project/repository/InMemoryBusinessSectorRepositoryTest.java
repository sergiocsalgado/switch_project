package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryBusinessSectorRepository.class
)
class InMemoryBusinessSectorRepositoryTest {

    @InjectMocks
    InMemoryBusinessSectorRepository inMemoryBusinessSectorRepository;

    @MockBean
    BusinessSector businessSector;

    @MockBean
    BusinessSectorID businessSectorID;

    /**
     * This method is executed before each test case to initialize the BusinessSectorRepository instance.
     *
     * @throws Exception if an error occurs during setup.
     */
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Unit Test for {@link InMemoryBusinessSectorRepository#save(BusinessSector)}.
     * Unit Test for {@link InMemoryBusinessSectorRepository#findAll()} )}.
     */
    @Test
    void ensureBusinessSectorSavedIsReturned() {
        //Arrange
        when(businessSector.identity()).thenReturn(businessSectorID);
        inMemoryBusinessSectorRepository.save(businessSector);
        List<BusinessSector> expected = Arrays.asList(businessSector);
        //Act
        List<BusinessSector> result = inMemoryBusinessSectorRepository.findAll();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link InMemoryBusinessSectorRepository#save(BusinessSector)}.
     * Unit Test for {@link InMemoryBusinessSectorRepository#ofIdentity(BusinessSectorID)}.
     * Unit Test for {@link InMemoryBusinessSectorRepository#containsOfIdentity(BusinessSectorID)}.
     */
    @Test
    void ensureReturnsOptionalOfBusinessSector() {
        //Arrange
        when(businessSector.identity()).thenReturn(businessSectorID);
        inMemoryBusinessSectorRepository.save(businessSector);
        Optional<BusinessSector> expected = Optional.of(businessSector);
        //Act
        Optional<BusinessSector> result = inMemoryBusinessSectorRepository.ofIdentity(businessSectorID);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link InMemoryBusinessSectorRepository#save(BusinessSector)}.
     * Unit Test for {@link InMemoryBusinessSectorRepository#ofIdentity(BusinessSectorID)}.
     * Unit Test for {@link InMemoryBusinessSectorRepository#containsOfIdentity(BusinessSectorID)}.
     */
    @Test
    void ensureReturnsEmptyOptionalOfBusinessSector() {
        //Arrange
        Optional<BusinessSector> expected = Optional.empty();
        //Act
        Optional<BusinessSector> result = inMemoryBusinessSectorRepository.ofIdentity(businessSectorID);
        //Assert
        assertEquals(expected, result);
    }
}