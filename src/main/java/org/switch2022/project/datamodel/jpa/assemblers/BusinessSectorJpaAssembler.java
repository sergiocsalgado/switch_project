package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.BusinessSectorJPA;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

public final class BusinessSectorJpaAssembler {

    private BusinessSectorJpaAssembler() {
    }

    public static BusinessSectorJPA toDataModel(BusinessSector businessSector) {
        return new BusinessSectorJPA(
                businessSector.getBusinessSectorID().getId(),
                businessSector.getBusinessSectorDescription().getDescription()
        );
    }

    public static BusinessSector toDomain(BusinessSectorJPA businessSectorJPA) {
        return new BusinessSector(
                new BusinessSectorID(businessSectorJPA.getBusinessSectorID()),
                new Description(businessSectorJPA.getBusinessSectorDescription())
        );
    }
}
