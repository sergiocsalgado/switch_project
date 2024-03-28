package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a User Story,
 * containing information such as userStoryID, userStoryText and status.
 */
public class UserStoryDTO {
    private String projectCode;
    private String userStoryID;
    private String userStoryText;
    private String status;
    private String userStoryNumber;
    private String actor;
    private int priority;

    /**
     * Returns the userStoryID of userStoryDTO.
     *
     * @return the userStoryID.
     */
    public String getUserStoryID() {
        return this.userStoryID;
    }

    /**
     * Set the userStoryID of userStoryDTO.
     *
     * @param userStoryID the user story ID.
     */
    public void setUserStoryID(String userStoryID) {
        this.userStoryID = userStoryID;
    }

    /**
     * Returns the projectCode of userStoryDTO.
     *
     * @return the projectCode.
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Set the projectCode of userStoryDTO.
     *
     * @param projectCode the project code.
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * Returns the userStoryNumber of userStoryDTO.
     *
     * @return the userStoryNumber.
     */
    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    /**
     * Set the userStoryNumber of userStoryDTO.
     *
     * @param userStoryNumber the user story number.
     */
    public void setUserStoryNumber(String userStoryNumber) {
        this.userStoryNumber = userStoryNumber;
    }

    /**
     * Returns the actor of userStoryDTO.
     *
     * @return the actor.
     */
    public String getActor() {
        return actor;
    }

    /**
     * Set the actor of userStoryDTO.
     *
     * @param actor the actor.
     */
    public void setActor(String actor) {
        this.actor = actor;
    }

    /**
     * Returns the userStoryText of userStoryDTO.
     *
     * @return the userStoryText.
     */
    public String getUserStoryText() {
        return this.userStoryText;
    }

    /**
     * Set the userStoryText of userStoryDTO.
     *
     * @param userStoryText the user story text.
     */
    public void setUserStoryText(String userStoryText) {
        this.userStoryText = userStoryText;
    }

    /**
     * Returns the status of userStoryDTO.
     *
     * @return the status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Set the status of userStoryDTO.
     *
     * @param status the user story status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the priority of userStoryDTO.
     *
     * @return the priority.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Set the priority of userStoryDTO.
     *
     * @param priority the priority of the user story.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Compares two UserStoryDTO objects.
     *
     * @param o the object to compare with.
     * @return true if the userStoryID, userStoryText and status fields are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() == o.getClass()) {
            return userStoryID.equals(((UserStoryDTO) o).getUserStoryID())
                    && userStoryText.equals(((UserStoryDTO) o).getUserStoryText())
                    && status.equals(((UserStoryDTO) o).getStatus());
        }
        return false;
    }

    /**
     * Returns a hash code value for this UserStoryDTO object based on its userStoryID, userStoryText and status fields.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, userStoryText, status);
    }
}

