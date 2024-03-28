package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.profile.FactoryProfile;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.service.interfaces.CreateProfileService;

import java.util.List;

/**
 * Implementation of the CreateProfileService interface.
 * Responsible for creating and saving Profile objects.
 */
@Service
public class CreateProfileServiceImpl implements CreateProfileService {

    private final Repository<ProfileID, Profile> profileRepository;
    private final FactoryProfile factoryProfile;

    /**
     * Constructs a CreateProfileServiceImpl object with the specified dependencies.
     *
     * @param profileRepository the profile repository
     * @param factoryProfile    the factory for creating profiles
     */
    public CreateProfileServiceImpl(
            @Qualifier("profileJPARepository") Repository<ProfileID, Profile> profileRepository,
            FactoryProfile factoryProfile) {
        this.profileRepository = profileRepository;
        this.factoryProfile = factoryProfile;
    }

    /**
     * Checks if the profile repository contains a profile with the same ID as the given profile.
     *
     * @param profile the profile to check
     * @return true if the profile repository contains a profile with the same ID, false otherwise
     */
    private boolean containsIDAndDescription(Profile profile) {
        return profileRepository.containsOfIdentity(profile.getProfileID())
                || existsDescription(profile.getDescription());
    }

    private boolean existsDescription(Description description) {
        List<Profile> profiles = profileRepository.findAll();
        for (Profile ps : profiles) {
            if (ps.getDescription().getDescription().equalsIgnoreCase(description.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new profile with the specified profile ID and description, and saves it in the repository.
     *
     * @param profileID   the profile ID
     * @param description the profile description
     * @return the created profile
     * @throws IllegalArgumentException if the profile ID or description is invalid or if the profile already exists
     */
    public Profile createProfile(String profileID, String description) {
        ProfileID profileID1;
        Description description1;

        try {
            profileID1 = new ProfileID(profileID);
            description1 = new Description(description);

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Profile profile = factoryProfile.createProfile(profileID1, description1);

        if (containsIDAndDescription(profile)) {
            throw new IllegalArgumentException("Could not create Profile, this profile already exists.");
        } else {
            return profileRepository.save(profile);
        }
    }
}
