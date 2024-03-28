package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.SprintBacklogID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FactorySprintBacklogImplTest {


    @Test
    void create_shouldCreateAValidSprintBacklog() {
        FactorySprintBacklog factory = new FactorySprintBacklogImpl();
        SprintBacklogID sprintBacklogID = mock(SprintBacklogID.class);

        SprintBacklog expected = new SprintBacklog(sprintBacklogID);

        SprintBacklog result = factory.create(sprintBacklogID);

        assertEquals(expected, result);
    }
}