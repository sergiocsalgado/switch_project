package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.Email;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ChangeStatusServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ChangeAccountStatusServiceImpl.class)
class ChangeAccountStatusServiceImplTest {
    @MockBean
    Account account;

    @MockBean
    @Qualifier("accountJPARepository")
    private Repository<Email, Account> accountRepository;
    @Autowired
    private ChangeAccountStatusServiceImpl serviceUnderTest;


    /**
     * Test method for changing the account status to inactive.
     */
    @Test
    void changeStatusActive() {
        // Arrange
        String emailInput = "isep@isep.pt";
        String accountStatusToDefine = "inactive";

        Email email = new Email(emailInput);
        AccountStatus accountStatusNew = new AccountStatus(accountStatusToDefine);

        Optional<Account> accountOptionalDouble = mock(Optional.class);
        Account accountDouble = mock(Account.class);

        // Mock the behavior of accountRepository.ofIdentity() method
        when(accountRepository.ofIdentity(email)).thenReturn(Optional.of(accountDouble));

        // Mock the behavior of accountOptionalDouble.isPresent() method
        when(accountOptionalDouble.isPresent()).thenReturn(true);

        // Mock the behavior of accountOptionalDouble.get() method
        when(accountOptionalDouble.get()).thenReturn(accountDouble);
        // Mock the behavior of accountDouble.setStatus() method
        when(accountDouble.setStatus(accountStatusNew)).thenReturn(true);

        // Act
        boolean result = serviceUnderTest.changeStatus(emailInput, accountStatusToDefine);

        // Assert
        assertTrue(result);
    }
    /**
     * Test method for changing the account status to inactive.
     */
    @Test
    void changeStatus_ensureItFails() {
        // Arrange
        String emailInput = "isep@isep.pt";
        String accountStatusToDefine = "inactive";

        Email email = new Email(emailInput);
        AccountStatus accountStatusNew = new AccountStatus(accountStatusToDefine);

        Optional<Account> accountOptionalDouble = mock(Optional.class);
        Account accountDouble = mock(Account.class);

        // Mock the behavior of accountRepository.ofIdentity() method
        when(accountRepository.ofIdentity(email)).thenReturn(Optional.of(accountDouble));

        // Mock the behavior of accountOptionalDouble.isPresent() method
        when(accountOptionalDouble.isPresent()).thenReturn(true);

        // Mock the behavior of accountOptionalDouble.get() method
        when(accountOptionalDouble.get()).thenReturn(accountDouble);
        // Mock the behavior of accountDouble.setStatus() method
        when(accountDouble.setStatus(accountStatusNew)).thenReturn(false);

        // Act
        boolean result = serviceUnderTest.changeStatus(emailInput, accountStatusToDefine);

        // Assert
        assertFalse(result);
    }


    /**
     * Test method for changing the account status to inactive when the account is not present.
     */
    @Test
    void changeStatusInactiveAccountNotPresent() {
        // Arrange
        String emailInput = "isep@isep.pt";
        String accountStatusToDefine = "inactive";

        Account accountDouble = mock(Account.class);

        Email email = new Email(emailInput);

        Optional<Account> accountOptionalDouble = mock(Optional.class);

        // Mock the behavior of accountRepository.ofIdentity() method
        when(accountRepository.ofIdentity(email)).thenReturn(Optional.empty());

        // Mock the behavior of accountOptionalDouble.isPresent() method
        when(accountOptionalDouble.isPresent()).thenReturn(true);

        // Mock the behavior of accountOptionalDouble.get() method
        when(accountOptionalDouble.get()).thenReturn(accountDouble);

        // Mock the behavior of accountDouble.setStatus() method
        when(accountOptionalDouble.isPresent()).thenReturn(false);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            serviceUnderTest.changeStatus(emailInput, accountStatusToDefine);
        });
        //Assert
        assertEquals("Account with that email does not exists.", exception.getMessage());
    }

    /**
     * Test method for changing the account status to inactive when the account is not present.
     */
    @Test
    void changeStatusInactiveEmailInvalid() {
        // Arrange
        String emailInput = "isep.pt";
        String accountStatusToDefine = "inactive";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.changeStatus(emailInput, accountStatusToDefine);
        });

        //Assert
        assertEquals("Email is not valid", exception.getMessage());
    }

    @Test
    void changeStatusInactiveAccountStatusInvalid() {
        // Arrange
        String emailInput = "email@isep.pt";
        String accountStatusToDefine = "status";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.changeStatus(emailInput, accountStatusToDefine);
        });

        //Assert
        assertEquals("Invalid Account Status.", exception.getMessage());
    }

}