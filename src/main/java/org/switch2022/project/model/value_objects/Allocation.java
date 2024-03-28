package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

/**
 * Represents the percentage allocation of a resource.
 */
public final class Allocation implements ValueObject<Allocation> {
    private final double percentageAllocation;

    private static final int MAX_ALLOCATION=100;

    /**
     * Constructs an Allocation object with the given percentage allocation.
     *
     * @param percentageAllocation the percentage allocation of the resource
     * @throws IllegalArgumentException if the percentage allocation is not between 0 and 100
     */
    public Allocation(double percentageAllocation) {
        if (percentageAllocation <= 0 || percentageAllocation > MAX_ALLOCATION) {
            throw new IllegalArgumentException("Allocation value must be between 0 and 100");
        }
        this.percentageAllocation = percentageAllocation;
    }

    /**
     * Returns the percentage allocation of the resource.
     *
     * @return the percentage allocation of the resource
     */
    public double getAllocation() {
        return percentageAllocation;
    }

    /**
     * Returns true if this Allocation object is the same as the other Allocation object.
     *
     * @param other the other Allocation object to compare to
     * @return true if this Allocation object is the same as the other Allocation object
     */
    @Override
    public boolean sameAs(Allocation other) {
        return Double.compare(other.percentageAllocation, percentageAllocation) == 0;
    }

    /**
     * Returns true if this Allocation object is equal to the other object.
     *
     * @param o the other object to compare to
     * @return true if this Allocation object is equal to the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Allocation that = (Allocation) o;

        return sameAs(that);
    }

    /**
     * Returns the hash code of this Allocation object.
     *
     * @return the hash code of this Allocation object
     */
    @Override
    public int hashCode() {
        return Objects.hash(percentageAllocation);
    }

}
