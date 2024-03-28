package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.AccountJpa;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.PhoneNumber;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.ProfileID;

/**
 * This class provides static methods to convert between Account and AccountJpa objects.
 */
public final class AccountJpaAssembler {

    private AccountJpaAssembler() {
    }

    /**
     * Converts an Account object to an AccountJpa object.
     *
     * @param account The Account object to convert.
     * @return The converted AccountJpa object.
     */
    public static AccountJpa toDataModel(Account account) {
        return new AccountJpa(
                account.getEmail().getEmail(),
                account.getName().getValue(),
                account.getPhoneNumber().getPhoneNumber(),
                account.getStatus().getStatus(),
                account.getProfileID().getProfileID());
    }

    /**
     * Converts an AccountJpa object to an Account object.
     *
     * @param accountJpa The AccountJpa object to convert.
     * @return The converted Account object.
     */
    public static Account toDomain(AccountJpa accountJpa) {
        return new Account(
                new Email(accountJpa.getEmail()),
                new Name(accountJpa.getName()),
                new PhoneNumber(accountJpa.getPhoneNumber()),
                new AccountStatus(accountJpa.getStatus()),
                new ProfileID(accountJpa.getProfileID()));
    }
}