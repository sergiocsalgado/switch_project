package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.mapper.TypologyMapper;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.TypologyID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListTypologiesServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListTypologiesServiceImpl.class)
class ListTypologiesServiceImplTest {

    @MockBean
    Typology typology;

    @MockBean
    @Qualifier("typologyJPARepository")
    Repository<TypologyID, Typology> typologyRepository;

    @Autowired
    ListTypologiesServiceImpl listTypologiesService;

    @Test
    void ensureGetTypologies() {
        //Arrange
        TypologyDTO typologyDTO1 = mock(TypologyDTO.class);
        TypologyDTO typologyDTO2 = mock(TypologyDTO.class);

        List<Typology> typologies = typologyRepository.findAll();

        when(typologyRepository.findAll()).thenReturn(typologies);

        List<TypologyDTO> expected = List.of(typologyDTO1, typologyDTO2);

        try (
                MockedStatic<TypologyMapper> mapperDouble = Mockito.mockStatic(TypologyMapper.class)) {
            mapperDouble.when(() -> TypologyMapper.listTypologyDTO(typologies)).thenReturn(expected);
            //ACT
            List<TypologyDTO> result = listTypologiesService.getTypologies();

            //ASSERT
            assertEquals(expected, result);
        }
    }
}
