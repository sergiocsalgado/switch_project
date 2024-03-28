package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.human_resource.FactoryAccount;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.RegisterAccountService;

import java.util.Optional;

/**
 * Service class responsible for registering and saving user accounts.
 */
@Service
public class RegisterAccountServiceImpl implements RegisterAccountService {
    private static final String USER = "user";
    private final Repository<Email, Account> accountRepository;
    private final Repository<ProfileID, Profile> profileRepository;
    private final FactoryAccount factoryAccount;

    /**
     * Constructs a new RegisterAccountService with the specified dependencies.
     *
     * @param accountRepository The repository for managing user accounts.
     * @param profileRepository The repository for managing user profiles.
     * @param factoryAccount    The factory for creating new Account instances.
     */
    public RegisterAccountServiceImpl(
            @Qualifier("accountJPARepository") Repository<Email, Account> accountRepository,
            @Qualifier("profileJPARepository") Repository<ProfileID, Profile> profileRepository,
            FactoryAccount factoryAccount) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
        this.factoryAccount = factoryAccount;
    }

    /**
     * Creates a new account with the provided input parameters.
     *
     * @param emailInput       the email address for the account.
     * @param nameInput        the name for the account.
     * @param phoneNumberInput the phone number for the account.
     * @param statusInput      the status for the account.
     * @return the created Account object.
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the user profile is not found.
     * @throws IllegalStateException    if the user account already exists.
     */
    public Account createAccount(String emailInput,
                                 String nameInput,
                                 String phoneNumberInput,
                                 String statusInput) {
        Email email;
        Name name;
        PhoneNumber phoneNumber;
        AccountStatus status;

        try {
            email = new Email(emailInput);
            name = new Name(nameInput);
            phoneNumber = new PhoneNumber(phoneNumberInput);
            status = new AccountStatus(statusInput);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Profile> profileOptional = getUserProfile();
        if (profileOptional.isPresent()) {
            ProfileID profileID = profileOptional.get().getProfileID();
            Account account = factoryAccount.createAccount(email, name, phoneNumber, status, profileID);

            if (notExistsAccount(email)) {
                return accountRepository.save(account);
            } else {
                throw new IllegalArgumentException("User account already exists.");
            }

        } else {
            throw new IllegalStateException("User profile not found for creating an account.");
        }
    }

    /**
     * Returns an Optional object of user Profile.
     *
     * @return na Optional object of Profile if exists a Profile in the repository with the description "user",
     * empty Optional of Profile otherwise.
     */
    private Optional<Profile> getUserProfile() {
        return profileRepository.findAll()
                .stream()
                .filter(profile -> USER.equals(profile.getDescription().getDescription()))
                .findFirst();
    }

    /**
     * Returns true if the account exists, false otherwise.
     *
     * @param email the email of the account to search.
     * @return true if account exists with ID email passed, false otherwise.
     */
    private boolean notExistsAccount(Email email) {
        return !accountRepository.containsOfIdentity(email);
    }
}