package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

/**
 *  Represents a unique identifier for a business sector.
 *  It provides a constructor that takes a code as a parameter and creates a new identifier by concatenating the code
 *  with a prefix "bs-".
 *  If the input code is null, the class throws an IllegalArgumentException.
 */
public final class BusinessSectorID implements EntityID {

    private final String id;

    /**
     * Constructs a new BusinessSectorID object that takes a String code as a parameter. It is constructed by
     * concatenating the prefix "bs-" and the code. If the code is null, it throws an IllegalArgumentException.
     *
     *  @param code: the code to be concatenated with the prefix "bs-" and form the id.
     */
    public BusinessSectorID(String code) {
        StringValidation.checkNull("Business Sector ID", code);

        StringValidation.checkBlank("Business Sector ID", code);

        this.id = code;
    }

    /**
     * Returns the id representation of this BusinessSectorID object.
     *
     * @return the id of this BusinessSectorID object.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a string representation of the BusinessSectorID object, including its id field.
     *
     *  @return a string representation of the object
     */
    @Override
    public String toString() {
        return "BusinessSectorID{" +
                "id='" + id + '\'' +
                '}';
    }

    /**
     *  Returns true if the given Object is not null and represents the same identifier as the BusinessSectorID object.
     *  It compares the id field of both objects for equality.
     *  If the given Object is not an instance of BusinessSectorID, it returns false.
     *
     * @param other The other value object.
     * @return true if the given object is not null and represents the same identifier as th BusinessSectorID object.
     * false otherwise.
     */
    @Override
    public boolean sameAs(final Object other) {
        return other != null
                && this.id.equals(((BusinessSectorID)other).getId());
    }

    /**
     * Returns true if this BusinessSectorID object is equal to the specified object, false otherwise.
     *
     * @param other the object to compare this BusinessSectorID object to.
     * @return true if this BusinessSectorID object is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        BusinessSectorID that = (BusinessSectorID) other;

        return sameAs(that);
    }

    /**
     * Returns the hash code of this BusinessSectorID object.
     *
     * @return the hash code of this BusinessSectorID object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
