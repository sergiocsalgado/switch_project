package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.mapper.TypologyMapper;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.service.interfaces.CreateTypologyService;
import org.switch2022.project.service.interfaces.ListTypologiesService;

import java.util.List;

/**
 * The TypologyController class is responsible for adding and listing typologies.
 */
@Controller
@RestController
@RequestMapping(path = "/typologies")
public class TypologyController {

    private final CreateTypologyService createTypologyService;
    private final ListTypologiesService listTypologiesService;


    /**
     * Constructs a BusinessSectorController object with the given parameters.
     *
     * @param createTypologyService the TypologyService object has access to TypologyRepository
     * @param listTypologiesService the TypologyService object has access to TypologyRepository
     */

    public TypologyController(CreateTypologyService createTypologyService,
                              ListTypologiesService listTypologiesService) {
        this.createTypologyService = createTypologyService;
        this.listTypologiesService = listTypologiesService;
    }

    /**
     * Adds a typology based on the provided TypologyDTO.
     *
     * @param typologyDTO the TypologyDTO containing the typology details.
     * @return the TypologyDTO representing the added typology.
     * @throws IllegalArgumentException if the provided TypologyDTO contains invalid input parameters.
     */
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createTypology(@RequestBody TypologyDTO typologyDTO) {

        try {
            Typology typologyToCreate = createTypologyService.createTypology(
                    typologyDTO.getTypologyID(),
                    typologyDTO.getTypologyDescription());

            TypologyDTO typologyCreated = TypologyMapper.toDTO(typologyToCreate);

            return new ResponseEntity<>(typologyCreated, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }

    /**
     * Returns a list of typologies.
     *
     * @return a list of typologies containing information of all typologies
     * such as typology id and typology description.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getTypologies() {
        List<TypologyDTO> typologies = listTypologiesService.getTypologies();

        return new ResponseEntity<>(typologies, HttpStatus.OK);
    }
}
