package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.AccountJpa;

import java.util.Optional;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpa, Long> {

    /**
     * Retrieves an Optional containing the AccountJpa entity with the specified email.
     *
     * @param email The email of the AccountJpa entity.
     * @return An Optional containing the AccountJpa entity if found, or an empty Optional if not found.
     */
    Optional<AccountJpa> findByEmail(String email);

    /**
     * Checks if an AccountJpa entity with the specified email exists in the repository.
     *
     * @param email The email of the AccountJpa entity.
     * @return true if an AccountJpa entity with the specified email exists, false otherwise.
     */
    boolean existsByEmail(String email);
}