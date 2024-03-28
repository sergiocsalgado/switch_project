package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class SprintBacklogID implements EntityID {
    private final String idDescription;


    public SprintBacklogID(String idDescription) {
        StringValidation.checkNull("SprintBacklog ID", idDescription);

        StringValidation.checkBlank("SprintBacklog ID", idDescription);
        this.idDescription = idDescription;
    }

    public String getIdDescription() {
        return idDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        SprintBacklogID sprtBacklogId = (SprintBacklogID) o;
        return sameAs(sprtBacklogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDescription);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.idDescription.equals(((SprintBacklogID) other).getIdDescription());
    }

}