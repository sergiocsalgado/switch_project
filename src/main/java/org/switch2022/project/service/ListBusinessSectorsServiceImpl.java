package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.mapper.BusinessSectorMapper;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.service.interfaces.ListBusinessSectorsService;

import java.util.List;

@Service
public class ListBusinessSectorsServiceImpl implements ListBusinessSectorsService {

    private final Repository<BusinessSectorID, BusinessSector> businessSectorRepository;

    public ListBusinessSectorsServiceImpl(
            @Qualifier("businessSectorJPARepository")
            Repository<BusinessSectorID, BusinessSector> businessSectorRepository) {
        this.businessSectorRepository = businessSectorRepository;
    }

    public List<BusinessSectorDTO> getBusinessSectors() {

        return BusinessSectorMapper.listBusinessSectorDTO(businessSectorRepository.findAll());
    }
}
