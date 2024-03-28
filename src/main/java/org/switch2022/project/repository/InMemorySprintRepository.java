package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.SprintID;

import java.util.*;

@org.springframework.stereotype.Repository("sprintMemoryRepository")
public class InMemorySprintRepository implements Repository<SprintID, Sprint> {

    private final Map<SprintID, Sprint> data;
    public InMemorySprintRepository(){
        data = new HashMap<>();
    }


    public Sprint save(Sprint entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    public List<Sprint> findAll() {
        return new ArrayList<>(data.values());
    }


    public Optional<Sprint> ofIdentity(SprintID id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }

    public boolean containsOfIdentity(SprintID id) {
        return data.containsKey(id);
    }
}
