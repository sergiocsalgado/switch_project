package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.ProjectJPA;
import org.switch2022.project.datamodel.jpa.ResourceInProjectJpa;
import org.switch2022.project.datamodel.jpa.assemblers.ProjectJpaAssembler;
import org.switch2022.project.datamodel.jpa.assemblers.ResourceInProjectJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.repository.jpa.interfaces.ProjectJpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link Repository} interface using JPA for storing and retrieving projects
 */

@org.springframework.stereotype.Repository("projectJPARepository")
public class ProjectRepositoryImpl implements Repository<ProjectCode, Project> {

    private final ProjectJpaRepository jpaRepository;

    /**
     * Constructs a new instance of the {@code ProjectRepositoryImpl} class with the specified JPA repository.
     *
     * @param jpaRepository the JPA repository for projects
     */
    public ProjectRepositoryImpl(ProjectJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Saves the specified project by converting it to a JPA data model and invoking the save method on
     * the JPA repository.
     *
     * @param project the project to save
     * @return the saved project
     */
    @Transactional
    public Project save(Project project) {
        Optional<ProjectJPA> optionalProjectJpa =
                jpaRepository.findByProjectCode(project.getProjectCode().getProjectCode());

        if (optionalProjectJpa.isPresent()) {
            ProjectJPA projectJpaToUpdate = optionalProjectJpa.get();

            jpaRepository.delete(projectJpaToUpdate);

            ProjectJPA projectJpaToSave = ProjectJpaAssembler.toDataModel(project);

            jpaRepository.save(projectJpaToSave);

            return ProjectJpaAssembler.toDomain(projectJpaToSave);
        }

        ProjectJPA projectJpaToSave = ProjectJpaAssembler.toDataModel(project);

        jpaRepository.save(projectJpaToSave);

        return ProjectJpaAssembler.toDomain(projectJpaToSave);
    }

    /**
     * Retrieves all projects by invoking the findAll method on the JPA repository and converting
     * the returned JPA data models to domain models.
     *
     * @return a list of all projects
     */
    @Transactional
    public List<Project> findAll() {

        List<ProjectJPA> savedProjects = jpaRepository.findAll();
        List<Project> projects = new ArrayList<>();

        for (ProjectJPA prjJpa: savedProjects) {
            projects.add(ProjectJpaAssembler.toDomain(prjJpa));
        }
        return projects;
    }

    /**
     * Retrieves a project by its identity from the JPA repository and converts it to a domain model.
     *
     * @param id the identity of the project
     * @return an optional containing the retrieved project if found, or an empty optional if not found
     */
    @Transactional
    public Optional<Project> ofIdentity(ProjectCode id) {

        Optional<ProjectJPA> optionalProjectJPA = jpaRepository.findByProjectCode(id.getProjectCode());

        if (optionalProjectJPA.isPresent()) {
            Project project = ProjectJpaAssembler.toDomain(optionalProjectJPA.get());

            List<ResourceInProjectJpa> resourceInProjectJpas = optionalProjectJPA.get().getResourceInProjectJpas();
            List<ResourceInProject> resources = new ArrayList<>();

            for (ResourceInProjectJpa resourceJpa : resourceInProjectJpas) {
                resources.add(ResourceInProjectJpaAssembler.toDomain(resourceJpa));
            }

            project.setResources(resources);

            return Optional.of(project);
        }

        return Optional.empty();
    }

    /**
     * Checks if a project with the specified identity exists in the JPA repository.
     *
     * @param id the identity of the project
     * @return {@code true} if the project exists, {@code false} otherwise
     */
    @Transactional
    public boolean containsOfIdentity(ProjectCode id) {
        return jpaRepository.existsByProjectCode(id.getProjectCode());
    }
}
