package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "sprints")
public class SprintJPA {
    @Id
    private String sprintID;
    private String projectCode;
    private int sprintNumber;
    private String startDate;
    private String endDate;
    private String sprintStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sprintJPA")
    private List<SprintUserStoryJpa> sprintUserStories;

    public SprintJPA(String sprintID, String projectCode,
                     int sprintNumber, String startDate, String endDate, String sprintStatus) {
        this.sprintID = sprintID;
        this.projectCode = projectCode;
        this.sprintNumber = sprintNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintUserStories = new ArrayList<>();
        this.sprintStatus = sprintStatus;
    }

    public String getSprintID() {
        return sprintID;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getSprintNumber() {
        return sprintNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(String sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    public List<SprintUserStoryJpa> getSprintUserStories() {
        return new ArrayList<>(sprintUserStories);
    }

    public void setSprintUserStories(List<SprintUserStoryJpa> sprintUserStories) {
        this.sprintUserStories = new ArrayList<>(sprintUserStories);
    }
}
