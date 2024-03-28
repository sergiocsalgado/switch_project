package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.project.Project;

import java.time.LocalDate;

/**
 * Class responsible for mapping a list of {@link Project} objects to a list of {@link ProjectDTO} objects.
 */
@Component
public final class ProjectDTOMapper {

    private ProjectDTOMapper() {
    }

    public static ProjectDTO toDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode(project.getProjectCode().getProjectCode());
        projectDTO.setName(project.getProjectName().getValue());
        projectDTO.setDescription(project.getProjectDescription().getDescription());
        projectDTO.setCustomer(project.getCustomerID().getIdOfCustomer());
        projectDTO.setBusinessSector(project.getBusinessSectorID().getId());
        projectDTO.setTypology(project.getTypologyID().getIdOfTypology());
        projectDTO.setStartDate(project.getPeriod().getStartDate().toString());
        projectDTO.setEndDate(project.getPeriod().getEndDate().toString());
        projectDTO.setStatus(project.getProjectStatus().getProjectStatus());
        projectDTO.setSprintDuration(project.getSprintDuration().getNumber());
        projectDTO.setNumberOfPlannedSprints(project.getNumberOfPlannedSprints().getNumber());
        projectDTO.setBudget(project.getBudget().toString());

        return projectDTO;
    }

    public static ProjectDTO toDTOWithoutIDs(Project project,
                                             String customerName,
                                             String businessSectorDescription,
                                             String typologyDescription) {

        LocalDate eDate = project.getPeriod().getEndDate();
        String endDate = eDate != null ? eDate.toString() : null;

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode(project.getProjectCode().getProjectCode());
        projectDTO.setName(project.getProjectName().getValue());
        projectDTO.setDescription(project.getProjectDescription().getDescription());
        projectDTO.setCustomer(customerName);
        projectDTO.setBusinessSector(businessSectorDescription);
        projectDTO.setTypology(typologyDescription);
        projectDTO.setStartDate(project.getPeriod().getStartDate().toString());
        projectDTO.setEndDate(endDate);
        projectDTO.setStatus(project.getProjectStatus().getProjectStatus());
        projectDTO.setSprintDuration(project.getSprintDuration().getNumber());
        projectDTO.setNumberOfPlannedSprints(project.getNumberOfPlannedSprints().getNumber());
        projectDTO.setBudget(project.getBudget().toString());

        return projectDTO;
    }
}
