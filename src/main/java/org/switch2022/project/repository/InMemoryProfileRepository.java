package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.ProfileID;

import java.util.*;

@org.springframework.stereotype.Repository("profileMemoryRepository")
public class InMemoryProfileRepository implements Repository<ProfileID, Profile> {
    private final Map<ProfileID, Profile> data = new HashMap<>();

    /**
     * Saves the provided Profile entity in the repository.
     *
     * @param entity The Profile entity to save.
     * @return The saved Profile entity.
     */
    public Profile save(Profile entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    /**
     * Retrieves all the Profile entities from the repository.
     *
     * @return A list of all the Profile entities.
     */
    public List<Profile> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Retrieves an Optional containing the Profile entity with the specified identity.
     *
     * @param id The identity (ProfileID) of the Profile entity.
     * @return An Optional containing the Profile entity if found, or an empty Optional if not found.
     */
    public Optional<Profile> ofIdentity(ProfileID id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }

    /**
     * Checks if the repository contains a Profile entity with the specified identity.
     *
     * @param id The identity (ProfileID) of the Profile entity.
     * @return true if the Profile entity with the specified identity exists in the repository, false otherwise.
     */
    public boolean containsOfIdentity(ProfileID id) {
        return data.containsKey(id);
    }

}
