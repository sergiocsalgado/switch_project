package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.mapper.AccountMapper;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.service.interfaces.AccountsAndStatusService;

import java.util.List;

@Service
public class AccountsAndStatusServiceImpl implements AccountsAndStatusService {
    private final Repository<Email, Account> accountRepository;

    public AccountsAndStatusServiceImpl(
            @Qualifier("accountJPARepository") Repository<Email, Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Returns the list of all accounts in account repository in form of a DTO object.
     *
     * @return the list of all accounts in the account repository in the form of a DTO object.
     */
    public List<AccountDTO> getAccountAndStatus() {
        List<Account> accounts = accountRepository.findAll();
        return AccountMapper.listAccountsDTO(accounts);
    }
}
