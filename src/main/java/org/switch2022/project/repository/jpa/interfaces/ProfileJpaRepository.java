package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.ProfileJpa;
import java.util.Optional;

@Repository
public interface ProfileJpaRepository extends JpaRepository<ProfileJpa, Long> {

    /**
     * Retrieves an Optional containing the ProfileJpa entity with the specified profile ID.
     *
     * @param profileID The profile ID of the ProfileJpa entity.
     * @return An Optional containing the ProfileJpa entity if found, or an empty Optional if not found.
     */
    Optional<ProfileJpa> findByProfileID(String profileID);

    /**
     * Checks if a ProfileJpa entity with the specified profile ID exists in the repository.
     *
     * @param profileID The profile ID of the ProfileJpa entity.
     * @return true if a ProfileJpa entity with the specified profile ID exists, false otherwise.
     */
    boolean existsByProfileID(String profileID);
}
