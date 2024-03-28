package org.switch2022.project.model.business_sector;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.Description;

import java.util.Objects;

public class BusinessSector implements AggregateRoot<BusinessSectorID> {
    private final BusinessSectorID businessSectorID;
    private final Description businessSectorDescription;

    public BusinessSector(BusinessSectorID businessSectorID, Description businessSectorDescription) {

        if (businessSectorID == null) {
            throw new IllegalArgumentException("Business Sector ID can not be null");
        }
        this.businessSectorID = businessSectorID;

        if (businessSectorDescription == null) {
            throw new IllegalArgumentException("Business Sector Description can not be null");
        }
        this.businessSectorDescription = businessSectorDescription;
    }

    public boolean verifyBusinessSectorDescription(Description businessSectorDescription) {
        return this.businessSectorDescription.equals(businessSectorDescription);
    }

    public BusinessSector copy() {
        return new BusinessSector(this.businessSectorID, this.businessSectorDescription);
    }

    public BusinessSectorID getBusinessSectorID() {
        return businessSectorID;
    }

    public Description getBusinessSectorDescription() {
        return businessSectorDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        BusinessSector businessSector = (BusinessSector) o;
        return sameIDAs(businessSector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessSectorDescription);
    }

    @Override
    public BusinessSectorID identity() {
        return businessSectorID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        if (object instanceof BusinessSector) {
            BusinessSector businessSector = (BusinessSector) object;
            return this.businessSectorID.equals(businessSector.getBusinessSectorID());
        }
        return false;
    }
}
