package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

/**
 * stands for name for various uses.
 */
public final class
Name implements ValueObject<Name> {
    private final String value;

    /**
     * Constructs a new Name object.
     *
     * @param value o valor do nome, deve ser conter String positiva.
     * @throws IllegalArgumentException if the value is not positive or is null.
     */

    public Name(String value) {
        StringValidation.checkNull("Name", value);

        StringValidation.checkBlank("Name", value);
        this.value = value;
    }

    /**
     * Returns the value of this Name object.
     *
     * @return the value of this Name object.
     */
    public String getValue() {
        return value;
    }

    @Override
    public boolean sameAs(Name other) {
        return Objects.equals(this.value, other.getValue());
    }

    /**
     * Returns true if this Name object is equal to the specified object, false otherwise.
     *
     * @param other the object to compare this Name object to.
     * @return true if this Name object is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Name name = (Name) other;

        return sameAs(name);
    }

    /**
     * Returns a string representation of the object. In this case, the string
     * returned will include the name value.
     *
     * @return a string representation of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}
