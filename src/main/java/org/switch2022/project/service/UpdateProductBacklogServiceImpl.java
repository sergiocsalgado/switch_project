package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.model.value_objects.UserStoryStatus;
import org.switch2022.project.service.interfaces.UpdateProductBacklogService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateProductBacklogServiceImpl implements UpdateProductBacklogService {

    private static final String STATUS_PLANNED = "planned";
    private static final String STATUS_RUNNING = "running";
    private final Repository<UserStoryID, UserStory> userStoryRepository;
    private final Repository<ProjectCode, Project> projectRepository;

    public UpdateProductBacklogServiceImpl(
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository
    ) {
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
    }

    public List<UserStory> updateProductBacklog(String projectCode) {

        ProjectCode prjCode;
        try {
            prjCode = new ProjectCode(projectCode);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        if (projectRepository.containsOfIdentity(prjCode)) {

            List<UserStory> userStories = userStoryRepository.findAll();

            List<UserStory> projectUserStories = new ArrayList<>();

            for (UserStory us : userStories) {
                String usProjectCode = us.getProjectCode().getProjectCode();
                if (usProjectCode.equals(projectCode)) {
                    projectUserStories.add(us);
                }
            }
            for (UserStory us : projectUserStories) {
                String usStatus = us.getStatus().getDescription();
                if (STATUS_RUNNING.equals(usStatus)) {
                    us.setStatus(new UserStoryStatus(STATUS_PLANNED));
                    userStoryRepository.save(us);
                }
            }
            return projectUserStories;
        } else {
            throw new IllegalStateException("Project does not exist");
        }
    }
}
