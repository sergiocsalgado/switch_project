package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.SprintJPA;

import java.util.Optional;

@Repository
public interface SprintJpaRepository extends JpaRepository<SprintJPA, Long> {
    Optional<SprintJPA> findBySprintID(String sprintID);

    boolean existsBySprintID(String sprintID);
}
