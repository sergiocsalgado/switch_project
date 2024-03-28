package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.service.interfaces.ListSprintBacklog;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class implementing the ListSprintBacklog interface.
 * Retrieves the sprint backlog for a given sprint ID.
 */
@Service
public class ListSprintBacklogImpl implements ListSprintBacklog {
    private final Repository<SprintID, Sprint> sprintRepository;
    private final Repository<UserStoryID, UserStory> userStoryRepository;

    /**
     * Constructs a new ListSprintBacklogImpl instance.
     *
     * @param sprintRepository    the repository for sprints.
     * @param userStoryRepository the repository for user stories.
     */
    public ListSprintBacklogImpl(
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository,
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository) {
        this.sprintRepository = sprintRepository;
        this.userStoryRepository = userStoryRepository;
    }

    /**
     * Retrieves the sprint backlog for the specified sprint ID.
     *
     * @param sprintIDInput the input string representing the sprint ID.
     * @return a list of UserStoryDTO objects representing the sprint backlog.
     * @throws IllegalArgumentException if the sprint ID is invalid.
     * @throws IllegalStateException    if the sprint does not exist.
     */
    public List<UserStoryDTO> getSprintBacklog(String sprintIDInput) {
        SprintID sprintID;

        try {
            sprintID = new SprintID(sprintIDInput);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Sprint> optionalSprint = sprintRepository.ofIdentity(sprintID);

        if (optionalSprint.isPresent()) {
            Sprint sprint = optionalSprint.get();
            List<UserStoryID> storyIDList = sprint.getScrumBoardList();

            List<UserStory> userStories = getUserStories(storyIDList);

            return UserStoryMapper.getUserStoriesDTO(userStories);

        } else {
            throw new IllegalStateException("Sprint does not exists.");
        }
    }

    /**
     * Retrieves a list of user stories based on the given list of story IDs.
     *
     * @param storyIDList the list of user story IDs.
     * @return a list of UserStory objects.
     */
    private List<UserStory> getUserStories(List<UserStoryID> storyIDList) {
        return storyIDList.stream()
                .map(userStoryRepository::ofIdentity)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableList());
    }
}
