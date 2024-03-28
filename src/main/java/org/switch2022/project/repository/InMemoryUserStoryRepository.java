package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.*;
@org.springframework.stereotype.Repository("storyMemoryRepository")
public class InMemoryUserStoryRepository implements Repository<UserStoryID, UserStory> {
    private static Map<UserStoryID, UserStory> data = new HashMap<>();

    public static void resetRepo() {
        data = new HashMap<>();
    }

    public UserStory save(UserStory entity) {
        data.put(entity.identity(), entity);
        return entity;
    }

    public List<UserStory> findAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<UserStory> ofIdentity(UserStoryID id) {
        if (!containsOfIdentity(id)) {
            return Optional.empty();
        } else {
            return Optional.of(data.get(id));
        }
    }

    public boolean containsOfIdentity(UserStoryID id) {
        return data.containsKey(id);
    }
}
