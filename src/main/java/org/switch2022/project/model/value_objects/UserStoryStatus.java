package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;
import java.util.Set;

/**
 * Represents the status of a user story, which must have a non-null and non-empty description.
 * The valid values for the status are limited to the following options: planned, running, finished, or blocked.
 * If an invalid input string is provided, the class will throw an IllegalArgumentException.
 */
public final class UserStoryStatus implements ValueObject<UserStoryStatus> {
    private static final Set<String> status = Set.of("planned", "running", "finished", "blocked");
    private final String description;

    /**
     * Constructs a new UserStoryStatus object with the specified description.
     *
     * @param description a valid description for the UserStoryStatus object.
     * @throws IllegalArgumentException if the provided description is null.
     * @throws IllegalArgumentException if the provided description is blank or empty.
     * @throws IllegalArgumentException if the description is not planned, running, finished or blocked.
     */
    public UserStoryStatus(String description) {
        StringValidation.checkNull("description", description);

        StringValidation.checkBlank("description", description);

        if (!status.contains(description.trim().toLowerCase())) {
            throw new IllegalArgumentException("description is not valid");
        }

        this.description = description.toLowerCase();
    }

    /**
     * Returns the description of this UserStoryStatus object.
     *
     * @return the description of this UserStoryStatus object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if other UserStoryStatus object not null and is description equals this description,
     * false otherwise.
     *
     * @param other the object to compare this UserStoryStatus object to.
     * @return true if this UserStoryStatus object is not null and is description equals this description,
     * false otherwise.
     */
    @Override
    public boolean sameAs(UserStoryStatus other) {
        return other != null
                && this.description.equals(other.getDescription());
    }

    /**
     * Returns true if this UserStoryStatus object is equal to the specified object, false otherwise.
     *
     * @param other the object to compare this UserStoryStatus object to.
     * @return true if this UserStoryStatus object is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        return sameAs((UserStoryStatus) other);
    }

    /**
     * Returns the hash code of this UserStoryStatus object.
     *
     * @return the hash code of this UserStoryStatus object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

}

