package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.mapper.AccountMapper;
import org.switch2022.project.mapper.ChangeStatusDTO;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.human_resource.FactoryAccount;
import org.switch2022.project.model.human_resource.FactoryAccountImpl;
import org.switch2022.project.model.profile.FactoryProfile;
import org.switch2022.project.model.profile.FactoryProfileImpl;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.repository.InMemoryAccountRepository;
import org.switch2022.project.repository.InMemoryProfileRepository;
import org.switch2022.project.service.AccountsAndStatusServiceImpl;
import org.switch2022.project.service.ChangeAccountStatusServiceImpl;
import org.switch2022.project.service.RegisterAccountServiceImpl;
import org.switch2022.project.service.SetAccountProfileServiceImpl;
import org.switch2022.project.service.interfaces.AccountsAndStatusService;
import org.switch2022.project.service.interfaces.ChangeStatusService;
import org.switch2022.project.service.interfaces.RegisterAccountService;
import org.switch2022.project.service.interfaces.SetAccountProfileService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the RegisterAccountController class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountController.class)
class AccountControllerTest {
    @MockBean
    Account account;
    @MockBean
    Email email;
    @MockBean
    Name name;
    @MockBean
    PhoneNumber phoneNumber;
    @MockBean
    AccountStatus accountStatus;
    @MockBean
    Profile profile;
    @MockBean
    ProfileID profileId;

    @MockBean
    private RegisterAccountService registerAccountService;
    @MockBean
    private SetAccountProfileService setAccountProfileService;
    @MockBean
    private ChangeStatusService changeStatusService;
    @MockBean
    private AccountsAndStatusService accountsAndStatusService;
    @Autowired
    private AccountController controllerUnderTest;


    /**
     * Unit Tests class for {@link AccountController#createAccount(AccountDTO)}  .
     */
    @Test
    void registerAccount_ensureAccountIsCreatedWithSuccessWithValidGivenParameters_Unit() {
        //Arrange
        String emailInput = "email@email.com";
        String nameInput = "name";
        String phoneInput = "912345678";
        String statusInput = "active";

        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getPhoneNumber()).thenReturn(phoneNumber);
        when(account.getStatus()).thenReturn(accountStatus);
        when(account.getProfileID()).thenReturn(profileId);

        when(email.getEmail()).thenReturn(emailInput);
        when(name.getValue()).thenReturn(nameInput);
        when(phoneNumber.getPhoneNumber()).thenReturn(phoneInput);
        when(accountStatus.getStatus()).thenReturn(statusInput);
        when(profile.getProfileID()).thenReturn(profileId);

        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail(emailInput);
        receivedAccountDTO.setName(nameInput);
        receivedAccountDTO.setPhoneNumber(phoneInput);
        receivedAccountDTO.setStatus(statusInput);

        when(registerAccountService.createAccount(emailInput, nameInput, phoneInput, statusInput)).thenReturn(account);

