package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class UserStoryID implements EntityID {

    private final String idUserStory;

    public UserStoryID(String idUserStory) {
        StringValidation.checkNull("User Story ID", idUserStory);

        StringValidation.checkBlank("User Story ID", idUserStory);

        if (!idUserStory.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("User story ID cannot contain special characters");
        }
        this.idUserStory = idUserStory;
    }

    public String getUserStoryID() {
        return idUserStory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStoryID)) {
            return false;
        }
        UserStoryID that = (UserStoryID) o;

        return sameAs(that);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.idUserStory.equals(((UserStoryID) other).getUserStoryID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStory);
    }


}