package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.SprintDTO;

import java.util.List;

public interface ListSprintsInProjectService {
    List<SprintDTO> getSprintsInProject(String projectCode);
}
