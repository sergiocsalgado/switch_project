package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.service.interfaces.AddBusinessSectorService;
import org.switch2022.project.service.interfaces.ListBusinessSectorsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the BusinessSectorController class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BusinessSectorController.class
)
class BusinessSectorControllerTest {

    @MockBean
    BusinessSector businessSector;

    @MockBean
    BusinessSectorID businessSectorID;

    @MockBean
    Description description;

    @MockBean
    AddBusinessSectorService addBusinessSectorService;
    @MockBean
    ListBusinessSectorsService listBusinessSectorsService;

    @Autowired
    BusinessSectorController businessSectorController;

    /**
     * Unit Test for {@link BusinessSectorController#addBusinessSector(BusinessSectorDTO)}.
     */
    @Test
    void ensureBusinessSectorIsAdded_True() {
        //Arrange
        String bSectorID0 = "BS-1";
        String bSectorDescription0 = "finance";

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(description);

        when(businessSectorID.getId()).thenReturn(bSectorID0);
        when(description.getDescription()).thenReturn(bSectorDescription0);

        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        businessSectorDTO.setBusinessSectorID(bSectorID0);
        businessSectorDTO.setDescription(bSectorDescription0);

        when(addBusinessSectorService.addBusinessSector(bSectorID0, bSectorDescription0))
                .thenReturn(businessSector);

        ResponseEntity<Object> expected = new ResponseEntity<>(businessSectorDTO, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> result = businessSectorController.addBusinessSector(businessSectorDTO);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link BusinessSectorController#addBusinessSector(BusinessSectorDTO)}.
     */
    @Test
    void ensureBusinessSectorIsAdded_False() {
        //Arrange
        String bSectorID0 = null;
        String bSectorDescription0 = "finance";

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(description);

        when(businessSectorID.getId()).thenReturn(bSectorID0);
        when(description.getDescription()).thenReturn(bSectorDescription0);

        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        businessSectorDTO.setBusinessSectorID(bSectorID0);
        businessSectorDTO.setDescription(bSectorDescription0);

        IllegalArgumentException exception = new IllegalArgumentException("Invalid parameters to add a Business Sector");

        when(addBusinessSectorService.addBusinessSector(bSectorID0, bSectorDescription0))
                .thenThrow(exception);

        //Act
        ResponseEntity<Object> result = businessSectorController.addBusinessSector(businessSectorDTO);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }

    /**
     * Unit Test for {@link BusinessSectorController#getBusinessSectors()}.
     */
    @Test
    void ensureGetsBusinessSectors_Success() {
        //Arrange
        BusinessSectorDTO businessSectorDTO1 = mock(BusinessSectorDTO.class);
        BusinessSectorDTO businessSectorDTO2 = mock(BusinessSectorDTO.class);

        List<BusinessSectorDTO> businessSectorDTOS = List.of(businessSectorDTO1, businessSectorDTO2);

        when(listBusinessSectorsService.getBusinessSectors()).thenReturn(businessSectorDTOS);

        ResponseEntity<Object> expected = new ResponseEntity<>(businessSectorDTOS, HttpStatus.OK);

        //Act
        ResponseEntity<Object> result = businessSectorController.getBusinessSectors();

        //Assert
        assertEquals(expected, result);
    }
}