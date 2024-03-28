package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.value_objects.BusinessSectorID;

import java.util.*;

@org.springframework.stereotype.Repository("businessSectorMemoryRepository")
public class InMemoryBusinessSectorRepository implements Repository<BusinessSectorID, BusinessSector> {

    private final Map<BusinessSectorID, BusinessSector> data = new HashMap<>();

    public BusinessSector save(BusinessSector entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    public List<BusinessSector> findAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<BusinessSector> ofIdentity(BusinessSectorID id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }

    public boolean containsOfIdentity(BusinessSectorID id) {
        return data.containsKey(id);
    }
}
