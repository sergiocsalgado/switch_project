package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.mapper.BusinessSectorMapper;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.service.interfaces.AddBusinessSectorService;
import org.switch2022.project.service.interfaces.ListBusinessSectorsService;

import java.util.List;

/**
 * The BusinessSectorController class is responsible for adding and listing business sectors.
 */

@Controller
@RestController
@RequestMapping(path = "/business-sectors")
public class BusinessSectorController {

    private final AddBusinessSectorService addBusinessSectorService;
    private final ListBusinessSectorsService listBusinessSectorsService;

    /**
     * Constructs a BusinessSectorController object with the given parameters.
     *
     * @param addBusinessSectorService   the BusinessSectorService object has access to BusinessSectorRepository
     * @param listBusinessSectorsService the BusinessSectorService object has access to BusinessSectorRepository
     */
    public BusinessSectorController(AddBusinessSectorService addBusinessSectorService,
                                    ListBusinessSectorsService listBusinessSectorsService) {
        this.addBusinessSectorService = addBusinessSectorService;
        this.listBusinessSectorsService = listBusinessSectorsService;
    }

    /**
     * Adds a business sector based on the provided BusinessSectorDTO.
     *
     * @param businessSectorDTO the BusinessSectorDTO containing the business sector details.
     * @return the BusinessSectorDTO representing the added business sector.
     * @throws IllegalArgumentException if the provided businessSectorDTO contains invalid input parameters.
     */
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> addBusinessSector(@RequestBody BusinessSectorDTO businessSectorDTO) {

        try {
            BusinessSector addBSector = addBusinessSectorService.addBusinessSector(
                    businessSectorDTO.getBusinessSectorID(),
                    businessSectorDTO.getDescription()
            );

            BusinessSectorDTO addedBSector = BusinessSectorMapper.toDTO(addBSector);

            return new ResponseEntity<>(addedBSector, HttpStatus.CREATED);

        } catch (IllegalArgumentException argumentException) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(argumentException.getMessage());
        }
    }

    /**
     * Returns a list of business sectors.
     *
     * @return a list of business sectors containing information of all business sectors
     * such as business sector id and business sector description.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getBusinessSectors() {
        List<BusinessSectorDTO> businessSectors = listBusinessSectorsService.getBusinessSectors();

        return new ResponseEntity<>(businessSectors, HttpStatus.OK);
    }
}
