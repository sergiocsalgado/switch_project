package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.ViewScrumBoardService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class implementing the ViewScrumBoardService interface.
 * Retrieves the scrum board for a given project code.
 */
@Service
public class ViewScrumBoardServiceImpl implements ViewScrumBoardService {
    private static final String SPRINT_OPEN_STATUS = "open";
    private final Repository<SprintID, Sprint> sprintRepository;
    private final Repository<UserStoryID, UserStory> userStoryRepository;
    private final Repository<ProjectCode, Project> projectRepository;

    /**
     * Constructs a new ViewScrumBoardServiceImpl with the specified repositories.
     *
     * @param sprintRepository    the repository for sprints.
     * @param userStoryRepository the repository for user stories.
     * @param projectRepository   the repository for projects.
     */
    public ViewScrumBoardServiceImpl(
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository,
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository) {
        this.sprintRepository = sprintRepository;
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Returns the set of user story statuses applicable for the scrum board.
     *
     * @return a set of user story statuses.
     */
    private static Set<UserStoryStatus> getUserStoriesStatusForScrumBoard() {
        return Set.of(
                new UserStoryStatus("planned"),
                new UserStoryStatus("running"),
                new UserStoryStatus("finished"));
    }

    /**
     * Retrieves the user stories for the scrum board of the specified project.
     *
     * @param projectCode the code of the project.
     * @return a list of user stories representing the scrum board.
     * @throws IllegalArgumentException if the project code is invalid.
     * @throws IllegalStateException    if the project does not exist or there is no sprint going on.
     */
    public List<UserStory> getScrumBoard(String projectCode) {
        ProjectCode projectID;

        try {
            projectID = new ProjectCode(projectCode);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        if (projectExists(projectID)) {
            Optional<Sprint> sprintSelected = getCurrentSprint(projectID);

            if (sprintSelected.isPresent()) {
                Sprint sprint = sprintSelected.get();

                List<UserStory> userStories = userStoryRepository.findAll();

                return userStories.stream()
                        .filter(userStory -> {
                            UserStoryStatus status = new UserStoryStatus(userStory.getStatus().getDescription());
                            UserStoryID userStoryID = userStory.getUserStoryID();

                            return sprint.getScrumBoardList().contains(userStoryID)
                                    && getUserStoriesStatusForScrumBoard().contains(status);
                        })
                        .sorted(Comparator.comparing(userStory -> userStory.getStatus().getDescription()))
                        .collect(Collectors.toUnmodifiableList());
            } else {
                throw new IllegalStateException("There is no sprint going on.");
            }

        } else {
            throw new IllegalStateException("Project does not exists.");
        }
    }

    /**
     * Retrieves the current sprint associated with the specified project code.
     *
     * @param projectCode the code of the project.
     * @return an optional containing the current sprint, or empty if not found.
     */
    private Optional<Sprint> getCurrentSprint(ProjectCode projectCode) {
        SprintStatus openStatus = new SprintStatus(SPRINT_OPEN_STATUS);

        return sprintRepository.findAll().stream()
                .filter(sprint -> sprint.getProjectCode().equals(projectCode)
                        && sprint.getSprintStatus().equals(openStatus))
                .findFirst();
    }

    /**
     * Checks if a project with the specified project code exists.
     *
     * @param projectCode the code of the project.
     * @return true if the project exists, false otherwise.
     */
    private boolean projectExists(ProjectCode projectCode) {
        return projectRepository.containsOfIdentity(projectCode);
    }
}
