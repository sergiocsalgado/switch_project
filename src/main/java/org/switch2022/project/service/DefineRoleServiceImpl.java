package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.FactoryResourceInProject;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.DefineRoleService;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DefineRoleServiceImpl implements DefineRoleService {

    private static final String TEAM_MEMBER = "team member";
    private final Repository<ProjectCode, Project> projectRepository;
    private final FactoryResourceInProject factoryResourceInProject;

    public DefineRoleServiceImpl(
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository,
            FactoryResourceInProject factoryResourceInProject) {
        this.projectRepository = projectRepository;
        this.factoryResourceInProject = factoryResourceInProject;
    }

    public ResourceInProject defineRole(String projectCode, String resourceInProjectID, String email,
                                        String role, Double allocation, Double costPerHour, String currency,
                                        String startDate, String endDate) {

        ProjectCode projCode;
        ResourceInProjectID resourceInProjID;
        Email emailAddress;
        Role roleDescription;
        Allocation percentageAllocation;
        Cost costHour;
        Period period;

        LocalDate roleStartDate;
        LocalDate roleEndDate;

        try {
            projCode = new ProjectCode(projectCode);
            resourceInProjID = new ResourceInProjectID(resourceInProjectID);
            emailAddress = new Email(email);
            roleDescription = new Role(role);
            percentageAllocation = new Allocation(allocation);
            costHour = new Cost(costPerHour, currency);

            roleStartDate = DateManagement.toLocalDate(startDate);
            roleEndDate = DateManagement.toLocalDate(endDate);

            period = new Period(roleStartDate, roleEndDate);

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Project> optional = getProjectByCode(projCode);

        if (optional.isPresent()) {
            Project project = optional.get();

            if (doesNotExceedPercentageAllocation(emailAddress, allocation, roleStartDate)
                    && validPeriodOfAllocationRelativelyToProjectPeriod(project, roleStartDate, roleEndDate)
                    && (isTeamMember(role) || roleDoesNotExistsInProject(project, roleDescription, roleStartDate))
                    && resourceNotInProject(project, roleStartDate, emailAddress)) {

                ResourceInProject resourceInProject =
                        factoryResourceInProject.create(resourceInProjID, emailAddress,
                                roleDescription, period, costHour, percentageAllocation);

                if (project.addResourceInProject(resourceInProject)) {
                    projectRepository.save(project);
                    return resourceInProject;
                }
            }
            throw new IllegalArgumentException("Resource in Project not created");

        } else {
            throw new IllegalStateException("Project does not exists");
        }
    }

    private Optional<Project> getProjectByCode(ProjectCode projectCode) {
        return projectRepository.ofIdentity(projectCode);
    }

    private boolean doesNotExceedPercentageAllocation(Email email,
                                                      double allocation,
                                                      LocalDate startDate) {
        double currentAllocation = 0.0;
        final int MAX_ALLOCATION = 100;

        List<Project> projects = projectRepository.findAll();

        for (Project project : projects) {
            currentAllocation += project.getResourceInProjectAllocation(email, startDate);
        }

        return allocation + currentAllocation <= MAX_ALLOCATION;
    }

    private static boolean validPeriodOfAllocationRelativelyToProjectPeriod(Project project,
                                                                            LocalDate roleStartDate,
                                                                            LocalDate roleEndDate) {
        return project.getPeriod().getStartDate().isBefore(roleStartDate)
                && (project.getPeriod().getEndDate() == null
                || project.getPeriod().getEndDate().isAfter(roleEndDate));
    }

    private static boolean isTeamMember(String roleDescription) {
        return TEAM_MEMBER.equals(roleDescription);
    }

    private static boolean roleDoesNotExistsInProject(Project project,
                                                      Role role,
                                                      LocalDate startDate) {

        return project.roleDoesNotExistsInProject(role, startDate);
    }

    private static boolean resourceNotInProject(Project project,
                                                LocalDate startDate,
                                                Email email) {

        return !project.resourceIsInProject(email, startDate);
    }
}
