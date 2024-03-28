package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.human_resource.FactoryAccount;
import org.switch2022.project.model.human_resource.FactoryAccountImpl;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.repository.InMemoryAccountRepository;
import org.switch2022.project.repository.InMemoryProfileRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * This is a test class for the SetAccountProfileService class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SetAccountProfileServiceImpl.class)
class SetAccountProfileServiceImplTest {
    @MockBean
    private Account account;
    @MockBean
    private Profile profile;

    @MockBean
    @Qualifier("accountJPARepository")
    private Repository<Email, Account> accountRepository;
    @MockBean
    @Qualifier("profileJPARepository")
    private Repository<ProfileID, Profile> profileRepository;

    @Autowired
    private SetAccountProfileServiceImpl serviceUnderTest;


    /**
     * Unit Test class for {@link SetAccountProfileServiceImpl#changeProfile(String, String)} .
     */
    @Test
    void changeProfile_shouldReturnAValidAccountDTOWhenTheEmailAndProfileIDAreValidAndExistent_Unit() {
        // Arrange
        String emailInput = "email@email.com";
        String profileId = "P1";

        ProfileID profileID1 =  new ProfileID(profileId);
        Email email1 = new Email(emailInput);

        when(profile.getProfileID()).thenReturn(profileID1);
        when(profileRepository.containsOfIdentity(profileID1)).thenReturn(true);

        Optional<Account> accountOptional = mock(Optional.class);
        when(accountOptional.isPresent()).thenReturn(true);
        when(accountOptional.get()).thenReturn(account);
        when(accountRepository.ofIdentity(email1)).thenReturn(accountOptional);

        when(account.setProfile(profileID1)).thenReturn(true);
        when(accountRepository.save(account)).thenReturn(account);

        //Act
        Account accountResult = serviceUnderTest.changeProfile(emailInput, profileId);

        //Assert
        assertEquals(account, accountResult);
    }
    @Test
    void changeProfile_shouldThrowIllegalArgumentExceptionWhenInValidParametersAreGiven_Unit() {
        // Arrange
        String emailInput = "email.com";
        String profileId = "P1";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.changeProfile(emailInput, profileId);
        });

        //Assert
        assertEquals("Email is not valid", exception.getMessage());
    }
    @Test
    void changeProfile_shouldThrowIllegalStateExceptionWhenUserProfileDoesNotExists_Unit() {
        // Arrange
        String emailInput = "email@email.com";
        String profileId = "P1";

        ProfileID profileID1 =  new ProfileID(profileId);
        Email email1 = new Email(emailInput);

        when(profile.getProfileID()).thenReturn(profileID1);
        when(profileRepository.containsOfIdentity(profileID1)).thenReturn(false);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.changeProfile(emailInput, profileId);
        });

        //Assert
        assertEquals("Profile does not exist.", exception.getMessage());
    }
    @Test
    void changeProfile_shouldThrowIllegalStateExceptionWhenAccountDoesNotExists_Unit() {
        // Arrange
        String emailInput = "email@email.com";
        String profileId = "P1";

        ProfileID profileID1 =  new ProfileID(profileId);
        Email email1 = new Email(emailInput);

        when(profile.getProfileID()).thenReturn(profileID1);
        when(profileRepository.containsOfIdentity(profileID1)).thenReturn(true);

        Optional<Account> accountOptional = mock(Optional.class);
        when(accountOptional.isPresent()).thenReturn(false);
        when(accountRepository.ofIdentity(email1)).thenReturn(accountOptional);

        when(account.setProfile(profileID1)).thenReturn(true);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.changeProfile(emailInput, profileId);
        });

        //Assert
        assertEquals("Account with that email does not exists.", exception.getMessage());
    }

    /**
     * Integration Test class for {@link SetAccountProfileServiceImpl#changeProfile(String, String)}   .
     */
    @Test
    void changeProfile_shouldReturnAValidAccountDTOWhenTheEmailAndProfileIDAreValidAndExistent_Integration() {
        // Arrange
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();

        SetAccountProfileServiceImpl service = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);

        String emailInput = "email@email.com";
        String nameInput = "john doe";
        String phoneInput = "912345678";
        String statusInput = "active";
        String profileIdAdmin = "P1";
        String profileIdUser = "P2";
        String profileDescriptionAdmin = "administrator";
        String profileDescriptionUser = "user";

        Email email = new Email(emailInput);
        Name name = new Name(nameInput);
        PhoneNumber phoneNumber = new PhoneNumber(phoneInput);
        AccountStatus accountStatus = new AccountStatus(statusInput);

        ProfileID profileIDUser =  new ProfileID(profileIdUser);
        Description descriptionUser = new Description(profileDescriptionUser);
        Profile profileUser = new Profile(profileIDUser, descriptionUser);

        ProfileID profileIDAdmin =  new ProfileID(profileIdAdmin);
        Description descriptionAdmin = new Description(profileDescriptionAdmin);
        Profile profileAdmin = new Profile(profileIDAdmin, descriptionAdmin);

        profileRepository1.save(profileAdmin);
        profileRepository1.save(profileUser);

        Account accountUserToAdmin = factoryAccount1.createAccount(email, name, phoneNumber, accountStatus, profileIDUser);
        accountRepository1.save(accountUserToAdmin);

        // Assert account has user profile
        assertEquals(profileIDUser, accountUserToAdmin.getProfileID());

        //Act
        service.changeProfile(emailInput, profileIdAdmin);

        //Assert
        assertEquals(profileIDAdmin, accountUserToAdmin.getProfileID());
    }
}
