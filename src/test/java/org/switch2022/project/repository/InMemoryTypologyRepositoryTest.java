package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.TypologyID;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryTypologyRepository.class)
class InMemoryTypologyRepositoryTest {

    @MockBean
    Typology typology;

    @MockBean
    TypologyID typologyID;

    @InjectMocks
    InMemoryTypologyRepository inMemoryTypologyRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavedTypologyReturned() {
        //ARRANGE
        when(typology.identity()).thenReturn(typologyID);
        Typology saveTypology = inMemoryTypologyRepository.save(typology);
        List<Typology> expected = Arrays.asList(saveTypology);
        //ACT
        List<Typology> typologies = inMemoryTypologyRepository.findAll();
        //ASSERT
        assertEquals(expected, typologies);
    }

    @Test
    void verifyReturnOptionalTypology() {
        //ARRANGE
        when(typology.identity()).thenReturn(typologyID);
        inMemoryTypologyRepository.save(typology);
        Optional<Typology> expected = Optional.of(typology);
        //ACT
        Optional<Typology> result = inMemoryTypologyRepository.ofIdentity(typologyID);
        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void testReturnsEmptyOptional() {
        //ARRANGE
        when(typology.identity()).thenReturn(typologyID);
        inMemoryTypologyRepository.save(typology);
        Optional<Typology> expected = Optional.empty();
        TypologyID id = mock(TypologyID.class);
        //ACT
        Optional<Typology> result = inMemoryTypologyRepository.ofIdentity(id);
        //ASSERT
        assertEquals(expected, result);
    }

}