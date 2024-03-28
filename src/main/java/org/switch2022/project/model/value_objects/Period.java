package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public final class Period implements ValueObject<Period> {

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new instance of the {@code Period} class with the specified start and end dates.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @throws IllegalArgumentException if the start date or end date is null,
     *                                  if the start date is not before the end date,
     *                                  or if the start date is not before the current date
     */
    public Period(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (endDate != null && !startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("End date must be after the start date");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start date of this period.
     *
     * @return the start date of this period
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of this period.
     *
     * @return the end date of this period
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Determines whether this period is the same as another period.
     *
     * @param other the other period to compare with
     * @return {@code true} if the two periods have the same start and end dates; {@code false} otherwise
     */
    @Override
    public boolean sameAs(Period other) {
        return other != null
                && this.startDate.equals(other.getStartDate())
                && this.endDate.equals(other.getEndDate());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the {@code o} argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Period period = (Period) o;
        return sameAs(period);
    }

    /**
     * This method is used to generate the hash code value based on the values of startDate, endDate,
     * and createDate instance variables.
     *
     * @return a hash code value based on the values of those arguments
     */
    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
