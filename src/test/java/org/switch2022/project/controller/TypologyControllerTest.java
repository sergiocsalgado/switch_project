package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;
import org.switch2022.project.service.interfaces.CreateTypologyService;
import org.switch2022.project.service.interfaces.ListTypologiesService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the TypologyController class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TypologyController.class)
class TypologyControllerTest {

    @MockBean
    Typology typology;

    @MockBean
    TypologyID typologyID;

    @MockBean
    Description typologyDescription;

    @MockBean
    CreateTypologyService createTypologyService;
    @MockBean
    ListTypologiesService listTypologiesService;

    @Autowired
    private TypologyController typologyController;


    /**
     * Unit Test for {@link TypologyController#createTypology(TypologyDTO)}.
     */
    @Test
    void ensureTypologyIsCreated_Success() {
        //Arrange
        String typologyID_input = "TYP01";
        String typologyDescription_input = "Test_Typology";

        when(typology.getTypologyID()).thenReturn(typologyID);
        when(typology.getDescription()).thenReturn(typologyDescription);

        when(typologyID.getIdOfTypology()).thenReturn(typologyID_input);
        when(typologyDescription.getDescription()).thenReturn(typologyDescription_input);

        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setTypologyID(typologyID_input);
        typologyDTO.setTypologyDescription(typologyDescription_input);

        when(createTypologyService.createTypology(typologyID_input, typologyDescription_input))
                .thenReturn(typology);

        ResponseEntity<Object> expected = new ResponseEntity<>(typologyDTO, HttpStatus.CREATED);
        //Act
        ResponseEntity<Object> result = typologyController.createTypology(typologyDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureTypologyIsNotCreated_BadRequest_InvalidValidParameters() {
        // Arrange
        TypologyDTO invalidTypologyDTO = new TypologyDTO();
        invalidTypologyDTO.setTypologyID("typology");
        invalidTypologyDTO.setTypologyDescription("description");

        IllegalArgumentException exception = new IllegalArgumentException("Invalid input parameters for creating typology.");

        when(createTypologyService.createTypology(
                invalidTypologyDTO.getTypologyID(),
                invalidTypologyDTO.getTypologyDescription()
        )).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = typologyController.createTypology(invalidTypologyDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    /**
     * Unit Test for {@link BusinessSectorController#getBusinessSectors()}.
     */
    @Test
    void ensureGetsTypologies_Success() {
        //Arrange
        TypologyDTO typologyDTO1 = mock(TypologyDTO.class);
        TypologyDTO typologyDTO2 = mock(TypologyDTO.class);

        List<TypologyDTO> typologyDTOS = List.of(typologyDTO1, typologyDTO2);

        when(listTypologiesService.getTypologies()).thenReturn(typologyDTOS);

        ResponseEntity<Object> expected = new ResponseEntity<>(typologyDTOS, HttpStatus.OK);

        //Act
        ResponseEntity<Object> result = typologyController.getTypologies();

        //Assert
        assertEquals(expected, result);
    }
}
