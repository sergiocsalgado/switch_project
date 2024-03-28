package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

/**
 * Represents a profile ID entity.
 * The profile ID is a unique identifier for a profile.
 */
public final class ProfileID implements EntityID {
    private final String idProfile;

    /**
     * Constructs a ProfileID object.
     *
     * @param idProfile the profile ID string
     * @throws IllegalArgumentException if the provided ID is null or blank
     */
    public ProfileID(String idProfile) {
        StringValidation.checkNull("Profile ID", idProfile);
        StringValidation.checkBlank("Profile ID", idProfile);

        this.idProfile = idProfile;
    }

    /**
     * Returns the profile ID string.
     *
     * @return the profile ID string
     */
    public String getProfileID() {
        return idProfile;
    }

    /**
     * Returns a string representation of the profile ID.
     *
     * @return a string representation of the profile ID
     */
    @Override
    public String toString() {
        return "Profile ID{" + "id='" + idProfile + '\'' + '}';
    }

    /**
     * Checks if this profile ID is the same as the specified other profile ID.
     *
     * @param other the other profile ID to compare with
     * @return true if the profile IDs are the same, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        return other != null && this.idProfile.equals(((ProfileID) other).getProfileID());
    }

    /**
     * Compares this profile ID with the specified object for equality.
     *
     * @param otherID the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object otherID) {
        if (this == otherID) {
            return true;
        }
        if (otherID == null || this.getClass() != otherID.getClass()) {
            return false;
        }
        ProfileID thatID = (ProfileID) otherID;
        return sameAs(thatID);
    }

    /**
     * Returns the hash code value for this profile ID.
     *
     * @return the hash code value for this profile ID
     */
    @Override
    public int hashCode() {
        return Objects.hash(idProfile);
    }
}
