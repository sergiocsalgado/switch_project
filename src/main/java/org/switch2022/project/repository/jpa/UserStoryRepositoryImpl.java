package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.UserStoryJpa;
import org.switch2022.project.datamodel.jpa.assemblers.UserStoryJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.repository.jpa.interfaces.UserStoryJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link Repository} interface using JPA for storing and retrieving user stories.
 */
@org.springframework.stereotype.Repository("storyJPARepository")
public class UserStoryRepositoryImpl implements Repository<UserStoryID, UserStory> {

    private final UserStoryJpaRepository userStoryJpaRepository;

    /**
     * Constructs a new instance of the {@code UserStoryJpaRepository} class with the specified JPA repository.
     *
     * @param userStoryJpaRepository the JPA repository for user stories.
     */
    public UserStoryRepositoryImpl(UserStoryJpaRepository userStoryJpaRepository) {
        this.userStoryJpaRepository = userStoryJpaRepository;
    }

    /**
     * Saves the specified user story by converting it to a JPA data model and invoking the save method
     * on the JPA repository.
     *
     * @param userStory the user story to save.
     * @return the saved profile.
     */
    public UserStory save(UserStory userStory) {
        Optional<UserStoryJpa> optionalUserStoryJpa =
                userStoryJpaRepository.findByUserStoryID(userStory.getUserStoryID().getUserStoryID());

        if (optionalUserStoryJpa.isPresent()) {
            UserStoryJpa userStoryJpa = optionalUserStoryJpa.get();

            userStoryJpa.setStatus(userStory.getStatus().getDescription());
            userStoryJpa.setPriority(userStory.getPriority().getIndex());

            userStoryJpaRepository.save(userStoryJpa);

            return UserStoryJpaAssembler.toDomain(userStoryJpa);
        }

        UserStoryJpa userStoryJpa = UserStoryJpaAssembler.toDataModel(userStory);

        userStoryJpaRepository.save(userStoryJpa);

        return UserStoryJpaAssembler.toDomain(userStoryJpa);
    }

    /**
     * Retrieves all user stories by invoking the findAll method on the JPA repository and
     * converting the returned JPA data models to domain models.
     *
     * @return a list of all user stories.
     */
    public List<UserStory> findAll() {
        List<UserStoryJpa> savedUserStories = userStoryJpaRepository.findAll();
        List<UserStory> userStories = new ArrayList<>();

        for (UserStoryJpa userStory : savedUserStories) {
            userStories.add(UserStoryJpaAssembler.toDomain(userStory));
        }

        return userStories;
    }

    /**
     * Retrieves a user story by its identity from the JPA repository and converts it to a domain model.
     *
     * @param id the identity of the user story
     * @return an optional containing the retrieved user story if found, or an empty optional if not found
     */
    public Optional<UserStory> ofIdentity(UserStoryID id) {
        Optional<UserStoryJpa> optionalUserStoryJpa = userStoryJpaRepository.findByUserStoryID(id.getUserStoryID());

        if (optionalUserStoryJpa.isPresent()) {
            UserStory userStory = UserStoryJpaAssembler.toDomain(optionalUserStoryJpa.get());
            return Optional.of(userStory);
        }

        return Optional.empty();
    }

    /**
     * Checks if a user story with the specified identity exists in the JPA repository.
     *
     * @param id the identity of the user story
     * @return true if the user story exists, false otherwise
     */
    public boolean containsOfIdentity(UserStoryID id) {
        return userStoryJpaRepository.existsByUserStoryID(id.getUserStoryID());
    }
}
