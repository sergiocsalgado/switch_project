package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a project,
 * containing information such as project code, project name, project description,
 * customer, businessSector, typology, project startDate, project endDate,
 * project status, project sprintDuration, project numberOfPlannedSprints,
 * project budget and project budget currency.
 */
public class ProjectDTO {

    private String projectCode;
    private String name;
    private String description;
    private String customer;
    private String businessSector;
    private String typology;
    private String startDate;
    private String endDate;
    private String status;
    private int sprintDuration;
    private int numberOfPlannedSprints;
    private String budget;

    /**
     * Returns the code of the project.
     *
     * @return the code.
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Set the code of the project.
     *
     * @param projectCode the project code of the project.
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * Returns the name of the project.
     *
     * @return the project name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the project.
     *
     * @param name the name of the project.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the project.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the project.
     *
     * @param description the description of the project.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the customer of the project.
     *
     * @return the customer.
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Set the customer of the project.
     *
     * @param customer the customer of the project.
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Returns the businessSectorID of the project.
     *
     * @return the businessSector.
     */
    public String getBusinessSector() {
        return businessSector;
    }

    /**
     * Set the businessSectorID of the project.
     *
     * @param businessSector the business sector of the project.
     */
    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    /**
     * Returns the typology of the project.
     *
     * @return the typology.
     */
    public String getTypology() {
        return typology;
    }

    /**
     * Set the typology of the project.
     *
     * @param typology the typology of the project.
     */
    public void setTypology(String typology) {
        this.typology = typology;
    }

    /**
     * Returns the start date of the project.
     *
     * @return the startDate.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the project.
     *
     * @param startDate the start date of the project.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the project.
     *
     * @return the endDate.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of the project.
     *
     * @param endDate the end date of the project.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the status of the project.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the project.
     *
     * @param status the status of the project.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the sprint duration of the project.
     *
     * @return the sprintDuration.
     */
    public int getSprintDuration() {
        return sprintDuration;
    }

    /**
     * Set the sprint duration of the project.
     *
     * @param sprintDuration the sprint duration of the project.
     */
    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    /**
     * Returns the number of planned sprints of the project.
     *
     * @return the numberOfPlannedSprints.
     */
    public int getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    /**
     * Set the number of planned sprints of the project.
     *
     * @param numberOfPlannedSprints the number of planned sprints of the project.
     */
    public void setNumberOfPlannedSprints(int numberOfPlannedSprints) {
        this.numberOfPlannedSprints = numberOfPlannedSprints;
    }

    /**
     * Returns the budget of the project.
     *
     * @return the budget.
     */
    public String getBudget() {
        return budget;
    }

    /**
     * Set the budget of the project.
     *
     * @param budget the budget of the project.
     */
    public void setBudget(String budget) {
        this.budget = budget;
    }


    /**
     * Compares two ProjectDTO objects.
     *
     * @param other the object to compare with.
     * @return true if the code and name fields are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ProjectDTO projectDTO = (ProjectDTO) other;

        return projectCode.equals(projectDTO.getProjectCode())
                && name.equals(projectDTO.getName())
                && description.equals(projectDTO.getDescription())
                && customer.equals(projectDTO.getCustomer())
                && businessSector.equals(projectDTO.getBusinessSector())
                && typology.equals(projectDTO.getTypology())
                && startDate.equals(projectDTO.getStartDate())
                && endDate.equals(projectDTO.getEndDate())
                && status.equals(projectDTO.getStatus())
                && sprintDuration==projectDTO.getSprintDuration()
                && numberOfPlannedSprints==projectDTO.getNumberOfPlannedSprints()
                && budget.equals(projectDTO.getBudget());
    }

    /**
     * Returns a hash code value for this ProjectDTO object based on its code and name fields.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectCode, name, description, customer, businessSector, typology,
                startDate, endDate, status, sprintDuration, numberOfPlannedSprints, budget);
    }
}
