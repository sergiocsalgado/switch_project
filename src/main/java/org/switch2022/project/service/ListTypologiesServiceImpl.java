package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.mapper.TypologyMapper;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.TypologyID;
import org.switch2022.project.service.interfaces.ListTypologiesService;

import java.util.List;

@Service
public class ListTypologiesServiceImpl implements ListTypologiesService {

    private final Repository<TypologyID, Typology> typologyRepository;

    public ListTypologiesServiceImpl(
            @Qualifier("typologyJPARepository") Repository<TypologyID, Typology> typologyRepository) {
        this.typologyRepository = typologyRepository;
    }

    public List<TypologyDTO> getTypologies() {
        return TypologyMapper.listTypologyDTO(typologyRepository.findAll());
    }
}
