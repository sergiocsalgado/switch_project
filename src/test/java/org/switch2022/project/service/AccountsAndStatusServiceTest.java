package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.PhoneNumber;
import org.switch2022.project.service.interfaces.AccountsAndStatusService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the AccountsAndStatusServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountsAndStatusServiceImpl.class)
class AccountsAndStatusServiceTest {
    @MockBean
    private Account account;
    @MockBean
    @Qualifier("accountJPARepository")
    private Repository<Email, Account> accountRepository;
    @Autowired
    private AccountsAndStatusServiceImpl accountsAndStatusService;


    /**
     * Test class for {@link AccountsAndStatusService#getAccountAndStatus()}.
     */
    @Test
    void getAccountAndStatus_shouldReturnAnEmptyList() {
        //Act
        List<AccountDTO> accounts = accountsAndStatusService.getAccountAndStatus();

        //Assert
        assertEquals(new ArrayList<>(), accounts);
    }

    @Test
    void getAccountAndStatus_shouldReturnAListWithOneAccountDTO() {
        //Arrange
        Email email = new Email("andre@isep.ipp.pt");
        Name name = new Name("andre");
        PhoneNumber phoneNumber = new PhoneNumber("911111111");
        AccountStatus accountStatus = new AccountStatus("active");

        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getPhoneNumber()).thenReturn(phoneNumber);
        when(account.getStatus()).thenReturn(accountStatus);

        List<Account> accountsDouble = List.of(account);
        when(accountRepository.findAll()).thenReturn(accountsDouble);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(email.getEmail());
        accountDTO.setName(name.getValue());
        accountDTO.setPhoneNumber(phoneNumber.getPhoneNumber());
        accountDTO.setStatus(accountStatus.getStatus());

        List<AccountDTO> expected = List.of(accountDTO);

        //Act
        List<AccountDTO> accounts = accountsAndStatusService.getAccountAndStatus();

        //Assert
        assertEquals(expected, accounts);
    }
}
