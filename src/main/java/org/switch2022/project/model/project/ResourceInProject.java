package org.switch2022.project.model.project;

import org.switch2022.project.ddd.Entity;
import org.switch2022.project.model.value_objects.*;

import java.util.Objects;

/**
 * Class represents a resource assigned to a project with its account, role, period, cost per hour and allocation.
 */
public class ResourceInProject implements Entity<ResourceInProjectID> {
    private final ResourceInProjectID resourceInProjectID;
    private final Email email;
    private final Role role;
    private final Period period;
    private final Cost costPerHour;
    private final Allocation allocation;

    /**
     * Constructs a new ResourceInProject object with the specified account, role, period, cost.
     * per hour and allocation.
     *
     * @param resourceInProjectID the account associated with this resource in the project.
     * @param email               the account associated with this resource in the project.
     * @param role                the role of this resource in the project.
     * @param period              the period for which this resource is allocated in the project.
     * @param costPerHour         the cost per hour of this resource in the project.
     * @param allocation          the allocation of this resource in the project.
     */
    public ResourceInProject(ResourceInProjectID resourceInProjectID, Email email, Role role,
                             Period period, Cost costPerHour, Allocation allocation) {
        if (isValid(resourceInProjectID, email, role, period, costPerHour, allocation)) {
            this.resourceInProjectID = resourceInProjectID;
            this.email = email;
            this.role = role;
            this.period = period;
            this.costPerHour = costPerHour;
            this.allocation = allocation;
        } else {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
    }

    private static boolean isValid(ResourceInProjectID resourceInProjectID, Email email, Role role,
                            Period period, Cost costPerHour, Allocation allocation) {
        return resourceInProjectID != null
                && email != null
                && role != null
                && period != null
                && costPerHour != null
                && allocation != null;
    }

    /**
     * Returns a copy of the resource in project id associated with this resource in the project.
     *
     * @return a copy of the resource in project id object.
     */
    public ResourceInProjectID getResourceInProjectID() {
        return resourceInProjectID;
    }

    /**
     * Returns a copy of the account associated with this resource in the project.
     *
     * @return a copy of the account object.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns a copy of the role of this resource in the project.
     *
     * @return a copy of the role object.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Returns a copy of the period for which this resource is allocated in the project.
     *
     * @return a copy of the period object.
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * Returns the cost per hour of this resource in the project.
     *
     * @return the cost per hour.
     */
    public Cost getCostPerHour() {
        return costPerHour;
    }

    /**
     * Returns the allocation of this resource in the project.
     *
     * @return the allocation.
     */
    public Allocation getAllocation() {
        return allocation;
    }

    /**
     * Returns a copy of the ResourceInProject object.
     *
     * @return a new ResourceInProject object with the same attributes as the original.
     */
    protected ResourceInProject copy() {
        return new ResourceInProject(resourceInProjectID, email, role, period,
                costPerHour, allocation);
    }

    /**
     * Overrides the default equals method to compare two ResourceInProject objects
     * based on their account, role and period.
     *
     * @param obj the object to be compared with the calling ResourceInProject object.
     * @return true if the two ResourceInProject objects have the same account, role and.
     * period, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ResourceInProject resource = (ResourceInProject) obj;

        return sameIDAs(resource.getResourceInProjectID());
    }

    /**
     * Overrides the default hashCode method to generate a hash code
     * based on the account, role and period of the calling ResourceInProject object.
     *
     * @return a hash code generated using the account, role and period of the calling ResourceInProject object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(resourceInProjectID);
    }

    @Override
    public ResourceInProjectID identity() {
        return resourceInProjectID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        return object != null && object.equals(resourceInProjectID);
    }
}




