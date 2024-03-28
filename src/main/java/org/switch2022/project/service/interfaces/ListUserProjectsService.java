package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.ProjectDTO;

import java.util.List;

public interface ListUserProjectsService {
    List<ProjectDTO> listAllUserProjects(String email, String date);
}
