package org.switch2022.project.model.user_story;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.*;

import java.util.Objects;

public class UserStory implements AggregateRoot<UserStoryID> {
    private final UserStoryID userStoryID;
    private final ProjectCode projectCode;
    private final UserStoryNumber userStoryNumber;
    private final Name actor;
    private final Description userStoryText;
    private UserStoryStatus status;
    private Priority priority;
    private Effort effort;

    public UserStory(UserStoryID userStoryID, ProjectCode projectCode,
                     UserStoryNumber userStoryNumber, Name actor, Description userStoryText,
                     UserStoryStatus status, Priority priority) {

        if (projectCode == null) {
            throw new IllegalArgumentException("Project Code can't be null");
        }
        this.projectCode = projectCode;

        if (userStoryID == null) {
            throw new IllegalArgumentException("User Story ID can't be null");
        }
        this.userStoryID = userStoryID;

        if(userStoryNumber == null) {
            throw new IllegalArgumentException("User Story Number can't be null");
        }
        this.userStoryNumber = userStoryNumber;

        if (actor == null) {
            throw new IllegalArgumentException("Actor can't be null");
        }
        this.actor = actor;

        if (userStoryText == null) {
            throw new IllegalArgumentException("User Story Text can't be null");
        }
        this.userStoryText = userStoryText;

        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        this.status = status;

        if (priority == null) {
            throw new IllegalArgumentException("Priority can't be null");
        }
        this.priority = priority;

    }

    public UserStory(UserStoryID userStoryID, ProjectCode projectCode,
                     UserStoryNumber userStoryNumber,
                     Name actor, Description userStoryText,
                     UserStoryStatus status, Priority priority, Effort effort) {
        this(userStoryID, projectCode, userStoryNumber, actor, userStoryText, status, priority);

        if (effort == null) {
            throw new IllegalArgumentException("Effort can't be null");
        }
        this.effort = effort;
    }

    public UserStoryID getUserStoryID() {
        return userStoryID;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public UserStoryNumber getUserStoryNumber() {
        return userStoryNumber;
    }

    public Name getActor() {
        return actor;
    }

    public Description getUserStoryText() {
        return userStoryText;
    }

    public UserStoryStatus getStatus() {
        return status;
    }

    public void setStatus(UserStoryStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setEffort(Effort effort) {
        this.effort = effort;
    }

    public Effort getEffort() {
        return effort;
    }

    public UserStory copy() {
        return new UserStory(this.userStoryID,this.projectCode, this.userStoryNumber,
                this.actor, this.userStoryText, this.status, this.priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStory userStory = (UserStory) o;

        return sameIDAs(userStory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, userStoryID);
    }

    @Override
    public UserStoryID identity() {
        return userStoryID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        if (object instanceof UserStory) {
            UserStory userStory = (UserStory) object;
            return this.projectCode.equals(userStory.getProjectCode())
                    && this.userStoryID.equals(userStory.getUserStoryID());
        }
        return false;
    }
}
