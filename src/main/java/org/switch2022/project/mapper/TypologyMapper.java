package org.switch2022.project.mapper;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TypologyMapper {

    private TypologyMapper() {
    }

    /**
     * Maps a list of {@link Typology} objects to a list of {@link TypologyDTO} objects.
     *
     * @param typologies a list of {@link Typology} objects to be mapped
     * @return a list of {@link TypologyDTO} objects
     */
    public static List<TypologyDTO> listTypologyDTO(List<Typology> typologies) {
        List<TypologyDTO> typologiesDTO = new ArrayList<>();
        for (Typology typ : typologies) {
            TypologyID typologyID = typ.getTypologyID();
            Description typologyDescription = typ.getDescription();

            TypologyDTO typologyDTO = new TypologyDTO();
            typologyDTO.setTypologyID(typologyID.getIdOfTypology());
            typologyDTO.setTypologyDescription(typologyDescription.getDescription());

            typologiesDTO.add(typologyDTO);
        }
        return Collections.unmodifiableList(typologiesDTO);
    }

    public static TypologyDTO toDTO(@NonNull Typology typology) {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setTypologyID(typology.getTypologyID().getIdOfTypology());
        typologyDTO.setTypologyDescription(typology.getDescription().getDescription());

        return typologyDTO;
    }
}
