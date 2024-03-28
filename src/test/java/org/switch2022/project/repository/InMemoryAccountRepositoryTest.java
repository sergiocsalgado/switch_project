package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.Email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the AccountRepository class, which is responsible for
 * testing the behavior of the AccountRepository class methods.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryAccountRepository.class)
class InMemoryAccountRepositoryTest {
    @MockBean
    Account account;
    @MockBean
    Email email;
    @InjectMocks
    private InMemoryAccountRepository inMemoryAccountRepository;

    /**
     * This method is executed before each test case to initialize the AccountRepository instance.
     *
     * @throws Exception if an error occurs during setup.
     */
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test class for {@link InMemoryAccountRepository#save(Account)}.
     */
    @Test
    void save_shouldSaveAndReturnTheAccountSaved_Success() {
        //Arrange
        when(account.identity()).thenReturn(email);

        // Act
        Account savedAccount = inMemoryAccountRepository.save(account);

        // Assert that the returned Account object is the same as the mock Account object and the same ID
        assertEquals(account, savedAccount);
        assertEquals(account.identity(), savedAccount.identity());
    }

    /**
     * Test class for {@link InMemoryAccountRepository#findAll()}.
     */
    @Test
    void findAll_shouldReturnAnEmptyList_Success() {
        // Assert the repository is empty
        List<Account> accounts = inMemoryAccountRepository.findAll();

        assertEquals(new ArrayList<>(), accounts);
    }

    /**
     * Test class for {@link InMemoryAccountRepository#findAll()}.
     */

    @Test
    void findAll_shouldReturnOneAccount_Success() {
        //Arrange
        when(account.identity()).thenReturn(email);
        Account savedAccount = inMemoryAccountRepository.save(account);
        List<Account> expectedList = Arrays.asList(savedAccount);

        //Act
        List<Account> accounts = inMemoryAccountRepository.findAll();

        // Assert the repository contains one object of type Account "account"
        assertEquals(expectedList, accounts);
    }

    /**
     * Test class for {@link InMemoryAccountRepository#ofIdentity(Email)} .
     */
    @Test
    void ofIdentity_shouldReturnOptionalOfDesiredAccount_Success() {
        //Arrange
        when(account.identity()).thenReturn(email);
        inMemoryAccountRepository.save(account);

        Optional<Account> expected = Optional.of(account);

        //Act
        Optional<Account> result = inMemoryAccountRepository.ofIdentity(email);

        // Assert the repository contains an Account with ID "email" and is retrieved has an Optional.
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link InMemoryAccountRepository#ofIdentity(Email)} .
     */
    @Test
    void ofIdentity_shouldReturnEmptyOptionalPassingAnEmailThatNotBelongsToAnyAccountInRepository_Success() {
        //Arrange
        when(account.identity()).thenReturn(email);
        inMemoryAccountRepository.save(account);

        Optional<Account> expected = Optional.empty();
        Email emailToCheck = mock(Email.class);

        //Act
        Optional<Account> result = inMemoryAccountRepository.ofIdentity(emailToCheck);

        // Assert the repository contains an Account with ID "email" and is retrieved has an Optional.
        assertEquals(expected, result);
    }


    /**
     * Test class for {@link InMemoryAccountRepository#containsOfIdentity(Email)}.
     */
    @Test
    void containsOfIdentity_shouldReturnTrueWhenThereIsAnAccountWithIDEmailGiven_Success() {
        //Arrange
        when(account.identity()).thenReturn(email);
        inMemoryAccountRepository.save(account);

        //Act
        boolean result = inMemoryAccountRepository.containsOfIdentity(email);

        // Assert the repository contains an account with ID "email"
        assertTrue(result);
    }

    /**
     * Test class for {@link InMemoryAccountRepository#containsOfIdentity(Email)}.
     */
    @Test
    void ofIdentify_shouldReturnFalseWhenThereIsNoAccountWithIDEmailGiven_Success() {
        when(account.identity()).thenReturn(email);
        inMemoryAccountRepository.save(account);

        Email emailToCheck = mock(Email.class);

        //Act
        boolean result = inMemoryAccountRepository.containsOfIdentity(emailToCheck);

        // Assert the repository does not contain an account with ID "emailToCheck"
        assertFalse(result);
    }
}
