package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.business_sector.BusinessSector;

public interface AddBusinessSectorService {
    BusinessSector addBusinessSector(String bSectorID, String bSectorDescription);
}
