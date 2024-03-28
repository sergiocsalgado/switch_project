package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.Effort;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.service.interfaces.EstimateEffortService;

import java.util.Optional;

@Service
public class EstimateEffortServiceImpl implements EstimateEffortService {
    private final Repository<UserStoryID, UserStory> userStoryRepository;

    public EstimateEffortServiceImpl(
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    /**
     * Sets the effort for a user story based on the provided EstimateEffortRequestDTO.
     *
     * @param userStoryID The ID to set for the user story.
     * @param storyPoints The number of story points to set for the effort.
     * @return true if the effort is set successfully, false otherwise.
     */
    public boolean setEffort(String userStoryID, int storyPoints) {
        boolean flag = false;
        UserStoryID userStoryID1 = createUserStoryId(userStoryID);

        // Find the user story in the repository based on the user story ID
        Optional<UserStory> userStoryOptional = userStoryRepository.ofIdentity(userStoryID1);

        if (userStoryOptional.isPresent()) {
            UserStory userStory = userStoryOptional.get();
            Effort effort = createEffort(storyPoints);

            // Set the effort for the user story
            userStory.setEffort(effort);
            userStoryRepository.save(userStory);
            flag = true;
        }

        return flag;
    }

    /**
     * Creates a new Effort object with the given number of story points.
     *
     * @param storyPoints - The number of story points to set for the effort.
     * @return A new Effort object with the given number of story points.
     */
    private static Effort createEffort(int storyPoints) {
        return new Effort(storyPoints);
    }

    /**
     * Creates a new UserStoryID object with the given ID.
     *
     * @param userStoryID - The ID to set for the user story.
     * @return A new UserStoryID object with the given ID.
     */
    private static UserStoryID createUserStoryId(String userStoryID) {
        return new UserStoryID(userStoryID);
    }
}
