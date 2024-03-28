package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.sprint.Sprint;


public interface SprintService {
    Sprint createAndSaveSprint(String sprintID, int sprintNumber,
                               String startDate, String endDate, String projectCode);
}
