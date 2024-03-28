package org.switch2022.project.model.project;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public interface FactoryProject {
    Project createProject(ProjectCode projectCode, Name projectName, Description projectDescription,
                          CustomerID customerID, BusinessSectorID businessSectorID, TypologyID typologyID,
                          ProjectStatus projectStatus, PositiveNumber sprintDuration,
                          PositiveNumber numberOfPlannedSprints, Cost budget, Period period);
}
