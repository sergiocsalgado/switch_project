package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.SprintJPA;
import org.switch2022.project.datamodel.jpa.SprintUserStoryJpa;
import org.switch2022.project.datamodel.jpa.assemblers.SprintJpaAssembler;
import org.switch2022.project.datamodel.jpa.assemblers.SprintUserStoryJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.model.value_objects.UserStoryID;
import org.switch2022.project.repository.jpa.interfaces.SprintJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository("sprintJPARepository")
public class SprintRepositoryImpl implements Repository<SprintID, Sprint> {

    private final SprintJpaRepository sprintJpaRepository;

    public SprintRepositoryImpl(SprintJpaRepository jpaRepository) {
        this.sprintJpaRepository = jpaRepository;
    }

    public Sprint save(Sprint sprint) {
        Optional<SprintJPA> optionalSprintJPA =
                sprintJpaRepository.findBySprintID(sprint.getSprintID().getSprintID());

        if (optionalSprintJPA.isPresent()) {
            SprintJPA sprintJpaToUpdate = optionalSprintJPA.get();

            sprintJpaRepository.delete(sprintJpaToUpdate);

            SprintJPA sprintJpaToSave = SprintJpaAssembler.toDataModel(sprint);

            sprintJpaRepository.save(sprintJpaToSave);

            return SprintJpaAssembler.toDomain(sprintJpaToSave);
        }

        SprintJPA sprintJpaToSave = SprintJpaAssembler.toDataModel(sprint);

        sprintJpaRepository.save(sprintJpaToSave);

        return SprintJpaAssembler.toDomain(sprintJpaToSave);
    }

    public List<Sprint> findAll() {
        List<SprintJPA> savedSprints = sprintJpaRepository.findAll();
        List<Sprint> sprints = new ArrayList<>();

        for (SprintJPA sprintJpa : savedSprints) {
            sprints.add(SprintJpaAssembler.toDomain(sprintJpa));
        }

        return sprints;
    }

    public Optional<Sprint> ofIdentity(SprintID id) {

        Optional<SprintJPA> optionalSprintJPA = sprintJpaRepository.findBySprintID(id.getSprintID());

        if (optionalSprintJPA.isPresent()) {
            Sprint sprint = SprintJpaAssembler.toDomain(optionalSprintJPA.get());

            List<SprintUserStoryJpa> sprintUserStories = optionalSprintJPA.get().getSprintUserStories();
            List<UserStoryID> userStoryIDS = new ArrayList<>();

            for (SprintUserStoryJpa storyJpa : sprintUserStories) {
                userStoryIDS.add(SprintUserStoryJpaAssembler.toDomain(storyJpa));
            }

            sprint.setSprintBacklog(userStoryIDS);

            return Optional.of(sprint);
        }

        return Optional.empty();
    }

    public boolean containsOfIdentity(SprintID id) {
        return sprintJpaRepository.existsBySprintID(id.getSprintID());
    }
}
