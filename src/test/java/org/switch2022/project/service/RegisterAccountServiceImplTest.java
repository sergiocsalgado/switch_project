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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the RegisterAccountService class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RegisterAccountServiceImpl.class)
class RegisterAccountServiceImplTest {
    @MockBean
    private Account account;
    @MockBean
    private Email email;
    @MockBean
    private Name name;
    @MockBean
    private PhoneNumber phoneNumber;
    @MockBean
    private AccountStatus accountStatus;
    @MockBean
    private Profile profile;
    @MockBean
    private Description description;
    @MockBean
    private ProfileID profileID;

    @MockBean
    @Qualifier("accountJPARepository")
    private Repository<Email, Account> accountRepository;
    @MockBean
    @Qualifier("profileJPARepository")
    private Repository<ProfileID, Profile> profileRepository;
    @MockBean
    private FactoryAccount factoryAccount;

    @Autowired
    private RegisterAccountServiceImpl serviceUnderTest;

    /**
     * Unit Test class for {@link RegisterAccountServiceImpl#createAccount(String, String, String, String)}  .
     */
    @Test
    void createAccount_ShouldCreateAndSaveAValidAccount_Unit() {
        // Arrange
        String emailInput = "email@email.com";
        String nameInput = "John Doe";
        String phoneNumberInput = "923456789";
        String statusInput = "active";
        String profileDescription = "user";

        when(profileRepository.findAll()).thenReturn(List.of(profile));

        Optional<Profile> profileOptional = mock(Optional.class);
        when(profileOptional.isPresent()).thenReturn(true);
        when(profileOptional.get()).thenReturn(profile);
        when(profile.getDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn(profileDescription);

        when(profile.getProfileID()).thenReturn(profileID);

        when(factoryAccount.createAccount(email, name, phoneNumber, accountStatus, profileID)).thenReturn(account);
        when(accountRepository.save(any())).thenReturn(account);

        Account expected = factoryAccount.createAccount(email, name, phoneNumber, accountStatus, profileID);

        // Act
        Account accountResult = serviceUnderTest.createAccount(emailInput, nameInput, phoneNumberInput, statusInput);

        // Assert
        assertEquals(expected, accountResult);
    }

    @Test
    void createAccount_ShouldThrowIllegalStateExceptionWhenTheProfileUserDoesNotExists_Unit() {
        // Arrange
        String emailInput = "email@email.com";
        String nameInput = "John Doe";
        String phoneNumberInput = "923456789";
        String statusInput = "active";
        String profileDescription = "administrator";

        when(profileRepository.findAll()).thenReturn(List.of(profile));

        Optional<Profile> profileOptional = mock(Optional.class);
        when(profileOptional.isPresent()).thenReturn(true);
        when(profile.getDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn(profileDescription);

        when(profile.getProfileID()).thenReturn(profileID);

        when(factoryAccount.createAccount(email, name, phoneNumber, accountStatus, profileID)).thenReturn(account);
        when(accountRepository.save(any())).thenReturn(account);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.createAccount(emailInput, nameInput, phoneNumberInput, statusInput);
        });

        //Assert
        assertEquals("User profile not found for creating an account.", exception.getMessage());
    }

    @Test
    void createAccount_ShouldThrowIllegalArgumentExceptionWhenEmailIsInvalid_Unit() {
        // Arrange
        String emailInput = "email.com";
        String nameInput = "";
        String phoneNumberInput = "923456";
        String statusInput = "ACTIVES";
        String profileDescription = "ADMIN";

        when(profileRepository.findAll()).thenReturn(List.of(profile));

        Optional<Profile> profileOptional = mock(Optional.class);
        when(profileOptional.isPresent()).thenReturn(true);
        when(profile.getDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn(profileDescription);

        when(profile.getProfileID()).thenReturn(profileID);

        when(factoryAccount.createAccount(email, name, phoneNumber, accountStatus, profileID)).thenReturn(account);
        when(accountRepository.save(any())).thenReturn(account);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.createAccount(emailInput, nameInput, phoneNumberInput, statusInput);
        });

        //Assert
        assertEquals("Email is not valid", exception.getMessage());
    }

    @Test
    void createAccount_ShouldThrowSecurityExceptionWhenAccountAlreadyExists_Unit() {
        // Arrange
        String emailInput = "email@email.com";
        String nameInput = "John Doe";
        String phoneNumberInput = "923456789";
        String statusInput = "active";
        String profileDescription = "user";

        Email email1 = new Email(emailInput);
        Name name1 = new Name(nameInput);
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneNumberInput);
        AccountStatus accountStatus1 = new AccountStatus(statusInput);

        when(profileRepository.findAll()).thenReturn(List.of(profile));

        Optional<Profile> profileOptional = mock(Optional.class);
        when(profileOptional.isPresent()).thenReturn(true);
        when(profile.getDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn(profileDescription);

        when(profile.getProfileID()).thenReturn(profileID);

        when(email.getEmail()).thenReturn(emailInput);

        when(factoryAccount.createAccount(email1, name1, phoneNumber1, accountStatus1, profileID)).thenReturn(account);
        when(accountRepository.containsOfIdentity(email1)).thenReturn(true);

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.createAccount(emailInput, nameInput, phoneNumberInput, statusInput);
        });

        //Assert
        assertEquals("User account already exists.", exception.getMessage());
    }

    /**
     * Integration Test class for {@link RegisterAccountServiceImpl#createAccount(String, String, String, String)}  .
     */
    @Test
    void createAccount_ShouldCreateAndSaveAValidAccount_Integration() {
        // Arrange
        String emailInput = "email@email.com";
        String nameInput = "John Doe";
        String phoneInput = "912345678";
        String status = "active";
        String profileDescription = "user";

        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();

        RegisterAccountServiceImpl service = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);

        profileRepository1.save(new Profile(new ProfileID("1"), new Description(profileDescription)));

        Account expected = factoryAccount1.createAccount(
                new Email(emailInput),
                new Name(nameInput),
                new PhoneNumber(phoneInput),
                new AccountStatus(status),
                new ProfileID("1"));

        //Act
        Account accountResult = service.createAccount(
                emailInput,
                nameInput,
                phoneInput,
                status);

        //Assert
        assertEquals(expected, accountResult);
    }
}
