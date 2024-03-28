package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.user_story.UserStory;

public interface UpdateUserStoryStatusService {
    public UserStory updateUserStoryStatus(UserStoryDTO userStoryDTO);
}
