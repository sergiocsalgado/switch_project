package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a resourceInProject,
 * containing information such as project email, name, description.
 */

public class ResourcesInProjectDTO {

    private String projectCode;
    private String resourceInProjectID;
    private String email;
    private String role;
    private Double allocation;
    private Double costPerHour;
    private String currency;
    private String startDate;
    private String endDate;

    /**
     * Returns the resourceInProjectID of a resourceInProject.
     *
     * @return resourceInProjectID.
     */
    public String getResourceInProjectID() {
        return resourceInProjectID;
    }

    /**
     * Set the resourceInProjectID of a resourceInProject.
     *
     * @param resourceInProjectID the resource ID.
     */
    public void setResourceInProjectID(String resourceInProjectID) {

        this.resourceInProjectID = resourceInProjectID;
    }

    /**
     * Returns the project code.
     *
     * @return the project code.
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Set the project code.
     *
     * @param projectCode the project code to set.
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * Returns the email of a resourceInProject.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of a resourceInProject.
     *
     * @param email the email of the resource.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Returns the role of a resourceInProject.
     *
     * @return the role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the role of a resourceInProject.
     *
     * @param role the role to associate to the resource.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the allocation of a resourceInProject.
     *
     * @return the allocation.
     */
    public Double getAllocation() {
        return allocation;
    }

    /**
     * Set the allocation of a resourceInProject.
     *
     * @param allocation the allocation to set.
     */
    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }

    /**
     * Returns the cost per hour of a resourceInProject.
     *
     * @return the cost per hour.
     */
    public Double getCostPerHour() {
        return costPerHour;
    }

    /**
     * Set the cost per hour of a resourceInProject.
     *
     * @param costPerHour the cost per hour to set.
     */
    public void setCostPerHour(Double costPerHour) {
        this.costPerHour = costPerHour;
    }

    /**
     * Returns the currency of a resourceInProject.
     *
     * @return the currency.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Set the currency of a resourceInProject.
     *
     * @param currency the currency to set.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Returns the start date of a resourceInProject.
     *
     * @return the start date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of a resourceInProject.
     *
     * @param startDate the start date to set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of a resourceInProject.
     *
     * @return the end date.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of a resourceInProject.
     *
     * @param endDate the end date to set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Compares two ResourceInProjectDTO objects.
     *
     * @param o the object to compare with.
     * @return true if the email, name and description fields are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() == o.getClass()) {
            return email.equals(((ResourcesInProjectDTO) o).getEmail())
                    && role.equals(((ResourcesInProjectDTO) o).getRole())
                    && resourceInProjectID.equals(((ResourcesInProjectDTO) o).getResourceInProjectID());
        }
        return false;
    }

    /**
     * Returns a hash code value for this ResourceInProjectDTO object based on its email.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, resourceInProjectID, role);
    }
}
