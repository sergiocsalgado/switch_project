package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a business sector,
 * containing information such as businessSectorID and description.
 */

public class BusinessSectorDTO {

    private String businessSectorID;

    private String description;

    /**
     * Returns the businessSectorID of BusinessSectorDTO.
     *
     * @return businessSectorID.
     */
    public String getBusinessSectorID() {
        return this.businessSectorID;
    }

    /**
     * Set the businessSectorID of BusinessSectorDTO.
     *
     * @param businessSectorID the ID of the business sector.
     */
    public void setBusinessSectorID(String businessSectorID) {
        this.businessSectorID = businessSectorID;
    }

    /**
     * Returns the description of BusinessSectorDTO.
     *
     * @return description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description of BusinessSectorDTO.
     *
     * @param description the description of the business sector.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compares two BusinessSectorDTO objects.
     *
     * @param o the object to compare with.
     * @return true if the businessSectorID and description fields are equal, false otherwise.
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
            return businessSectorID.equals(((BusinessSectorDTO) o).getBusinessSectorID())
                    && description.equals(((BusinessSectorDTO) o).getDescription());
        }
        return false;
    }

    /**
     * Returns a hash code value for this BusinessSectorDTO object based on its businessSectorID and description.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(businessSectorID, description);
    }
}
