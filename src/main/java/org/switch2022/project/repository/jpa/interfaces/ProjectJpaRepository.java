package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.ProjectJPA;

import java.util.Optional;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectJPA,Long> {
    Optional<ProjectJPA> findByProjectCode (String projectCode);

    boolean existsByProjectCode(String projectCode);
}
