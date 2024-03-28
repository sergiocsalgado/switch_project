package org.switch2022.project.mapper;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for mapping a list of {@link ResourceInProject} objects to a list.
 * of {@link ResourcesInProjectDTO} objects.
 */
public final class ResourcesInProjectMapper {

    private ResourcesInProjectMapper() {
    }

    /**
     * Creates a new ResourcesInProjectDTO objects.
     *
     * @param resourceInProjectID an attribute of ResourcesInProject object to be mapped.
     * @param email               an attribute of ResourcesInProject object to be mapped.
     * @param role                an attribute of ResourcesInProject object to be mapped.
     * @return a new ResourcesInProjectDTO object.
     */
    private static ResourcesInProjectDTO createResourceDTO(ProjectCode projectCode,
                                                           ResourceInProjectID resourceInProjectID,
                                                           Email email, Role role, Period period,
                                                           Cost cost, Allocation allocation) {
        ResourcesInProjectDTO resourceDTO = new ResourcesInProjectDTO();
        resourceDTO.setProjectCode(projectCode.getProjectCode());
        resourceDTO.setResourceInProjectID(resourceInProjectID.getResourceOfProjectID());
        resourceDTO.setEmail(email.getEmail());
        resourceDTO.setRole(role.getDescription());
        resourceDTO.setStartDate(period.getStartDate().toString());
        resourceDTO.setEndDate(period.getEndDate().toString());
        resourceDTO.setCostPerHour(cost.getValue());
        resourceDTO.setCurrency(cost.getCurrency());
        resourceDTO.setAllocation(allocation.getAllocation());

        return resourceDTO;
    }

    /**
     * Maps a list of ResourceInProject objects to a list of ResourcesInProjectDTO objects.
     *
     * @param resources the list of resources in project.
     * @param projectCode the project code of the project.
     * @return a list of ResourcesInProjectDTO objects.
     */
    public static List<ResourcesInProjectDTO> toResourcesDTOList(List<ResourceInProject> resources,
                                                                 ProjectCode projectCode) {

        List<ResourcesInProjectDTO> resourcesInProjectDTO = new ArrayList<>();

        for (ResourceInProject resourceInProject : resources) {

            ResourceInProjectID resourceInProjectID = resourceInProject.getResourceInProjectID();
            Email email = resourceInProject.getEmail();
            Role role = resourceInProject.getRole();
            Period period = resourceInProject.getPeriod();
            Cost cost = resourceInProject.getCostPerHour();
            Allocation allocation = resourceInProject.getAllocation();

            resourcesInProjectDTO.add(createResourceDTO(
                    projectCode,
                    resourceInProjectID,
                    email,
                    role,
                    period,
                    cost,
                    allocation));
        }
        return Collections.unmodifiableList(resourcesInProjectDTO);
    }

    public static ResourcesInProjectDTO toDTO(@NonNull ResourceInProject resourceInProject) {

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        resourcesInProjectDTO.setResourceInProjectID(resourceInProject
                .getResourceInProjectID().getResourceOfProjectID());
        resourcesInProjectDTO.setEmail(resourceInProject.getEmail().getEmail());
        resourcesInProjectDTO.setRole(resourceInProject.getRole().getDescription());
        resourcesInProjectDTO.setAllocation(resourceInProject.getAllocation().getAllocation());
        resourcesInProjectDTO.setCostPerHour(resourceInProject.getCostPerHour().getValue());
        resourcesInProjectDTO.setCurrency(resourceInProject.getCostPerHour().getCurrency());
        resourcesInProjectDTO.setStartDate(resourceInProject.getPeriod().getStartDate().toString());
        resourcesInProjectDTO.setEndDate(resourceInProject.getPeriod().getEndDate().toString());

        return resourcesInProjectDTO;
    }
}
