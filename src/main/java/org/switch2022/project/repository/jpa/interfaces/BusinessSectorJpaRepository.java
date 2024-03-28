package org.switch2022.project.repository.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.jpa.BusinessSectorJPA;

import java.util.Optional;

@Repository
public interface BusinessSectorJpaRepository extends JpaRepository<BusinessSectorJPA, Long> {

    Optional<BusinessSectorJPA> findByBusinessSectorID(String businessSectorID);

    boolean existsByBusinessSectorID (String businessSectorID);
}
