package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.typology.Typology;

public interface CreateTypologyService {

    Typology createTypology(String typologyID,
                            String typologyDescription);

}
