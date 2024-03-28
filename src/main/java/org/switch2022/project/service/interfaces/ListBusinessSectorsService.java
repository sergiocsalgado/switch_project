package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.BusinessSectorDTO;

import java.util.List;

public interface ListBusinessSectorsService {
    List<BusinessSectorDTO> getBusinessSectors();
}
