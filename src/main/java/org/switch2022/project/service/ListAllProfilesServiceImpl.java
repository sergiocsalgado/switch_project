package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProfileDTO;
import org.switch2022.project.mapper.ProfileMapper;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.service.interfaces.ListAllProfilesService;

import java.util.List;

/**
 * Service class for listing all the profiles.
 */
@Service
public class ListAllProfilesServiceImpl implements ListAllProfilesService {

    private final Repository<ProfileID, Profile> profileRepository;

    /**
     * Constructs a new ListAllProfilesService with the given repository.
     *
     * @param profileRepository the repository for profile entities.
     */
    public ListAllProfilesServiceImpl(
            @Qualifier("profileJPARepository") Repository<ProfileID, Profile> profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Returns the ProfileDTO objects representing the profiles contained in the profile repository.
     *
     * @return the list of ProfileDTO objects.
     */
    public List<ProfileDTO> getProfiles() {
        return ProfileMapper.getProfilesDTO(profileRepository.findAll());
    }
}
