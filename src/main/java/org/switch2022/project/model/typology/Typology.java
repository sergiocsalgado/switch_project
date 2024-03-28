package org.switch2022.project.model.typology;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import java.util.Objects;

public class Typology implements AggregateRoot<TypologyID> {

    TypologyID typologyID;
    Description typologyDescription;

    public Typology(TypologyID typologyID, Description typologyDescription) {

        if (typologyID == null) {
            throw new IllegalArgumentException("TypologyID can´t be null");
        }
        this.typologyID = typologyID;

        if (typologyDescription == null) {
            throw new IllegalArgumentException("The description is null!");
        }
        this.typologyDescription = typologyDescription;
    }

    public Typology copy() {
        return new Typology(this.typologyID, this.typologyDescription);
    }

    public Description getDescription() {
        return typologyDescription;
    }

    public TypologyID getTypologyID() {
        return typologyID;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Typology typology = (Typology) object;
        return sameIDAs(typology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyID, typologyDescription);
    }

    @Override
    public TypologyID identity() {
        return typologyID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        if (object instanceof Typology) {
            Typology typology = (Typology) object;
            return this.typologyDescription.equals(typology.getDescription());
        }
        return false;
    }
}
