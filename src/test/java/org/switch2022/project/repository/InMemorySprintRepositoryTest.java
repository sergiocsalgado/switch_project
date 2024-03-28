package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.*;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemorySprintRepository.class)
class InMemorySprintRepositoryTest {

    @Autowired
    InMemorySprintRepository inMemorySprintRepository;

    @MockBean
    Sprint sprint;

    @MockBean
    SprintID sprintID;

    @MockBean
    LocalDate date;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        inMemorySprintRepository = new InMemorySprintRepository();
    }
    /**
     * Unit Test for {@link InMemorySprintRepository#save(Sprint)}.
     * Unit Test for {@link InMemorySprintRepository#findAll()}.
     */
    @Test
    void findAll_Success() {
        //ARRANGE
        when(sprint.identity()).thenReturn(sprintID);
        Sprint saveSprint = inMemorySprintRepository.save(sprint);
        List<Sprint> expected = Arrays.asList(saveSprint);
        //ACT
        List<Sprint> result = inMemorySprintRepository.findAll();
        //ASSERT
        assertEquals(expected, result);
    }
    /**
     * Unit Test for {@link InMemorySprintRepository#save(Sprint)}.
     * Unit Test for {@link InMemorySprintRepository#ofIdentity(SprintID)}.
     * Unit Test for {@link InMemorySprintRepository#containsOfIdentity(SprintID)}.
     */
    @Test
    void ofIdentity_SprintOp_Success() {
        //ARRANGE
        when(sprint.identity()).thenReturn(sprintID);
        inMemorySprintRepository.save(sprint);
        Optional<Sprint> expected = Optional.of(sprint);
        //ACT
        Optional<Sprint> result = inMemorySprintRepository.ofIdentity(sprintID);
        //ASSERT
        assertEquals(expected, result);
    }
    @Test
    void ofIdentity_EmptyOptional_Success() {
        //ARRANGE
        Optional<Sprint> expected = Optional.empty();
        SprintID sprintID = mock(SprintID.class);
        //ACT
        Optional<Sprint> result = inMemorySprintRepository.ofIdentity(sprintID);
        //ASSERT
        assertEquals(expected, result);
    }

}
