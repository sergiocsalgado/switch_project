package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents a JPA entity for a user story in sprint backlog.
 * This class maps to the "sprintUserStory" table in the database.
 */
@NoArgsConstructor
@Entity
@Table(name = "sprint_user_stories")
public class SprintUserStoryJpa {
    @Id
    private String userStoryID;

    @ManyToOne
    @JoinColumn(name = "sprintJPA")
    private SprintJPA sprintJPA;

    public SprintUserStoryJpa(String userStoryID, SprintJPA sprint) {
        this.userStoryID = userStoryID;
        this.sprintJPA = sprint;
    }

    /**
     * Retrieves the ID of the sprint ID.
     *
     * @return The ID of the sprint ID.
     */
    public SprintJPA getSprintID() {
        return sprintJPA;
    }

    /**
     * Retrieves the ID of the user story ID.
     *
     * @return The ID of the user story ID.
     */
    public String getUserStoryID() {
        return userStoryID;
    }

    /**
     * Sets the SprintJPA.
     *
     * @param sprintJPA the sprint that the user story is part of.
     */
    public void setSprint(SprintJPA sprintJPA) {
        this.sprintJPA = sprintJPA;
    }
}
