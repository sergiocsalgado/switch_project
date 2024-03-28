package org.switch2022.project.model.sprint;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.*;

import java.util.List;
import java.util.Objects;

public class Sprint implements AggregateRoot<SprintID> {
    private final ProjectCode projectCode;
    private final SprintID sprintID;
    private final SprintNumber sprintNumber;
    private final Period period;
    private final SprintBacklog sprintBacklog;
    private SprintStatus sprintStatus;

    public Sprint(ProjectCode projectCode, SprintID sprintID, SprintNumber sprintNumber,
                  Period period, SprintBacklog sprintBacklog, SprintStatus sprintStatus) {
        this.projectCode = projectCode;
        this.sprintID = sprintID;
        this.sprintNumber = sprintNumber;
        this.period = period;
        this.sprintBacklog = sprintBacklog;
        this.sprintStatus = sprintStatus;
    }

    public SprintID getSprintID() {
        return sprintID;
    }

    public SprintNumber getSprintNumber() {
        return sprintNumber;
    }

    public Period getPeriod() {
        return period;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public void setSprintBacklog(List<UserStoryID> userStoryIDS) {
        sprintBacklog.setUserStoriesIDs(userStoryIDS);
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    public boolean addUserStoryToSprintBacklog(UserStoryID userStoryID) {
        return sprintBacklog.addUserStory(userStoryID);
    }

    public List<UserStoryID> getScrumBoardList() {
        return sprintBacklog.getSprintBacklog();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Sprint sprint = (Sprint) o;

        return sameIDAs(sprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }

    @Override
    public SprintID identity() {
        return sprintID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        if (object instanceof Sprint) {
            Sprint sprint = (Sprint) object;

            return this.sprintID.equals(sprint.getSprintID())
                    && this.projectCode.equals(sprint.getProjectCode());
        }

        return false;
    }
}
