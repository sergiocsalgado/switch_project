package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.ResourcesInProjectDTO;

import java.util.List;

public interface ListResourcesService {
    List<ResourcesInProjectDTO> getResourcesInProjectDTO(String projectCode);
}
