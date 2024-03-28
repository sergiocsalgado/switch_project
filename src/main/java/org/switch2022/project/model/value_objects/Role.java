package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class Role implements ValueObject<Role> {
    private final String description;

    public Role(String description) {
        StringValidation.checkNull("Role", description);

        StringValidation.checkBlank("Role", description);
        if (!isValidRole(description)) {
            throw new IllegalArgumentException("Role is not valid, see valid roles for more information");
        }
        this.description = description.toLowerCase();
    }

    private static boolean isValidRole(String description) {
        switch (description.toLowerCase()) {
            case "project manager":
            case "product owner":
            case "scrum master":
            case "team member":
                return true;
            default:
                return false;
        }
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean sameAs(Role other) {
        return other != null
                && this.description.equals(other.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Role role = (Role) other;

        return sameAs(role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}