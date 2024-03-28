package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.SprintDTO;
import org.switch2022.project.mapper.SprintMapper;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.service.interfaces.ListSprintBacklog;
import org.switch2022.project.service.interfaces.ListSprintsInProjectService;
import org.switch2022.project.service.interfaces.SetSprintStatusService;
import org.switch2022.project.service.interfaces.SprintService;

import java.util.List;


@Controller
@RestController
@RequestMapping(path = "/projects")
public class SprintController {
    private final SprintService sprintService;
    private final ListSprintsInProjectService listAllSprintsService;
    private final ListSprintBacklog listSprintBacklog;
    private final SetSprintStatusService setSprintStatusService;

    public SprintController(SprintService sprintService,
                            ListSprintsInProjectService listAllSprintsService,
                            ListSprintBacklog listSprintBacklog,
                            SetSprintStatusService setSprintStatusService) {
        this.sprintService = sprintService;
        this.listAllSprintsService = listAllSprintsService;
        this.listSprintBacklog = listSprintBacklog;
        this.setSprintStatusService = setSprintStatusService;
    }

    /**
     * Creates a new sprint with the information contained in SprintDTO.
     *
     * @param projectCode the project code of the project.
     * @param sprintDTO the sprintDTO with sprint details
     * @return the SprintDTO representing the created and saved sprint.
     * @throws IllegalArgumentException if the provided sprintDTO contains invalid input parameters.
     * @throws IllegalStateException    if the project code is not found.
     */
    @PostMapping("/{projectCode}/sprints")
    @ResponseBody
    public ResponseEntity<Object> createSprint(@PathVariable String projectCode, @RequestBody SprintDTO sprintDTO) {
        try {
            Sprint sprintToCreate = sprintService.createAndSaveSprint(
                    sprintDTO.getSprintID(),
                    sprintDTO.getSprintNumber(),
                    sprintDTO.getStartDate(),
                    sprintDTO.getEndDate(),
                    projectCode);

            SprintDTO sprintCreated = SprintMapper.toDTO(sprintToCreate);

            return new ResponseEntity<>(sprintCreated, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        } catch (IllegalStateException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

    /**
     * Retrieves all sprints.
     *
     * @param projectCode the project code of the project.
     * @return the list of sprintDTOs.
     */
    @GetMapping("/{projectCode}/sprints")
    @ResponseBody
    public ResponseEntity<Object> getSprintsInProject(@PathVariable String projectCode) {
        List<SprintDTO> sprintDTOS = listAllSprintsService.getSprintsInProject(projectCode);
        return new ResponseEntity<>(sprintDTOS, HttpStatus.OK);
    }


    /**
     * Returns the list of user stories of the sprint backlog.
     *
     * @param sprintID the sprintID of the sprint.
     * @return the list of userStoryDTOs of the sprint backlog containing information about the user stories.
     */
    @GetMapping("/{projectCode}/sprints/{sprintID}/sprint-backlog")
    @ResponseBody
    public ResponseEntity<Object> getSprintBacklog(@PathVariable String sprintID) {
        List<UserStoryDTO> sprintBacklog = listSprintBacklog.getSprintBacklog(sprintID);

        return new ResponseEntity<>(sprintBacklog, HttpStatus.OK);
    }

    /**
     * Set status of Sprint.
     *
     * @param sprintDTO the sprintDTO to set status.
     * @return the sprint information updated.
     */
    @PatchMapping("/{projectCode}/sprints")
    @ResponseBody
    public ResponseEntity<Object> setSprintStatus(@RequestBody SprintDTO sprintDTO) {
        try {
            Sprint sprintToUpdate = setSprintStatusService.setSprintStatus(sprintDTO.getSprintID());

            SprintDTO sprintUpdated = SprintMapper.toDTOUpdatedStatus(sprintToUpdate);
            return new ResponseEntity<>(sprintUpdated, HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException stateException) {
            return new ResponseEntity<>(stateException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
