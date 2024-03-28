package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

/**
 * Represents a cost with a positive value and a specified currency, witch must have a non-null and non-empty currency.
 * The valid values for the currency are limited to the following options: EUR and USD.
 * If the input currency is invalid, or the value is not positive, the class throws an IllegalArgumentException.
 */
public final class Cost implements ValueObject<Cost> {
    public static final String EUR = "EUR";
    public static final String USD = "USD";
    private final double value;
    private final String currency;

    /**
     * Constructs a new Cost object with the specified value and currency.
     *
     * @param value    the cost value, must be a positive number.
     * @param currency the currency code
     * @throws IllegalArgumentException if the provided value is not positive.
     * @throws IllegalArgumentException if the provided currency is null.
     * @throws IllegalArgumentException if the provided currency is blank or empty.
     * @throws IllegalArgumentException if the currency is not EUR or USD.
     */
    public Cost(double value, String currency) {
        if (value <= 0) {
            throw new IllegalArgumentException("invalid cost value");
        }

        StringValidation.checkNull("currency", currency);

        StringValidation.checkBlank("currency", currency);

        if (!EUR.equals(currency) && !USD.equals(currency)) {
            throw new IllegalArgumentException("invalid currency");
        }

        this.value = value;
        this.currency = currency.toUpperCase();
    }

    /**
     * Returns the value of this Cost object.
     *
     * @return the value of this Cost object.
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the currency of this Cost object.
     *
     * @return the currency of this Cost object.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns a string representation of the object. In this case, the string
     * returned will include the cost value and currency.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {

        return value + currency;
    }

    /**
     * Compares the current Cost object with another Cost object to determine if they represent the same cost.
     * The comparison is based on the equality of their value and currency fields.
     *
     * @param other: The Cost object to compare with the current object.
     * @return true if the given Cost object is not null and has the same value and currency as the current Cost object.
     * false otherwise.
     */
    @Override
    public boolean sameAs(final Cost other) {
        return other != null
                && this.value == other.getValue()
                && this.currency.equals(other.getCurrency());
    }

    /**
     * Returns true if this Cost object is equal to the specified object, false otherwise.
     *
     * @param other the object to compare this Cost object to.
     * @return true if this Cost object is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Cost cost = (Cost) other;

        return sameAs(cost);
    }

    /**
     * Returns the hash code of this Cost object.
     *
     * @return the hash code of this Cost object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
