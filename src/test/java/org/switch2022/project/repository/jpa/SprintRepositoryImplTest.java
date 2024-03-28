package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.SprintJPA;
import org.switch2022.project.datamodel.jpa.SprintUserStoryJpa;
import org.switch2022.project.datamodel.jpa.assemblers.SprintJpaAssembler;
import org.switch2022.project.datamodel.jpa.assemblers.SprintUserStoryJpaAssembler;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.repository.jpa.interfaces.SprintJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SprintRepositoryImpl.class)
class SprintRepositoryImplTest {

    @MockBean
    SprintJpaRepository sprintJpaRepository;

    @MockBean
    Sprint sprint;

    @MockBean
    SprintID sprintID;

    @MockBean
    SprintJPA sprintJPA;

    @MockBean
    Optional<SprintJPA> optionalSprintJPA;

    @Autowired
    SprintRepositoryImpl sprintRepositoryImpl;

    @MockBean
    SprintUserStoryJpa sprintUserStoryJpa;

    @MockBean
    UserStoryID userStoryID;

    @Test
    void save() {
        String sprintID_input = "SP1";
        Sprint expected = sprint;
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintID()).thenReturn(sprintID_input);

        Optional<SprintJPA> optionalSprint = mock(Optional.class);
        when(sprintJpaRepository.findBySprintID(sprintID_input)).thenReturn(optionalSprint);
        when(optionalSprint.isPresent()).thenReturn(false);

        try (MockedStatic<SprintJpaAssembler> assemblerDouble =
                     Mockito.mockStatic(SprintJpaAssembler.class)) {

            assemblerDouble.when(() ->
                    SprintJpaAssembler.toDataModel(sprint)).thenReturn(sprintJPA);

            when(sprintJpaRepository.save(sprintJPA)).thenReturn(sprintJPA);

            assemblerDouble.when(() ->
                    SprintJpaAssembler.toDomain(sprintJPA)).thenReturn(sprint);

            Sprint result = sprintRepositoryImpl.save(sprint);

            assertEquals(expected, result);
        }
    }

    @Test
    void findAll() {
        List<SprintJPA> savedSprints = Collections.singletonList(sprintJPA);
        when(sprintJpaRepository.findAll()).thenReturn(savedSprints);

        List<Sprint> expected = Collections.singletonList(sprint);

        try (MockedStatic<SprintJpaAssembler> assemblerDouble =
                     Mockito.mockStatic(SprintJpaAssembler.class)) {

            assemblerDouble.when(() ->
                    SprintJpaAssembler.toDomain(sprintJPA)).thenReturn(sprint);

            List<Sprint> result = sprintRepositoryImpl.findAll();

            assertEquals(expected, result);
        }
    }

    @Test
    void ensureGetOptionalSprint() {
        //Arrange
        String idSprint = "sprint1";
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintID()).thenReturn(idSprint);
        when(sprintJpaRepository.findBySprintID(idSprint)).thenReturn(optionalSprintJPA);

        when(optionalSprintJPA.isPresent()).thenReturn(true);

        when(optionalSprintJPA.get()).thenReturn(sprintJPA);


        Optional<Sprint> expected = Optional.of(sprint);

        try (MockedStatic<SprintJpaAssembler> assemblerDouble = Mockito.mockStatic(SprintJpaAssembler.class)) {
            assemblerDouble.when(() -> SprintJpaAssembler.toDomain(sprintJPA)).thenReturn(sprint);

            List<SprintUserStoryJpa> sprintUserStoryJpas = Collections.singletonList(sprintUserStoryJpa);
            when(sprintJPA.getSprintUserStories()).thenReturn(sprintUserStoryJpas);

            try (MockedStatic<SprintUserStoryJpaAssembler> assemblerDouble2 = Mockito.mockStatic(SprintUserStoryJpaAssembler.class)) {
                assemblerDouble2.when(() -> SprintUserStoryJpaAssembler.toDomain(sprintUserStoryJpa)).thenReturn(userStoryID);
                //Act
                Optional<Sprint> result = sprintRepositoryImpl.ofIdentity(sprintID);

                //Assert
                assertEquals(expected, result);
            }
        }
    }

    @Test
    void ofIdentity_OptionalEmpty() {
        when(sprintID.getSprintID()).thenReturn("1");

        when(sprintJpaRepository.findBySprintID("1")).thenReturn(optionalSprintJPA);

        when(optionalSprintJPA.isPresent()).thenReturn(false);

        Optional<Sprint> expected = Optional.empty();

        Optional<Sprint> result = sprintRepositoryImpl.ofIdentity(sprintID);

        assertEquals(expected, result);

    }

    @Test
    void containsOfIdentity() {
        when(sprintID.getSprintID()).thenReturn("1");

        when(sprintJpaRepository.existsBySprintID("1")).thenReturn(true);

        boolean expected = true;
        boolean result = sprintRepositoryImpl.containsOfIdentity(sprintID);

        assertEquals(expected, result);
    }

    @Test
    void ensureUpdateSprint() {
        //Arrange
        String idSprint = "sprint1";
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintID()).thenReturn(idSprint);
        when(sprintJpaRepository.findBySprintID(idSprint)).thenReturn(optionalSprintJPA);

        when(optionalSprintJPA.isPresent()).thenReturn(true);

        when(optionalSprintJPA.get()).thenReturn(sprintJPA);


        Sprint expected = sprint;

        try (MockedStatic<SprintJpaAssembler> assemblerDouble = Mockito.mockStatic(SprintJpaAssembler.class)) {
            assemblerDouble.when(() -> SprintJpaAssembler.toDataModel(sprint)).thenReturn(sprintJPA);

            List<SprintUserStoryJpa> sprintUserStoryJpas = Collections.singletonList(sprintUserStoryJpa);
            when(sprintJPA.getSprintUserStories()).thenReturn(sprintUserStoryJpas);

            when(sprintJpaRepository.save(sprintJPA)).thenReturn(sprintJPA);

            assemblerDouble.when(() -> SprintJpaAssembler.toDomain(sprintJPA)).thenReturn(sprint);
            //Act
            Sprint result = sprintRepositoryImpl.save(sprint);

            //Assert
            assertEquals(expected, result);
        }
    }
}