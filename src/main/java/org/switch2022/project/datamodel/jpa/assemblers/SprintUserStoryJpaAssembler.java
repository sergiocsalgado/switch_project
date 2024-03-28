package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.SprintJPA;
import org.switch2022.project.datamodel.jpa.SprintUserStoryJpa;
import org.switch2022.project.model.value_objects.UserStoryID;

/**
 * The SprintUserStoryJpaAssembler class is responsible for converting objects between the SprintUserStory and
 * SprintUserStoryJpa classes.
 */
public final class SprintUserStoryJpaAssembler {
    private SprintUserStoryJpaAssembler(){}

    /**
     * Creates a SprintUserStoryJPA object from the given user story ID and SprintJPA object.
     *
     * @param userStoryID The user story ID.
     * @param sprintJPA The SprintJPA object.
     * @return The corresponding SprintUserStoryJPa.
     */
    public static SprintUserStoryJpa toDataModel(String userStoryID, SprintJPA sprintJPA){
        return new SprintUserStoryJpa(userStoryID, sprintJPA);
    }

    public static UserStoryID toDomain(SprintUserStoryJpa storyJpa) {
        return new UserStoryID(storyJpa.getUserStoryID());
    }
}
