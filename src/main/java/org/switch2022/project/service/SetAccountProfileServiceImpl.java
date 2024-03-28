package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.service.interfaces.SetAccountProfileService;

import java.util.Optional;

/**
 * Service class for setting the profile of an account.
 */
@Service
public class SetAccountProfileServiceImpl implements SetAccountProfileService {

    private final Repository<Email, Account> accountRepository;
    private final Repository<ProfileID, Profile> profileRepository;

    /**
     * Constructs a new SetAccountProfileService with the given repositories.
     *
     * @param accountRepository  the repository for account entities.
     * @param profileRepository the repository for profile entities.
     */
    public SetAccountProfileServiceImpl(
            @Qualifier("accountJPARepository") Repository<Email, Account> accountRepository,
            @Qualifier("profileJPARepository") Repository<ProfileID, Profile> profileRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
    }

    /**
     * Changes the profile of an account based on the provided email and profile ID.
     *
     * @param emailInput      the email of the account.
     * @param profileIdInput  the ID of the profile to set.
     * @return the updated AccountDTO after changing the profile.
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the user profile does not exist or if the account with the given email
     * does not exist.
     */
    public Account changeProfile(String emailInput, String profileIdInput) {
        Email email;
        ProfileID profileId;

        try {
            email = new Email(emailInput);
            profileId = new ProfileID(profileIdInput);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        if (existsProfile(profileId)) {
            Optional<Account> accountOptional = accountRepository.ofIdentity(email);
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();

                account.setProfile(profileId);
                return accountRepository.save(account);

            } else {
                throw new IllegalStateException("Account with that email does not exists.");
            }

        } else {
            throw new IllegalStateException("Profile does not exist.");
        }
    }

    /**
     * Checks if a profile with the given profile ID exists.
     *
     * @param profileID the ID of the profile to check.
     * @return true if the profile exists, false otherwise.
     */
    private boolean existsProfile(ProfileID profileID) {
        return profileRepository.containsOfIdentity(profileID);
    }
}
