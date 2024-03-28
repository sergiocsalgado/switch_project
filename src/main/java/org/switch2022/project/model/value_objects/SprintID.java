package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class SprintID implements EntityID {

    private final String code;

    public SprintID(String sprintID) {
        StringValidation.checkNull("Sprint ID", sprintID);

        StringValidation.checkBlank("Sprint ID", sprintID);
        this.code = sprintID;
    }

    public String getSprintID() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SprintID sprintID = (SprintID) o;
        return sameAs(sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.code.equals(((SprintID) other).getSprintID());
    }
}