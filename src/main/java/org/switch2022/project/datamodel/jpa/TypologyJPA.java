package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "typologys")
public class TypologyJPA {
    @Id
    private String typologyID;
    private String typologyDescription;

    public TypologyJPA(String typologyID, String typologyDescription) {
        this.typologyID = typologyID;
        this.typologyDescription = typologyDescription;
    }

    public String getTypologyID() {
        return typologyID;
    }

    public String getTypologyDescription() {
        return typologyDescription;
    }
}
