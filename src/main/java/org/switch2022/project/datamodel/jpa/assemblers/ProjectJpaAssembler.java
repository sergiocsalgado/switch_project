package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.ProjectJPA;
import org.switch2022.project.datamodel.jpa.ResourceInProjectJpa;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public final class ProjectJpaAssembler {

    private ProjectJpaAssembler() {
    }

    public static ProjectJPA toDataModel(Project project) {
        Period period = project.getPeriod();

        LocalDate endDate = null;

        if (period != null) {
            endDate = period.getEndDate();
        }

        ProjectJPA projectJpa = new ProjectJPA(
                project.getProjectCode().getProjectCode(),
                project.getProjectName().getValue(),
                project.getProjectDescription().getDescription(),
                project.getCustomerID().getIdOfCustomer(),
                project.getBusinessSectorID().getId(),
                project.getTypologyID().getIdOfTypology(),
                project.getProjectStatus().getProjectStatus(),
                project.getSprintDuration().getNumber(),
                project.getNumberOfPlannedSprints().getNumber(),
                project.getBudget().toString(),
                project.getPeriod().getStartDate().toString(),
                endDate != null ? endDate.toString() : null
        );

        List<ResourceInProject> resourcesInProject = project.getResources();

        List<ResourceInProjectJpa> resourcesInProjectJPA = resourcesInProject.stream()
                .map(resource -> new ResourceInProjectJpa(
                        resource.getResourceInProjectID().getResourceOfProjectID(),
                        resource.getEmail().getEmail(),
                        resource.getRole().getDescription(),
                        resource.getPeriod().getStartDate().toString(),
                        resource.getPeriod().getEndDate().toString(),
                        resource.getCostPerHour().getValue(),
                        resource.getCostPerHour().getCurrency(),
                        resource.getAllocation().getAllocation(),
                        projectJpa
                ))
                .collect(Collectors.toUnmodifiableList());

        projectJpa.setResourceInProjectJpas(resourcesInProjectJPA);

        return projectJpa;
    }

    public static Project toDomain(ProjectJPA projectJPA) {
        String budget = projectJPA.getBudget();
        double valueBudget = Double.parseDouble(budget.replaceAll("[^0-9.]", ""));
        String currencyBudget = budget.replaceAll("[^A-Za-z]", "");

        String projStartDate = projectJPA.getStartDate();
        String projEndDate = projectJPA.getEndDate();

        LocalDate startDate = DateManagement.toLocalDate(projStartDate);
        LocalDate endDate = DateManagement.toLocalDate(projEndDate);

        Project project = new Project(
                new ProjectCode(projectJPA.getProjectCode()),
                new Name(projectJPA.getProjectName()),
                new Description(projectJPA.getProjectDescription()),
                new CustomerID(projectJPA.getCustomerID()),
                new BusinessSectorID(projectJPA.getBusinessSectorID()),
                new TypologyID(projectJPA.getTypologyID()),
                new ProjectStatus(projectJPA.getProjectStatus()),
                new PositiveNumber(projectJPA.getSprintDuration()),
                new PositiveNumber(projectJPA.getNumberOfPlannedSprints()),
                new Cost(valueBudget, currencyBudget),
                new Period(startDate, endDate)
        );

        List<ResourceInProjectJpa> resourceInProjectJpaList = projectJPA.getResourceInProjectJpas();
        List<ResourceInProject> resourcesInProject = new ArrayList<>();

        for (ResourceInProjectJpa inProjectJpa : resourceInProjectJpaList) {
            resourcesInProject.add(ResourceInProjectJpaAssembler.toDomain(inProjectJpa));
        }

        project.setResources(resourcesInProject);

        return project;
    }
}
