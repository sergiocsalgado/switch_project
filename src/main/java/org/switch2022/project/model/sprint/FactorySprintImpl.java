package org.switch2022.project.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public class FactorySprintImpl implements FactorySprint {

    public Sprint createSprint(ProjectCode projectCode, SprintID sprintID, SprintNumber sprintNumber,
                               Period period, SprintBacklog sprintBacklog, SprintStatus sprintStatus) {
        return new Sprint(projectCode, sprintID, sprintNumber, period, sprintBacklog, sprintStatus);
    }


}
