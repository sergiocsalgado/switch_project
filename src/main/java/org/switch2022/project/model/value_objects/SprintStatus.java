package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;
import java.util.Set;

public final class SprintStatus implements ValueObject<SprintStatus> {
    private static final Set<String> status = Set.of("planned", "open", "closed");
    private final String description;

    public SprintStatus(String description) {
        StringValidation.checkNull("status", description);
        StringValidation.checkBlank("status" , description);

        if (!status.contains(description.trim().toLowerCase())) {
            throw new IllegalArgumentException("status is not valid");
        }

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return sameAs((SprintStatus) other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameAs(SprintStatus other) {
        return other != null
                && this.description.equals(other.getDescription());
    }
}
