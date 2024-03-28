package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.sprint.FactorySprint;
import org.switch2022.project.model.sprint.FactorySprintBacklog;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.SprintService;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CreateSprintServiceImpl implements SprintService {
    private static final String SPRINT_BACKLOG_ID_NUMBER = "SB1";
    private static final String SPRINT_INITIAL_STATUS = "planned";

    private final FactorySprint factorySprint;
    private final FactorySprintBacklog factorySprintBacklog;

    private final Repository<SprintID, Sprint> sprintRepository;

    private final Repository<ProjectCode, Project> projectRepository;

    public CreateSprintServiceImpl(
            FactorySprint factorySprint,
            FactorySprintBacklog factorySprintBacklog,
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository) {
        this.factorySprint = factorySprint;
        this.factorySprintBacklog = factorySprintBacklog;
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
    }

    public Sprint createAndSaveSprint(String id, int number, String sprintStartDate,
                                      String sprintEndDate, String projectCode) {
        ProjectCode projectCodeOfSprint;
        Period period;
        SprintID sprintID;
        SprintNumber sprintNumber;
        SprintBacklogID sprintBacklogID;
        SprintStatus sprintStatusEmpty;

        try {
            projectCodeOfSprint = new ProjectCode(projectCode);

            LocalDate startDate = DateManagement.toLocalDate(sprintStartDate);
            LocalDate endDate = DateManagement.toLocalDate(sprintEndDate);
            period = new Period(startDate, endDate);
            sprintID = new SprintID(id);
            sprintNumber = new SprintNumber(number);
            sprintBacklogID = new SprintBacklogID(SPRINT_BACKLOG_ID_NUMBER);
            sprintStatusEmpty = new SprintStatus(SPRINT_INITIAL_STATUS);

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Project> projectOptional = projectRepository.ofIdentity(projectCodeOfSprint);

        if (projectOptional.isPresent()) {

            SprintBacklog sprintBacklog = factorySprintBacklog.create(sprintBacklogID);

            Sprint sprint = factorySprint.createSprint(
                    projectCodeOfSprint,
                    sprintID,
                    sprintNumber,
                    period,
                    sprintBacklog,
                    sprintStatusEmpty
            );

            if (containsID(sprint)) {
                throw new IllegalStateException("Sprint already exists");
            }
            if (!periodVerificationValid(sprint)) {
                throw new IllegalArgumentException("Dates are not valid");
            }
            return sprintRepository.save(sprint);
        } else {
            throw new IllegalStateException("Project doesn't exist");
        }
    }

    private boolean containsID(Sprint sprint) {
        return sprintRepository.containsOfIdentity(sprint.getSprintID()) ||
                sameSprintNumberInProject(sprint.getProjectCode(),sprint.getSprintNumber());
    }

    private boolean sameSprintNumberInProject(ProjectCode projectCode, SprintNumber sprintNumber) {
        List<Sprint> sprints = sprintRepository.findAll();

        for (Sprint sprint : sprints) {
            if (sprint.getProjectCode().equals(projectCode) && sprint.getSprintNumber().equals(sprintNumber)) {
                return true;
            }
        }
        return false;
    }

    protected Optional<Sprint> getSprintById(SprintID sprintID) {
        return sprintRepository.ofIdentity(sprintID);
    }

    private boolean periodVerificationValid(Sprint sprint) {
        for (Sprint sprintsInProject : sprintRepository.findAll()) {
            if (sprintsInProject.getProjectCode().equals(sprint.getProjectCode())) {
                LocalDate startDateSprint = sprint.getPeriod().getStartDate();
                if (startDateSprint.isBefore(sprintsInProject.getPeriod().getEndDate())) {
                    return false;
                }
            }
        }
        return true;
    }
}
