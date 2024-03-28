package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

/**
 * Represents the ID of a typology.
 */
public final class TypologyID implements EntityID {

    private final String idOfTypology;

    /**
     * Constructs a TypologyID object with the given ID.
     *
     * @param code the ID of the typology
     * @throws IllegalArgumentException if the code is null or blank
     */
    public TypologyID(String code) {
        StringValidation.checkNull("Typology ID", code);

        StringValidation.checkBlank("Typology Sector ID", code);
        this.idOfTypology = code;
    }

    /**
     * Returns the ID of the typology.
     *
     * @return the ID of the typology
     */
    public String getIdOfTypology() {
        return idOfTypology;
    }

    /**
     * Returns a string representation of this TypologyID object.
     *
     * @return a string representation of this TypologyID object
     */
    public String toString() {
        return "TypologyID{" + "id='" + idOfTypology + '\'' + '}';
    }

    /**
     * Returns true if this TypologyID object is the same as the other object.
     *
     * @param other the other object to compare to
     * @return true if this TypologyID object is the same as the other object
     */
    @Override
    public boolean sameAs(final Object other) {
        return other != null && this.idOfTypology.equals(((TypologyID) other).getIdOfTypology());
    }

    /**
     * Returns true if this TypologyID object is equal to the other object.
     *
     * @param other the other object to compare to
     * @return true if this TypologyID object is equal to the other object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        TypologyID that = (TypologyID) other;
        return sameAs(that);
    }

    /**
     * Returns the hash code of this TypologyID object.
     *
     * @return the hash code of this TypologyID object
     */
    @Override
    public int hashCode() {
        return Objects.hash(idOfTypology);
    }
}