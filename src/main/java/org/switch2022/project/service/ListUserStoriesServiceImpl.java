package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.service.interfaces.ListUserStoriesService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListUserStoriesServiceImpl implements ListUserStoriesService {
    private static final String STATUS_PLANNED = "planned";
    private final Repository<UserStoryID, UserStory> userStoryRepository;
    private final Repository<ProjectCode, Project> projectRepository;

    public ListUserStoriesServiceImpl(
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository) {
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
    }

    public List<UserStory> getUserStories(String projectCode) {

        ProjectCode projectID = new ProjectCode(projectCode);

        if (projectRepository.containsOfIdentity(projectID)) {
            List<UserStory> userStories = userStoryRepository.findAll();

            return userStories.stream()
                    .filter(userStory -> userStory.getProjectCode().equals(projectID)
                            && STATUS_PLANNED.equals(userStory.getStatus().getDescription()))
                    .sorted(Comparator.comparing(userStory -> userStory.getPriority().getIndex()))
                    .collect(Collectors.toUnmodifiableList());
        } else {
            return Collections.emptyList();
        }
    }
}
