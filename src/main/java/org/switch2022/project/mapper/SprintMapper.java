package org.switch2022.project.mapper;


import org.springframework.lang.NonNull;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.SprintNumber;
import org.switch2022.project.model.value_objects.SprintStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SprintMapper {
    private SprintMapper() {
    }

    /**
     * Maps a list of {@link Sprint} objects to a list of {@link SprintDTO} objects.
     *
     * @param sprints a list of {@link Sprint} objects to be mapped
     * @return a list of {@link SprintDTO} objects
     */

    public static List<SprintDTO> listSprintDTO(List<Sprint> sprints) {
        List<SprintDTO> sprintsDTO = new ArrayList<>();
        for (Sprint spr : sprints) {
            ProjectCode projectCode = spr.getProjectCode();
            SprintID sprintID = spr.getSprintID();
            SprintNumber sprintNumber = spr.getSprintNumber();
            LocalDate startDate = spr.getPeriod().getStartDate();
            LocalDate endDate = spr.getPeriod().getEndDate();
            SprintStatus sprintStatus = spr.getSprintStatus();

            SprintDTO sprintDTO = new SprintDTO();
            sprintDTO.setProjectCode(projectCode.getProjectCode());
            sprintDTO.setSprintID(sprintID.getSprintID());
            sprintDTO.setSprintNumber(sprintNumber.getNumber());
            sprintDTO.setStartDate(String.valueOf(startDate));
            sprintDTO.setEndDate(String.valueOf(endDate));
            sprintDTO.setSprintStatus(sprintStatus.getDescription());

            sprintsDTO.add(sprintDTO);
        }
        return Collections.unmodifiableList(sprintsDTO);
    }

    public static SprintDTO toDTO(@NonNull Sprint sprint) {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode(sprint.getProjectCode().getProjectCode());
        sprintDTO.setSprintID(sprint.getSprintID().getSprintID());
        sprintDTO.setSprintNumber(sprint.getSprintNumber().getNumber());
        sprintDTO.setStartDate(sprint.getPeriod().getStartDate().toString());
        sprintDTO.setEndDate(sprint.getPeriod().getEndDate().toString());
        sprintDTO.setSprintStatus(sprint.getSprintStatus().getDescription());

        return sprintDTO;
    }

    public static SprintDTO toDTOUpdatedStatus(@NonNull Sprint sprint) {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintStatus(sprint.getSprintStatus().getDescription());
        return sprintDTO;
    }
}
