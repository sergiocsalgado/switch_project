package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.TypologyJPA;

import java.util.Optional;

@Repository
public interface TypologyJpaRepository extends JpaRepository<TypologyJPA, Long> {

    Optional<TypologyJPA> findByTypologyID(String typologyID);

    boolean existsByTypologyID(String typologyID);
}
