package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Represents a JPA entity for a profile.
 * This class maps to the "profile" table in the database.
 */
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileJpa {
    @Id
    private String profileID;
    private String description;

    /**
     * Constructs a new ProfileJpa object with the given profileID and description.
     *
     * @param profileID   The profile ID.
     * @param description The description of the profile.
     */
    public ProfileJpa(String profileID, String description) {
        this.profileID = profileID;
        this.description = description;
    }

    /**
     * Retrieves the profile ID.
     *
     * @return The profile ID.
     */
    public String getProfileID() {
        return profileID;
    }

    /**
     * Retrieves the description of the profile.
     *
     * @return The description of the profile.
     */
    public String getDescription() {
        return description;
    }
}