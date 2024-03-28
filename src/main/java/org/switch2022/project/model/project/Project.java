package org.switch2022.project.model.project;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.value_objects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The `Project` class represents a project within the system.
 * It implements the `AggregateRoot` interface and serves as an
 * aggregate root entity in the domain-driven design (DDD) architecture.
 * It encapsulates the project's information and provides various operations and methods to interact with the project.
 *
 * <p>This class provides attributes to store the project's code, name, description, customer ID, business sector ID,
 * typology ID, project status, sprint duration, number of planned sprints, budget, period, and resources.
 * It also offers methods to retrieve and modify these attributes, as well as perform operations related to resources
 * in the project such as checking if a resource is in the project, retrieving resource allocations, adding resources,
 * checking for role existence, and obtaining a list of resource data transfer objects (DTOs) for a given date.
 * The class also overrides methods for equality, hashing, and identity.
 */

public class Project implements AggregateRoot<ProjectCode> {
    private final ProjectCode projectCode;
    private final Name projectName;
    private final Description projectDescription;
    private final CustomerID customerID;
    private final BusinessSectorID businessSectorID;
    private final TypologyID typologyID;
    private final ProjectStatus projectStatus;
    private final PositiveNumber sprintDuration;
    private final PositiveNumber numberOfPlannedSprints;
    private final Cost budget;
    private Period period;
    private List<ResourceInProject> resources;

    /**
     * Constructs a new `Project` instance with the specified parameters.
     *
     * @param projectCode           The code assigned to the project.
     * @param projectName           The name of the project.
     * @param projectDescription    The description of the project.
     * @param customerID            The ID of the customer associated with the project.
     * @param businessSectorID      The ID of the business sector associated with the project.
     * @param typologyID            The ID of the typology associated with the project.
     * @param projectStatus         The status of the project.
     * @param sprintDuration        The duration of each sprint in the project.
     * @param numberOfPlannedSprints The number of planned sprints for the project.
     * @param budget                The budget allocated for the project.
     * @param period                The period during which the project takes place.
     */
    public Project(ProjectCode projectCode, Name projectName, Description projectDescription, CustomerID customerID,
                   BusinessSectorID businessSectorID, TypologyID typologyID, ProjectStatus projectStatus,
                   PositiveNumber sprintDuration, PositiveNumber numberOfPlannedSprints, Cost budget, Period period) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.customerID = customerID;
        this.businessSectorID = businessSectorID;
        this.typologyID = typologyID;
        this.projectStatus = projectStatus;
        this.resources = new ArrayList<>();
        this.sprintDuration = sprintDuration;
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        this.budget = budget;
        this.period = period;
    }



    private static boolean validResourceInProject(ResourceInProject resourceInProject) {
        return resourceInProject != null;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public Name getProjectName() {
        return projectName;
    }

    public Description getProjectDescription() {
        return projectDescription;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public BusinessSectorID getBusinessSectorID() {
        return businessSectorID;
    }

    public TypologyID getTypologyID() {
        return typologyID;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public PositiveNumber getSprintDuration() {
        return sprintDuration;
    }

    public PositiveNumber getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    public Cost getBudget() {
        return budget;
    }

    /**
     * Checks if a resource with the given email and date is associated with the project.
     *
     * @param email The email of the resource.
     * @param date  The date to check against the resource's period.
     * @return `true` if the resource is in the project at the given date, `false` otherwise.
     */

    public boolean resourceIsInProject(Email email, LocalDate date) {
        boolean flag = false;
        for (ResourceInProject resource : resources) {
            if (resource.getEmail().equals(email)) {
                LocalDate resourceStartDate = resource.getPeriod().getStartDate();
                LocalDate resourceEndDate = resource.getPeriod().getEndDate();
                if (resourceStartDate.isBefore(date) && resourceEndDate.isAfter(date)) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * Retrieves the allocation of a resource with the given email starting from the specified date.
     *
     * @param email     The email of the resource.
     * @param startDate The starting date from which to retrieve the allocation.
     * @return The allocation value of the resource starting from the given date.
     */

    public double getResourceInProjectAllocation(Email email, LocalDate startDate) {

        double allocation = 0.0;
        for (ResourceInProject resource : resources) {
            LocalDate resourceEndDate = resource.getPeriod().getEndDate();
            if (resource.getEmail().equals(email) && resourceEndDate.isAfter(startDate)) {
                allocation = resource.getAllocation().getAllocation();
            }
        }
        return allocation;
    }

    /**
     * Adds a new resource to the project.
     *
     * @param resourceInProject The resource to add to the project.
     * @return `true` if the resource was successfully added, `false` otherwise.
     */

    public boolean addResourceInProject(ResourceInProject resourceInProject) {
        boolean flag = false;

        if (!resources.contains(resourceInProject) && validResourceInProject(resourceInProject)) {
            resources.add(resourceInProject);
            flag = true;
        }

        return flag;
    }

    /**
     * Checks if a role exists in the project starting from the specified date.
     *
     * @param role      The role to check for existence.
     * @param startDate The starting date from which to check for role existence.
     * @return `true` if the role does not exist in the project from the given date, `false` otherwise.
     */

    public boolean roleDoesNotExistsInProject(Role role, LocalDate startDate) {

        boolean flag = true;
        for (ResourceInProject resource : resources) {
            LocalDate resourceInProjectEndDate = resource.getPeriod().getEndDate();
            if (resource.getRole().equals(role) && resourceInProjectEndDate.isAfter(startDate)) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Retrieves a list of resource data transfer objects (DTOs) for the project at the given date.
     *
     * @param date The date for which to retrieve the resource DTOs.
     * @return A list of resource DTOs for the project at the given date.
     */

    public List<ResourcesInProjectDTO> getResourcesInProjectDTO(LocalDate date) {

        List<ResourceInProject> resourcesInProject = new ArrayList<>();

        for (ResourceInProject resource : resources) {
            if (resource.getPeriod().getStartDate().isBefore(date) &&
                    resource.getPeriod().getEndDate().isAfter(date)) {
                resourcesInProject.add(resource);
            }
        }

        return ResourcesInProjectMapper.toResourcesDTOList(resourcesInProject,projectCode);
    }

    /**
     * Retrieves an unmodifiable list of resources associated with the project.
     *
     * @return An unmodifiable list of resources in the project.
     */
    public List<ResourceInProject> getResources() {
        return Collections.unmodifiableList(resources);
    }

    /**
     * Sets the resources associated with the project.
     *
     * @param newResources The new list of resources for the project.
     */

    public void setResources(List<ResourceInProject> newResources) {
        this.resources = new ArrayList<>(newResources);
    }

    public Project copy() {
        return new Project(this.projectCode, this.projectName, this.projectDescription, this.customerID,
                this.businessSectorID, this.typologyID, this.projectStatus, this.sprintDuration,
                this.numberOfPlannedSprints, this.budget, this.period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;

        return sameIDAs(project.getProjectCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, projectName);
    }

    @Override
    public ProjectCode identity() {
        return projectCode;
    }

    @Override
    public boolean sameIDAs(Object object) {
        return object != null && object.equals(projectCode);
    }
}

