package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.SprintStatus;
import org.switch2022.project.service.interfaces.SetSprintStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class SetSprintStatusServiceImpl implements SetSprintStatusService {
    private static final String PLANNED_STATUS = "planned";
    private static final String OPEN_STATUS = "open";
    private static final String CLOSED_STATUS = "closed";
    private final Repository<SprintID, Sprint> sprintRepository;

    public SetSprintStatusServiceImpl(
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint setSprintStatus(String sprintIDInput) {
        SprintID sprintID;

        try {
            sprintID = new SprintID(sprintIDInput);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Sprint> optionalSprint = sprintRepository.ofIdentity(sprintID);

        if (optionalSprint.isPresent()) {
            Sprint sprint = optionalSprint.get();
            if (PLANNED_STATUS.equals(sprint.getSprintStatus().getDescription())
                    && !verifyIfExistOpenStatusInProject(sprint.getProjectCode().getProjectCode())) {

                sprint.setSprintStatus(new SprintStatus(OPEN_STATUS));
                sprintRepository.save(sprint);

            } else if (OPEN_STATUS.equals(sprint.getSprintStatus().getDescription())) {
                sprint.setSprintStatus(new SprintStatus(CLOSED_STATUS));
                sprintRepository.save(sprint);
            } else {
                throw new IllegalArgumentException("Status not changed.");
            }
            return sprint;
        } else {
            throw new IllegalStateException("Sprint does not exist.");
        }
    }

    private boolean verifyIfExistOpenStatusInProject(String projectCode) {
        List<Sprint> sprints = sprintRepository.findAll();
        for (Sprint sprint : sprints) {
            if (sprint.getProjectCode().getProjectCode().equals(projectCode)
                    && sprint.getSprintStatus().getDescription().equals(OPEN_STATUS)) {
                throw new IllegalArgumentException("Already exist a open Sprint.");
            }
        }
        return false;
    }
}
