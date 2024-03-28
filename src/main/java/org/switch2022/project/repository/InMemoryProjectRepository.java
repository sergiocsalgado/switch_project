package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.value_objects.ProjectCode;

import java.util.*;

@org.springframework.stereotype.Repository("projectMemoryRepository")
public class InMemoryProjectRepository implements Repository<ProjectCode, Project> {

    private final Map<ProjectCode, Project> data = new HashMap<>();

    public Project save(Project entity) {
        data.put(entity.identity(), entity);
        return entity;
    }


    public List<Project> findAll() {
        return new ArrayList<>(data.values());
    }


    public Optional<Project> ofIdentity(ProjectCode id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }


    public boolean containsOfIdentity(ProjectCode id) {
        return data.containsKey(id);
    }


}
