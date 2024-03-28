package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.switch2022.project.datamodel.jpa.TypologyJPA;
import org.switch2022.project.datamodel.jpa.assemblers.TypologyJpaAssembler;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;
import org.switch2022.project.repository.jpa.interfaces.TypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyRepositoryImplTest {

    @Test
    void ensureSavesAndReturnsTypology_Success() {
        //Arrange
        TypologyJpaRepository jpaRepo = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl repo = new TypologyRepositoryImpl(jpaRepo);

        TypologyID typologyID = mock(TypologyID.class);
        Description description = mock(Description.class);

        Typology typology = new Typology(typologyID, description);
        TypologyJPA toSaveTypology = TypologyJpaAssembler.toDataModel(typology);

        when(jpaRepo.save(toSaveTypology)).thenReturn(toSaveTypology);

        //Act
        Typology savedTypology = repo.save(typology);

        //Assert
        assertEquals(typology, savedTypology);
        assertNotNull(savedTypology);
    }

    @Test
    void ensureReturnsListWillAllTypologies() {
        //Arrange
        TypologyJpaRepository jpaRepo = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl repo = new TypologyRepositoryImpl(jpaRepo);

        TypologyID typologyID1 = new TypologyID("TP1");
        TypologyID typologyID2 = new TypologyID("TP2");

        Description description1 = new Description("desc1");
        Description description2 = new Description("desc2");

        TypologyJPA typologyJPA1 = new TypologyJPA("TP1", "desc1");
        TypologyJPA typologyJPA2 = new TypologyJPA("TP2", "desc2");

        List<TypologyJPA> jpaTypologies = List.of(typologyJPA1, typologyJPA2);

        when(jpaRepo.findAll()).thenReturn(jpaTypologies);

        Typology typology1 = new Typology(typologyID1, description1);
        Typology typology2 = new Typology(typologyID2, description2);

        List<Typology> expected = List.of(typology1, typology2);

        //Act
        List<Typology> result = repo.findAll();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureReturnsEmptyList() {
        //Arrange
        TypologyJpaRepository jpaRepo = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl repo = new TypologyRepositoryImpl(jpaRepo);

        List<TypologyJPA> expected = new ArrayList<>();

        when(jpaRepo.findAll()).thenReturn(expected);
        //Act
        List<Typology> result = repo.findAll();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ofIdentity_Success() {
        //Arrange
        TypologyJpaRepository jpaRepo = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl repo = new TypologyRepositoryImpl(jpaRepo);

        String tpID = "BS1";
        String tpDesc = "desc1";

        TypologyID typologyID = mock(TypologyID.class);
        when(typologyID.getIdOfTypology()).thenReturn(tpID);
        Description typologyDescription = mock(Description.class);
        when(typologyDescription.getDescription()).thenReturn(tpDesc);

        Typology typology = mock(Typology.class);
        when(typology.getTypologyID()).thenReturn(typologyID);
        when(typology.getDescription()).thenReturn(typologyDescription);

        TypologyJPA typologyJPA = mock(TypologyJPA.class);
        when(typologyJPA.getTypologyID()).thenReturn(tpID);
        when(typologyJPA.getTypologyDescription()).thenReturn(tpDesc);

        when(jpaRepo.findByTypologyID(typologyID.getIdOfTypology())).thenReturn(Optional.of(typologyJPA));

        //Act
        Optional<Typology> optionalTypology = repo.ofIdentity(typologyID);

        try (MockedStatic<TypologyJpaAssembler> mapper = Mockito.mockStatic(TypologyJpaAssembler.class)) {
            mapper.when(() -> TypologyJpaAssembler.toDataModel(optionalTypology.get())).thenReturn(typologyJPA);

            //Assert
            assertEquals(typologyJPA, TypologyJpaAssembler.toDataModel(optionalTypology.get()));
            assertTrue(optionalTypology.isPresent());
            assertNotNull(optionalTypology);
        }
    }

    @Test
    void ensureContainsOfIdentity_Success() {
        //Arrange
        TypologyJpaRepository jpaRepo = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl repo = new TypologyRepositoryImpl(jpaRepo);

        TypologyID typologyID = mock(TypologyID.class);

        when(jpaRepo.existsByTypologyID(typologyID.getIdOfTypology())).thenReturn(true);

        //Act
        boolean identity = repo.containsOfIdentity(typologyID);

        //Assert
        assertTrue(identity);
    }

    @Test
    void ensureContainsOfIdentity_Fail() {
        //Arrange
        TypologyJpaRepository jpaRepo = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl repo = new TypologyRepositoryImpl(jpaRepo);

        TypologyID typologyID = mock(TypologyID.class);

        when(jpaRepo.existsByTypologyID(typologyID.getIdOfTypology())).thenReturn(false);

        //Act
        boolean identity = repo.containsOfIdentity(typologyID);

        //Assert
        assertFalse(identity);
    }

    @Test
    void ensureGetEmptyOptionalOfTypology() {

        TypologyJpaRepository typologyJpaRepositoryDouble = mock(TypologyJpaRepository.class);
        TypologyRepositoryImpl typologyRepository = new TypologyRepositoryImpl(typologyJpaRepositoryDouble);

        TypologyID typologyID = mock(TypologyID.class);


        String typology = "typ1";

        when(typologyID.getIdOfTypology()).thenReturn(typology);

        when(typologyJpaRepositoryDouble.findByTypologyID(typology)).thenReturn(Optional.empty());
        Optional<Typology> expected = Optional.empty();
        Optional<Typology> result = typologyRepository.ofIdentity(typologyID);

        assertEquals(expected, result);
    }
}
