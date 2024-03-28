package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

/**
 * Represents the number of a sprint.
 */
public final class SprintNumber implements ValueObject<SprintNumber> {

    private final int number;

    /**
     * Constructs a SprintNumber object with the given number.
     *
     * @param number the number of the sprint
     * @throws IllegalArgumentException if the number is below 0
     */
    public SprintNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Sprint Number cannot be below 0");
        }
        this.number = number;
    }

    /**
     * Returns the number of the sprint.
     *
     * @return the number of the sprint
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns true if this SprintNumber object is equal to the other object.
     *
     * @param o the other object to compare to
     * @return true if this SprintNumber object is equal to the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SprintNumber)) {
            return false;
        }
        SprintNumber that = (SprintNumber) o;

        return sameAs(that);
    }

    /**
     * Returns the hash code of this SprintNumber object.
     *
     * @return the hash code of this SprintNumber object
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    /**
     * Returns true if this SprintNumber object is the same as the other SprintNumber object.
     *
     * @param other the other SprintNumber object to compare to
     * @return true if this SprintNumber object is the same as the other SprintNumber object
     */
    @Override
    public boolean sameAs(SprintNumber other) {
        return other != null
                && this.number == other.getNumber();

    }
}