package org.switch2022.project.model.sprint;

import org.switch2022.project.ddd.Entity;
import org.switch2022.project.model.value_objects.SprintBacklogID;
import org.switch2022.project.model.value_objects.UserStoryID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class SprintBacklog implements Entity<SprintBacklogID> {
    private final SprintBacklogID sprintBacklogID;
    private List<UserStoryID> userStories;


    public SprintBacklog(SprintBacklogID sprintBacklogID) {
        if (sprintBacklogID != null) {
            this.sprintBacklogID = sprintBacklogID;
        } else {
            throw new IllegalArgumentException("SprintBacklog can not be null.");
        }
        this.userStories = new ArrayList<>();
    }

    public SprintBacklogID getSprintBacklogID() {
        return sprintBacklogID;
    }

    private boolean validateUserStory(UserStoryID userStoryID) {
        return userStoryID != null && !userStories.contains(userStoryID);
    }

    protected boolean addUserStory(UserStoryID userStoryID) {
        boolean flag = false;

        if (validateUserStory(userStoryID)) {
            flag = userStories.add(userStoryID);
        }
        return flag;
    }

    public void setUserStoriesIDs(List<UserStoryID> userStoryIDS) {
        this.userStories = new ArrayList<>(userStoryIDS);
    }

    protected List<UserStoryID> getSprintBacklog() {
        return Collections.unmodifiableList(userStories);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        SprintBacklog sprintBacklog = (SprintBacklog) obj;

        return sameIDAs(sprintBacklog.getSprintBacklogID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintBacklogID);
    }

    @Override
    public SprintBacklogID identity() {
        return sprintBacklogID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        return object != null && object.equals(sprintBacklogID);
    }
}
