package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.mapper.BusinessSectorMapper;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListBusinessSectorsServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListBusinessSectorsServiceImpl.class)
class ListBusinessSectorsServiceImplTest {

    @MockBean
    BusinessSector businessSector;
    @MockBean
    @Qualifier("businessSectorJPARepository")
    Repository<BusinessSectorID, BusinessSector> businessSectorRepository;
    @Autowired
    ListBusinessSectorsServiceImpl listBusinessSectorsService;

    @Test
    void ensureGetBusinessSectors() {
        //Arrange
        BusinessSectorDTO businessSectorDTO1 = mock(BusinessSectorDTO.class);
        BusinessSectorDTO businessSectorDTO2 = mock(BusinessSectorDTO.class);

        List<BusinessSector> businessSectors = businessSectorRepository.findAll();

        when(businessSectorRepository.findAll()).thenReturn(businessSectors);

        List<BusinessSectorDTO> expected = List.of(businessSectorDTO1, businessSectorDTO2);

        try (
                MockedStatic<BusinessSectorMapper> mapperDouble = Mockito.mockStatic(BusinessSectorMapper.class)) {
            mapperDouble.when(() -> BusinessSectorMapper.listBusinessSectorDTO(businessSectors)).thenReturn(expected);
            //ACT
            List<BusinessSectorDTO> result = listBusinessSectorsService.getBusinessSectors();

            //ASSERT
            assertEquals(expected, result);
        }
    }
}