        ResponseEntity<Object> expected = new ResponseEntity<>(receivedAccountDTO, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.createAccount(receivedAccountDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void registerAccount_ensureAccountIsNOTCreatedWithInvalidEmail_Unit() {
        // Arrange
        AccountDTO invalidAccountDTO = new AccountDTO();
        invalidAccountDTO.setEmail("email.com");
        invalidAccountDTO.setName("name");
        invalidAccountDTO.setPhoneNumber("912345678");
        invalidAccountDTO.setStatus("active");

        IllegalArgumentException exception = new IllegalArgumentException("Email is not valid");

        // Stubbing the service method to throw an exception
        when(registerAccountService.createAccount(
                invalidAccountDTO.getEmail(),
                invalidAccountDTO.getName(),
                invalidAccountDTO.getPhoneNumber(),
                invalidAccountDTO.getStatus()
        )).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.createAccount(invalidAccountDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void registerAccount_ensureAccountIsNOTCreatedWhenThereIsNoUserProfileCreated_Unit() {
        // Arrange
        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail("email@email.com");
        receivedAccountDTO.setName("name");
        receivedAccountDTO.setPhoneNumber("912345678");
        receivedAccountDTO.setStatus("active");

        IllegalStateException exception = new IllegalStateException("User profile not found for creating an account.");

        // Stubbing the service method to throw an exception
        when(registerAccountService.createAccount(
                receivedAccountDTO.getEmail(),
                receivedAccountDTO.getName(),
                receivedAccountDTO.getPhoneNumber(),
                receivedAccountDTO.getStatus()
        )).thenThrow(exception);


        // Act
        ResponseEntity<Object> response = controllerUnderTest.createAccount(receivedAccountDTO);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void registerAccount_ensureAccountIsNOTCreatedWhenAccountAlreadyExists_Unit() {
        // Arrange
        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail("email@email.com");
        receivedAccountDTO.setName("name");
        receivedAccountDTO.setPhoneNumber("912345678");
        receivedAccountDTO.setStatus("active");

        IllegalArgumentException exception = new IllegalArgumentException("User account already exists.");

        // Stubbing the service method to throw an exception
        when(registerAccountService.createAccount(
                receivedAccountDTO.getEmail(),
                receivedAccountDTO.getName(),
                receivedAccountDTO.getPhoneNumber(),
                receivedAccountDTO.getStatus()
        )).thenThrow(exception);


        // Act
        ResponseEntity<Object> response = controllerUnderTest.createAccount(receivedAccountDTO);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    /**
     * Integration Tests class for {@link AccountController#createAccount(AccountDTO)}  .
     */
    @Test
    void registerAccount_ensureAccountIsCreatedWithSuccessWithValidGivenParameters_Integration() {
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        profileRepository1.save(factoryProfile.createProfile(new ProfileID("1"), new Description("user")));

        String emailInput = "email@email.com";
        String nameInput = "NAME";
        String phoneInput = "912345678";
        String statusInput = "active";

        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail(emailInput);
        receivedAccountDTO.setName(nameInput);
        receivedAccountDTO.setPhoneNumber(phoneInput);
        receivedAccountDTO.setStatus(statusInput);

        ResponseEntity<Object> expected = new ResponseEntity<>(receivedAccountDTO, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> result = controller.createAccount(receivedAccountDTO);

        assertEquals(expected, result);
    }

    @Test
    void registerAccount_ensureAccountIsNOTCreatedWithInvalidValidGivenParameters_Integration() {
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        profileRepository1.save(factoryProfile.createProfile(new ProfileID("1"), new Description("user")));

        String emailInput = "email.com";
        String nameInput = "";
        String phoneInput = "912345678888";
        String statusInput = "sat";

        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail(emailInput);
        receivedAccountDTO.setName(nameInput);
        receivedAccountDTO.setPhoneNumber(phoneInput);
        receivedAccountDTO.setStatus(statusInput);

        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is not valid");

        // Act
        ResponseEntity<Object> result = controller.createAccount(receivedAccountDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void registerAccount_ensureAccountIsNOTCreatedWhenThereIsNoUserProfileCreated_Integration() {
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new AccountsAndStatusServiceImpl(accountRepository1);

        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        String emailInput = "email@email.com";
        String nameInput = "NAME";
        String phoneInput = "912345678";
        String statusInput = "active";

        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail(emailInput);
        receivedAccountDTO.setName(nameInput);
        receivedAccountDTO.setPhoneNumber(phoneInput);
        receivedAccountDTO.setStatus(statusInput);

        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("User profile not found for creating an account.");

        // Act
        ResponseEntity<Object> result = controller.createAccount(receivedAccountDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void registerAccount_ensureAccountIsNOTCreatedWhenAlreadyExistsTheAccount_Integration() {
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        profileRepository1.save(factoryProfile.createProfile(new ProfileID("1"), new Description("user")));

        String emailInput = "email@email.com";
        String nameInput = "NAME";
        String phoneInput = "912345678";
        String statusInput = "active";

        AccountDTO receivedAccountDTO = new AccountDTO();
        receivedAccountDTO.setEmail(emailInput);
        receivedAccountDTO.setName(nameInput);
        receivedAccountDTO.setPhoneNumber(phoneInput);
        receivedAccountDTO.setStatus(statusInput);

        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User account already exists.");

        controller.createAccount(receivedAccountDTO);

        //Act
        ResponseEntity<Object> result = controller.createAccount(receivedAccountDTO);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Tests class for {@link AccountController#setAccountProfile(String, String)} .
     */
    @Test
    void setAccountProfile_ensureProfileOfAccountIsChangedWithValidGivenParameters_Unit() {
        //Arrange
        String emailInput = "email@email.com";
        String profileIdInput = "Profile1";

        String nameAccount = "name";
        String phoneAccount = "912345678";
        String statusAccount = "active";

        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getPhoneNumber()).thenReturn(phoneNumber);
        when(account.getStatus()).thenReturn(accountStatus);
        when(account.getProfileID()).thenReturn(profileId);

        when(email.getEmail()).thenReturn(emailInput);
        when(name.getValue()).thenReturn(nameAccount);
        when(phoneNumber.getPhoneNumber()).thenReturn(phoneAccount);
        when(accountStatus.getStatus()).thenReturn(statusAccount);
        when(profile.getProfileID()).thenReturn(profileId);
        when(profileId.getProfileID()).thenReturn(profileIdInput);

        AccountDTO expectedAccountDTO = new AccountDTO();
        expectedAccountDTO.setEmail(emailInput);
        expectedAccountDTO.setName(nameAccount);
        expectedAccountDTO.setPhoneNumber(phoneAccount);
        expectedAccountDTO.setStatus(statusAccount);

        when(setAccountProfileService.changeProfile(emailInput, profileIdInput)).thenReturn(account);

        ResponseEntity<Object> expected = new ResponseEntity<>(expectedAccountDTO, HttpStatus.OK);

        try (MockedStatic<AccountMapper> mapperDouble = Mockito.mockStatic(AccountMapper.class)) {
            mapperDouble.when(() -> AccountMapper.toDTO(account)).thenReturn(expectedAccountDTO);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.setAccountProfile(emailInput, profileIdInput);

            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void setAccountProfile_ensureProfileOfAccountIsNotChangedWithValidGivenParameters_Unit() {
        //Arrange
        String emailInput = "email@email.com";
        String profileIdInput = "Profile1";

        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid input parameters for changing the account profile.");

        // Stubbing the service method to throw an exception
        when(setAccountProfileService.changeProfile(
                emailInput,
                profileIdInput
        )).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.setAccountProfile(emailInput, profileIdInput);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void setAccountProfile_ensureAccountProfileIsNOTChangedWhenProfileIdPassedDoesNotExists_Unit() {
        //Arrange
        String emailInput = "email@email.com";
        String profileIdInput = "Profile1";

        IllegalStateException exception = new IllegalStateException("User profile does not exist.");

        // Stubbing the service method to throw an exception
        when(setAccountProfileService.changeProfile(
                emailInput,
                profileIdInput
        )).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.setAccountProfile(emailInput, profileIdInput);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void setAccountProfile_ensureAccountProfileIsNOTChangedWhenAccountPassedDoesNotExists_Unit() {
        //Arrange
        String emailInput = "email@email.com";
        String profileIdInput = "Profile1";

        IllegalStateException exception = new IllegalStateException("Account with that email does not exists.");

        // Stubbing the service method to throw an exception
        when(setAccountProfileService.changeProfile(
                emailInput,
                profileIdInput
        )).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.setAccountProfile(emailInput, profileIdInput);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    /**
     * Integration Tests class for {@link AccountController#setAccountProfile(String, String)} .
     */
    @Test
    void setAccountProfile_ensureProfileOfAccountIsChangedWithValidGivenParameters_Integration() {
        //Arrange
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        //Create and save account and profile to repository
        String emailInput = "email@email.com";
        String nameAccount = "NAME";
        String phoneAccount = "912345678";
        String statusAccount = "active";
        String profileIdInput = "1";
        String profileDescription = "user";

        Email email1 = new Email(emailInput);
        Name name1 = new Name(nameAccount);
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneAccount);
        AccountStatus accountStatus1 = new AccountStatus(statusAccount);
        ProfileID profileID = new ProfileID(profileIdInput);

        profileRepository1.save(factoryProfile.createProfile(
                new ProfileID(profileIdInput),
                new Description(profileDescription)));

        accountRepository1.save(factoryAccount1.createAccount(
                email1,
                name1,
                phoneNumber1,
                accountStatus1,
                profileID));

        // Create AccountDTO with attributes of the account previously created.
        AccountDTO expectedAccountDTO = new AccountDTO();
        expectedAccountDTO.setEmail(emailInput);
        expectedAccountDTO.setName(nameAccount);
        expectedAccountDTO.setPhoneNumber(phoneAccount);
        expectedAccountDTO.setStatus(statusAccount);

        ResponseEntity<Object> expected = new ResponseEntity<>(expectedAccountDTO, HttpStatus.OK);

        //Act
        ResponseEntity<Object> result = controller.setAccountProfile(emailInput, profileIdInput);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setAccountProfile_ensureAccountProfileIsNOTChangedWhenDoesNotExistsAccountWithGivenEmail_Integration() {
        //Arrange
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        //Create and save account and profile to repository
        String emailInput = "email@email.com";
        String nameAccount = "name";
        String phoneAccount = "912345678";
        String statusAccount = "active";
        String profileIdInput = "1";
        String profileDescription = "user";

        Email email1 = new Email(emailInput);
        Name name1 = new Name(nameAccount);
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneAccount);
        AccountStatus accountStatus1 = new AccountStatus(statusAccount);
        ProfileID profileID = new ProfileID(profileIdInput);

        profileRepository1.save(factoryProfile.createProfile(
                new ProfileID(profileIdInput),
                new Description(profileDescription)));

        accountRepository1.save(factoryAccount1.createAccount(
                email1,
                name1,
                phoneNumber1,
                accountStatus1,
                profileID));

        String failEmail = "fail@email.com";

        //Assert
        String expectedErrorMessage = "Account with that email does not exists.";

        // Act
        ResponseEntity<Object> response = controller.setAccountProfile(failEmail, profileIdInput);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedErrorMessage, response.getBody());
    }

    @Test
    void setAccountProfile_ensureAccountProfileIsNOTChangedWhenDoesNotExistsProfileWithGivenProfileID_Integration() {
        //Arrange
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        //Create and save account and profile to repository
        String emailInput = "email@email.com";
        String nameAccount = "name";
        String phoneAccount = "912345678";
        String statusAccount = "active";
        String profileIdInput = "1";
        String profileDescription = "user";

        Email email1 = new Email(emailInput);
        Name name1 = new Name(nameAccount);
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneAccount);
        AccountStatus accountStatus1 = new AccountStatus(statusAccount);
        ProfileID profileID = new ProfileID(profileIdInput);

        profileRepository1.save(factoryProfile.createProfile(
                new ProfileID(profileIdInput),
                new Description(profileDescription)));

        accountRepository1.save(factoryAccount1.createAccount(
                email1,
                name1,
                phoneNumber1,
                accountStatus1,
                profileID));

        String failProfile = "2";

        //Assert
        String expectedErrorMessage = "Profile does not exist.";

        // Act
        ResponseEntity<Object> response = controller.setAccountProfile(emailInput, failProfile);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedErrorMessage, response.getBody());
    }

    @Test
    void setAccountProfile_ensureAccountProfileIsNOTChangedWhenPassedInvalidProfile_Integration() {
        //Arrange
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        //Create and save account and profile to repository
        String emailInput = "email@email.com";
        String nameAccount = "name";
        String phoneAccount = "912345678";
        String statusAccount = "active";
        String profileIdInput = "1";
        String profileDescription = "user";

        Email email1 = new Email(emailInput);
        Name name1 = new Name(nameAccount);
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneAccount);
        AccountStatus accountStatus1 = new AccountStatus(statusAccount);
        ProfileID profileID = new ProfileID(profileIdInput);

        profileRepository1.save(factoryProfile.createProfile(
                new ProfileID(profileIdInput),
                new Description(profileDescription)));

        accountRepository1.save(factoryAccount1.createAccount(
                email1,
                name1,
                phoneNumber1,
                accountStatus1,
                profileID));

        String invalidProfile = null;

        //Assert
        String expectedErrorMessage = "Profile ID cannot be null";

        // Act
        ResponseEntity<Object> response = controller.setAccountProfile(emailInput, invalidProfile);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrorMessage, response.getBody());
    }

    @Test
    void setAccountProfile_ensureAccountProfileIsNOTChangedWhenPassedInvalidEmail_Integration() {
        //Arrange
        Repository<ProfileID, Profile> profileRepository1 = new InMemoryProfileRepository();
        Repository<Email, Account> accountRepository1 = new InMemoryAccountRepository();
        FactoryAccount factoryAccount1 = new FactoryAccountImpl();
        FactoryProfile factoryProfile = new FactoryProfileImpl();

        RegisterAccountService registerAccountService1 = new RegisterAccountServiceImpl(accountRepository1, profileRepository1, factoryAccount1);
        SetAccountProfileService setAccountProfileService1 = new SetAccountProfileServiceImpl(accountRepository1, profileRepository1);
        ChangeStatusService changeStatusService1 = new ChangeAccountStatusServiceImpl(accountRepository1);
        AccountsAndStatusService accountsAndStatusService1 = new org.switch2022.project.service.AccountsAndStatusServiceImpl(accountRepository1);
        AccountController controller = new AccountController(
                registerAccountService1,
                setAccountProfileService1,
                changeStatusService1,
                accountsAndStatusService1);

        //Create and save account and profile to repository
        String emailInput = "email@email.com";
        String nameAccount = "name";
        String phoneAccount = "912345678";
        String statusAccount = "active";
        String profileIdInput = "1";
        String profileDescription = "user";

        Email email1 = new Email(emailInput);
        Name name1 = new Name(nameAccount);
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneAccount);
        AccountStatus accountStatus1 = new AccountStatus(statusAccount);
        ProfileID profileID = new ProfileID(profileIdInput);

        profileRepository1.save(factoryProfile.createProfile(
                new ProfileID(profileIdInput),
                new Description(profileDescription)));

        accountRepository1.save(factoryAccount1.createAccount(
                email1,
                name1,
                phoneNumber1,
                accountStatus1,
                profileID));

        String invalidEmail = "email.com";

        //Assert
        String expectedErrorMessage = "Email is not valid";

        // Act
        ResponseEntity<Object> response = controller.setAccountProfile(invalidEmail, profileIdInput);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrorMessage, response.getBody());
    }

    /**
     * Unit Tests class for {@link AccountController#changeStatus(ChangeStatusDTO)}  .
     */
    @Test
    void inactivateAccountChangeStatusToActivate() {
        // Arrange
        String email = "isep@isep.pt";
        String status = "active";
        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
        changeStatusDTO.setEmailAddress(email);
        changeStatusDTO.setStatus(status);
        when(changeStatusService.changeStatus(email,status)).thenReturn(true);

        ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = controllerUnderTest.changeStatus(changeStatusDTO);

        // Assert
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }
    @Test
    void inactivateAccountChangeStatusToInactivate_FailsWhenPassedInvalidParameters() {
        // Arrange
        String email = "isep@isep.pt";
        String status = "inactivate";
        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
        changeStatusDTO.setEmailAddress(email);
        changeStatusDTO.setStatus(status);

        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid input parameters for changing the account profile.");

        // Stubbing the service method to throw an exception
        when(changeStatusService.changeStatus(email, status)).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.changeStatus(changeStatusDTO);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }
    @Test
    void inactivateAccountChangeStatusToInactive_FailWhenAccountDoesNotExist() {
        // Arrange
        String email = "isep@isep.pt";
        String status = "inactivate";
        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
        changeStatusDTO.setEmailAddress(email);
        changeStatusDTO.setStatus(status);

        IllegalStateException exception =
                new IllegalStateException("Account with that email does not exists.");

        // Stubbing the service method to throw an exception
        when(changeStatusService.changeStatus(email, status)).thenThrow(exception);

        // Act
        ResponseEntity<Object> response = controllerUnderTest.changeStatus(changeStatusDTO);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    /**
     * Test class for {@link AccountController#getAccountsAndStatus()} .
     */
    @Test
    void getAccountsAndStatus_andEnsureReturnsAListWithOneAccountDTOObject() {
        //Arrange
        List<AccountDTO> accountDTOList = new ArrayList<>();
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Account1");
        accountDTO.setStatus("Active");
        accountDTOList.add(accountDTO);
        when(accountsAndStatusService.getAccountAndStatus()).thenReturn(accountDTOList);

        //Act
        ResponseEntity<Object> responseEntity = controllerUnderTest.getAccountsAndStatus();

        //Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAccountsAndStatus_ensureReturnsAnEmptyList() {
        //Arrange
        List<AccountDTO> accountDTOList = new ArrayList<>();
        when(accountsAndStatusService.getAccountAndStatus()).thenReturn(accountDTOList);

        //Act
        ResponseEntity<Object> responseEntity = controllerUnderTest.getAccountsAndStatus();

        //Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAccountsAndStatus_ensureReturnsAListWithTwoAccountDTOObject() {
        //Arrange
        List<AccountDTO> accountDTOList = new ArrayList<>();
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Account1");
        accountDTO.setStatus("Active");
        accountDTOList.add(accountDTO);
        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setName("Account2");
        accountDTO2.setStatus("Active");
        accountDTOList.add(accountDTO2);
        when(accountsAndStatusService.getAccountAndStatus()).thenReturn(accountDTOList);

        //Act
        ResponseEntity<Object> responseEntity = controllerUnderTest.getAccountsAndStatus();

        //Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
