package org.switch2022.project.model.typology;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

@Component
public class FactoryTypologyImpl implements FactoryTypology{
    public Typology createTypology(TypologyID typologyID, Description typologyDescription) {
        return new Typology(typologyID, typologyDescription);
    }
}
