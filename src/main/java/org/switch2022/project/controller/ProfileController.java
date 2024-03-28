package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.ProfileDTO;
import org.switch2022.project.mapper.ProfileMapper;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.service.interfaces.CreateProfileService;
import org.switch2022.project.service.interfaces.ListAllProfilesService;

import java.util.List;

/**
 * The controller class for managing profiles.
 */
@Controller
@RestController
@RequestMapping(path = "/profiles")
public class ProfileController {
    private final CreateProfileService createProfileService;
    private final ListAllProfilesService listAllProfilesService;

    /**
     * Constructs a new ProfileController with the specified services.
     *
     * @param createProfileService   The service responsible for creating profiles.
     * @param listAllProfilesService The service responsible for listing all profiles.
     */
    public ProfileController(CreateProfileService createProfileService, ListAllProfilesService listAllProfilesService) {
        this.createProfileService = createProfileService;
        this.listAllProfilesService = listAllProfilesService;
    }

    /**
     * Creates a new profile.
     *
     * @param profileDTO The profile DTO object containing profile information.
     * @return ResponseEntity containing the created profile DTO.
     */
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createProfile(@RequestBody ProfileDTO profileDTO) {
        try {
            Profile profile = createProfileService.createProfile(
                    profileDTO.getProfileID(),
                    profileDTO.getDescription());

            ProfileDTO profileCreated = ProfileMapper.toDTO(profile);

            return new ResponseEntity<>(profileCreated, HttpStatus.CREATED);

        } catch (IllegalArgumentException argumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(argumentException.getMessage());
        }
    }

    /**
     * Retrieves all profiles.
     *
     * @return ResponseEntity containing a list of profile DTOs.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getProfiles() {
        List<ProfileDTO> profileDTOS = listAllProfilesService.getProfiles();

        return new ResponseEntity<>(profileDTOS, HttpStatus.OK);
    }
}
