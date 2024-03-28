package org.switch2022.project.model.business_sector;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

@Component
public interface FactoryBusinessSector {
    public BusinessSector addBusinessSector(BusinessSectorID businessSectorID, Description businessSectorDescription);
}
