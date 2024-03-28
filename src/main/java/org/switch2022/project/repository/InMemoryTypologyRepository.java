package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.TypologyID;

import java.util.*;

@org.springframework.stereotype.Repository("typologyMemoryRepository")
public class InMemoryTypologyRepository implements Repository<TypologyID, Typology> {

    private final Map<TypologyID, Typology> data = new HashMap<>();

    public Typology save(Typology entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    public List<Typology> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean containsOfIdentity(TypologyID id) {
        return data.containsKey(id);
    }

    public Optional<Typology> ofIdentity(TypologyID id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }
}
