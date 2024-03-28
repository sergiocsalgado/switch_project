package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.business_sector.FactoryBusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.service.interfaces.AddBusinessSectorService;

import java.util.List;

/**
 * Javadoc for the {@code AddBusinessSectorServiceImpl} class.
 * This class implements the {@code AddBusinessSectorService} interface and provides the functionality to add a new
 * business sector.
 */
@Service
public class AddBusinessSectorServiceImpl implements AddBusinessSectorService {

    private final FactoryBusinessSector factoryBusinessSector;

    private final Repository<BusinessSectorID, BusinessSector> businessSectorRepository;

    /**
     * Constructs an instance of {@code AddBusinessSectorServiceImpl} with the specified dependencies.
     *
     * @param factoryBusinessSector       the factory for creating business sector objects
     * @param businessSectorRepository    the repository for business sectors
     */
    public AddBusinessSectorServiceImpl(
            FactoryBusinessSector factoryBusinessSector,
            @Qualifier("businessSectorJPARepository")
            Repository<BusinessSectorID, BusinessSector> businessSectorRepository) {
        this.factoryBusinessSector = factoryBusinessSector;
        this.businessSectorRepository = businessSectorRepository;
    }

    /**
     * Checks if the given business sector already exists based on its ID or description.
     *
     * @param businessSector    the business sector to check
     * @return true if the business sector with the same ID or description exists, false otherwise
     */
    public boolean containsIDOrDescription(BusinessSector businessSector) {
        return businessSectorRepository.containsOfIdentity(businessSector.getBusinessSectorID())
                || existDescription(businessSector.getBusinessSectorDescription());
    }

    /**
     * Checks if a business sector with the same description already exists.
     *
     * @param businessSectorDescription    the description of the business sector
     * @return true if a business sector with the same description exists, false otherwise
     */
    private boolean existDescription(Description businessSectorDescription) {
        List<BusinessSector> businessSectors = businessSectorRepository.findAll();

        for (BusinessSector bs : businessSectors) {
            if (bs.getBusinessSectorDescription().equals(businessSectorDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new business sector with the specified ID and description.
     *
     * @param bSectorID             the ID of the business sector
     * @param bSectorDescription    the description of the business sector
     * @return the added business sector
     * @throws IllegalArgumentException if the provided ID or description is invalid,
     * or if the business sector already exists.
     */
    public BusinessSector addBusinessSector(String bSectorID, String bSectorDescription) {
        BusinessSectorID businessSectorID;
        Description businessSectorDescription;

        try {
            businessSectorID = new BusinessSectorID(bSectorID);
            businessSectorDescription = new Description(bSectorDescription);

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        BusinessSector businessSector = factoryBusinessSector
                .addBusinessSector(businessSectorID, businessSectorDescription);

        if (!containsIDOrDescription(businessSector)) {
            return businessSectorRepository.save(businessSector);
        } else {
            throw new IllegalArgumentException("Business Sector already exists");
        }
    }
}
