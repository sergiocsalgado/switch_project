package org.switch2022.project.model.profile;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

import java.util.Objects;

/**
 * Represents a profile aggregate root.
 * A profile consists of a unique profile ID and a description.
 */
public class Profile implements AggregateRoot<ProfileID> {

    private final Description description;
    private final ProfileID profileID;

    /**
     * Constructs a Profile object with the specified profile ID and description.
     *
     * @param profileID   the profile ID
     * @param description the profile description
     * @throws IllegalArgumentException if the profile ID or description is null
     */
    public Profile(ProfileID profileID, Description description) {
        if (profileID == null) {
            throw new IllegalArgumentException("ProfileID cannot be null");
        }
        this.profileID = profileID;

        if (description == null) {
            throw new IllegalArgumentException("The profile description cannot be null");
        }
        this.description = description;
    }

    /**
     * Creates a copy of this Profile object.
     *
     * @return a new Profile object with the same profile ID and description
     */
    public Profile copy() {
        return new Profile(this.profileID, this.description);
    }

    /**
     * Returns the profile ID of this profile.
     *
     * @return the profile ID
     */
    public ProfileID getProfileID() {
        return profileID;
    }

    /**
     * Returns the description of this profile.
     *
     * @return the profile description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Checks if this profile is equal to the specified object.
     *
     * @param object the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Profile profile = (Profile) object;
        return sameIDAs(profile);
    }

    /**
     * Returns the hash code value for this profile.
     *
     * @return the hash code value for this profile
     */
    @Override
    public int hashCode() {
        return Objects.hash(profileID, description);
    }

    /**
     * Returns the identity of this profile.
     *
     * @return the profile ID
     */
    @Override
    public ProfileID identity() {
        return profileID;
    }

    /**
     * Checks if this profile has the same identity as the specified object.
     *
     * @param object the object to compare with
     * @return true if the objects have the same profile ID, false otherwise
     */
    @Override
    public boolean sameIDAs(Object object) {
        if (object instanceof Profile) {
            Profile profile = (Profile) object;
            return this.description.equals(profile.getDescription());
        }
        return false;
    }
}
