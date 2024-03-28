package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.service.interfaces.ChangeStatusService;

import java.util.Optional;


@Service
public class ChangeAccountStatusServiceImpl implements ChangeStatusService {

    private final Repository<Email, Account> accountRepository;

    public ChangeAccountStatusServiceImpl(
            @Qualifier("accountJPARepository") Repository<Email, Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Changes the account status of a user with the given email address.
     *
     * @param emailInput the email of the account to change status.
     * @param accountStatusInput the account status to set in the account.
     * @return true if the email exists and
     */
    public boolean changeStatus(String emailInput, String accountStatusInput) {
        Email email;
        AccountStatus accountStatus;

        try {
            email = new Email(emailInput);
            accountStatus = new AccountStatus(accountStatusInput);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Account> accountOptional = accountRepository.ofIdentity(email);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.setStatus(accountStatus)) {
                accountRepository.save(account);
                return true;
            }
            return false;

        } else {
            throw new IllegalStateException("Account with that email does not exists.");
        }
    }
}

