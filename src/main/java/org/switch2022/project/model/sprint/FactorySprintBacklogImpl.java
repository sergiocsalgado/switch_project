package org.switch2022.project.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.SprintBacklogID;

@Component
public class FactorySprintBacklogImpl implements FactorySprintBacklog{
    @Override
    public SprintBacklog create(SprintBacklogID sprintBacklogID) {
        return new SprintBacklog(sprintBacklogID);
    }
}
