package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;
import java.util.Objects;

/**
 * Represents a phone number value object.
 * The phone number should have nine digits and can only contain numbers.
 * It should not start with 6 or 9.
 */
public final class PhoneNumber implements ValueObject<PhoneNumber> {
    private final String number;

    /**
     * Constructs a PhoneNumber object.
     *
     * @param number the phone number string
     * @throws IllegalArgumentException if the provided number is null, blank, doesn't have nine digits,
     *                                  contains non-digit characters, or starts with 6 or 9.
     */
    public PhoneNumber(String number) {
        StringValidation.checkNull("Phone Number", number);
        StringValidation.checkBlank("Phone Number", number);

        if (number.length() != 9) {
            throw new IllegalArgumentException("The phone number should have nine digits");
        }

        if (!number.matches("[0-9]+")) {
            throw new IllegalArgumentException("The phone number can only contain numbers");
        }

        if (number.matches("^[13456780]\\d{8}$")) {
            throw new IllegalArgumentException("The phone number can only start with a 6 or a 9");
        }
        this.number = number;
    }

    /**
     * Returns the phone number string.
     *
     * @return the phone number string
     */
    public String getPhoneNumber() {
        return number;
    }

    /**
     * Checks if this phone number is the same as the specified other phone number.
     *
     * @param other the other phone number to compare with
     * @return true if the phone numbers are the same, false otherwise
     */
    @Override
    public boolean sameAs(PhoneNumber other) {
        return other != null && this.number.equals(other.getPhoneNumber());
    }

    /**
     * Compares this phone number with the specified object for equality.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber phoneNumber = (PhoneNumber) o;
        return sameAs(phoneNumber);
    }

    /**
     * Returns the hash code value for this phone number.
     *
     * @return the hash code value for this phone number
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
