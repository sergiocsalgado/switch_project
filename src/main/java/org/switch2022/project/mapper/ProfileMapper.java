package org.switch2022.project.mapper;


import org.springframework.lang.NonNull;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The ProfileMapper class provides methods for mapping Profile objects to ProfileDTO objects.
 * It also includes a method for retrieving a list of ProfileDTO objects from a list of Profile objects.
 */
public final class ProfileMapper {

    private ProfileMapper() {
    }

    /**
     * Converts a list of Profile objects to a list of ProfileDTO objects.
     *
     * @param profiles the list of Profile objects to be converted
     * @return an unmodifiable list of ProfileDTO objects
     */
    public static List<ProfileDTO> getProfilesDTO(List<Profile> profiles) {
        List<ProfileDTO> profileDTOS = new ArrayList<>();

        for (Profile profile : profiles) {
            Description description = profile.getDescription();
            ProfileID profileID = profile.getProfileID();

            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setDescription(description.getDescription());
            profileDTO.setProfileID(profileID.getProfileID());

            profileDTOS.add(profileDTO);
        }

        return Collections.unmodifiableList(profileDTOS);
    }

    /**
     * Converts a Profile object to a ProfileDTO object.
     *
     * @param profile the Profile object to be converted
     * @return the corresponding ProfileDTO object
     */
    public static ProfileDTO toDTO(@NonNull Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setProfileID(profile.getProfileID().getProfileID());
        profileDTO.setDescription(profile.getDescription().getDescription());

        return profileDTO;
    }
}
