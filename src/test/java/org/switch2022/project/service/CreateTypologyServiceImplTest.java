package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.typology.FactoryTypology;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Javadoc for the test class {@code CreateTypologyServiceImplTest}.
 * This class is responsible for testing the behavior of the methods in the {@code CreateTypologyServiceImpl} class
 * using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateTypologyServiceImpl.class)
class CreateTypologyServiceImplTest {

    @MockBean
    FactoryTypology factoryTypology;
    @MockBean
    Typology typology;
    @MockBean
    TypologyID typologyID;
    @MockBean
    Description typologyDescription;
    @Autowired
    CreateTypologyServiceImpl createTypologyServiceImpl;
    @MockBean
    @Qualifier("typologyJPARepository")
    Repository<TypologyID, Typology> typologyRepository;


    /**
     * Javadoc for the test method {@code testCreateTypology_SuccessfulCreation()}.
     * This test verifies the successful creation of a typology by the {@code createTypology()} method in the
     * {@code CreateTypologyServiceImpl} class.
     * It sets up the necessary mock objects and stubs the required method calls.
     * The test asserts that the returned typology object is equal to the expected typology object.
     */
    @Test
    void ensureTypologyIsCreated() {
        //ARRANGE
        String iDInput = "typologyID";
        String descriptionInput = "description";

        TypologyID typologyID = new TypologyID(iDInput);
        Description typologyDescription = new Description(descriptionInput);

        when(factoryTypology.createTypology(typologyID, typologyDescription)).thenReturn(typology);
        when(typologyRepository.containsOfIdentity(typologyID)).thenReturn(false);
        when(typologyRepository.save(typology)).thenReturn(typology);

        // ACT
        Typology result = createTypologyServiceImpl.createTypology(iDInput, descriptionInput);

        //ASSERT
        assertEquals(typology, result);
    }

    @Test
    void ensureTypologyIsCreated_2() {
        //ARRANGE
        String iDInput = "typologyID";
        String descriptionInput = "description";

        TypologyID typologyIDInput2 = new TypologyID("typologyID2");
        Description descriptionInput2 = new Description("descriptionInput2");

        TypologyID typologyID = new TypologyID(iDInput);
        Description typologyDescription = new Description(descriptionInput);

        Typology typologyDouble = mock(Typology.class);
        when(typologyDouble.getTypologyID()).thenReturn(typologyIDInput2);
        when(typologyDouble.getDescription()).thenReturn(descriptionInput2);

        when(factoryTypology.createTypology(typologyID, typologyDescription)).thenReturn(typology);
        when(typologyRepository.containsOfIdentity(typologyID)).thenReturn(false);
        when(typology.getTypologyID()).thenReturn(typologyID);

        List<Typology> typologyList = List.of(typologyDouble);
        when(typologyRepository.findAll()).thenReturn(typologyList);

        when(typology.getDescription()).thenReturn(typologyDescription);
        when(typologyRepository.save(typology)).thenReturn(typology);

        // ACT
        Typology result = createTypologyServiceImpl.createTypology(iDInput, descriptionInput);

        //ASSERT
        assertEquals(typology, result);
    }

