package org.switch2022.project.model.profile;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

/**
 * Factory interface for creating Profile objects.
 */
@Component
public interface FactoryProfile {

    /**
     * Creates a new Profile object with the specified profile ID and description.
     *
     * @param profileID   the profile ID
     * @param description the profile description
     * @return a new Profile object
     */
    Profile createProfile(ProfileID profileID, Description description);
}
