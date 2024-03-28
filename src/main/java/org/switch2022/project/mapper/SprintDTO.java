package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a sprint,
 * containing information such as sprintID, sprintNumber, startDate, EndDate, project code.
 */
public class SprintDTO {
    private String projectCode;
    private String sprintID;
    private int sprintNumber;
    private String startDate;
    private String endDate;
    private String sprintStatus;

    /**
     * Returns the project code of SprintDTO.
     *
     * @return projectCode.
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * Set the projectCode of SprintDTO.
     *
     * @param projectCode the project code of the project of the sprint.
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * Returns the sprintID of SprintDTO.
     *
     * @return sprintID.
     */
    public String getSprintID() {
        return this.sprintID;
    }

    /**
     * Set the sprintID of SprintDTO.
     *
     * @param sprintID the ID of the sprint.
     */
    public void setSprintID(String sprintID) {
        this.sprintID = sprintID;
    }

    /**
     * Returns the sprintNumberof SprintDTO.
     *
     * @return sprintNumber.
     */
    public int getSprintNumber() {
        return this.sprintNumber;
    }

    /**
     * Set the sprintNumber of SprintDTO.
     *
     * @param sprintNumber the number of the sprint.
     */
    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    /**
     * Returns the startDate SprintDTO.
     *
     * @return startDate.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Set the StartDate of SprintDTO.
     *
     * @param startDate the start date of the sprint.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the endDate SprintDTO.
     *
     * @return endNumber.
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Set the endDate of SprintDTO.
     *
     * @param endDate the end date of the sprint.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the status of SprintDTO.
     *
     * @return sprintStatus.
     */
    public String getSprintStatus() {
        return sprintStatus;
    }

    /**
     * Set the status of SprintDTO.
     *
     * @param sprintStatus the status of the sprint.
     */
    public void setSprintStatus(String sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    /**
     * Compares two SprintDTO objects.
     *
     * @param o the object to compare with.
     * @return true if the sprintID, SprintNumber, startDate,endDate and ProjectCode fields are equal, false otherwise.
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
            return projectCode.equals(((SprintDTO) o).getProjectCode())
                    && startDate.equals(((SprintDTO) o).getStartDate())
                    && endDate.equals(((SprintDTO) o).getEndDate())
                    && sprintID.equals(((SprintDTO) o).getSprintID());
        }
        return false;
    }

    /**
     * Returns a hash code value for this SprintDTO object based on its sprintID, SprintNumber,
     * startDate,endDate and ProjectCode fields.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectCode, sprintID, sprintNumber, startDate, endDate);
    }
}