    /**
     * Javadoc for the test method {@code testCreateTypology_CouldNotCreateBecauseTypologyAlreadyExists()}.
     * This test checks the scenario where the {@code createTypology()}
     * method should throw an {@code IllegalArgumentException}
     * because the typology with the given ID already exists in the repository.
     * It sets up the necessary mock objects and stubs the required method calls.
     * The test asserts that the thrown exception message is "Could not create Typology.".
     */
    @Test
    void testCreateTypology_CouldNotCreateBecauseTypologyAlreadyExists() {
        //ARRANGE
        String iDInput = "typologyID";
        String iDInput2 = "typologyID";
        String descriptionInput = "description";

        TypologyID typologyID = new TypologyID(iDInput);
        TypologyID typologyID2 = new TypologyID(iDInput2);
        Description typologyDescription = new Description(descriptionInput);

        Typology typology1 = mock(Typology.class);

        when(factoryTypology.createTypology(typologyID2, typologyDescription)).thenReturn(typology);

        when(typology1.getTypologyID()).thenReturn(typologyID2);
        when(typologyRepository.containsOfIdentity(typologyID2)).thenReturn(false);
        when(typology1.getDescription()).thenReturn(typologyDescription);

        when(typologyRepository.findAll()).thenReturn(List.of(typology));

        when(typology.getTypologyID()).thenReturn(typologyID);
        when(typology.getDescription()).thenReturn(typologyDescription);
        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(iDInput, descriptionInput);
        });

        //ASSERT
        assertEquals("Could not create Typology.", exception.getMessage());
    }

    /**
     * Javadoc for the test method {@code testCreateTypology_CouldNotCreateBecauseIDAlreadyExists()}.
     * This test validates the case where the {@code createTypology()}
     * method should throw an {@code IllegalArgumentException}
     * because a typology with the given ID already exists in the repository.
     * It sets up the necessary mock objects and stubs the required method calls.
     * The test asserts that the thrown exception message is "Could not create Typology.".
     */
    @Test
    void EnsureCreateTypologyFail_BecauseTypologyIDAlreadyExists() {
        //ARRANGE
        String iDInput = "typologyID";
        String descriptionInput = "description";

        TypologyID typologyID = new TypologyID(iDInput);
        Description typologyDescription = new Description(descriptionInput);

        when(factoryTypology.createTypology(typologyID, typologyDescription)).thenReturn(typology);

        when(typology.getTypologyID()).thenReturn(typologyID);
        when(typology.getDescription()).thenReturn(typologyDescription);

        when(typologyRepository.containsOfIdentity(typologyID)).thenReturn(true);
        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(iDInput, descriptionInput);
        });

        //ASSERT
        assertEquals("Could not create Typology.", exception.getMessage());
    }

    /**
     * Javadoc for the test method {@code EnsureCreateTypologyFail_BecauseDescriptionAlreadyExists()}.
     * This test verifies the scenario where the {@code createTypology()}
     * method should throw an {@code IllegalArgumentException}
     * because a typology with the given description already exists in the repository.
     * It sets up the necessary mock objects and stubs the required method calls.
     * The test asserts that the thrown exception message is "Could not create Typology.".
     */

    @Test
    void EnsureCreateTypologyFail_BecauseDescriptionAlreadyExists() {
        //ARRANGE
        String iDInput = "typologyID";
        String descriptionInput = "description";

        TypologyID typologyID = new TypologyID(iDInput);
        Description typologyDescription = new Description(descriptionInput);

        Typology mockTypology = mock(Typology.class);
        when(mockTypology.getTypologyID()).thenReturn(typologyID);
        when(mockTypology.getDescription()).thenReturn(typologyDescription);

        when(factoryTypology.createTypology(typologyID, typologyDescription)).thenReturn(typology);
        when(typologyRepository.containsOfIdentity(typologyID)).thenReturn(false);
        when(typology.getTypologyID()).thenReturn(typologyID);

        List<Typology> typologyList = List.of(mockTypology);
        when(typologyRepository.findAll()).thenReturn(typologyList);

        when(typology.getDescription()).thenReturn(typologyDescription);
        when(typologyRepository.save(mockTypology)).thenReturn(mockTypology);

        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(iDInput, descriptionInput);
        });

        //ASSERT
        assertEquals("Could not create Typology.", exception.getMessage());
    }

    /**
     * Javadoc for the test method {@code ensureCreateTypologyFail_InvalidParameters()}.
     * This test verifies that the {@code createTypology()} method throws an {@code IllegalArgumentException} when
     * invalid input parameters are provided.
     * It sets up the necessary input parameters for the test.
     * The test asserts that the thrown exception message is "Invalid input parameters".
     */
    @Test
    void ensureCreateTypologyFail_TypologyIDIsEmpty() {
        //ARRANGE
        String typologyID1 = "";
        String typologyDescription1 = "Test";

        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(typologyID1, typologyDescription1);
        });

        //ASSERT
        assertEquals("Invalid input parameters", exception.getMessage());
    }

    @Test
    void ensureCreateTypologyFail_TypologyIDIsNull() {
        //ARRANGE
        String typologyID1 = null;
        String typologyDescription1 = "Test";

        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(typologyID1, typologyDescription1);
        });

        //ASSERT
        assertEquals("Invalid input parameters", exception.getMessage());
    }

    @Test
    void ensureCreateTypologyFail_DescriptionIsEmpty() {
        //ARRANGE
        String typologyID1 = "typologyID";
        String typologyDescription1 = "";

        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(typologyID1, typologyDescription1);
        });

        //ASSERT
        assertEquals("Invalid input parameters", exception.getMessage());
    }

    @Test
    void ensureCreateTypologyFail_DescriptionISNull() {
        //ARRANGE
        String typologyID1 = "typologyID";
        String typologyDescription1 = null;

        //ACT
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            createTypologyServiceImpl.createTypology(typologyID1, typologyDescription1);
        });

        //ASSERT
        assertEquals("Invalid input parameters", exception.getMessage());
    }
}
