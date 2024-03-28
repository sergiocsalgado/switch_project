package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for typology,
 * containing information such as typologyID and typologyDescription.
 */

public class TypologyDTO {

    private String typologyID;

    private String typologyDescription;

    /**
     * Returns the typology ID of this TypologyDTO object.
     *
     * @return the typology ID
     */
    public String getTypologyID() {
        return this.typologyID;
    }

    /**
     * Set the typology ID for this TypologyDTO object.
     *
     * @param typologyID the typology ID to set
     */
    public void setTypologyID(String typologyID) {
        this.typologyID = typologyID;
    }

    /**
     * Returns the typology description of this TypologyDTO object.
     *
     * @return the typology description
     */
    public String getTypologyDescription() {
        return this.typologyDescription;
    }

    /**
     * Set the typology description for this TypologyDTO object.
     *
     * @param typologyDescription the typology description to set
     */
    public void setTypologyDescription(String typologyDescription) {
        this.typologyDescription = typologyDescription;
    }

    /**
     * Compares two TypologyDTO objects.
     *
     * @param o the object to compare with.
     * @return true if the typologyID and typologyDescription fields are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() == o.getClass()) {
            return typologyID.equals(((TypologyDTO) o).getTypologyID())
                    && typologyDescription.equals(((TypologyDTO) o).getTypologyDescription());
        }
        return false;
    }

    /**
     * Returns a hash code value for this TypologyDTO object based on its typologyID and typologyDescription.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(typologyID, typologyDescription);
    }

}
