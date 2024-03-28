package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.UserStoryDTO;

import java.util.List;

public interface ListSprintBacklog {
    List<UserStoryDTO> getSprintBacklog(String sprintID);
}
