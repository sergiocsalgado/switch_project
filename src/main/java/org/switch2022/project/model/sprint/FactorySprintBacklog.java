package org.switch2022.project.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.SprintBacklogID;

@Component
public interface FactorySprintBacklog {
    SprintBacklog create( SprintBacklogID sprintBacklogID);
}
