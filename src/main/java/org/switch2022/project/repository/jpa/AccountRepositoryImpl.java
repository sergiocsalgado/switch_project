package org.switch2022.project.repository.jpa;

import org.switch2022.project.datamodel.jpa.AccountJpa;
import org.switch2022.project.datamodel.jpa.assemblers.AccountJpaAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.repository.jpa.interfaces.AccountJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link Repository} interface using JPA for storing and retrieving accounts.
 */

@org.springframework.stereotype.Repository("accountJPARepository")
public class AccountRepositoryImpl implements Repository<Email, Account> {

    private final AccountJpaRepository accountJpaRepository;

    /**
     * Constructs a new instance of the {@code AccountRepositoryJpaImpl} class with the specified JPA repository.
     *
     * @param accountJpaRepository the JPA repository for accounts.
     */

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    /**
     * Saves the specified account by converting it to a JPA data model and invoking the save method on
     * the JPA repository.
     *
     * @param account the account to save.
     * @return the saved account.
     */
    public Account save(Account account) {
        Optional<AccountJpa> optionalAccountJpa =
                accountJpaRepository.findByEmail(account.getEmail().getEmail());

        if (optionalAccountJpa.isPresent()) {
            AccountJpa accountJpa = optionalAccountJpa.get();

            accountJpa.setStatus(account.getStatus().getStatus());
            accountJpa.setProfileID(account.getProfileID().getProfileID());

            accountJpaRepository.save(accountJpa);

            return AccountJpaAssembler.toDomain(accountJpa);
        }

        AccountJpa accountJpa = AccountJpaAssembler.toDataModel(account);

        accountJpaRepository.save(accountJpa);

        return account;
    }

    /**
     * Retrieves all accounts by invoking the findAll method on the JPA repository and converting
     * the returned JPA data models to domain models.
     *
     * @return a list of all accounts
     */
    public List<Account> findAll() {
        List<AccountJpa> savedAccounts = accountJpaRepository.findAll();
        List<Account> accounts = new ArrayList<>();
        for (AccountJpa accJpa : savedAccounts) {
            accounts.add(AccountJpaAssembler.toDomain(accJpa));
        }
        return accounts;
    }

    /**
     * Retrieves the account with the specified identity by invoking the findByEmail method on the JPA repository
     * and converting the returned JPA data models to domain models.
     *
     * @param email the identity of the account to retrieve
     * @return the account with the specified identity
     */

    public Optional<Account> ofIdentity(Email email) {

        Optional<AccountJpa> accountJpa = accountJpaRepository.findByEmail(email.getEmail());
        if (accountJpa.isPresent()) {
            Account account = AccountJpaAssembler.toDomain(accountJpa.get());
            return Optional.of(account);
        }
        return Optional.empty();
    }

    /**
     * Checks if the account with the specified identity exists by invoking the existsByEmail method
     * on the JPA repository.
     *
     * @param email the identity of the account to check
     * @return true if the account with the specified identity exists, false otherwise
     */

    public boolean containsOfIdentity(Email email) {
        return accountJpaRepository.existsByEmail(email.getEmail());
    }
}
