package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.profile.Profile;

/**
 * Interface for the service responsible for creating profiles.
 */
public interface CreateProfileService {

    /**
     * Creates a new profile with the specified profile ID and description.
     *
     * @param profileID   the profile ID
     * @param description the profile description
     * @return the created profile
     */
    Profile createProfile(String profileID, String description);
}
