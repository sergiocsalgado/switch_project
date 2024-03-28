package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "business_sectors")
public class BusinessSectorJPA {
    @Id
    private String businessSectorID;
    private String businessSectorDescription;

    public BusinessSectorJPA(String businessSectorID,
                             String businessSectorDescription) {
        this.businessSectorID = businessSectorID;
        this.businessSectorDescription = businessSectorDescription;
    }

    public String getBusinessSectorID() {
        return businessSectorID;
    }

    public void setBusinessSectorID(String businessSectorID) {
        this.businessSectorID = businessSectorID;
    }

    public String getBusinessSectorDescription() {
        return businessSectorDescription;
    }

    public void setBusinessSectorDescription(String businessSectorDescription) {
        this.businessSectorDescription = businessSectorDescription;
    }
}
