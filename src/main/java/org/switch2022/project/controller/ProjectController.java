package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.mapper.ProjectDTOMapper;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.service.interfaces.DefineRoleService;
import org.switch2022.project.service.interfaces.ListAllProjectsService;
import org.switch2022.project.service.interfaces.ListResourcesService;
import org.switch2022.project.service.interfaces.RegisterProjectService;

import java.util.List;

@Controller
@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    private final RegisterProjectService registerProjectService;

    private final DefineRoleService defineRoleService;

    private final ListAllProjectsService listAllProjectsService;

    private final ListResourcesService listResourcesService;

    public ProjectController(RegisterProjectService registerProjectService,
                             DefineRoleService defineRoleService,
                             ListAllProjectsService listAllProjectsService,
                             ListResourcesService listResourcesService) {
        this.registerProjectService = registerProjectService;
        this.defineRoleService = defineRoleService;
        this.listAllProjectsService = listAllProjectsService;
        this.listResourcesService = listResourcesService;
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> registerProject(@RequestBody ProjectDTO projDTO) {
        try {
            Project project = registerProjectService.registerProject(projDTO);
            String customerName = projDTO.getCustomer();
            String bSName = projDTO.getBusinessSector();
            String typologyName = projDTO.getTypology();

            ProjectDTO projectDTO = ProjectDTOMapper.toDTOWithoutIDs(project, customerName, bSName, typologyName);

            return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);

        } catch (IllegalArgumentException argumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(argumentException.getMessage());
        } catch (IllegalStateException stateException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stateException.getMessage());
        }
    }

    @PostMapping("/{projectCode}/resources")
    @ResponseBody
    public ResponseEntity<Object> defineRole(@PathVariable String projectCode,
                                             @RequestBody ResourcesInProjectDTO resourcesInProjectDTO) {
        try {
            ResourceInProject resourcesInProjectToCreate = defineRoleService.defineRole(
                    projectCode,
                    resourcesInProjectDTO.getResourceInProjectID(),
                    resourcesInProjectDTO.getEmail(),
                    resourcesInProjectDTO.getRole(),
                    resourcesInProjectDTO.getAllocation(),
                    resourcesInProjectDTO.getCostPerHour(),
                    resourcesInProjectDTO.getCurrency(),
                    resourcesInProjectDTO.getStartDate(),
                    resourcesInProjectDTO.getEndDate());

            ResourcesInProjectDTO newResourcesInProjectCreated =
                    ResourcesInProjectMapper.toDTO(resourcesInProjectToCreate);
            newResourcesInProjectCreated.setProjectCode(projectCode);

            return new ResponseEntity<>(newResourcesInProjectCreated, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        } catch (IllegalStateException stateException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(stateException.getMessage());
        }
    }

    /**
     * Returns a list of project data transfer objects.
     *
     * @return a list of project data transfer objects containing information of all projects
     * such as project code, project name and project status.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getProjects() {
        List<ProjectDTO> projectDTOS = listAllProjectsService.getProjects();

        return new ResponseEntity<>(projectDTOS, HttpStatus.OK);
    }

    /**
     * Returns a list of resources in project in a specific date.
     *
     * @param projectCode the project code of the project.
     * @return a list of resources in project in a specific date.
     */
    @GetMapping("/{projectCode}/resources")
    @ResponseBody
    public ResponseEntity<Object> resourcesInProjectDTO(@PathVariable String projectCode) {
        try {
            List<ResourcesInProjectDTO> resourcesDTO = listResourcesService.getResourcesInProjectDTO(projectCode);

            return new ResponseEntity<>(resourcesDTO, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        } catch (IllegalStateException stateException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(stateException.getMessage());
        }
    }
}