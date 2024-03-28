package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.user_story.UserStory;

public interface CreateUserStoryService {
    UserStory createUserStory(String projectCode,
                              String userStoryID,
                              String userStoryNumber,
                              String actor,
                              String userStoryText,
                              int priority);
}
