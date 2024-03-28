package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.UserStoryJpa;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

/**
 * The UserStoryJpaAssembler class provides static methods to convert UserStory
 * objects to UserStoryJpa objects and vice versa.
 */
public final class UserStoryJpaAssembler {
    private UserStoryJpaAssembler(){}

    /**
     * Converts a UserStory object to a UserStoryJpa object.
     *
     * @param userStory The UserStory object to be converted.
     * @return The converted UserStoryJpa object.
     */
    public static UserStoryJpa toDataModel(UserStory userStory) {
        return new UserStoryJpa(
                userStory.getUserStoryID().getUserStoryID(),
                userStory.getProjectCode().getProjectCode(),
                userStory.getUserStoryNumber().getNumber(),
                userStory.getActor().getValue(),
                userStory.getUserStoryText().getDescription(),
                userStory.getStatus().getDescription(),
                userStory.getPriority().getIndex()
        );
    }

    /**
     * Converts a UserStoryJpa object to a UserStory object.
     *
     * @param userStoryJpa The UserStoryJpa object to be converted.
     * @return The converted UserStory object.
     */
    public static UserStory toDomain(UserStoryJpa userStoryJpa) {
        int priority = userStoryJpa.getPriority();
        return new UserStory(
                new UserStoryID(userStoryJpa.getUserStoryID()),
                new ProjectCode(userStoryJpa.getProjectCode()),
                new UserStoryNumber(userStoryJpa.getUserStoryNumber()),
                new Name(userStoryJpa.getActor()),
                new Description(userStoryJpa.getUserStoryText()),
                new UserStoryStatus(userStoryJpa.getStatus()),
                new Priority(priority));
    }
}
