package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.ProfileDTO;

import java.util.List;

public interface ListAllProfilesService {
    List<ProfileDTO> getProfiles();
}
