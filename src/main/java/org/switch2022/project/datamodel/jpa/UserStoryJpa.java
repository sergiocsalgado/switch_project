package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents a user story in a project.
 * This class is used for storing user story information in a database.
 */
@NoArgsConstructor
@Entity
@Table(name = "user_stories")
public class UserStoryJpa {
    @Id
    private String userStoryID;
    private String projectCode;
    private String userStoryNumber;
    private String actor;
    private String userStoryText;
    private String status;
    private int priority;
    private String effort;

    /**
     * Constructs a new UserStoryJpa object with the specified parameters.
     *
     * @param userStoryID     The ID of the user story.
     * @param projectCode     The code of the project.
     * @param userStoryNumber The number of the user story.
     * @param actor           The actor associated with the user story.
     * @param userStoryText   The text of the user story.
     * @param status          The status of the user story.
     * @param priority        The priority of the user story.
     */
    public UserStoryJpa(String userStoryID, String projectCode, String userStoryNumber,
                        String actor, String userStoryText, String status,
                        int priority) {
        this.userStoryID = userStoryID;
        this.projectCode = projectCode;
        this.userStoryNumber = userStoryNumber;
        this.actor = actor;
        this.userStoryText = userStoryText;
        this.status = status;
        this.priority = priority;
    }

    /**
     * Returns the user story ID.
     *
     * @return The user story ID.
     */
    public String getUserStoryID() {
        return userStoryID;
    }

    /**
     * Returns the project code.
     *
     * @return The project code.
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Returns the user story number.
     *
     * @return The user story number.
     */
    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    /**
     * Returns the actor associated with the user story.
     *
     * @return The actor associated with the user story.
     */
    public String getActor() {
        return actor;
    }

    /**
     * Returns the text of the user story.
     *
     * @return The text of the user story.
     */
    public String getUserStoryText() {
        return userStoryText;
    }

    /**
     * Returns the status of the user story.
     *
     * @return The status of the user story.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the user story.
     *
     * @param status The priority of the user story.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the priority of the user story.
     *
     * @return The priority of the user story.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the user story.
     *
     * @param priority The priority of the user story.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the effort of the user story.
     *
     * @return The effort of the user story.
     */
    public String getEffort() {
        return effort;
    }

    /**
     * Sets the effort of the user story.
     *
     * @param effort The effort of the user story.
     */
    public void setEffort(String effort) {
        this.effort = effort;
    }
}
