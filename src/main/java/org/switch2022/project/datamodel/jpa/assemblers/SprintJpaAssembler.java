package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.SprintJPA;
import org.switch2022.project.datamodel.jpa.SprintUserStoryJpa;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SprintJpaAssembler {

    private SprintJpaAssembler() {
    }

    public static SprintJPA toDataModel(Sprint sprint) {
        SprintJPA sprintJPA = new SprintJPA(
                sprint.getSprintID().getSprintID(),
                sprint.getProjectCode().getProjectCode(),
                sprint.getSprintNumber().getNumber(),
                sprint.getPeriod().getStartDate().toString(),
                sprint.getPeriod().getEndDate().toString(),
                sprint.getSprintStatus().getDescription()
        );

        List<UserStoryID> userStoryIDS = sprint.getScrumBoardList();

        List<SprintUserStoryJpa> sprintUserStoryJpas = userStoryIDS.stream()
                .map(userStoryID -> new SprintUserStoryJpa(
                        userStoryID.getUserStoryID(),
                        sprintJPA
                ))
                .collect(Collectors.toUnmodifiableList());

        sprintJPA.setSprintUserStories(sprintUserStoryJpas);

        return sprintJPA;
    }

    public static Sprint toDomain(SprintJPA sprintJPA) {

        String startDate = sprintJPA.getStartDate();
        String endDate = sprintJPA.getEndDate();
        Period period = new Period(DateManagement.toLocalDate(startDate),
                DateManagement.toLocalDate(endDate)
        );

        SprintBacklogID sprintBacklogID = new SprintBacklogID("SPBL1");
        SprintBacklog sprintBacklog = new SprintBacklog(sprintBacklogID);

        Sprint sprint = new Sprint(
                new ProjectCode(sprintJPA.getProjectCode()),
                new SprintID(sprintJPA.getSprintID()),
                new SprintNumber(sprintJPA.getSprintNumber()),
                period,
                sprintBacklog,
                new SprintStatus(sprintJPA.getSprintStatus())
        );

        List<SprintUserStoryJpa> userStoryJpas = sprintJPA.getSprintUserStories();
        List<UserStoryID> userStoryIDS = new ArrayList<>();

        for (SprintUserStoryJpa userStoryJpa : userStoryJpas) {
            userStoryIDS.add(SprintUserStoryJpaAssembler.toDomain(userStoryJpa));
        }
        sprint.setSprintBacklog(userStoryIDS);

        return sprint;
    }
}
