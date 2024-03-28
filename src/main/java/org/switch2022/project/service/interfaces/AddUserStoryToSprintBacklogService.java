package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.UserStoryID;


public interface AddUserStoryToSprintBacklogService {
    boolean addUserStoryToSprintBacklog(UserStoryID userStoryID, SprintID sprintID, ProjectCode projectCode);
}
