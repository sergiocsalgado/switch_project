package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.SprintStatus;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.service.interfaces.AddUserStoryToSprintBacklogService;

import java.util.Optional;

/**
 * Service class for adding a user story from product backlog to sprint backlog.
 */
@Service
public class AddUserStoryToSprintBacklogServiceImpl implements AddUserStoryToSprintBacklogService {
    private static final String SPRINT_PLANNED_STATUS = "planned";
    private final Repository<SprintID, Sprint> sprintRepository;

    public AddUserStoryToSprintBacklogServiceImpl(
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    /**
     * adds a user Story to SprintBacklog if the sprint exists, and it didn't happen yet.
     *
     * @param userStoryID - the identifier of a user story
     * @param sprintID-   the identifier of a sprint
     * @return a user story added to the sprint backlog
     */
    public boolean addUserStoryToSprintBacklog(UserStoryID userStoryID, SprintID sprintID, ProjectCode projectCode) {

        Optional<Sprint> sprintOpt = sprintRepository.ofIdentity(sprintID);
        if (sprintOpt.isPresent() && isPlanned(sprintID, projectCode)) {
            Sprint sprint = sprintOpt.get();

            if (sprint.addUserStoryToSprintBacklog(userStoryID)) {
                sprintRepository.save(sprint);
                return true;
            }
        }

        return false;
    }

    /**
     * verifies if the sprint from the project is planned.
     *
     * @param sprintID-    the identifier of a sprint
     * @param projectCode- the identifier of a Project
     * @return true if the sprint is planned.
     */
    private boolean isPlanned(SprintID sprintID, ProjectCode projectCode) {
        SprintStatus statusPlanned = new SprintStatus(SPRINT_PLANNED_STATUS);
        return sprintRepository.findAll().stream()
                .anyMatch(sprint -> sprint.getSprintID().equals(sprintID)
                        && sprint.getProjectCode().equals(projectCode)
                        && sprint.getSprintStatus().equals(statusPlanned));
    }
}
