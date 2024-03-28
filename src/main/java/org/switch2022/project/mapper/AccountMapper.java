package org.switch2022.project.mapper;

import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.PhoneNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for mapping a list of {@link Account} objects to a list of {@link AccountDTO} objects.
 */
public final class AccountMapper {
    private AccountMapper() {}

    /**
     * Maps a list of {@link Account} objects to a list of {@link AccountDTO} objects.
     * @param accounts a list of {@link Account} objects to be mapped
     * @return a list of {@link AccountDTO} objects
     */
    public static List<AccountDTO> listAccountsDTO(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();

        for (Account acc: accounts) {
            Email email = acc.getEmail();
            Name name = acc.getName();
            PhoneNumber phoneNumber = acc.getPhoneNumber();
            AccountStatus status = acc.getStatus();

            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setEmail(email.getEmail());
            accountDTO.setName(name.getValue());
            accountDTO.setPhoneNumber(phoneNumber.getPhoneNumber());
            accountDTO.setStatus(status.getStatus());

            accountDTOS.add(accountDTO);
        }

        return Collections.unmodifiableList(accountDTOS);
    }

    /**
     * Retrieve an accountDTO with email, name and status from an account object.
     *
     * @param account the account to map to dto.
     * @return accountDTO object with email, name, phone number, and status attributes.
     */
    public static AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setEmail(account.getEmail().getEmail());
        accountDTO.setName(account.getName().getValue());
        accountDTO.setPhoneNumber(account.getPhoneNumber().getPhoneNumber());
        accountDTO.setStatus(account.getStatus().getStatus());

        return accountDTO;
    }
}
