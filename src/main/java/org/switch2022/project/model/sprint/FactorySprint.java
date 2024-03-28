package org.switch2022.project.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public interface FactorySprint {
    Sprint createSprint(ProjectCode projectCode, SprintID sprintID, SprintNumber sprintNumber,
                        Period period, SprintBacklog sprintBacklog, SprintStatus sprintStatus);
}
