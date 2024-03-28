package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectJPA {

    @Id
    private String projectCode;
    private String projectName;
    private String projectDescription;
    private String customerID;
    private String businessSectorID;
    private String typologyID;
    private String projectStatus;
    private int sprintDuration;
    private int numberOfPlannedSprints;
    private String budget;
    private String startDate;
    private String endDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projectJpa")
    private List<ResourceInProjectJpa> resourceInProjectJpas;

    public ProjectJPA(String projectCode, String projectName, String projectDescription, String customerID,
                      String businessSectorID, String typologyID, String projectStatus, int sprintDuration,
                      int numberOfPlannedSprints, String budget, String startDate, String endDate) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.customerID = customerID;
        this.businessSectorID = businessSectorID;
        this.typologyID = typologyID;
        this.projectStatus = projectStatus;
        this.sprintDuration = sprintDuration;
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.resourceInProjectJpas = new ArrayList<>();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getBusinessSectorID() {
        return businessSectorID;
    }

    public String getTypologyID() {
        return typologyID;
    }


    public String getProjectStatus() {
        return projectStatus;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    public int getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    public String getBudget() {
        return budget;
    }
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<ResourceInProjectJpa> getResourceInProjectJpas() {
        return new ArrayList<>(resourceInProjectJpas);
    }

    public void setResourceInProjectJpas(List<ResourceInProjectJpa> resourceInProjectJpas) {
        this.resourceInProjectJpas = new ArrayList<>(resourceInProjectJpas);
    }
}
