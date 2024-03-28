package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.user_story.UserStory;

import java.util.List;

public interface ListUserStoriesService {
    List<UserStory> getUserStories(String projectCode);
}
