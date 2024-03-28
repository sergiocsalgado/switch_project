package org.switch2022.project.mapper;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for mapping a list of {@link UserStory} objects to a list
 * of {@link UserStoryDTO} objects.
 */
public final class UserStoryMapper {
    private UserStoryMapper() {
    }

    /**
     * Maps a list of {@link UserStory} objects to a list of {@link UserStoryDTO} objects.
     *
     * @param userStories a list of {@link UserStory} objects to be mapped
     * @return a list of {@link UserStoryDTO} objects
     */
    public static List<UserStoryDTO> getUserStoriesDTO(List<UserStory> userStories) {

        List<UserStoryDTO> userStoryDTOS = new ArrayList<>();

        for (UserStory us : userStories) {
            UserStoryID userStoryID = us.getUserStoryID();
            ProjectCode projectCode = us.getProjectCode();
            UserStoryNumber userStoryNumber = us.getUserStoryNumber();
            Name actor = us.getActor();
            Description userStoryText = us.getUserStoryText();
            UserStoryStatus status = us.getStatus();
            Priority priority = us.getPriority();

            UserStoryDTO usDTO = new UserStoryDTO();
            usDTO.setUserStoryID(userStoryID.getUserStoryID());
            usDTO.setProjectCode(projectCode.getProjectCode());
            usDTO.setUserStoryNumber(userStoryNumber.getNumber());
            usDTO.setActor(actor.getValue());
            usDTO.setUserStoryText(userStoryText.getDescription());
            usDTO.setStatus(status.getDescription());
            usDTO.setPriority(priority.getIndex());

            userStoryDTOS.add(usDTO);
        }
        return Collections.unmodifiableList(userStoryDTOS);
    }

    public static UserStoryDTO toDTO(@NonNull UserStory userStory) {
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setUserStoryID(userStory.getUserStoryID().getUserStoryID());
        userStoryDTO.setProjectCode(userStory.getProjectCode().getProjectCode());
        userStoryDTO.setUserStoryNumber(userStory.getUserStoryNumber().getNumber());
        userStoryDTO.setActor(userStory.getActor().getValue());
        userStoryDTO.setUserStoryText(userStory.getUserStoryText().getDescription());
        userStoryDTO.setStatus(userStory.getStatus().getDescription());
        userStoryDTO.setPriority(userStory.getPriority().getIndex());

        return userStoryDTO;
    }
}
