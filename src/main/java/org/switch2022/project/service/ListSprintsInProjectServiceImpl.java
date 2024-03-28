package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTO;
import org.switch2022.project.mapper.SprintMapper;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.service.interfaces.ListSprintsInProjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListSprintsInProjectServiceImpl implements ListSprintsInProjectService {
    private final Repository<SprintID, Sprint> sprintRepository;

    public ListSprintsInProjectServiceImpl(
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public List<SprintDTO> getSprintsInProject(String projectCode) {
        return SprintMapper.listSprintDTO(findAllSprintsInProject(projectCode));
    }

    private List<Sprint> findAllSprintsInProject(String projectCode) {
        List<Sprint> allSprints = sprintRepository.findAll();
        List<Sprint> sprintsInProject = new ArrayList<>();

        for (Sprint sprint : allSprints) {
            if (sprint.getProjectCode().getProjectCode().equals(projectCode)) {
                sprintsInProject.add(sprint);
            }
        }

        return sprintsInProject;
    }
}
