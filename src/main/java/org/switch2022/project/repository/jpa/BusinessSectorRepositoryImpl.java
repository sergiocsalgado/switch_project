package org.switch2022.project.repository.jpa;


import org.switch2022.project.datamodel.jpa.BusinessSectorJPA;
import org.switch2022.project.datamodel.jpa.assemblers.BusinessSectorJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.repository.jpa.interfaces.BusinessSectorJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link Repository} interface using JPA for storing and retrieving business sectors.
 */
@org.springframework.stereotype.Repository("businessSectorJPARepository")
public class BusinessSectorRepositoryImpl implements Repository<BusinessSectorID, BusinessSector> {

    private final BusinessSectorJpaRepository businessSectorJpaRepository;

    /**
     * Constructs a new instance of the {@code BusinessSectorRepositoryJpaImpl} class with the specified JPA repository.
     *
     * @param businessSectorJpaRepository the JPA repository for business sectors
     */

    public BusinessSectorRepositoryImpl(BusinessSectorJpaRepository businessSectorJpaRepository) {
        this.businessSectorJpaRepository = businessSectorJpaRepository;
    }

    /**
     * Saves the specified businessSector by converting it to a JPA data model and invoking the
     * save method on the JPA repository.
     *
     * @param businessSector the businessSector to save
     * @return the saved businessSector
     */

    public BusinessSector save(BusinessSector businessSector) {
        BusinessSectorJPA toSaveBusinessSectorJPA = BusinessSectorJpaAssembler.toDataModel(businessSector);

        businessSectorJpaRepository.save(toSaveBusinessSectorJPA);

        return businessSector;
    }

    /**
     * Retrieves all business sectors by invoking the findAll method on the JPA repository and
     * converting the returned JPA data models to domain models.
     *
     * @return a list of all business sectors
     */

    public List<BusinessSector> findAll() {
        List<BusinessSectorJPA> savedBusinessSectors = businessSectorJpaRepository.findAll();
        List<BusinessSector> businessSectors = new ArrayList<>();

        for (BusinessSectorJPA bSJpa : savedBusinessSectors) {
            businessSectors.add(BusinessSectorJpaAssembler.toDomain(bSJpa));
        }
        return businessSectors;
    }

    /**
     * Retrieves a businessSector by its identity from the JPA repository and converts it to a domain model.
     *
     * @param id the identity of the businessSector
     * @return an optional containing the retrieved businessSector if found, or an empty optional if not found
     */

    public Optional<BusinessSector> ofIdentity(BusinessSectorID id) {

        Optional<BusinessSectorJPA> optionalBusinessSectorJPA =
                businessSectorJpaRepository.findByBusinessSectorID(id.getId());

        if (optionalBusinessSectorJPA.isPresent()) {
            BusinessSector businessSector = BusinessSectorJpaAssembler
                    .toDomain(optionalBusinessSectorJPA.get());
            return Optional.of(businessSector);
        }
        return Optional.empty();
    }

    /**
     * Checks if a businessSector with the specified identity exists in the JPA repository.
     *
     * @param id the identity of the businessSector
     * @return {@code true} if the businessSector exists, {@code false} otherwise
     */

    public boolean containsOfIdentity(BusinessSectorID id) {
        return businessSectorJpaRepository.existsByBusinessSectorID(id.getId());
    }
}
