package org.switch2022.project.model.profile;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

/**
 * Implementation of the FactoryProfile interface.
 * Responsible for creating Profile objects.
 */
@Component
public class FactoryProfileImpl implements FactoryProfile {

    /**
     * Creates a new Profile object with the specified profile ID and description.
     *
     * @param profileID   the profile ID
     * @param description the profile description
     * @return a new Profile object
     */
    public Profile createProfile(ProfileID profileID, Description description) {
        return new Profile(profileID, description);
    }
}
