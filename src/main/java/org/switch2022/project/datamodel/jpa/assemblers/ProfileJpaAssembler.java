package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.ProfileJpa;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;

/**
 * The ProfileJpaAssembler class is responsible for converting objects between the Profile and ProfileJpa classes.
 */
public final class ProfileJpaAssembler {
    private ProfileJpaAssembler(){}

    /**
     * Converts a Profile object to a ProfileJpa object.
     *
     * @param profile The Profile object to be converted.
     * @return The corresponding ProfileJpa object.
     */
    public static ProfileJpa toDataModel(Profile profile) {
        return new ProfileJpa(
                profile.getProfileID().getProfileID(),
                profile.getDescription().getDescription()
        );
    }

    /**
     * Converts a ProfileJpa object to a Profile object.
     *
     * @param profileJpa The ProfileJpa object to be converted.
     * @return The corresponding Profile object.
     */
    public static Profile toDomain(ProfileJpa profileJpa) {
        return new Profile(
                new ProfileID(profileJpa.getProfileID()),
                new Description(profileJpa.getDescription())
        );
    }
}
