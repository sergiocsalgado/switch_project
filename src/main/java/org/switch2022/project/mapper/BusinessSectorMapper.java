package org.switch2022.project.mapper;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BusinessSectorMapper {

    private BusinessSectorMapper() {
    }

    /**
     * Maps a list of {@link BusinessSector} objects to a list of {@link BusinessSectorDTO} objects.
     *
     * @param businessSectors a list of {@link BusinessSector} objects to be mapped
     * @return a list of {@link BusinessSectorDTO} objects
     */
    public static List<BusinessSectorDTO> listBusinessSectorDTO(List<BusinessSector> businessSectors) {
        List<BusinessSectorDTO> businessSectorsDTO = new ArrayList<>();
        for (BusinessSector bs : businessSectors) {
            BusinessSectorID businessSectorID = bs.getBusinessSectorID();
            Description description = bs.getBusinessSectorDescription();

            BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
            businessSectorDTO.setBusinessSectorID(businessSectorID.getId());
            businessSectorDTO.setDescription(description.getDescription());

            businessSectorsDTO.add(businessSectorDTO);
        }
        return Collections.unmodifiableList(businessSectorsDTO);
    }

    /**
     * Retrieve a businessSectorDTO with businessSectorID and description from a businessSector object.
     *
     * @param businessSector the businessSector to map to dto.
     * @return businessSectorDTO object with businessSectorID and description attributes.
     */
    public static BusinessSectorDTO toDTO(@NonNull BusinessSector businessSector) {
        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        businessSectorDTO.setBusinessSectorID(businessSector.getBusinessSectorID().getId());
        businessSectorDTO.setDescription(businessSector.getBusinessSectorDescription().getDescription());

        return businessSectorDTO;
    }
}
