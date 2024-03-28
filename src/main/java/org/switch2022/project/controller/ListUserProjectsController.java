package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.ProjectDTO;

import org.switch2022.project.service.interfaces.ListUserProjectsService;

import java.util.List;

/**
 * This class represents the controller responsible for handling requests related to listing user projects.
 * It provides endpoints to retrieve a list of projects where a user is present.
 */

@Controller
@RestController
@RequestMapping(path = "/projects")
public class ListUserProjectsController {

    private final ListUserProjectsService listUserProjectsService;

    /**
     * Constructs a new ListUserProjectsController with the given ListUserProjectsService.
     *
     * @param listUserProjectsService the service used for retrieving the list of user projects
     */
    public ListUserProjectsController(ListUserProjectsService listUserProjectsService) {
        this.listUserProjectsService = listUserProjectsService;
    }

    /**
     * Returns a list of projects where a user is present.
     *
     * @param email the email of the user account.
     * @param date the date when want to see the projects list.
     * @return the list o projectDTOs where a user is present.
     */
    @GetMapping("/project-team/{email}")
    @ResponseBody
    public ResponseEntity<Object> listAllUserProjects(@PathVariable String email, String date) {
        try {
            List<ProjectDTO> projectsDTO = listUserProjectsService.listAllUserProjects(email, date);

            return new ResponseEntity<>(projectsDTO,HttpStatus.OK);
        }
        catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input parameters to list projects where the user is present.");
        }

    }
}

