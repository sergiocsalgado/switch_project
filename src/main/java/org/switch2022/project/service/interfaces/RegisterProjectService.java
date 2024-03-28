package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.model.project.Project;


public interface RegisterProjectService {

    Project registerProject(ProjectDTO projDTO);
}
