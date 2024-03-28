package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.UserStoryJpa;

import java.util.Optional;

@Repository
public interface UserStoryJpaRepository extends JpaRepository<UserStoryJpa, Long> {
    Optional<UserStoryJpa> findByUserStoryID(String userStoryID);
    boolean existsByUserStoryID(String userStoryID);
}
