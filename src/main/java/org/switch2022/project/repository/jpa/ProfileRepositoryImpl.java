package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.ProfileJpa;
import org.switch2022.project.datamodel.jpa.assemblers.ProfileJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.repository.jpa.interfaces.ProfileJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link Repository} interface using JPA for storing and retrieving profiles.
 */
@org.springframework.stereotype.Repository("profileJPARepository")
public class ProfileRepositoryImpl implements Repository<ProfileID, Profile> {

    private final ProfileJpaRepository profileJpaRepository;

    /**
     * Constructs a new instance of the {@code ProfileRepositoryJpaImpl} class with the specified JPA repository.
     *
     * @param profileJpaRepository the JPA repository for profiles.
     */
    public ProfileRepositoryImpl(ProfileJpaRepository profileJpaRepository) {
        this.profileJpaRepository = profileJpaRepository;
    }

    /**
     * Saves the specified profile by converting it to a JPA data model and invoking the save method on
     * the JPA repository.
     *
     * @param profile the profile to save.
     * @return the saved profile.
     */
    public Profile save(Profile profile) {
        ProfileJpa profileJpaToSave = ProfileJpaAssembler.toDataModel(profile);

        profileJpaRepository.save(profileJpaToSave);

        return profile;
    }

    /**
     * Retrieves all profiles by invoking the findAll method on the JPA repository and converting
     * the returned JPA data models to domain models.
     *
     * @return a list of all profiles
     */
    public List<Profile> findAll() {
        List<ProfileJpa> savedProfiles = profileJpaRepository.findAll();
        List<Profile> profiles = new ArrayList<>();

        for (ProfileJpa prfJpa : savedProfiles) {
            profiles.add(ProfileJpaAssembler.toDomain(prfJpa));
        }

        return profiles;
    }

    /**
     * Retrieves a profile by its identity from the JPA repository and converts it to a domain model.
     *
     * @param id the identity of the profile
     * @return an optional containing the retrieved profile if found, or an empty optional if not found
     */
    public Optional<Profile> ofIdentity(ProfileID id) {

        Optional<ProfileJpa> optionalProfileJpa = profileJpaRepository.findByProfileID(id.getProfileID());

        if (optionalProfileJpa.isPresent()) {
            Profile profile = ProfileJpaAssembler.toDomain(optionalProfileJpa.get());
            return Optional.of(profile);
        }

        return Optional.empty();
    }

    /**
     * Checks if a profile with the specified identity exists in the JPA repository.
     *
     * @param id the identity of the profile
     * @return true if the profile exists, false otherwise
     */
    public boolean containsOfIdentity(ProfileID id) {
        return profileJpaRepository.existsByProfileID(id.getProfileID());
    }
}
