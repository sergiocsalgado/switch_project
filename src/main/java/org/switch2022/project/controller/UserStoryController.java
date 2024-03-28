package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.service.interfaces.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(path = "/projects")
public class UserStoryController {

    private final CreateUserStoryService userStoryService;
    private final AddUserStoryToSprintBacklogService addUserStoryToSprintBacklogService;
    private final ListUserStoriesService listUserStoriesService;
    private final ViewScrumBoardService viewScrumBoardService;
    private final UpdateUserStoryStatusService updateUserStoryStatusService;
    private final UpdateProductBacklogService updateProductBacklogService;

    public UserStoryController(
            CreateUserStoryService userStoryService,
            AddUserStoryToSprintBacklogService addUserStoryToSprintBacklogService,
            ListUserStoriesService listUserStoriesService,
            ViewScrumBoardService viewScrumBoardService,
            UpdateUserStoryStatusService updateUserStoryStatusService,
            UpdateProductBacklogService updateProductBacklogService) {
        this.userStoryService = userStoryService;
        this.addUserStoryToSprintBacklogService = addUserStoryToSprintBacklogService;
        this.listUserStoriesService = listUserStoriesService;
        this.viewScrumBoardService = viewScrumBoardService;
        this.updateUserStoryStatusService = updateUserStoryStatusService;
        this.updateProductBacklogService = updateProductBacklogService;
    }

    /**
     * Adds a new user story to the specified project.
     *
     * @param projectCode  the code of the project to add the user story to
     * @param userStoryDTO the DTO object containing the details of the user story to add
     * @return a ResponseEntity object containing the DTO representation of the
     * newly created user story and an HTTP status.
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the project does not exist.
     */
    @PostMapping("/{projectCode}/product-backlog")
    @ResponseBody
    public ResponseEntity<Object> createUserStoryAndAddToProductBacklog(@PathVariable("projectCode") String projectCode,
                                                                        @RequestBody UserStoryDTO userStoryDTO) {
        try {
            UserStory userStoryToCreate = userStoryService.createUserStory(
                    projectCode,
                    userStoryDTO.getUserStoryID(),
                    userStoryDTO.getUserStoryNumber(),
                    userStoryDTO.getActor(),
                    userStoryDTO.getUserStoryText(),
                    userStoryDTO.getPriority());

            UserStoryDTO newUserStory = UserStoryMapper.toDTO(userStoryToCreate);

            return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        } catch (IllegalStateException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

    /**
     * Returns true if the user story with the specified code has been moved
     * from the product backlog to the sprint backlog of the project with the
     * corresponding code; otherwise, returns false.
     *
     * @param userStoryID from the UserStory.
     * @param sprintID    from the Sprint.
     * @param projectCode from the Project.
     * @return true if user story is added to sprint backlog, false otherwise.
     * @throws IllegalArgumentException if the input parameters are invalid.
     */
    @CrossOrigin
    @PatchMapping("/{projectCode}/product-backlog/{userStoryID}/{sprintID}")
    @ResponseBody
    public ResponseEntity<Object> addUserStoryFromProductBacklogToSprintBacklog(
            @PathVariable("projectCode") String projectCode,
            @PathVariable("userStoryID") String userStoryID,
            @PathVariable("sprintID") String sprintID) {

        try {
            addUserStoryToSprintBacklogService.addUserStoryToSprintBacklog(new UserStoryID(userStoryID),
                    new SprintID(sprintID), new ProjectCode(projectCode));
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Changes the status of a user story.
     *
     * @param userStoryDTO the user story DTO representation to update status.
     * @return a ResponseEntity object containing the DTO representation of the updated user story and an HTTP status.
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the project or the user story does not exist.
     */
    @PatchMapping("/{projectCode}/scrum-board/{userStoryID}")
    @ResponseBody
    public ResponseEntity<Object> updateUserStoryStatus(@RequestBody UserStoryDTO userStoryDTO) {

        try {
            UserStory updatedUserStory =
                    updateUserStoryStatusService.updateUserStoryStatus(userStoryDTO);
            UserStoryDTO responseUserStoryDTO = UserStoryMapper.toDTO(updatedUserStory);

            return new ResponseEntity<>(responseUserStoryDTO, HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException stateException) {
            return new ResponseEntity<>(stateException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns the Product Backlog, i.e. a list of the User Stories where the status is "planned".
     *
     * @param projectCode the project code of the project.
     * @return a list of User Stories with the status "planned".
     * @throws IllegalArgumentException if the input parameters are invalid.
     */
    @GetMapping("/{projectCode}/product-backlog")
    @ResponseBody
    public ResponseEntity<Object> getProductBacklogDTO(@PathVariable("projectCode") String projectCode) {
        try {
            List<UserStory> userStories = listUserStoriesService.getUserStories(projectCode);

            List<UserStoryDTO> userStoryDTO = UserStoryMapper.getUserStoriesDTO(userStories);

            return new ResponseEntity<>(userStoryDTO, HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Returns the list of user stories in scrum board of the given project if exists.
     *
     * @param projectCode the project to view the scrum board.
     * @return the scrum board of the current sprint of the given project
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the project does not exist.
     */
    @GetMapping("/{projectCode}/scrum-board")
    @ResponseBody
    public ResponseEntity<Object> viewScrumBoard(@PathVariable String projectCode) {
        try {
            List<UserStory> userStories = viewScrumBoardService.getScrumBoard(projectCode);
            List<UserStoryDTO> userStoriesDTO = UserStoryMapper.getUserStoriesDTO(userStories);

            return new ResponseEntity<>(userStoriesDTO, HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException stateException) {
            return new ResponseEntity<>(stateException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Returns to the product backlog the unfinished user stories in the sprint.
     *
     * @param projectCode to select the userStories of the select project.
     * @return a ResponseEntity object containing the DTO representation of the updated user stories status and an HTTP
     * status.
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the project or the user story does not exist.
     */
    @PatchMapping("/{projectCode}/product-backlog")
    @ResponseBody
    public ResponseEntity<Object> updateProductBacklog(@PathVariable("projectCode") String projectCode) {

        try {
            List<UserStory> userStories = updateProductBacklogService.updateProductBacklog(projectCode);

            List<UserStoryDTO> userStoryDTO = UserStoryMapper.getUserStoriesDTO(userStories);

            return new ResponseEntity<>(userStoryDTO, HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException stateException) {
            return new ResponseEntity<>(stateException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}