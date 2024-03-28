package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.typology.FactoryTypology;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;
import org.switch2022.project.service.interfaces.CreateTypologyService;

import java.util.List;

/**
 * Javadoc for the {@code CreateTypologyServiceImpl} class.
 * This class implements the {@code CreateTypologyService} interface and provides the functionality
 * to create a new typology.
 */
@Service
public class CreateTypologyServiceImpl implements CreateTypologyService {

    private final FactoryTypology factoryTypology;

    private final Repository<TypologyID, Typology> typologyRepository;

    /**
     * Constructs an instance of {@code CreateTypologyServiceImpl} with the specified dependencies.
     *
     * @param factoryTypology    the factory for creating typology objects
     * @param typologyRepository the repository for typologies
     */
    public CreateTypologyServiceImpl(
            FactoryTypology factoryTypology,
            @Qualifier("typologyJPARepository") Repository<TypologyID, Typology> typologyRepository) {
        this.factoryTypology = factoryTypology;
        this.typologyRepository = typologyRepository;
    }

    /**
     * Checks if the given typology already exists based on its ID or description.
     *
     * @param typology the typology to check
     * @return true if the typology with the same ID or description exists, false otherwise
     */
    public boolean containsIDOrDescription(Typology typology) {
        return typologyRepository.containsOfIdentity(typology.getTypologyID())
                || existsTypology(typology.getDescription());
    }

    /**
     * Checks if a typology with the same description already exists.
     *
     * @param description the description of the typology
     * @return true if a typology with the same description exists, false otherwise
     */
    private boolean existsTypology(Description description) {
        List<Typology> typologyList = typologyRepository.findAll();

        for (Typology typology : typologyList) {
            if (typology.getDescription().getDescription().equalsIgnoreCase(description.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new typology with the specified ID and description.
     *
     * @param typologyIDInput          the ID of the typology
     * @param typologyDescriptionInput the description of the typology
     * @return the created typology
     * @throws IllegalArgumentException if the provided ID or description is invalid, or if the typology already exists
     */
    @Override
    public Typology createTypology(String typologyIDInput, String typologyDescriptionInput) {
        TypologyID typologyID;
        Description typologyDescription;

        try {
            typologyID = new TypologyID(typologyIDInput);
            typologyDescription = new Description(typologyDescriptionInput);

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        Typology typology = factoryTypology.createTypology(typologyID, typologyDescription);

        if (containsIDOrDescription(typology)) {
            throw new IllegalArgumentException("Could not create Typology.");
        } else {
            return typologyRepository.save(typology);
        }
    }
}
