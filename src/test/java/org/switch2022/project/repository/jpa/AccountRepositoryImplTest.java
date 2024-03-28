package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.AccountJpa;
import org.switch2022.project.datamodel.jpa.assemblers.AccountJpaAssembler;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.model.value_objects.ProfileID;
import org.switch2022.project.repository.jpa.interfaces.AccountJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountRepositoryImpl.class)
class AccountRepositoryImplTest {

    @MockBean
    Account account;
    @MockBean
    AccountJpa accountJpa;
    @MockBean
    Optional optionalAccountJpa;
    @MockBean
    Email email;
    @MockBean
    AccountStatus accountStatus;
    @MockBean
    ProfileID profileID;

    @MockBean
    AccountJpaRepository accountJpaRepository;

    @Autowired
    AccountRepositoryImpl accountRepositoryImpl;


    @Test
    void shouldSaveAccountWhenAccountDoesNotExists_Success() {
        //Arrange
        String emailInput = "email@email.com";
        Account expected = account;

        when(account.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn(emailInput);

        when(accountJpaRepository.findByEmail(emailInput)).thenReturn(optionalAccountJpa);
        when(optionalAccountJpa.isPresent()).thenReturn(false);

        try (MockedStatic<AccountJpaAssembler> assemblerDouble = Mockito.mockStatic(AccountJpaAssembler.class)) {
            assemblerDouble.when(() -> AccountJpaAssembler.toDataModel(account)).thenReturn(accountJpa);

            when(accountJpaRepository.save(accountJpa)).thenReturn(accountJpa);
            //Act
            Account result = accountRepositoryImpl.save(account);
            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void shouldSaveAccountWhenAccountExistsAndUpdateStatusAndProfileID_Success() {
        //Arrange
        String emailInput = "email@email.com";
        Email accountEmail = new Email(emailInput);
        AccountStatus statusToSave = new AccountStatus("inactive");
        ProfileID profileIDToSave = new ProfileID("P1");

        Account accountToSave = mock(Account.class);

        when(accountToSave.getEmail()).thenReturn(accountEmail);
        when(account.getEmail()).thenReturn(accountEmail);

        when(accountToSave.getStatus()).thenReturn(statusToSave);
        when(accountToSave.getProfileID()).thenReturn(profileIDToSave);

        when(accountJpaRepository.findByEmail(emailInput)).thenReturn(optionalAccountJpa);
        when(optionalAccountJpa.isPresent()).thenReturn(true);
        when(optionalAccountJpa.get()).thenReturn(accountJpa);

        try (MockedStatic<AccountJpaAssembler> assemblerDouble = Mockito.mockStatic(AccountJpaAssembler.class)) {
            assemblerDouble.when(() -> AccountJpaAssembler.toDomain(accountJpa)).thenReturn(accountToSave);

            //Act
            Account result = accountRepositoryImpl.save(accountToSave);
            //Assert
            assertEquals(accountToSave, result);
        }
    }

    @Test
    void ensureEmptyOptionalAccount() {

        //Arrange
        Optional<Account> expected = Optional.empty();

        try (MockedStatic<AccountJpaAssembler> assemblerDouble = Mockito.mockStatic(AccountJpaAssembler.class)) {
            assemblerDouble.when(() -> AccountJpaAssembler.toDomain(accountJpa)).thenReturn(account);

            when(accountJpaRepository.findByEmail(email.getEmail())).thenReturn(optionalAccountJpa);

            when(accountJpaRepository.findById(1L)).thenReturn(optionalAccountJpa);
            //Act
            Optional<Account> result = accountRepositoryImpl.ofIdentity(email);
            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureEmptyOptional() {

        //Arrange
        try (MockedStatic<AccountJpaAssembler> assemblerDouble = Mockito.mockStatic(AccountJpaAssembler.class)) {
            assemblerDouble.when(() -> AccountJpaAssembler.toDomain(accountJpa)).thenReturn(account);

            when(accountJpaRepository.findByEmail(email.getEmail())).thenReturn(optionalAccountJpa);
            when(accountJpaRepository.findById(1L)).thenReturn(optionalAccountJpa);
            //Act
            Optional<Account> expected = Optional.empty();
            Optional<Account> result = accountRepositoryImpl.ofIdentity(email);
            //Assert
            assertEquals(expected, result);
        }
    }


    @Test
    void ensureOfIdentity_shouldRetunrTrue() {

        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountRepositoryImpl accountRepositoryImpl = new AccountRepositoryImpl(accountJpaRepository);

        Email email = mock(Email.class);

        when(accountJpaRepository.findByEmail(email.getEmail())).thenReturn(Optional.of(accountJpa));
        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountJpa));

        try (MockedStatic<AccountJpaAssembler> assemblerDouble = Mockito.mockStatic(AccountJpaAssembler.class)) {
            assemblerDouble.when(() -> AccountJpaAssembler.toDomain(accountJpa)).thenReturn(account);

            //Act
            Optional<Account> result = accountRepositoryImpl.ofIdentity(email);
            //Assert
            assertTrue(result.isPresent());
        }
    }


    @Test
    void findAll_ShouldReturnList() {
        // Arrange
        List<AccountJpa> accountJpaList = Collections.singletonList(accountJpa);
        when(accountJpaRepository.findAll()).thenReturn(accountJpaList);

        List<Account> expected = Collections.singletonList(account);

        try (MockedStatic<AccountJpaAssembler> assemblerDouble = Mockito.mockStatic(AccountJpaAssembler.class)) {
            assemblerDouble.when(() -> AccountJpaAssembler.toDomain(accountJpa)).thenReturn(account);

            // Act
            List<Account> result = accountRepositoryImpl.findAll();

            // Assert
            assertEquals(expected, result);

        }
    }

    @Test
    void containsOfIdentity_False() {
        // Arrange
        Email email = mock(Email.class);
        when(accountJpaRepository.existsByEmail(email.getEmail())).thenReturn(false);

        // Act
        boolean result = accountRepositoryImpl.containsOfIdentity(email);

        // Assert
        assertFalse(result);
    }

    @Test
    void containsOfIdentity_True() {
        // Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountRepositoryImpl accountRepositoryImpl = new AccountRepositoryImpl(accountJpaRepository);

        Email email = mock(Email.class);

        when(accountJpaRepository.existsByEmail(email.getEmail())).thenReturn(true);

        // Act
        boolean result = accountRepositoryImpl.containsOfIdentity(email);

        // Assert
        assertTrue(result);
    }
}
