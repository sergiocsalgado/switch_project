package org.switch2022.project.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.EstimateEffortRequestDTO;
import org.switch2022.project.service.interfaces.EstimateEffortService;

/**
 * Controller class that handles HTTP requests related to estimating the effort of user stories.
 */
@Controller
@RestController
@RequestMapping(path = "/projects")
public class EstimateEffortController {

    private final EstimateEffortService estimateEffortService;

    /**
     * Constructs an instance of EstimateEffortController with the specified EstimateEffortService.
     *
     * @param estimateEffortService the EstimateEffortService used for estimating the effort of user stories.
     */
    public EstimateEffortController(EstimateEffortService estimateEffortService) {
        this.estimateEffortService = estimateEffortService;
    }

    /**
     * Sets the effort for a user story based on the provided EstimateEffortRequestDTO.
     *
     * @param estimateEffortRequestDTO the EstimateEffortRequestDTO containing the effort details.
     * @return a ResponseEntity with HTTP status OK if the effort is set successfully, otherwise with
     * HTTP status BAD REQUEST.
     */
    @PatchMapping("/product-backlog/{userStoryID}")
    @ResponseBody
    public ResponseEntity<Object> setEffortToUserStory(@RequestParam EstimateEffortRequestDTO
                                                               estimateEffortRequestDTO) {
        String userStoryID = estimateEffortRequestDTO.getUserStoryID();
        int storyPoints = estimateEffortRequestDTO.getStoryPoints();
        if (estimateEffortService.setEffort(userStoryID, storyPoints)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
