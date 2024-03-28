package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a profile,
 * containing description and profileID as information.
 */
public class ProfileDTO {

    private String profileID;
    private String description;

    /**
     * Returns the description of the ProfileDTO.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the ProfileDTO.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the profileID of the ProfileDTO.
     *
     * @return the profileID
     */
    public String getProfileID() {
        return profileID;
    }

    /**
     * Sets the profileID of the ProfileDTO.
     *
     * @param profileID the profileID to set
     */
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    /**
     * Compares two ProfileDTO objects for equality.
     *
     * @param object the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ProfileDTO other = (ProfileDTO) object;
        return Objects.equals(description, other.description) &&
                Objects.equals(profileID, other.profileID);
    }

    /**
     * Returns a hash code value for the ProfileDTO object.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, profileID);
    }
}
