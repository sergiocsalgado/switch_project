package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.TypologyJPA;
import org.switch2022.project.datamodel.jpa.assemblers.TypologyJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.TypologyID;
import org.switch2022.project.repository.jpa.interfaces.TypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link Repository} interface using JPA for storing and retrieving typologies.
 */
@org.springframework.stereotype.Repository("typologyJPARepository")
public class TypologyRepositoryImpl implements Repository<TypologyID, Typology> {

    private final TypologyJpaRepository typologyJpaRepository;

    /**
     * Constructs a new instance of the {@code TypologyRepositoryImpl} class with the specified JPA repository.
     *
     * @param typologyJpaRepository the JPA repository for business sectors
     */
    public TypologyRepositoryImpl(TypologyJpaRepository typologyJpaRepository) {
        this.typologyJpaRepository = typologyJpaRepository;
    }

    /**
     * Saves the specified typology by converting it to a JPA data model and invoking the save method
     * on the JPA repository.
     *
     * @param typology the typology to save
     * @return the saved typology
     */
    public Typology save(Typology typology) {
        TypologyJPA toSaveTypologyJPA = TypologyJpaAssembler.toDataModel(typology);

        typologyJpaRepository.save(toSaveTypologyJPA);

        return typology;
    }

    /**
     * Retrieves all typologies by invoking the findAll method on the JPA repository and converting
     * the returned JPA data models to domain models.
     *
     * @return a list of all typologies
     */
    public List<Typology> findAll() {
        List<TypologyJPA> savedTypologiesJPA = typologyJpaRepository.findAll();
        List<Typology> typologies = new ArrayList<>();

        for (TypologyJPA typologiesJPA : savedTypologiesJPA) {
            typologies.add(TypologyJpaAssembler.toDomain(typologiesJPA));
        }
        return typologies;
    }

    /**
     * Retrieves a typology by its identity from the JPA repository and converts it to a domain model.
     *
     * @param id the identity of the typology
     * @return an optional containing the retrieved typologies if found, or an empty optional if not found
     */
    public Optional<Typology> ofIdentity(TypologyID id) {

        Optional<TypologyJPA> optionalTypologyJPA = typologyJpaRepository.findByTypologyID(id.getIdOfTypology());

        if (optionalTypologyJPA.isPresent()) {
            Typology typology = TypologyJpaAssembler.toDomain(optionalTypologyJPA.get());
            return Optional.of(typology);
        }
        return Optional.empty();
    }

    /**
     * Checks if a typology with the specified identity exists in the JPA repository.
     *
     * @param id the identity of the typology
     * @return {@code true} if the typology exists, {@code false} otherwise
     */
    public boolean containsOfIdentity(TypologyID id) {
        return typologyJpaRepository.existsByTypologyID(id.getIdOfTypology());
    }
}
