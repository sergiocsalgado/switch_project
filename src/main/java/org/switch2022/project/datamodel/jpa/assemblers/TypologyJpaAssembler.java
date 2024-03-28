package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.TypologyJPA;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

public final class TypologyJpaAssembler {

    private TypologyJpaAssembler() {
    }

    public static TypologyJPA toDataModel(Typology typology) {
        return new TypologyJPA(
                typology.getTypologyID().getIdOfTypology(),
                typology.getDescription().getDescription()
        );
    }

    public static Typology toDomain(TypologyJPA typologyJPA) {
        return new Typology(
                new TypologyID(typologyJPA.getTypologyID()),
                new Description(typologyJPA.getTypologyDescription())
        );
    }
}
