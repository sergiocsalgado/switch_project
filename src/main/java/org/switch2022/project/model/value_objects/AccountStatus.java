package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;
import java.util.Set;

/**
 * A class representing the status of an account, which can be either "active"
 * or "inactive".
 */
public final class AccountStatus implements ValueObject<AccountStatus> {
    private static final Set<String> VAlID_STATUS = Set.of("active", "inactive");
    private final String status;

    /**
     * Creates a new instance of AccountStatus with the specified status.
     *
     * @param status the status of the account, which must be either "active"
     *               or "inactive".
     * @throws IllegalArgumentException if the specified status is not valid.
     */
    public AccountStatus(String status) {
        StringValidation.checkNull("Account status", status);

        String statusLowerCase = status.trim().toLowerCase();

        StringValidation.checkBlank("Account status", statusLowerCase);
        if (!VAlID_STATUS.contains(statusLowerCase)) {
            throw new IllegalArgumentException("Invalid Account Status.");
        }
        this.status = statusLowerCase;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean sameAs(AccountStatus o) {
        return o != null
                && this.status.equals(o.getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountStatus accountStatus = (AccountStatus) o;
        return sameAs(accountStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
