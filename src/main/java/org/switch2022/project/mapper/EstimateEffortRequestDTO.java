package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a EstimateEffortRequest,
 * containing information such as userStoryID and storyPoints.
 */

public class EstimateEffortRequestDTO {
    private String userStoryID;
    private int storyPoints;

    /**
     * Get the user story ID.
     *
     * @return the user story ID.
     */
    public String getUserStoryID() {
        return userStoryID;
    }

    /**
     * Set the user story ID.
     *
     * @param userStoryID the user story ID to set.
     */
    public void setUserStoryID(String userStoryID) {
        this.userStoryID = userStoryID;
    }

    /**
     * Get the story points.
     *
     * @return the story points.
     */
    public int getStoryPoints() {
        return storyPoints;
    }

    /**
     * Set the story points.
     *
     * @param storyPoints the story points to set.
     */
    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    /**
     * Check if this EstimateEffortRequestDTO is equal to another object.
     *
     * @param o the object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstimateEffortRequestDTO that = (EstimateEffortRequestDTO) o;
        return getStoryPoints() == that.getStoryPoints() && Objects.equals(getUserStoryID(), that.getUserStoryID());
    }

    /**
     * Calculate the hash code of this EstimateEffortRequestDTO.
     *
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, storyPoints);
    }
}